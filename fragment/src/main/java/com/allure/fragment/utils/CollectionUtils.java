package com.allure.fragment.utils;

import java.util.Collection;

/**
 * Created by Cherish on 2017/11/30.
 */

public class CollectionUtils {

    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
