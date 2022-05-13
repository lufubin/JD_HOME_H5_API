package com.jd.home.constant;

/**
 * 常用常量
 */
public interface JdConstant {

    /**
     * 请求地址
     */
    String JD_URL = "https://daojia.jd.com/client";

    /**
     * 参数1
     */
    String JD_PARAM_FUNCTION = "functionId";

    /**
     * 参数2
     */
    String JD_PARAM_DJENCRYPT = "djencrypt";

    /**
     * &
     */
    char AND = '&';

    /**
     * =
     */
    char EQUAL = '=';

    /**
     * question mark
     */
    char Q_M = '?';

    /**
     * 分号
     */
    char SEMICOLON = ';';

    /**
     * APPName
     */
    String APP_NAME = "appName";
    String APP_NAME_VALUE = "paidaojia";

    /**
     * platCode
     */
    String PLAT_CODE = "platCode";
    String PLAT_CODE_VALUE = "H5";
    /**
     * 浏览器标识
     */
    String USER_AGENT = "User-Agent";
    String USER_AGENT_VALUE = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Mobile Safari/537.36 Edg/101.0.1210.39";

    String REFERER = "Referer";
    String REFERER_VALUE = "https://daojia.jd.com";

    /**
     * Cookie
     */
    String COOKIE = "Cookie";

    /**
     * 版本
     */
    String APP_VERSION = "appVersion";
    String APP_VERSION_VALUE = "8.19.0";

    /**
     * 结构体
     */
    String BODY = "body";

    String SIGN_NEED_BODY = "signNeedBody";

    String SIGN_KEY_V1 = "signKeyV1";

    String JD_RANDOM = "_jdrandom";

    String DEVICES_ID = "deviceId";

    String TRACE_ID = "traceId";

}
