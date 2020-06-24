-- 禁用外键约束
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(64) NOT NULL COMMENT '请求获取到的code',
  `authentication` blob NULL COMMENT '认证的内容',
  `creator` bigint NOT NULL DEFAULT 0 COMMENT '创建人的ID（由于是系统自动创建的，所以其默认值均为0）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 0 COMMENT '修改人的ID（由于是系统自动创建的，所以其默认值均为0）',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '认证的code表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `token_id` varchar(255) NULL COMMENT 'token ID',
  `token` blob NULL COMMENT 'token, 在MySQL数据库中，blob有四种类型：tinyblob仅255个字符；blob最大限制到65K字节；mediumblob限制到16M字节；longblob可达到4GB',
  `authentication_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '授权ID',
  `user_name` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户名，其实体对应的是账户名，也就是手机号',
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '客户端的ID',
  `creator` bigint NOT NULL DEFAULT 0 COMMENT '创建人的ID（由于是系统自动创建的，所以其默认值均为0）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 0 COMMENT '修改人的ID（由于是系统自动创建的，所以其默认值均为0）',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_authentication_id` (`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '认证的客户端token表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `token_id` varchar(255) NULL COMMENT 'tokenID',
  `token` blob NULL COMMENT 'token, 在MySQL数据库中，blob有四种类型：tinyblob仅255个字符；blob最大限制到65K字节；mediumblob限制到16M字节；longblob可达到4GB',
  `authentication_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '认证ID',
  `user_name` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户名，在此处对应的是账户名，也就是手机号',
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '客户端ID',
  `authentication` blob NULL COMMENT '认证',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '刷新的token',
  `creator` bigint NOT NULL DEFAULT 1 COMMENT '创建人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 1 COMMENT '修改人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_authentication_id`(`authentication_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '认证的accessToken表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `token_id` varchar(255) NOT NULL COMMENT 'token ID',
  `token` blob NULL COMMENT 'token, 在MySQL数据库中，blob有四种类型：tinyblob仅255个字符；blob最大限制到65K字节；mediumblob限制到16M字节；longblob可达到4GB',
  `authentication` blob NULL COMMENT '认证的内容',
  `creator` bigint NOT NULL DEFAULT 0 COMMENT '创建人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 0 COMMENT '修改人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '刷新accessToken表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `client_id` varchar(64) NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '客户端密匙',
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '资源的ID',
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '授权的范围',
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '认证的授权类型',
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '重定向的URL',
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '权限',
  `access_token_validity` int(11) NULL COMMENT 'accessToken的有限时间',
  `refresh_token_validity` int(11) NULL COMMENT '刷新Token的有限时间',
  `additional_information` blob NULL COMMENT '额外信息',
  `autoapprove` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '自定认证，值为true或者false',
  `creator` bigint NOT NULL DEFAULT 1 COMMENT '创建人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 1 COMMENT '修改人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_client_id` (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '认证的客户端详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `client_details`;
CREATE TABLE `client_details`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `appId` varchar(64) NOT NULL COMMENT '应用的ID',
  `resourceIds` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '资源的ID列表',
  `appSecret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '应用的密匙',
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '授权的范围',
  `grantTypes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '授权的类型',
  `redirectUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '重定向的URL',
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '授权列表',
  `access_token_validity` int(11) NULL COMMENT 'access_token的有效时长',
  `refresh_token_validity` int(11) NULL COMMENT 'refresh_access_token的时长',
  `additional_information` blob NULL COMMENT '额外信息',
  `autoApproveScopes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '自动认证范围',
  `creator` bigint NOT NULL DEFAULT 1 COMMENT '创建人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 1 COMMENT '修改人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_appId` (`appId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户端详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`(
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `expiresAt` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL COMMENT '过期时间',
  `status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `lastModifiedAt` timestamp NULL COMMENT '上次修改的时间',
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端的ID',
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权范围',
  `creator` bigint NOT NULL DEFAULT 1 COMMENT '创建人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `modifier` bigint NOT NULL DEFAULT 1 COMMENT '修改人的ID（其对应用户信息表的主键ID），默认值是管理员的ID',
  `modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改的时间',
  `validation` int NOT NULL DEFAULT 0 COMMENT '数据的有效性（0：有效；1：无效），默认值为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '自动认证表' ROW_FORMAT = Dynamic;

-- 启用外键约束
SET FOREIGN_KEY_CHECKS = 1;