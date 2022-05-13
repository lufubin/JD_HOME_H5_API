package com.jd.home.api.store;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.jd.home.entity.JdParam;
import com.jd.home.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jd.home.constant.JdFuncConstant.STORE_CATEGORY_PRODUCTS;
import static com.jd.home.constant.JdFuncConstant.STORE_SHOP_DETAIL;

@Api(tags = "商品")
@RestController
public class ShopController {

    @ApiOperation("获取店铺分类商品数据")
    @GetMapping("storeIndexSearch/searchByCategoryPost")
    public String getStoreClassificationProductData(
            @ApiParam(value = "店铺Id", required = true) String storeId,
            @ApiParam(value = "店铺代码", required = true) String orgCode,
            @ApiParam(value = "类型Id", required = true) String catId,
            @ApiParam(value = "类型名称") String catName,
            @ApiParam(value = "类型编号") String type
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "newChannelPage");
        var catIds = new JSONArray();
        var cat = new JSONObject();
        cat.set("catId", catId);
        cat.set("type", type);
        cat.set("catName", catName);
        cat.set("skuList", new JSONArray());
        cat.set("childCatSize", 0);
        catIds.add(cat);
        bodyJson.set("catIds", catIds);
        bodyJson.set("storeId", storeId);
        bodyJson.set("orgCode", orgCode);
        bodyJson.set("industryTag", "99");
        bodyJson.set("showSoldOutSkus", true);
        bodyJson.set("needPreSell", true);
        bodyJson.set("ref", "channel");
        bodyJson.set("pageSource", "store");
        bodyJson.set("ctp", "storeinfo");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(STORE_CATEGORY_PRODUCTS, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("获取店铺商品详情")
    @GetMapping("spuSaleAttr/spuSaleAttrListPost")
    public String getStoreShopDetail(
            @ApiParam(value = "店铺Id", required = true) String storeId,
            @ApiParam(value = "店铺代码", required = true) String orgCode,
            @ApiParam(value = "SPU", required = true) String spuId,
            @ApiParam(value = "SKU", required = true) String skuId

    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "newChannelPage");
        bodyJson.set("storeId", storeId);
        bodyJson.set("orgCode", orgCode);
        bodyJson.set("spuId", spuId);
        bodyJson.set("skuId", skuId);
        bodyJson.set("pageSource", "store");
        bodyJson.set("ref", "storeinfo");
        bodyJson.set("ctp", "ExposureSpuGoods");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(STORE_SHOP_DETAIL, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
