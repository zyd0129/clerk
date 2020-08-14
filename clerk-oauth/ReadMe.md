认证及权限错误码整理

请求认证，获取accessToken,出现的异常
Bad credentials 用户名密码错误 400

资源访问，携带accessToken，出现的异常
Failed to validate the token 401
Jwt expired at 2020-08-14T11:12:26Z 401
Access Denied 403

refresh_token
Cannot convert access token to JSON 401
Invalid refresh token (expired) 401
