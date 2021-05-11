package com.ming.it.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 11119638
 * @date 2020/11/23 19:32
 */
@Data
@Builder // 默认创建全参构造器，而查询需提供无参构造器
// 两者缺一不可
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 2791899421419871727L;

    /** 设置ID自增，默认生成随机Long类型数据 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 编号 */
    private String code;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 邮件 */
    private String email;

    /** 属性与字段一一对应，排除非数据库属性 */
    @TableField(exist = false)
    private String extraField;

    /** 创建时间 */
    private Date createAt;

    /** 更改时间 */
    private Date modifiedAt;

    /* 日期时间不能用equal比较 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(code, user.code) && Objects.equals(name, user.name) && Objects.equals(age, user.age) && Objects.equals(email, user.email) && Objects.equals(extraField, user.extraField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, age, email, extraField);
    }
}
