/*******************************************************************************
 * @(#)LogKeyUtil.java Dec 31, 2010
 * <p>
 * Copyright 2010 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.myexample.common.util;

/**
 * UUID工具类
 */
public final class UUIDUtil {

    private static long[] ls = new long[3000];

    private static int li = 0;

    /**
     * 私有构造器
     */
    private UUIDUtil() {

    }

    /**
     * 获取随机数
     *
     * @return
     */
    public static synchronized String getUUID() {
        long lo = getKey();
        for (int i = 0; i < 3000; i++) {
            long lt = ls[i];
            if (lt == lo) {
                lo = getKey();
                break;
            }
        }
        ls[li] = lo;
        li++;
        if (li == 3000) {
            li = 0;
        }
        return Long.toString(lo);
    }

    /**
     * 根据当前时间与随机数生成key
     *
     * @return
     */
    private static long getKey() {
        String a = (String.valueOf(System.currentTimeMillis())).substring(3, 13);
        String d = (String.valueOf(Math.random())).substring(2, 8);
        return Long.parseLong(a + d);
    }
}
