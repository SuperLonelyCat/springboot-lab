CREATE TABLE `account` (
    `uid` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'ID',
    `password` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
    `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
    `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
    `email` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '邮件',
    `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更改时间',
    KEY `auth_index` (`uid`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';