package com.cn.cloud.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName   : CookieUtil.java
 * @Description : 
 * @author Yin Xueyuan
 * @since 2017年3月9日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年3月9日        Yin Xueyuan           fisrt create
 * </pre>
 */

public class CookieUtil {
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];

            String cookieName = cookie.getName();
            if (name.equalsIgnoreCase(cookieName)) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
