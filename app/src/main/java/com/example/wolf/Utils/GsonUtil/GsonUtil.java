package com.example.wolf.Utils.GsonUtil;

import com.example.wolf.land.FarmData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class GsonUtil {

    public GsonUtil() {
    }


    public List Gson(String json,Object s) {
        Gson gson = new Gson();
    //    Seed seed = new Seed();
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        ArrayList BeanList = new ArrayList<>();
        //加强for循环遍历JsonArray
        for (JsonElement seedp : jsonArray) {
            //使用GSON，直接转成Bean对象
            Object ss = gson.fromJson(seedp, (Class)s);
            BeanList.add(ss);
        }

        return BeanList;


    }
    public FarmData getfarmjson(String json){
        Gson gson = new Gson();
        FarmData ddd = gson.fromJson(json,FarmData.class );

        return  ddd;
    }



}
