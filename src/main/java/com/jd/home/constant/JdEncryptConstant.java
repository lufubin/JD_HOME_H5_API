package com.jd.home.constant;

import java.nio.charset.StandardCharsets;

/**
 * 加密相关常量
 */
public interface JdEncryptConstant {
    /**
     * AES IV
     */
    String AES_IV = "";
    /**
     * AES KEY
     */
    String AES_KEY = "";

    /**
     * AES KEY
     */
    byte[] KEY_BYTE = AES_KEY.getBytes(StandardCharsets.UTF_8);

    /**
     * AES IV
     */
    byte[] IV_BYTE = AES_IV.getBytes(StandardCharsets.UTF_8);

    /**
     * HMAC 256加密密钥
     */
    String HMAC_256_KEY = "";

    /**
     * HMAC 256字节数组
     */
    byte[] HMAC_256_KEY_BYTE = HMAC_256_KEY.getBytes(StandardCharsets.UTF_8);

    /**
     * 加密模式
     */
    String MODE = "CBC";

    /**
     * 填充
     */
    String PADDING = "PKCS7Padding";
}
