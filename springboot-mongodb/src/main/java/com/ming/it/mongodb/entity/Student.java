package com.ming.it.mongodb.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 11119638
 * @date 2021/3/23 15:02
 */
@Data
@Builder
public class Student implements Serializable {

    private static final long serialVersionUID = 3137763102492797227L;

    /** MongoDB ObjectId */
    @Id
    private String id;

    /** 学号 */
    private String studentCode;

    /** 学生姓名 */
    private String studentName;

    /** 课程编码 */
    private String courseCode;

    /** 是否启用 */
    private Boolean disabled;

    /** 创建时间 */
    private Date createAt;

    /** 更改时间 */
    private Date modifiedAt;
}
