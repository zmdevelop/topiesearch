package com.dm.platform.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cgj on 2015/11/22.
 */
public class ResponseUtil {
    public static Map success() {
        Map map = new HashMap();
        map.put("status", 1);
        map.put("msg", "success");
        return map;
    }

    public static <T> Map success(T data) {
        Map map = new HashMap();
        map.put("status", 1);
        map.put("msg", "success");
        map.put("data", data);
        return map;
    }

    public static Map success(String msg) {
        Map map = new HashMap();
        map.put("status", 1);
        map.put("msg", msg);
        return map;
    }

    public static Map error() {
        Map map = new HashMap();
        map.put("status", 0);
        map.put("msg", "error");
        return map;
    }

    public static Map error(String msg) {
        Map map = new HashMap();
        map.put("status", 0);
        map.put("msg", msg);
        return map;
    }

    public static Map timeout() {
        Map map = new HashMap();
        map.put("status", "timeout");
        map.put("msg", "请先登录");
        return map;
    }
}
