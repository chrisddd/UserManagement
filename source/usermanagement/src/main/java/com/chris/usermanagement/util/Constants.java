package com.chris.usermanagement.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Constants.
 *
 * @author did
 * @date 2017年12月29日
 */
public class Constants {

    /**
     * The constant LOGIN_SESSION_VALUE.
     */
    public static final String LOGIN_SESSION_VALUE = "system_account";

    /**
     * The constant LOGIN_SESSION_USERCODE.
     */
    public static final String LOGIN_SESSION_USERCODE = "login_userCode";

    /**
     * The constant CACHE_EXPIRE_MEDIA.
     */
    public static final String CACHE_EXPIRE_MEDIA = "expire_media_id";

    public static final String VALI_CODE = "LOGIN_VALIDATION_CODE";
    /**
     * The constant LEVEL_CUSTOMER.
     */
    public static final Long LEVEL_CUSTOMER = 998L;
    /**
     * The constant LEVEL_CUSTOMER_DIRECTOR.
     */
    public static final Long LEVEL_CUSTOMER_DIRECTOR = 999L;
    /**
     * The constant LEVEL_DIRECTOR.
     */
    public static final Long LEVEL_DIRECTOR = 1000L;
    /**
     * The constant LEVEL_MANAGER.
     */
    public static final Long LEVEL_MANAGER = 2000L;
    /**
     * The constant LEVEL_ADMIN.
     */
    public static final Long LEVEL_ADMIN = 9999L;

    /**
     * Gets server url.
     *
     * @param request the request
     * @param path    the path
     * @return the server url
     */
    public static String getServerUrl(HttpServletRequest request, String path) {
        StringBuffer url = new StringBuffer();
        url.append("http://");
        String serverName = request.getServerName();
        url.append(serverName);
        String context = request.getContextPath();
        url.append(context);
        url.append(path);
        return url.toString();
    }


    /**
     * The enum Status.
     */
    public enum Status {
        // Active status.
        Active,

        //Delete status
        Delete
    }
}
