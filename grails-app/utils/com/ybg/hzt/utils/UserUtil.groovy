package com.ybg.hzt.utils

import org.apache.commons.codec.digest.DigestUtils

/**
 * Created by yangbagang on 16/7/16.
 */
class UserUtil {

    private static Map container = [:]

//    static {
//        container.put(1L, "e07e054dfc57bf5401088582da1feba9631ab0732f82ad0fbd92b388b1e1d696")
//    }

    static String createToken(String key, Long userId) {
        def token = DigestUtils.sha256Hex("${System.currentTimeMillis()}--${key}")
        container.put(userId, token)
        token
    }

    static boolean checkToken(String token) {
        return container.containsValue(token)
    }

    static Long getUserId(String token) {
        if (!checkToken(token)) {
            return 0
        }
        def key = container.find{it.value == token}?.key
        return key
    }

    static boolean removeToken(String token) {
        if (container.containsValue(token)) {
            def key = getUserId(token)
            return container.remove(key, token)
        }
        return false;
    }

}
