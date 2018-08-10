package com.netease.kaola.guava;

/**
 * Created by hzwangqiqing
 * on 2017/10/27.
 */

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hzyouzhangfeng.
 * @date 2017/9/29.
 */
@Component
public class LocalGuavaCache {

    public static final LoadingCache<String, List<Ad>> localListAdCache;

    public static final LoadingCache<String, Map<Integer, List<Ad>>> localMapListAdCache;

    private static final int MAX_SIZE = 100000;

    /* 初始化 */
    static {
        localListAdCache = CacheBuilder
                .newBuilder()
                .maximumSize(MAX_SIZE)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, List<Ad>>() {
                    @Override
                    public List<Ad> load(String key) throws Exception {
                        return localListAdCache.getIfPresent(key);
                    }
                });

        localMapListAdCache = CacheBuilder
                .newBuilder()
                .maximumSize(MAX_SIZE)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Map<Integer, List<Ad>>>() {
                    @Override
                    public Map<Integer, List<Ad>> load(String key) throws Exception {
                        return localMapListAdCache.getIfPresent(key);
                    }
                });
    }
}
