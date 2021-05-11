CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `code` varchar(64) DEFAULT NULL COMMENT '编号',
    `name` varchar(32) DEFAULT NULL COMMENT '姓名',
    `age` tinyint(4) unsigned DEFAULT NULL COMMENT '年龄',
    `email` varchar(32) DEFAULT NULL COMMENT '邮件',
    `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表'
