package com.jd.home.api.address;

import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jd.home.constant.JdFuncConstant.*;


@Api(tags = "地址")
@RestController
public class AddressController {


    @ApiOperation("获取我的收获地址")
    @GetMapping("addresspdj/getAddressList")
    public String getAddressList() {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "indexSltAddress");
        bodyJson.set("pageSource", "poi_search_lst");
        bodyJson.set("ref", "location");
        bodyJson.set("ctp", "poi_search_lst");
        bodyJson.set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(GET_MY_HARVEST_ADDRESS, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    @ApiOperation("添加收获地址")
    @GetMapping("addresspdj/addAddress")
    public String addAddress(
            @ApiParam(value = "定位地址", required = true) String poi,
            @ApiParam(value = "手机号", required = true) String mobile,
            @ApiParam(value = "市级Id", required = true) String cityId,
            @ApiParam(value = "市级名称") String cityName,
            @ApiParam(value = "纬度") String lat,
            @ApiParam(value = "经度") String lng,
            @ApiParam(value = "县级Id", required = true) String countyId,
            @ApiParam(value = "县级名称") String countyName,
            @ApiParam(value = "标签(1-家,2-公司,3-学校)") String tags,
            @ApiParam(value = "标签名(home,company,school)") String tagsMta,
            @ApiParam(value = "详细地址", required = true) String addressDetail,
            @ApiParam(value = "收货人", required = true) String name
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "indexSltAddress")
                .set("poi", poi)
                .set("mobile", mobile)
                .set("cityName", cityName)
                .set("latitude", lat)
                .set("longitude", lng)
                .set("countyId", countyId)
                .set("countyName", countyName)
                .set("cityId", cityId)
                .set("standardAddress", "")
                .set("loading", false)
                .set("orderInfo", null)
                .set("error", null)
                .set("superzone", false)
                .set("tags", tags)
                .set("tagsMta", tagsMta)
                .set("addressDetail", addressDetail)
                .set("name", name)
                .set("coordType", "2")
                .set("pageSource", "addAddress")
                .set("ref", "location")
                .set("ctp", "new_address")
                .set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(ADD_ADDRESS, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("删除收货地址")
    @GetMapping("addresspdj/delAddress")
    public String delAddress(
            @ApiParam(value = "id", required = true) String id
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "indexSltAddress")
                .set("id", id)
                .set("pageSource", "addAddress")
                .set("ref", "location")
                .set("ctp", "new_address")
                .set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(DEL_ADDRESS, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
