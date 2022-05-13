package com.jd.home.util;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

import static com.jd.home.constant.JdConstant.*;
import static com.jd.home.util.JdUtil.AES_OBJ;
import static com.jd.home.util.JdUtil.setSignKeyV1;


/**
 * 统一请求封装
 */
public class RequestUtil {

    private final HashMap<String, String> headers = new HashMap<>() {
        {
            put(USER_AGENT, USER_AGENT_VALUE);
            put(REFERER, REFERER_VALUE);
        }
    };

    private String deviceId;

    private static final RequestUtil REQUEST = new RequestUtil();

    private RequestUtil() {
    }

    public static RequestUtil getInstance() {
        return REQUEST;
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String cookie) {
        if (StrUtil.isNotBlank(cookie)) {
            this.deviceId = ReUtil.get("deviceid_pdj_jd=([\\w-]+)", cookie, 1);
        }
        setHeader(COOKIE, cookie);
    }

    public void clearDeviceId() {
        this.deviceId = null;
        setHeader(COOKIE, null);
    }

    private HttpResponse get(String url) {
        try (var execute = HttpRequest.get(url).headerMap(getHeaders(), true).execute()) {
            return execute;
        }
    }

    private HttpResponse post(String url) {
        try (var execute = HttpRequest.post(url).headerMap(getHeaders(), true).execute()) {
            return execute;
        }
    }

    public HttpResponse execRequest(String funcId, JSONObject params, boolean isPost) throws CloneNotSupportedException {
        //生成signKeyV1
        setSignKeyV1(params);
        //加密
        var jsonParamStr = JSONUtil.toJsonStr(params);
        var djSignStr = URLEncodeUtil.encodeAll(AES_OBJ.encryptBase64(jsonParamStr));
        System.out.println(params);
        var url = JD_URL + Q_M + JD_PARAM_FUNCTION + EQUAL + funcId + AND + JD_PARAM_DJENCRYPT + EQUAL + djSignStr;
        try (var httpResponse = isPost ? post(url) : get(url)) {
            return httpResponse;
        }
    }


}
