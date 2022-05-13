package com.jd.home.api.address;

import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jd.home.constant.JdFuncConstant.ADDRESS_SEARCH;
import static com.jd.home.constant.JdFuncConstant.GET_CITIES;

@Api(tags = "定位")
@RestController
public class CityController {

    @ApiOperation("获取城市")
    @GetMapping("city")
    public String getCities() throws CloneNotSupportedException {
        var bodyJsonObject = new JSONObject();
        bodyJsonObject.set("refPageSource", "home");
        bodyJsonObject.set("pageSource", "indexSltAddress");
        bodyJsonObject.set("ref", "home");
        bodyJsonObject.set("ctp", "location");
        bodyJsonObject.set("refPar", "");
        var params = new JdParam(bodyJsonObject);
        try (var httpResponse = RequestUtil.getInstance().execRequest(GET_CITIES, params, false)) {
            return httpResponse.body();
        }
    }

    @ApiOperation("获取详细具体地点")
    @GetMapping("address/search")
    public String get(@ApiParam(value = "城市代号") int areaCode,
                      @ApiParam(value = "城市名称") String areaName,
                      @ApiParam(value = "搜索的地址") String address) throws CloneNotSupportedException {
        var bodyJsonObject = new JSONObject();
        bodyJsonObject.set("refPageSource", "indexSltAddress");
        bodyJsonObject.set("region", areaName);
        bodyJsonObject.set("key", address);
        bodyJsonObject.set("areaCode", areaCode);
        bodyJsonObject.set("pageSource", "poi_search_lst");
        bodyJsonObject.set("ref", "location");
        bodyJsonObject.set("ctp", "poi_search_lst");
        bodyJsonObject.set("refPar", "");
        var params = new JdParam(bodyJsonObject);
        try (var httpResponse = RequestUtil.getInstance().execRequest(ADDRESS_SEARCH, params, false)) {
            return httpResponse.body();
        }
    }
}
