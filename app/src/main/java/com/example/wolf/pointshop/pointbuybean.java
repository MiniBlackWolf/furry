package com.example.wolf.pointshop;

import java.util.List;

public class pointbuybean  {


    /**
     * userid : 2
     * totle : 13000
     * nums : 2
     * item : [{"sid":1,"sprice":3000,"snums":1,"sname":"名字","stype":1,"sland":7}]
     */

    private int userid;
    private double totle;
    private int nums;
    private List<ItemBean> item;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    public double getTotle() {
        return totle;
    }

    public void setTotle(double totle) {
        this.totle = totle;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        /**
         * sid : 1
         * sprice : 3000
         * snums : 1
         * sname : 名字
         * stype : 1
         * sland : 7
         */

        private int sid;
        private double sprice;
        private int snums;
        private String sname;
        private int stype;
        private int sland;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public double getSprice() {
            return sprice;
        }

        public void setSprice(double sprice) {
            this.sprice = sprice;
        }

        public int getSnums() {
            return snums;
        }

        public void setSnums(int snums) {
            this.snums = snums;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public int getStype() {
            return stype;
        }

        public void setStype(int stype) {
            this.stype = stype;
        }

        public int getSland() {
            return sland;
        }

        public void setSland(int sland) {
            this.sland = sland;
        }
    }
}
