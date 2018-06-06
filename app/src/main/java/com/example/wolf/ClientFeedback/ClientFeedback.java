package com.example.wolf.ClientFeedback;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author
 */
public class ClientFeedback implements Serializable {
    /**
     * ID
     */
    private Integer iid;

    /**
     * userBase的ID
     */
    private Integer uid;

    /**
     * 客户的留言
     */
    private String info;

    /**
     * 留言日期
     */
    private Timestamp infodate;

    private static final long serialVersionUID = 1L;

    public ClientFeedback() {
    }

    public ClientFeedback(Integer iid, Integer uid, String info, Timestamp infodate) {
        this.iid = iid;
        this.uid = uid;
        this.info = info;
        this.infodate = infodate;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Timestamp getInfodate() {
        return infodate;
    }

    public void setInfodate(Timestamp infodate) {
        this.infodate = infodate;
    }
}