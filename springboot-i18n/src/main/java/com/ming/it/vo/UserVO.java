package com.ming.it.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 11119638
 * @date 2021/6/23 14:45
 */
@Data
public class UserVO {

    @NotBlank
    private String code;
    @NotBlank
    private String name;
}
