package com.example.wolf.seed;





public class Seed {

    /**
     * sid : 1
     * seedname : 土豆
     * money : 0.5
     * growday : 14
     * sowingbegin : 1
     * sowingend : 2
     * description : 超高级土豆
     * fileurl : http://203p75796g.iok.la:43975/res/seed/a.png
     * count : 197
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

    @Override
    public String toString() {
        return "Seed{" +
                "sid=" + sid +
                ", seedname='" + seedname + '\'' +
                ", money=" + money +
                ", growday=" + growday +
                ", sowingbegin=" + sowingbegin +
                ", sowingend=" + sowingend +
                ", description='" + description + '\'' +
                ", fileurl='" + fileurl + '\'' +
                ", count=" + count +
                '}';
    }
}