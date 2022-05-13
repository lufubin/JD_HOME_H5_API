package com.jd.home.api.index;

import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jd.home.constant.JdFuncConstant.GET_INDEX;
import static com.jd.home.constant.JdFuncConstant.GET_TYPE_INDEX;

@Api(tags = "首页")
@RestController
public class IndexController {

    @ApiOperation("获取首页数据")
    @GetMapping("indexh5/getIndex")
    public String getIndexH5(
            @ApiParam(value = "经度", required = true) String lng,
            @ApiParam(value = "纬度", required = true) String lat,
            @ApiParam(value = "城市名") String city
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("city", city);
        bodyJson.set("longitude", lng);
        bodyJson.set("latitude", lat);
        bodyJson.set("coordType", "2");
        bodyJson.set("h5From", "");
        bodyJson.set("currentPage", "");
        bodyJson.set("storeId", "");
        bodyJson.set("activityId", "");
        bodyJson.set("isIndex", false);
        bodyJson.set("pageSource", "home");
        bodyJson.set("ctp", "home");
        bodyJson.set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(GET_INDEX, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("获取分类数据")
    @GetMapping("zone/getNewChannelDetailPost")
    public String getTypeIndex(
            @ApiParam(value = "经度", required = true) String lng,
            @ApiParam(value = "纬度", required = true) String lat,
            @ApiParam(value = "分类id", required = true) String channelId
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "home");
        bodyJson.set("longitude", lng);
        bodyJson.set("latitude", lat);
        bodyJson.set("city", "");
        bodyJson.set("coordType", "2");
        bodyJson.set("channelId", channelId);
        bodyJson.set("pageSource", "newChannelPage");
        bodyJson.set("ref", "home");
        bodyJson.set("ctp", "channel");
        bodyJson.set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(GET_TYPE_INDEX, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
