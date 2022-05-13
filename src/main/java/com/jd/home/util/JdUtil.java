package com.jd.home.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONObject;

import static com.jd.home.constant.JdConstant.*;
import static com.jd.home.constant.JdEncryptConstant.*;

/**
 * 加密相关
 */
public class JdUtil {

    /**
     * AES
     */
    public static final AES AES_OBJ = new AES(MODE, PADDING, KEY_BYTE, IV_BYTE);

    /**
     * HMAC256
     */
    public static final HMac H_MAC = new HMac(HmacAlgorithm.HmacSHA256, HMAC_256_KEY_BYTE);


    public static void setSignKeyV1(JSONObject params) throws CloneNotSupportedException {
        var newParams = params.clone();
        newParams.remove(SIGN_NEED_BODY);
        newParams.remove(JD_PARAM_FUNCTION);
        var sign = new StringBuilder();
        newParams.keySet().stream().sorted(String::compareTo).forEach(item -> {
            if (StrUtil.equals(item, BODY)) {
                JSONObject jsonBody = newParams.getJSONObject(item);
                jsonBody.keySet().stream().sorted(String::compareTo).forEach(next -> {
                    var val = jsonBody.get(next);
                    if (ObjectUtil.isNotEmpty(val)) {
                        sign.append(val).append(AND);
                    }
                });
            } else if (StrUtil.isNotBlank(newParams.getStr(item))) {
                sign.append(newParams.get(item)).append(AND);
            }
        });
        var signStr = sign.substring(0, sign.lastIndexOf(String.valueOf(AND)));
        var signKeyV1 = H_MAC.digestHex(signStr);
        params.set(SIGN_KEY_V1, signKeyV1);
    }


}
