package com.example.wolf.news;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 
 */
public class News implements Serializable {
    /**
     * ID
     */
    private Integer nid;

    /**
     * 新闻标题
     */
    private String newstitle;

    /**
     * 新闻内容
     */
    private String newscontent;

    /**
     * 新闻类型：文本|图片|视频（用于表newsRes）
     */
    private String newstype;

    public static final String TX = "tx";
	public static final String IMAGE = "image";
	public static final String VIDEO = "video";

    /**
     * 新闻发布日期
     */
    private Timestamp newsdate;

    private static final long serialVersionUID = 1L;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public Timestamp getNewsdate() {
        return newsdate;
    }

    public void setNewsdate(Timestamp newsdate) {
        this.newsdate = newsdate;
    }
}