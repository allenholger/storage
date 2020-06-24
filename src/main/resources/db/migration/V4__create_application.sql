-- 禁用外键约束
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_application
-- ----------------------------
DROP TABLE IF EXISTS `t_application`;
CREATE TABLE `t_application`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `application_name` varchar(64) NOT NULL COMMENT '应用的名字',
  `application_code` varchar(64) NOT NULL COMMENT '应用的标识码',
  `app_key` varchar(32) NULL COMMENT '应用的Key',
  `app_secret` varchar(64) NULL COMMENT '应用的密匙',
  `client_id` varchar(32) NULL COMMENT '客户端的ID',
  `application_url` varchar(64) NULL COMMENT '应用部署的网址',
  `creator` bigint NOT NULL DEFAULT 0 COMMENT '创建人的ID（由于是系统自动创建的，所以其默认值均为0）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 0 COMMENT '修改人的ID（由于是系统自动创建的，所以其默认值均为0）',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `application_code_index`(`application_code`) COMMENT '每一个应用都有一个唯一的标识码' USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用表' ROW_FORMAT = Dynamic;

-- 启用外键约束
SET FOREIGN_KEY_CHECKS = 1;