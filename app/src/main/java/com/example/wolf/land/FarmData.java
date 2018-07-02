package com.example.wolf.land;

import java.util.List;

public class FarmData {


    /**
     * packagingFee : 5.5
     * pick : 2.11
     * selection : [{"m":10,"money":1}]
     * cultivating : {"sowing":15,"awkwardly":15.42,"weeding":10,"fertilizer":5,"watering":3,"exterminator":5.5}
     */

    private double packagingFee;
    private double pick;
    private CultivatingBean cultivating;
    private List<SelectionBean> selection;

    public double getPackagingFee() {
        return packagingFee;
    }

    public void setPackagingFee(double packagingFee) {
        this.packagingFee = packagingFee;
    }

    public double getPick() {
        return pick;
    }

    public void setPick(double pick) {
        this.pick = pick;
    }

    public CultivatingBean getCultivating() {
        return cultivating;
    }

    public void setCultivating(CultivatingBean cultivating) {
        this.cultivating = cultivating;
    }

    public List<SelectionBean> getSelection() {
        return selection;
    }

    public void setSelection(List<SelectionBean> selection) {
        this.selection = selection;
    }

    public static class CultivatingBean {
        /**
         * sowing : 15
         * awkwardly : 15.42
         * weeding : 10
         * fertilizer : 5
         * watering : 3
         * exterminator : 5.5
         */

        private double sowing;
        private double awkwardly;
        private double weeding;
        private double fertilizer;
        private double watering;
        private double exterminator;
        private double organic;

        public double getOrganic() {
            return organic;
        }

        public void setOrganic(double organic) {
            this.organic = organic;
        }

        public double getSowing() {
            return sowing;
        }

        public void setSowing(double sowing) {
            this.sowing = sowing;
        }

        public double getAwkwardly() {
            return awkwardly;
        }

        public void setAwkwardly(double awkwardly) {
            this.awkwardly = awkwardly;
        }

        public double getWeeding() {
            return weeding;
        }

        public void setWeeding(double weeding) {
            this.weeding = weeding;
        }

        public double getFertilizer() {
            return fertilizer;
        }

        public void setFertilizer(double fertilizer) {
            this.fertilizer = fertilizer;
        }

        public double getWatering() {
            return watering;
        }

        public void setWatering(double watering) {
            this.watering = watering;
        }

        public double getExterminator() {
            return exterminator;
        }

        public void setExterminator(double exterminator) {
            this.exterminator = exterminator;
        }
    }

    public static class SelectionBean {
        /**
         * m : 10
         * money : 1
         */

        private int m;
        private double money;

        public int getM() {
            return m;
        }

        public void setM(int m) {
            this.m = m;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }
    }
}
