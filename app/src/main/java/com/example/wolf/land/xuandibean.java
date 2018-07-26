package com.example.wolf.land;

import java.io.Serializable;
import java.util.List;

public class xuandibean implements Serializable {


    private Integer pid;
    private String goodsname;
    private Integer goodstype;
    private double price;
    private Integer userid;
    private long buytime;
    private String out_order;
    private List<payitem> payitem;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Integer getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(Integer goodstype) {
        this.goodstype = goodstype;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public long getBuytime() {
        return buytime;
    }

    public void setBuytime(long buytime) {
        this.buytime = buytime;
    }

    public String getOut_order() {
        return out_order;
    }

    public void setOut_order(String out_order) {
        this.out_order = out_order;
    }

    public List<payitem> getPayitem() {
        return payitem;
    }

    public void setPayitem(List<payitem> payitem) {
        this.payitem = payitem;
    }
    public  class payitem implements Serializable
    {

        private Integer id;
        private Integer payid;
        private String manyid;
        private String itemtype;
        private Integer counts;
        private double unitprice;

        public payitem()
        {
        }

        public Integer getId()
        {
            return id;
        }

        public void setId(Integer id)
        {
            this.id = id;
        }

        public Integer getPayid()
        {
            return payid;
        }

        public void setPayid(Integer payid)
        {
            this.payid = payid;
        }

        public String getManyid()
        {
            return manyid;
        }

        public void setManyid(String manyid)
        {
            this.manyid = manyid;
        }

        public String getItemtype()
        {
            return itemtype;
        }

        public void setItemtype(String itemtype)
        {
            this.itemtype = itemtype;
        }

        public Integer getCounts()
        {
            return counts;
        }

        public void setCounts(Integer counts)
        {
            this.counts = counts;
        }

        public double getUnitprice()
        {
            return unitprice;
        }

        public void setUnitprice(double unitprice)
        {
            this.unitprice = unitprice;
        }
    }
}
