drop table if exists bcd_admin;

/*==============================================================*/
/* Table: bcd_admin                                             */
/*==============================================================*/
create table bcd_admin
(
   id             int comment '主键id',
   name                 varchar(64) comment '名称',
   nickname             varchar(64) comment '昵称',
   password             varchar(64) comment '密码',
   sex                  varchar(32) comment '性别',
   age                  int comment '年龄',
   duty                 varchar(32) comment '职务',
   mobile               varchar(32) comment '移动电话',
   telephone            varchar(32) comment '固定电话',
   email                varchar(64) comment '电子邮件',
   address              varchar(256) comment '地址',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   open_id              varchar(64) comment '微信openid',
   entry_date           datetime comment '入职时间',
   user_level           int comment '用户等级',
   department_id        int comment '部门',
   state                int comment '状态',
   pwd_error_count      int comment '连续输错密码的次数',
   pwd_error_time       datetime comment '最后一次输错密码的时间',
   primary key (id)
);

ALTER TABLE `bcd_admin` ADD UNIQUE INDEX `name` (`name`) ;
alter table bcd_admin comment '管理员表';

drop table if exists bcd_sys_menu;

/*==============================================================*/
/* Table: bcd_sys_menu                                          */
/*==============================================================*/
create table bcd_sys_menu
(
   id              int comment '主键id',
   menu_name            varchar(64) comment '菜单名称',
   menu_url             varchar(255) comment '菜单url',
   menu_logo_url        varchar(255) comment '菜单图标url',
   menu_desc            varchar(255) comment '菜单描述',
   parent_id            int comment '父节点id',
   is_leaf              int comment '是否是子节点（0父节点，1子节点）',
   level                int comment '层级：一级菜单，二级菜单',
   state                int comment '状态（0,失效，1正常）',
   display_order        int comment '显示顺序',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table bcd_sys_menu comment '系统菜单表（用于后台权限控制）';

drop table if exists bcd_admin_role;

/*==============================================================*/
/* Table: bcd_admin_role                                        */
/*==============================================================*/
create table bcd_admin_role
(
   id              int comment '主键id',
   role_name            varchar(64) comment '角色名称',
   role_desc            varchar(255) comment '角色描述',
   state                int comment '状态（0失效，1有效）',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table bcd_admin_role comment '管理员角色表';


drop table if exists bcd_admin_role_ref;

/*==============================================================*/
/* Table: bcd_admin_role_ref                                    */
/*==============================================================*/
create table bcd_admin_role_ref
(
   id        int comment '主键id',
   admin_id             int comment '管理员id',
   role_ids             varchar(255) comment '角色ids（用,分隔）',
   state                int comment '状态（0失效，1有效）',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

ALTER TABLE `bcd_admin_role_ref` ADD INDEX `adminId` (`admin_id`) ;
alter table bcd_admin_role_ref comment '管理员权限关联表';

drop table if exists bcd_role_menu_ref;

/*==============================================================*/
/* Table: bcd_role_menu_ref                                     */
/*==============================================================*/
create table bcd_role_menu_ref
(
   id         int comment '主键id',
   role_id              int comment '角色id',
   menu_ids             varchar(255) comment '菜单ids(用,分隔)',
   state                int comment '状态',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

ALTER TABLE `bcd_role_menu_ref`ADD INDEX `roleId` (`role_id`) ;
alter table bcd_role_menu_ref comment '角色系统菜单关联表';

drop table if exists bcd_admin_operate_log;

/*==============================================================*/
/* Table: bcd_admin_operate_log                                 */
/*==============================================================*/
create table bcd_admin_operate_log
(
   id               int comment '日志主键',
   admin_id             int comment '操作员id',
   admin_name           varchar(64) comment '操作员姓名',
   operator_type        int comment '操作类型（1增加，2修改，3删除，4查询）',
   state                int comment '操作状态（0失败，1成功）',
   ip                   varchar(64) comment 'ip地址',
   content              varchar(255) comment '操作内容',
   add_time             datetime comment '添加时间',
   primary key (id)
);

ALTER TABLE `bcd_admin_operate_log` ADD INDEX `adminId` (`admin_id`) ;
alter table bcd_admin_operate_log comment '管理员操作日志表';




drop table if exists bcd_post_category;

/*==============================================================*/
/* Table: bcd_post_category                                     */
/*==============================================================*/
create table bcd_post_category
(
   id                   int not null comment '主键id',
   name                 varchar(255) comment '分类名称',
   all_name             varchar(255) comment '分类全称',
   display_order        int comment '显示顺序',
   parent_id            int comment '父id',
   right_id             int comment '右节点id',
   left_id              int comment '左节点id',
   post_count           int comment '文章总数',
   state                int comment '状态',
   add_time             int comment '添加时间',
   update_time          int comment '更新时间',
   category_desc        varchar(255) comment '描述',
   primary key (id)
);

alter table bcd_post_category comment '文章分类表';

drop table if exists bcd_post;

/*==============================================================*/
/* Table: bcd_post                                              */
/*==============================================================*/
create table bcd_post
(
   id                   int not null comment '主键id',
   post_author_id       int comment '文章作者id',
   post_source          varchar(255) comment '文章来源',
   post_date            bigint comment '发布时间',
   post_content         text comment '文章内容',
   post_title           varchar(255) comment '文章标题',
   post_excerpt         text comment '文章摘要',
   post_status          int comment '文章状态',
   comment_status       int comment '评论状态',
   ping_status          int,
   post_passwd          varchar(255) comment '文章密码',
   post_name            varchar(255) comment '文章缩略名',
   post_update_date     int comment '修改时间',
   post_parent          int comment '父文章',
   guid                 varchar(64) comment '唯一标识',
   display_order        int comment '排序id',
   post_type            int comment '文章类型',
   comment_count        int comment '评论数',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table bcd_post comment '文章';


drop table if exists bcd_post_tag;

/*==============================================================*/
/* Table: bcd_post_tag                                          */
/*==============================================================*/
create table bcd_post_tag
(
   id                   int not null comment '主键id',
   tag_name             varchar(64) comment '名称',
   state                int comment '状态',
   display_order        int comment '显示位置',
   type                 varchar(64) comment '类型',
   primary key (id)
);

alter table bcd_post_tag comment '文章标签表';

drop table if exists bcd_users;

/*==============================================================*/
/* Table: bcd_users                                             */
/*==============================================================*/
create table bcd_users
(
   id                   int not null comment '主键id',
   user_name            varchar(255) comment '用户名',
   user_passwd          varchar(255) comment '用户密码',
   user_nicename        varchar(255) comment '用户昵称',
   user_email           varchar(255) comment '用户email',
   user_url             varchar(255) comment '用户主页',
   user_register_date   datetime comment '注册时间',
   user_activation_key  varchar(32) comment '激活码',
   user_status          int comment '用户状态',
   display_name         varchar(255) comment '显示名称',
   user_last_login_date datetime comment '最后登录时间',
   qq_uid               varchar(255) comment 'qq',
   weixin_uid           varchar(255) comment '微信',
   alipay_uid           varchar(255) comment '支付宝',
   primary key (id)
);

alter table bcd_users comment '用户表';




