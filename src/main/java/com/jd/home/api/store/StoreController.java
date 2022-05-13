package com.jd.home.api.store;

import cn.hutool.core.util.StrUtil;
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

@Api(tags = "商家")
@RestController
public class StoreController {

    @ApiOperation("获取附近商家")
    @GetMapping("zone/recommendStoreListPost")
    public String getNearbyBusinesses(
            @ApiParam(value = "页数") String currentPage,
            @ApiParam(value = "城市名") String city,
            @ApiParam(value = "具体地址") String address,
            @ApiParam(value = "城市代码", required = true) String areaCode,
            @ApiParam(value = "经度", required = true) String lng,
            @ApiParam(value = "纬度", required = true) String lat,
            @ApiParam(value = "上一页最后一个店铺ID") String lastStoreId,
            @ApiParam(value = "排序") String rankType,
            @ApiParam(value = "标签") String filterTagIds
    ) throws CloneNotSupportedException {
        if (StrUtil.isBlank(currentPage)) {
            currentPage = "1";
        }
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "home");
        bodyJson.set("city", city);
        bodyJson.set("areaCode", areaCode);
        bodyJson.set("longitude", lng);
        bodyJson.set("latitude", lat);
        bodyJson.set("coordType", "2");
        bodyJson.set("address", address);
        bodyJson.set("channelId", "");
        bodyJson.set("currentPage", currentPage);
        bodyJson.set("pageSize", "10");
        bodyJson.set("rankType", rankType);
        bodyJson.set("lastStoreId", lastStoreId);
        bodyJson.set("filterTagIds", filterTagIds);
        bodyJson.set("slideStoreList", false);
        bodyJson.set("venderIndustryType", new JSONArray());
        bodyJson.set("sortType", "");
        bodyJson.set("level", new JSONArray());
        bodyJson.set("activityId", "");
        bodyJson.set("pageSource", "channelStorePage");
        bodyJson.set("ref", "home");
        bodyJson.set("ctp", "moreStoreList");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(GET_NEARBY_BUSINESSES, params, true)) {
            return httpResponse.body();
        }
    }


    @ApiOperation("获取筛选附近商家总数")
    @GetMapping("zone/getNumFilterTag")
    public String getNumFilterTag(
            @ApiParam(value = "城市代码", required = true) String areaCode,
            @ApiParam(value = "经度", required = true) String lng,
            @ApiParam(value = "纬度", required = true) String lat,
            @ApiParam(value = "标签") String filterTagIds
    ) throws CloneNotSupportedException {
        var bodyJson = new JSONObject();
        bodyJson.set("areaCode", areaCode);
        bodyJson.set("channelId", "");
        bodyJson.set("city", "");
        bodyJson.set("coordType", "2");
        bodyJson.set("filterTagIds", filterTagIds);
        bodyJson.set("latitude", lat);
        bodyJson.set("longitude", lng);
        bodyJson.set("pageSource", "channelStorePage");
        bodyJson.set("ctp", "moreStoreList");
        bodyJson.set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(GET_NUM_FILTER_TAG, params, true)) {
            return httpResponse.body();
        }
    }

    @ApiOperation("搜索")
    @GetMapping("suggest/listPost")
    public String suggestListPost(@ApiParam(value = "经度", required = true) String lng,
                                  @ApiParam(value = "纬度", required = true) String lat,
                                  @ApiParam(value = "搜索内容", required = true) String key) throws CloneNotSupportedException {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "home");
        bodyJson.set("longitude", lng);
        bodyJson.set("latitude", lat);
        bodyJson.set("key", key);
        bodyJson.set("type", "2");
        bodyJson.set("source", "newMultiSearch");
        bodyJson.set("pageSource", "searchTransitPage");
        bodyJson.set("ref", "home");
        bodyJson.set("ctp", "search");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(SUGGEST_LIST_POST, params, true)) {
            return httpResponse.body();
        }
    }

    @ApiOperation("搜索(店铺内)")
    @GetMapping("storeIndexSearch/searchPost")
    public String getStoreSearch(
            @ApiParam(value = "搜索内容", required = true) String key,
            @ApiParam(value = "店铺id", required = true) String storeId,
            @ApiParam(value = "页数", required = true, defaultValue = "1") String page,
            @ApiParam(value = "排序类型", defaultValue = "sort_default", example = "默认 sort_default") String sortType,
            @ApiParam(value = "排序类型2", example = "desc 倒序 ,正序 asc") String orderType,
            @ApiParam(value = "店铺代码", required = true) String orgCode
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "storeSearch");
        bodyJson.set("key", key);
        bodyJson.set("storeId", storeId);
        bodyJson.set("sortType", sortType);
        bodyJson.set("orderType", orderType);
        bodyJson.set("page", page);
        bodyJson.set("pageSize", "10");
        bodyJson.set("cartUuid", "");
        bodyJson.set("promotLable", "");
        bodyJson.set("orgCode", orgCode);
        bodyJson.set("needRec", true);
        bodyJson.set("needAggrCats", true);
        bodyJson.set("showSoldOutSkus", true);
        bodyJson.set("needPreSell", true);
        bodyJson.set("filterList", new JSONArray());
        bodyJson.set("pageSource", "storeSearchResult");
        bodyJson.set("ref", "storeSearch");
        bodyJson.set("ctp", "store_search");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(STORE_SEARCH, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("店铺详情")
    @GetMapping("store/storeDetailV220Post")
    public String storeDetails(
            @ApiParam(value = "商品Id", required = true) String storeId,
            @ApiParam(value = "SKU", required = true) String skuId,
            @ApiParam(value = "经度") String lng,
            @ApiParam(value = "纬度") String lat
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "newChannelPage");
        bodyJson.set("storeId", storeId);
        bodyJson.set("skuId", skuId);
        bodyJson.set("activityId", "");
        bodyJson.set("promotionType", "");
        bodyJson.set("longitude", lng);
        bodyJson.set("latitude", lat);
        bodyJson.set("missionId", "");
        bodyJson.set("sourcePage", "");
        bodyJson.set("keyWord", "");
        bodyJson.set("source", "");
        bodyJson.set("cateName", "");
        bodyJson.set("channelId", "");
        bodyJson.set("anchorTab", "");
        bodyJson.set("pageSource", "store");
        bodyJson.set("ref", "channel");
        bodyJson.set("ctp", "storeinfo");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(STORE_DETAIL, params, true)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }


    @ApiOperation("关注店铺")
    @GetMapping("store/doFollow")
    public String storeDoFollow(
            @ApiParam(value = "店铺id", required = true) String storeId,
            @ApiParam(value = "是否关注(true 关注，false 取消关注)") boolean isFollow
    ) {
        var bodyJson = new JSONObject();
        bodyJson.set("refPageSource", "newChannelPage")
                .set("isFollow", isFollow)
                .set("storeId", storeId)
                .set("pageSource", "store")
                .set("ref", "channel")
                .set("ctp", "storeinfo")
                .set("refPar", "");
        var params = new JdParam(bodyJson);
        try (var httpResponse = RequestUtil.getInstance().execRequest(FOLLOW_THE_STORE, params, false)) {
            return httpResponse.body();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
