package com.ming.it.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author 11119638
 * @date 2021/6/22 12:00
 */
@Slf4j
public class AESUtil {

    /** 密钥长度 */
    private static final int KEY_LENGTH_16 = 16;

    /** 算法/加密/填充 */
    private static final String ALGORITHM_PADDING = "AES/CBC/PKCS5Padding";

    /** 算法名称 */
    private static final String ALGORITHM = "AES";

    private AESUtil() {
        // 构造器私有
    }

    /**
     * 加密
     * @param content 待加密内容
     * @param key 密钥
     * @param ivs 偏移量
     * @return 加密后内容
     */
    public static String encrypt(String content, String key, String ivs) {
        if (Objects.isNull(key) || !Objects.equals(KEY_LENGTH_16, key.length())) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), ALGORITHM),
                    new IvParameterSpec(ivs.getBytes()));
            return Base64Utils.encodeToString(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.warn("AES Encrypt Exception - {}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * 解密
     * @param content 待解密内容
     * @param key 密钥
     * @param ivs 偏移量
     * @return 解密后内容
     */
    public static String decrypt(String content, String key, String ivs) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.US_ASCII), ALGORITHM),
                    new IvParameterSpec(ivs.getBytes()));
            byte[] encryptedData = Base64Utils.decodeFromString(content);
            return encryptedData.length < NumberUtils.INTEGER_ONE ? null :
                    new String(cipher.doFinal(encryptedData), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("AES Decrypt Exception - {}", ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
}
