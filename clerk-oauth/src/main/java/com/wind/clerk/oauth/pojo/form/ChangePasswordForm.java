package com.wind.clerk.oauth.pojo.form;

import lombok.Data;

@Data
public class ChangePasswordForm {
    private Integer id;
    private String oldPassword;
    private String newPassword;
}
