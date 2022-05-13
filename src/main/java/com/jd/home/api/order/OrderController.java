package com.jd.home.api.order;

import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jd.home.constant.JdFuncConstant.QUERY_ORDER_LIST;

@Api(tags = "订单")
@RestController
public class OrderController {

    @ApiOperation("获取订单列表")
    @GetMapping("order/listV2.0")
    public String getOrderList(
            @ApiParam(value = "起始位置", required = true, defaultValue = "0") String startIndex
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "home");
        bodyJson.set("startIndex", startIndex);
        bodyJson.set("dataSize", 10);
        bodyJson.set("jumpSource", 1);
        bodyJson.set("pageSource", "orderList");
        bodyJson.set("ref", "home");
        bodyJson.set("ctp", "myorderlist");
        bodyJson.set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(QUERY_ORDER_LIST, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
