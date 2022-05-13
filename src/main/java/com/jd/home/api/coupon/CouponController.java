package com.jd.home.api.coupon;

import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jd.home.constant.JdFuncConstant.GET_COUPONS;
import static com.jd.home.constant.JdFuncConstant.RECEIVE_COUPONS;

@Api(tags = "优惠券")
@RestController
public class CouponController {

    @ApiOperation("查询优惠卷")
    @GetMapping("coupon/stationCouponInfo")
    public String getCoupon(
            @ApiParam(value = "店铺代号", required = true) String orgCode,
            @ApiParam(value = "店铺Id") String storeId
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "ExposureSpuGoods");
        bodyJson.set("stationNo", storeId);
        bodyJson.set("orgCode", orgCode);
        bodyJson.set("pageSource", "store");
        bodyJson.set("ref", "ExposureSpuGoods");
        bodyJson.set("ctp", "storeinfo");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(GET_COUPONS, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("领取优惠券")
    @GetMapping("coupon/grabCoupon")
    public String grabCoupon(
            @ApiParam(value = "优惠券码", required = true) String code,
            @ApiParam(value = "店铺id", required = true) String storeId,
            @ApiParam(value = "店铺代号") String orgCode
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "newChannelPage")
                .set("code", code)
                .set("fromSource", "2")
                .set("storeNo", storeId)
                .set("operType", "1")
                .set("isFans", "0")
                .set("isFloor", "0")
                .set("needCouponGo", true)
                .set("grabPlat", "1")
                .set("platNewActivityFlag", "")
                .set("orgCode", orgCode)
                .set("source", "homestore")
                .set("channel", "station_home_page")
                .set("pageSource", "store")
                .set("ref", "channel")
                .set("ctp", "storeinfo")
                .set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(RECEIVE_COUPONS, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
