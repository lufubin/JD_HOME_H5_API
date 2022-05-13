package com.jd.home.api.other;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpCookie;
import java.util.List;

import static com.jd.home.constant.JdConstant.SEMICOLON;
import static com.jd.home.constant.JdFuncConstant.REFRESH_COOKIE;

@Api(tags = "Cookie设置")
@RestController
public class OtherController {

    @ApiOperation("刷新Cookie（未登录）")
    @GetMapping("refresh/cookie")
    public String refreshCookie() throws CloneNotSupportedException {
        var bodyJsonObject = new JSONObject();
        bodyJsonObject.set("groupCode", "common");
        bodyJsonObject.set("sysCode", "daojia.jd.com");
        bodyJsonObject.set("token", "2fd5776a6bb443b848673b3969ec0c53");
        bodyJsonObject.set("refPar", "");
        var params = new JdParam(bodyJsonObject);
        RequestUtil.getInstance().clearDeviceId();
        try (var httpResponse = RequestUtil.getInstance().execRequest(REFRESH_COOKIE, params, false)) {
            List<HttpCookie> cookies = httpResponse.getCookies();
            if (ArrayUtil.isNotEmpty(cookies) && cookies.size() > 0) {
                var cookieStr = new StringBuilder();
                cookies.forEach(item -> cookieStr.append(item).append(SEMICOLON));
                RequestUtil.getInstance().setDeviceId(String.valueOf(cookieStr));
            }
            return httpResponse.body();
        }
    }

    @ApiOperation("设置登录Cookie")
    @GetMapping("setting/login/cookie")
    public String settingLoginCookie(
            @ApiParam(value = "cookie", required = true) String cookie
    ) {
        RequestUtil.getInstance().setDeviceId(cookie);
        return "OK";
    }

}
