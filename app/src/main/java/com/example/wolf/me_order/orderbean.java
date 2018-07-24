package com.example.wolf.me_order;

import java.util.List;

public class orderbean {


    /**
     * pid : 1
     * goodsname : 购买耕耘券
     * goodstype : 1
     * price : 5.00
     * userid : 2
     * buytime : 1531810000
     * out_order : null
     * timesa : 2018.07.17
     * item : [{"id":1,"payid":1,"manyid":"1","itemtype":"1","counts":2,"unitprice":"2.50","shijian":"2018.07.17","name":"菜鸟环保|灌溉券","imgurl":"/static/farm/images/gengyun/x4.png","description":"保证作物正常生长，供给作物以充足的水分，补充作物所需水分的技术措施。"}]
     * zong : 1
     * typename : 耕耘券
     * typeimg : /farm/images/quan.png
     */

    private int pid;
    private String goodsname;
    private int goodstype;
    private String price;
    private int userid;
    private int buytime;
    private Object out_order;
    private String timesa;
    private int zong;
    private String typename;
    private String typeimg;
    private List<ItemBean> item;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public int getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(int goodstype) {
        this.goodstype = goodstype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBuytime() {
        return buytime;
    }

    public void setBuytime(int buytime) {
        this.buytime = buytime;
    }

    public Object getOut_order() {
        return out_order;
    }

    public void setOut_order(Object out_order) {
        this.out_order = out_order;
    }

    public String getTimesa() {
        return timesa;
    }

    public void setTimesa(String timesa) {
        this.timesa = timesa;
    }

    public int getZong() {
        return zong;
    }

    public void setZong(int zong) {
        this.zong = zong;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(String typeimg) {
        this.typeimg = typeimg;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        /**
         * id : 1
         * payid : 1
         * manyid : 1
         * itemtype : 1
         * counts : 2
         * unitprice : 2.50
         * shijian : 2018.07.17
         * name : 菜鸟环保|灌溉券
         * imgurl : /static/farm/images/gengyun/x4.png
         * description : 保证作物正常生长，供给作物以充足的水分，补充作物所需水分的技术措施。
         */

        private int id;
        private int payid;
        private String manyid;
        private String itemtype;
        private int counts;
        private String unitprice;
        private String shijian;
        private String name;
        private String imgurl;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPayid() {
            return payid;
        }

        public void setPayid(int payid) {
            this.payid = payid;
        }

        public String getManyid() {
            return manyid;
        }

        public void setManyid(String manyid) {
            this.manyid = manyid;
        }

        public String getItemtype() {
            return itemtype;
        }

        public void setItemtype(String itemtype) {
            this.itemtype = itemtype;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public String getUnitprice() {
            return unitprice;
        }

        public void setUnitprice(String unitprice) {
            this.unitprice = unitprice;
        }

        public String getShijian() {
            return shijian;
        }

        public void setShijian(String shijian) {
            this.shijian = shijian;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
