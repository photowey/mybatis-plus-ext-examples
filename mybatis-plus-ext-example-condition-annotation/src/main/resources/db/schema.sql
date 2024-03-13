DROP TABLE IF EXISTS `sys_employee`;

CREATE TABLE `sys_employee`
(
    `id`          BIGINT PRIMARY KEY COMMENT '主键标识',
    `create_by`   BIGINT       NOT NULL COMMENT '创建人',
    `create_time` TIMESTAMP    NOT NULL COMMENT '创建时间',
    `update_by`   BIGINT       NOT NULL COMMENT '更新人',
    `update_time` TIMESTAMP    NOT NULL COMMENT '更新时间',
    `deleted`     TINYINT      NOT NULL COMMENT '删除标记',
    `employee_no` VARCHAR(32)  NOT NULL COMMENT '工号',
    `org_id`      BIGINT       NOT NULL COMMENT '隶属机构标识',
    `org_name`    VARCHAR(128) NOT NULL COMMENT '隶属机构名称',
    `order_no`    INT          NOT NULL COMMENT '序号',
    `status`      TINYINT      NOT NULL COMMENT '状态',
    `remark`      VARCHAR(128) NULL DEFAULT NULL COMMENT '备注'
);
