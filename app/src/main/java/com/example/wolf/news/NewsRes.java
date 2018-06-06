package com.example.wolf.news;

import java.io.Serializable;

/**
 * @author 
 */
public class NewsRes implements Serializable {
    /**
     * ID
     */
    private Integer rid;

    /**
     * 新闻资源文件名
     */
    private String filename;

    /**
     * 新闻表ID
     */
    private Integer nid;

    private static final long serialVersionUID = 1L;

    public NewsRes()
    {
    }

    public NewsRes(Integer rid, String filename, Integer nid)
    {
        this.rid = rid;
        this.filename = filename;
        this.nid = nid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }
}