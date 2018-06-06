package com.example.wolf.SysAdmin;

import java.io.Serializable;

/**
 * @author 
 */
public class SysAdmin implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码的16进制SHA256（MD5加密后，使用SHA256）
     */
    private String password;

    /**
     * sys超级管理员
     */
    private String privilege;

    /**
     * 用户状态：离线 | 在线 | 冻结
     */
    private String status;

    private static final long serialVersionUID = 1L;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}