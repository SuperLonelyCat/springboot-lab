package com.ming.it.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author 11119638
 * @date 2021/6/30 10:48
 */
// 创建文档模型并制定索引库
@Document(indexName = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 2334036878862577675L;

    // 定义主键
    @Id
    private String uid;

    @Field(type = FieldType.Text)   // 可省略
    private String name;

    @Field(type = FieldType.Integer)   // 可省略
    private Integer age;

    @Field(type = FieldType.Text)   // 可省略
    private String address;

    public User(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }
}
