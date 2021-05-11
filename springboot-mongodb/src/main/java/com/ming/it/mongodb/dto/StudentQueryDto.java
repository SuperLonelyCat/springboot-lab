package com.ming.it.mongodb.dto;

import lombok.Data;

/**
 * @author 11119638
 * @date 2021/3/27 11:08
 */
@Data
public class StudentQueryDto {

    /** 关键字 */
    private String keyword;

    /** 学号 */
    private String studentCode;

    /** 学生姓名 */
    private String studentName;

    /** 课程编码 */
    private String courseCode;
}
