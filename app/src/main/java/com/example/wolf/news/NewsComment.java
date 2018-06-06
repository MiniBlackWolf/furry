package com.example.wolf.news;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 
 */
public class NewsComment implements Serializable {
    private Integer ncid;

    private Integer nid;

    private Integer uid;

    /**
     * 评论
     */
    private String comment;

    /**
     * 评论日期
     */
    private Timestamp commentdate;

    private static final long serialVersionUID = 1L;

    public Integer getNcid() {
        return ncid;
    }

    public void setNcid(Integer ncid) {
        this.ncid = ncid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Timestamp commentdate) {
        this.commentdate = commentdate;
    }
}