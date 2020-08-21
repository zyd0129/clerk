package com.ts.clerk.oauth.pojo.form;

import lombok.Data;

@Data
public class ChangePasswordForm {
    private String oldPassword;
    private String newPassword;
}
