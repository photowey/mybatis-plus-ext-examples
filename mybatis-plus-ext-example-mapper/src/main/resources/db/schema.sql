DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee`  (
  `id` bigint(20) PRIMARY KEY COMMENT '主键标识',
  `employee_no` varchar(128) NOT NULL COMMENT '工号',
  `org_id` bigint(20) NOT NULL COMMENT '隶属机构标识',
  `org_name` varchar(128) NOT NULL COMMENT '隶属机构名称',
  `order_no` int(11) NOT NULL COMMENT '序号',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `remark` varchar(128) NULL DEFAULT NULL COMMENT '备注',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新人',
  `deleted` tinyint(4) NOT NULL COMMENT '删除标记'
);