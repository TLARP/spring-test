package com.netease.kaola.guava;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzwangqiqing
 * on 2017/10/27.
 */
@Data
public class Ad implements Serializable{

    private static final long serialVersionUID = -4428208857710317648L;

    public Ad(String name){
        this.name=name;
    }

    public static String key="wapListKey";

    public static String mapKey="wapMapListKey";

    private String name="hello world";

    public static void main(String[] args) {
        List<Ad> adList=new ArrayList<>();
        Ad ad=new Ad("hello test");
        adList.add(ad);
        LocalGuavaCache.localListAdCache.put(key,adList);
        List<Ad> adList1=LocalGuavaCache.localListAdCache.getIfPresent(key);
        System.out.println(adList1);

        Map<Integer,List<Ad>> map=new HashMap<>();
        map.put(1,adList);
        LocalGuavaCache.localMapListAdCache.put(mapKey,map);
        Map<Integer,List<Ad>> map1=LocalGuavaCache.localMapListAdCache.getIfPresent(mapKey);
        System.out.println(map1);

    }
}
