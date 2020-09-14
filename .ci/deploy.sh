#!/bin/env bash
#
# 解析config.json文件并根据deployJarTemplate.sh生成部署shell脚本
# date: 2020-08-24
# auth: 廖发友

if [[ ${DEBUG} == "true" ]];then
  set -ex
  DEBUG="set -ex"
else
  set -e
  DEBUG="set -e"
fi

config=${ENV_CUSTOM_CONFIG}
template=".ci/deployJarTemplate.sh"
upload_max_count=3
#store=${ENV_CUSTOM_STORE}
stage=${ENV_CUSTOM_STAGE}
ref_name=${ENV_CUSTOM_REF_NAME}

function checkConfig() {
    if [[ "$1" == "build" ]];then
        if [[ ! -f ${config} ]];then
            echo -e "\e[91m没有检测到${config}模版配置文件,退出CI流程\e[0m"
            exit 1
        fi
        #tmp_config_path=$(echo ${config%/*})
        tmp_config_name=$(echo ${config##*/})
        tmp_config=".${tmp_config_name}"
        \cp -rf ${config} ${tmp_config}
        config=${tmp_config}
    elif [[ "$1" == "deploy" ]];then
        tmp_config_name=$(echo ${config##*/})
        tmp_config=".${tmp_config_name}"
        config=${tmp_config}
        if [[ ! -f ${config} ]];then
            echo -e "\e[91m没有检测到${config}配置文件,退出CI流程\e[0m"
            exit 1
        fi
    else
        echo -e "\e[91m检测配置文件错误,退出CI流程\e[0m"
        exit 1
    fi

    python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8'); \
        f=open(\"${config}\"); j=json.loads(f.read()); \
        print(j[\"${ref_name}\"])" > /dev/null 2>&1

    if [[ $? -ne 0 ]];then
        echo -e "\e[91m配置文件\t${config}\t解析错误\e[0m"
        exit 1
    fi

    python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8'); \
        f=open(\"${config}\"); j=json.loads(f.read()); \
        print(j[\"${ref_name}\"][\"comment\"])"
}

function deploy() {
    app_number=$(python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8'); \
        f=open(\"${config}\"); j=json.loads(f.read()); \
        print(len(j[\"${ref_name}\"][\"app\"]))")

    for (( app_count=0; app_count<${app_number}; app_count++ ));do
        host_number=$(python -c "import json; import sys;reload(sys); sys.setdefaultencoding('utf-8'); \
            f=open(\"${config}\"); j=json.loads(f.read(),encoding=\""utf-8\""); \
            N=j[\"${ref_name}\"][\"app\"][${app_count}][\"Host\"] if \"Host\" in j[\"${ref_name}\"][\"app\"][${app_count}] else j[\"${ref_name}\"][\"Host\"]; \
            print(len(N))")

        jar_name=$(python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8'); \
            f=open(\"${config}\"); j=json.loads(f.read(),encoding='utf-8'); \
            print(j[\"${ref_name}\"][\"app\"][${app_count}][\"JarName\"])")

        production_path=$(python -c "import json;import sys;reload(sys);sys.setdefaultencoding('utf-8'); \
            f=open(\"${config}\"); j=json.loads(f.read(),encoding='utf-8'); \
            print(j[\"${ref_name}\"][\"app\"][${app_count}][\"Path\"])")

        prefix_cmd=$(python -c "import json;import sys;reload(sys);sys.setdefaultencoding('utf-8'); \
            f=open(\"${config}\"); j=json.loads(f.read(),encoding='utf-8'); \
            print(j[\"${ref_name}\"][\"app\"][${app_count}][\"PrefixCmd\"] if \"PrefixCmd\" in j[\"${ref_name}\"][\"app\"][${app_count}] else j[\"${ref_name}\"][\"PrefixCmd\"])")

        suffix_cmd=$(python -c "import json;import sys;reload(sys);sys.setdefaultencoding('utf-8'); \
            f=open(\"${config}\"); j=json.loads(f.read(),encoding='utf-8'); \
            print(j[\"${ref_name}\"][\"app\"][${app_count}][\"SuffixCmd\"] if \"SuffixCmd\" in j[\"${ref_name}\"][\"app\"][${app_count}] else j[\"${ref_name}\"][\"SuffixCmd\"])")

        port=$(python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8'); \
            f=open(\"${config}\");j=json.loads(f.read(),encoding='utf-8'); \
            print(j[\"${ref_name}\"][\"app\"][${app_count}][\"Port\"])")

        for text in host_number jar_name production_path prefix_cmd suffix_cmd port;do
            if [[ ${text} == "suffix_cmd" ]];then
                continue
            fi
            check "${!text}" "${text}" "${app_count}"
        done

        for (( count=0; count<${host_number}; count++ ));do
            host_ip=$(python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8'); \
                f=open(\"${config}\"); j=json.loads(f.read(),encoding=\""utf-8\""); \
                print(j[\"${ref_name}\"][\"app\"][${app_count}][\"Host\"][${count}] if \"Host\" in j[\"${ref_name}\"][\"app\"][${app_count}] else j[\"${ref_name}\"][\"Host\"][${count}])")

            temp_name=${host_ip}_${jar_name}_${template##*/}
            start_cmd="${prefix_cmd} ${jar_name} ${suffix_cmd}"
            echo -e "开始生成部署Shell文件:\n\tHOST:${host_ip}\n\tJAR:${jar_name}\n\tSTART_CMD:${start_cmd}"
            cp ${template} ${temp_name}
            sed -i "s#DEBUG#${DEBUG}#" ${temp_name}
            sed -i "s#JAR_PATH#${production_path}/${jar_name}#" ${temp_name}
            sed -i "s#JAR_PROT#${port}#" ${temp_name}
            sed -i "s#START_CMD#${start_cmd}#" ${temp_name}

            for (( uploadCount=0; uploadCount<${upload_max_count}; uploadCount++ ));do
                echo -e "将文件 ${jar_name} 上传到 ${host_ip} 目录 /data/.tmp-gitlab-runner"
                ssh root@${host_ip} "if [[ ! -d /data/.tmp-gitlab-runner ]];then mkdir -p /data/.tmp-gitlab-runner;fi"
                local_jar_path=$(find . -name "${jar_name}")
                scp -B -C ${local_jar_path} root@${host_ip}:/data/.tmp-gitlab-runner
                echo -e "${jar_name} 开始进行文件md5校验"
                remote_md5=$(ssh root@${host_ip} "cd /data/.tmp-gitlab-runner && md5sum ${jar_name} | awk '{print $1}' ")
                remote_md5=$(echo ${remote_md5%% *})
                if ( fgrep -q "${remote_md5} ${jar_name}" .md5.txt );then
                    echo "md5值相同"
                    break
                elif [[ ${uploadCount} -lt $(( ${upload_max_count} - 1 )) ]];then
                    echo -e "\e[93m暂停3秒后重新上传文件 ${jar_name}\e[0m"
                    sleep 3
                else
                    echo -e "\e[91m服务器: ${host_ip} --> /data/.tmp-gitlab-runner/${jar_name} md5值与本地不一致, 退出部署流程\e[0m"
                    exit 1
                fi
            done

            echo "上传部署Shell文件 ${temp_name} 到 ${host_ip} 目录 /data/.tmp-gitlab-runner"
            scp -B -C ${temp_name} root@${host_ip}:/data/.tmp-gitlab-runner
            echo "执行部署Shel脚本, 开始部署 ${jar_name}"
            ssh root@${host_ip} "cd /data/.tmp-gitlab-runner && sh ${temp_name}"
        done
    done
    rm -rf ${config}
}

function build() {
    app_number=$(python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8'); \
        f=open(\"${config}\");j=json.loads(f.read()); \
        print(len(j[\"${ref_name}\"][\"app\"]))")

    for (( app_count=0; app_count<${app_number}; app_count++ ));do
        config_jar_name=$(python -c "import json; import sys; reload(sys); sys.setdefaultencoding('utf-8');  \
            f=open(\"${config}\"); j=json.loads(f.read(),encoding='utf-8'); \
            print(j[\"${ref_name}\"][\"app\"][${app_count}][\"JarName\"])")

        check "${config_jar_name}" "jar_name" "${app_count}"
        #prefix_name=$(echo ${jar_name} | grep -oP '(.*)(?=-(\d+\.)+(.*?)jar)')
        #cp -rf ${prefix_name}/target/${jar_name} ${store}
        #cp -rf target/${jar_name} ${store}
        #find_jar_name=$(echo ${config_jar_name/VERSION/*})
        find_jar_name=$(echo ".*?${config_jar_name/VERSION/[0-9.-]+?}")
        #path_name=$(find . -name "${find_jar_name}")
        path_name=$(find . -regextype "posix-egrep" -regex "${find_jar_name}")
        if [[ ! -n "${path_name}" ]];then
            echo -e "\e[91m没有找到配置文件定义的 ${find_jar_name} 文件\e[0m"
            exit 1
        fi
        jar_name=$(echo ${path_name##*/})
        #echo -e "转存 ${jar_name} --> ${store}/${jar_name}"
        echo -e "修改配置文件Jar包名 ${config_jar_name} --> ${jar_name}"
        #sed -i "s/${config_jar_name}/${jar_name}/"  ${config}
        python -c "import json; import sys; import os; reload(sys); sys.setdefaultencoding('utf-8'); \
            f=open(\"${config}\"); j=json.loads(f.read()); f.close(); \
            n={}; n[\"${ref_name}\"]=j[\"${ref_name}\"]; \
            n[\"${ref_name}\"][\"app\"][${app_count}][\"JarName\"]=\"${jar_name}\"; \
            u=open(\".tmp.json\",\"w\"); u.write(json.dumps(n, indent=4, ensure_ascii=False)); u.close(); \
            os.rename(\".tmp.json\", \"${config}\")
            "
        #cp -rf ${path_name} ${store}
        echo -e "生成文件\t${jar_name}\t的md5值"
        md5=$(md5sum ${path_name} | awk '{print $1}' )
        echo "${md5} ${jar_name}">> .md5.txt
    done
    #cd ${store}
    #for file in $(ls);do
        #echo -e "生成文件\t${file}\t的md5值"
        #md5sum $file >> md5.txt
    #done
}

function check() {
    local status=$1
    local name=$2
    local index=$3
    if [[ ${status:-"err"} == "err" ]];then
        echo -e "\e[91m解析json配置文件app项列表:${index}, 字段: ${name}失败\e[0m"
        exit 1
    fi
}

if [[ ${stage} == "build" ]];then
    checkConfig ${stage}
    build
elif [[ ${stage} == "deploy" ]];then
    checkConfig ${stage}
    deploy
else
    echo -e "\e[91m没有检索到相应的CI阶段\e[0m"
    exit 1
fi

