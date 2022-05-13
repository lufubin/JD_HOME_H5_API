package com.jd.home.entity;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.jd.home.util.RequestUtil;

import static com.jd.home.constant.JdConstant.*;

/**
 * 京东参数
 */
public class JdParam extends JSONObject {

    {
        {
            set(PLAT_CODE, PLAT_CODE_VALUE);
            set(APP_NAME, APP_NAME_VALUE);
            set(APP_VERSION, APP_VERSION_VALUE);
        }
    }

    public JdParam(JSONObject body) {
        var currentTimes = System.currentTimeMillis();
        this.set(BODY, body);
        this.set(JD_RANDOM, currentTimes);
        this.set(SIGN_NEED_BODY,"1");
        var deviceId = RequestUtil.getInstance().getDeviceId();
        if (StrUtil.isNotBlank(deviceId)) {
            this.set(DEVICES_ID, deviceId);
            this.set(TRACE_ID, deviceId + currentTimes);
        }
    }

    @Override
    public JSONObject set(String key, Object value) {
        return super.set(key, value);
    }
}
