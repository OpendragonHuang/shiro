package com.opendragonhuang.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class AuthenticationRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 从 token 中获取 username
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String up = upToken.getUsername();

        // 2. 通过 username 从数据库获取用户信息，这里直接模拟
        String username = "hkl";
        String password = "04bad5455464a7bfe377936c22e361dc"; // 加密后的密码

        if(!up.equals(username)){
            throw new UnknownAccountException("用户名或密码错误");
        }

        // 3. 创建 SimpleAuthenticationInfo 返回
        String realmName = getName();
        ByteSource salt = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, salt,realmName);
        return authenticationInfo;
    }

    public static void main(String[] args) {
        ByteSource salt = ByteSource.Util.bytes("hkl");
        SimpleHash simpleHash = new SimpleHash("MD5", "0320", salt, 3);
        System.out.println(simpleHash);
    }
}
