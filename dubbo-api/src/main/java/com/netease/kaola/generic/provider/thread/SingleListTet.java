package com.netease.kaola.generic.provider.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by hzwangqiqing
 * on 2018/8/20.
 */
public class SingleListTet {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        Collections.singletonList("str");
        System.out.println(list);
    }
}
