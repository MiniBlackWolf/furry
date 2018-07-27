package com.example.wolf.Utils;

import com.example.wolf.land.orderbeans;

import java.util.List;

public class OrderUtils {
    public static orderbeans.payitem addpayitem(String manyid,String itemtype,Integer counts,double unitprice){
        orderbeans.payitem payitem = new orderbeans.payitem();
        payitem.setManyid(manyid);
        payitem.setItemtype(itemtype);
        payitem.setCounts(counts);
        payitem.setUnitprice(unitprice);
        return payitem;
    }

    public  static  orderbeans addorder(String goodsname, Integer goodstype, double price, Integer userid, long buytime, List<orderbeans.payitem> payitem){
        orderbeans orderbeans = new orderbeans();
        orderbeans.setGoodsname(goodsname);
        orderbeans.setGoodstype(goodstype);
        orderbeans.setPrice(price);
        orderbeans.setUserid(userid);
        orderbeans.setBuytime(buytime);
        orderbeans.setPayitem(payitem);

        return orderbeans;
    }

}
