package com.ming.it.mongodb.service;

import com.ming.it.mongodb.constant.MongoConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author 11119638
 * @date 2021/3/24 19:33
 */
public interface MongoApi {

    Logger logger = LoggerFactory.getLogger(MongoApi.class);

    // 接口中的普通方法，可以被实现类集成
    default Update buildUpdatedData(Object object){

        final Update update = new Update();
        if (Objects.isNull(object)) {
            return update;
        }
        try {
            final Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                // 通过反射访问非public实例变量时，取消访问检查
                field.setAccessible(true);
                final Object fieldValue = field.get(object);
                if (Objects.isNull(fieldValue)) {
                    continue;
                }
                update.set(field.getName(), fieldValue);
            }
        } catch (Exception e) {
            logger.error("Failed to build updated data, exception: {}", e.getMessage());
        }
        return update;
    }

    default Query buildQuery() {

        return new Query();
    }

    default Criteria eqWithId(Object value) {

        return Criteria.where(MongoConstant.OBJECT_ID).is(value);
    }

    default Criteria eq(String field, Object value) {

        return Criteria.where(field).is(value);
    }

    default Criteria in(String field, Collection<?> values) {

        return Criteria.where(field).in(values);
    }

    default Criteria like(String field, Object value) {

        Pattern pattern = Pattern.compile("^.*" + value + ".*$", Pattern.CASE_INSENSITIVE);
        return Criteria.where(field).regex(pattern);
    }
}
