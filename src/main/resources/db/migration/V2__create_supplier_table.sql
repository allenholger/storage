-- 供货商表
-- 禁用外键约束
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_supplier
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '供货商的名字',
  `supplier_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '供货商的地址',
  `supplier_contact` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '供货商联系人',
  `supplier_telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '供货商联系方式',
  `creator` bigint NOT NULL DEFAULT 1 COMMENT '创建人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 1 COMMENT '修改人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账户表' ROW_FORMAT = Dynamic;

-- 启用外键约束
SET FOREIGN_KEY_CHECKS = 1;