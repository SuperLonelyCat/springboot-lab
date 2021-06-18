package com.ming.it.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 11119638
 * @date 2021/6/18 14:33
 */
@Data
@Builder
// @Builder自带全参构造器，序列化与反序列化需提供无参构造器
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = -3812079143944696860L;

    private String id;

    private String name;

    private Integer accountLife;

    private BigDecimal accountBalance;

    private LocalDateTime createdTime;

    private Date updatedTime;
}
