package com.ming.it.mongodb.dto;

import lombok.Data;

/**
 * @author 11119638
 * @date 2021/3/27 9:43
 */
@Data
public class PageQueryDto {

    /** 页码 */
    private Integer pageNo;

    /** 页长 */
    private Integer pageSize;

    /** 页偏移量 */
    private Integer pageOffset;

    /** 排序字段 */
    private String orderStr;

    /** 关键字 */
    private String keyword;
}
