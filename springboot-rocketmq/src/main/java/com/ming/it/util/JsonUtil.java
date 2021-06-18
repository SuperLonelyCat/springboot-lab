package com.ming.it.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author 11119638
 * @date 2021/6/18 14:40
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 忽略Java对象中值为null的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 可在对象属性上添加@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")注解完成格式转换
        // 取消Date序列化被转换为时间戳
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 设置Date序列化时区，默认为GMT格林威治时间
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // 时间模块配置
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 更改LocalDateTime序列化格式
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 配置LocalDateTime序列化格式，要不然反序列化报错
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 注册时间模块
        objectMapper.registerModule(javaTimeModule);
    }

    private JsonUtil(){
        // 私有构造器
    }

    public static String object2Json(Object object) {
        if (Objects.isNull(object)) {
            return StringUtils.EMPTY;
        }
        String json = StringUtils.EMPTY;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Object to Json, Exception - {}", ExceptionUtils.getStackTrace(e));
        }
        return json;
    }

    public static <T> T json2Object(String json, Class<T> c) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        T t = null;
        try {
            t = objectMapper.readValue(json, c);
        } catch (Exception e) {
            log.error("Json to Object, Exception - {}", ExceptionUtils.getStackTrace(e));
        }
        return t;
    }
}
