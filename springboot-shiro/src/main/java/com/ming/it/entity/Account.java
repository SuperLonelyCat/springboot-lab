package com.ming.it.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 11119638
 * @date 2021/6/28 17:08
 */
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = -8044927267596443878L;

    @NotBlank
    @TableId
    private String code;

    @NotBlank
    private String password;

    private String name;

    private String phone;

    private String email;

    private String roles;
}
