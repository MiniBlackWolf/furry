package com.example.wolf.me_order;

public class orderbean {


    /**
     * oid : 3
     * uid : 2
     * oiid : 316b79be9f43430792dc86b25035204220180515165246
     * name : A0001
     * type : 2
     * status : 2
     * orderdate : 1526374366000
     * description : null
     */

    private int oid;
    private int uid;
    private String oiid;
    private String name;
    private int type;
    private int status;
    private long orderdate;
    private Object description;

    public orderbean() {
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOiid() {
        return oiid;
    }

    public void setOiid(String oiid) {
        this.oiid = oiid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(long orderdate) {
        this.orderdate = orderdate;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }
}
