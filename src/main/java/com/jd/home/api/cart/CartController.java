package com.jd.home.api.cart;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jd.home.constant.JdFuncConstant.*;

@Api(tags = "购物车")
@RestController
public class CartController {

    @ApiOperation("添加购物车")
    @GetMapping("cartV3_3_0/changeItemNumPost")
    public String addCard(
            @ApiParam(value = "店铺Id", required = true) String storeId,
            @ApiParam(value = "店铺代号") String orgCode,
            @ApiParam(value = "SKU", required = true) String skuId,
            @ApiParam(value = "SPU", required = true) String spuId,
            @ApiParam(value = "数量", required = true) int num
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "ExposureSpuGoods");
        var skus = new JSONArray();
        var sku = new JSONObject();
        sku.set("id", skuId);
        sku.set("num", num);
        sku.set("spuId", spuId);
        skus.add(sku);
        bodyJson.set("skus", skus);
        bodyJson.set("fromSource", "2");
        bodyJson.set("storeId", storeId);
        bodyJson.set("orgCode", orgCode);
        bodyJson.set("chgNumReturnType", "0");
        bodyJson.set("isAdd", true);
        bodyJson.set("spuId", spuId);
        bodyJson.set("incrementFlag", true);
        bodyJson.set("cartOpenFlag", true);
        bodyJson.set("showedPurchaseLimitHotSalePopupVo", false);
        bodyJson.set("pageSource", "productDetail");
        bodyJson.set("ref", "ExposureSpuGoods");
        bodyJson.set("ctp", "goodsInfo");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(ADD_TO_THE_CART, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("查看购物车（店铺内）")
    @GetMapping("cartV3_3_0/querySingleCart")
    public String getCart(@ApiParam(value = "店铺Id", required = true) String storeId,
                          @ApiParam(value = "店铺代号") String orgCode
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "newChannelPage");
        bodyJson.set("storeId", storeId);
        bodyJson.set("orgCode", orgCode);
        bodyJson.set("fromSource", "2");
        bodyJson.set("cartOpenType", "1");
        bodyJson.set("positionType", "2");
        bodyJson.set("pageSource", "store");
        bodyJson.set("ctp", "mini_shopcar");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(QUERY_SHOPPING_CART_BY_STORE, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("查看购物车（全部）")
    @GetMapping("cartV3_3_0/queryallcarts")
    public String getCartAll(
            @ApiParam(value = "经度") String lng,
            @ApiParam(value = "纬度") String lat

    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "find");
        bodyJson.set("lng", lng);
        bodyJson.set("lat", lat);
        bodyJson.set("positionType", "2");
        bodyJson.set("fromSource", "2");
        bodyJson.set("cartType", "20");
        bodyJson.set("pageSource", "shopcar");
        bodyJson.set("ref", "find");
        bodyJson.set("ctp", "shopcar");
        bodyJson.set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(QUERY_SHOPPING_CART_BY_ALL, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("清空购物车")
    @GetMapping("cartV3_3_0/removeAllItems")
    public String removeAllCart(
            @ApiParam(value = "店铺Id", required = true) String storeId,
            @ApiParam(value = "店铺代码") String orgCode
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource","shopcar");
        bodyJson.set("storeId",storeId);
        bodyJson.set("orgCode",orgCode);
        bodyJson.set("fromSource","2");
        bodyJson.set("cartType","10");
        bodyJson.set("cartOpenFlag",true);
        bodyJson.set("pageSource","store");
        bodyJson.set("ref","storeinfo");
        bodyJson.set("ctp","mini_shopcar");
        bodyJson.set("refPar","");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(REMOVE_SHOPPING_CART, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
