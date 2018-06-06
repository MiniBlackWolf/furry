package com.example.wolf.seed;

public class seedbean {


    /**
     * seed : {"sid":1,"seedname":"土豆","money":2.44,"growday":50,"sowingbegin":2,"sowingend":3,"description":"超高级土豆OK","fileurl":"http://203p75796g.iok.la:43975/res/seed/5153ce055771402e8b91c5439125925c20180516170206.jpg","count":58}
     * farmId : A0001
     */

    private SeedBean seed;
    private String farmId;

    public SeedBean getSeed() {
        return seed;
    }

    public void setSeed(SeedBean seed) {
        this.seed = seed;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public static class SeedBean {
        /**
         * sid : 1
         * seedname : 土豆
         * money : 2.44
         * growday : 50
         * sowingbegin : 2
         * sowingend : 3
         * description : 超高级土豆OK
         * fileurl : http://203p75796g.iok.la:43975/res/seed/5153ce055771402e8b91c5439125925c20180516170206.jpg
         * count : 58
         */

        private int sid;
        private String seedname;
        private double money;
        private int growday;
        private int sowingbegin;
        private int sowingend;
        private String description;
        private String fileurl;
        private int count;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSeedname() {
            return seedname;
        }

        public void setSeedname(String seedname) {
            this.seedname = seedname;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getGrowday() {
            return growday;
        }

        public void setGrowday(int growday) {
            this.growday = growday;
        }

        public int getSowingbegin() {
            return sowingbegin;
        }

        public void setSowingbegin(int sowingbegin) {
            this.sowingbegin = sowingbegin;
        }

        public int getSowingend() {
            return sowingend;
        }

        public void setSowingend(int sowingend) {
            this.sowingend = sowingend;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
