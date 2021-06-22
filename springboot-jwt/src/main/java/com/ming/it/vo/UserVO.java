package com.ming.it.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 11119638
 * @date 2021/6/22 9:25
 */
@Data
public class UserVO {

    @NotBlank
    private String uid;

    @NotBlank
    private String password;
}
