package com.example.wolf.userbean;

import java.io.Serializable;

/**
 * @author 
 */
public class UserBase implements Serializable {
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码的16进制SHA256（MD5加密后，使用SHA256）
     */
    private String password;

    /**
     * 用户状态：离线 | 在线 | 冻结
     */
    private String status;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}