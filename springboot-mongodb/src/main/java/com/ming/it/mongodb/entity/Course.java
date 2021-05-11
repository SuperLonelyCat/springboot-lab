package com.ming.it.mongodb.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 11119638
 * @date 2021/3/23 15:23
 */
@Data
@Builder
public class Course implements Serializable {

    private static final long serialVersionUID = 5092999568136047343L;

    /** MongoDB ObjectId */
    @Id
    private String id;

    /** 课程编码 */
    private String courseCode;

    /** 课程名称 */
    private String courseName;

    /** 教师工号 */
    private String teacherCode;

    /** 教师姓名 */
    private String teacherName;

    /** 创建时间 */
    private Date createAt;

    /** 更改时间 */
    private Date modifiedAt;
}
