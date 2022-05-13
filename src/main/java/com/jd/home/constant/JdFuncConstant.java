package com.jd.home.constant;

/**
 * 请求 FunctionId
 */
public interface JdFuncConstant {

    /**
     * 刷新Cookie
     */
    String REFRESH_COOKIE = "/group/item/list";

    /**
     * 获取城市
     */
    String GET_CITIES = "addresspdj/getCities";

    /**
     * 搜索详细地点
     */
    String ADDRESS_SEARCH = "address/search";

    /**
     * 获取我的收获地址
     */
    String GET_MY_HARVEST_ADDRESS = "addresspdj/getAddressList";

    /**
     * 添加收获地址
     */
    String ADD_ADDRESS = "addresspdj/addAddress";

    /**
     * 删除收获地址
     */
    String DEL_ADDRESS = "addresspdj/delAddress";

    /**
     * 获取热门城市 （需登录）
     */
    String GET_HOT_CITIES = "addresspdj/getHotCities";

    /**
     * 获取附近商家
     */
    String GET_NEARBY_BUSINESSES = "zone/recommendStoreListPost";

    /**
     * 获取筛选附近商家总数
     */
    String GET_NUM_FILTER_TAG = "zone/getNumFilterTag";

    /**
     * 搜索
     */
    String SUGGEST_LIST_POST = "suggest/listPost";

    /**
     * 搜索（店铺）
     */
    String STORE_SEARCH = "storeIndexSearch/searchPost";

    /**
     * 店铺详情
     */
    String STORE_DETAIL = "store/storeDetailV220Post";

    /**
     * 关注店铺
     */
    String FOLLOW_THE_STORE = "store/doFollow";

    /**
     * 获取店铺分类商品
     */
    String STORE_CATEGORY_PRODUCTS = "storeIndexSearch/searchByCategoryPost";

    /**
     * 获取店铺商品详情
     */
    String STORE_SHOP_DETAIL = "spuSaleAttr/spuSaleAttrListPost";

    /**
     * 获取优惠券
     */
    String GET_COUPONS = "coupon/stationCouponInfo";

    /**
     * 领取优惠券
     */
    String RECEIVE_COUPONS = "coupon/grabCoupon";

    /**
     * 加入购物车
     */
    String ADD_TO_THE_CART = "cartV3_3_0/changeItemNumPost";

    /**
     * 查看购物车(店铺)
     */
    String QUERY_SHOPPING_CART_BY_STORE = "cartV3_3_0/querySingleCart";

    /**
     * 查看购物车（全部）
     */
    String QUERY_SHOPPING_CART_BY_ALL = "cartV3_3_0/queryallcarts";

    /**
     * 清空购物车
     */
    String REMOVE_SHOPPING_CART = "cartV3_3_0/removeAllItems";

    /**
     * 查看订单
     */
    String QUERY_ORDER_LIST = "order/listV2.0";

    /**
     * 获取首页数据
     */
    String GET_INDEX = "indexh5/getIndex";

    /**
     * 获取首页分类数据
     */
    String GET_TYPE_INDEX = "zone/getNewChannelDetailPost";
}
