drop table if exists bcd_admin;

/*==============================================================*/
/* Table: bcd_admin                                             */
/*==============================================================*/
create table bcd_admin
(
   admin_id             int comment '主键id',
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
   pwd_error_time       datetime comment '最后一次输错密码的时间'
);

alter table bcd_admin comment '管理员表';

drop table if exists bcd_sys_menu;

/*==============================================================*/
/* Table: bcd_sys_menu                                          */
/*==============================================================*/
create table bcd_sys_menu
(
   menu_id              int comment '主键id',
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
   update_time          datetime comment '更新时间'
);

alter table bcd_sys_menu comment '系统菜单表（用于后台权限控制）';

drop table if exists bcd_admin_role;

/*==============================================================*/
/* Table: bcd_admin_role                                        */
/*==============================================================*/
create table bcd_admin_role
(
   role_id              int comment '主键id',
   role_name            varchar(64) comment '角色名称',
   role_desc            varchar(255) comment '角色描述',
   state                int comment '状态（0失效，1有效）',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间'
);

alter table bcd_admin_role comment '管理员角色表';


drop table if exists bcd_admin_role_ref;

/*==============================================================*/
/* Table: bcd_admin_role_ref                                    */
/*==============================================================*/
create table bcd_admin_role_ref
(
   admin_role_id        int comment '主键id',
   admin_id             int comment '管理员id',
   role_ids             varchar(255) comment '角色ids（用,分隔）',
   state                int comment '状态（0失效，1有效）',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间'
);

alter table bcd_admin_role_ref comment '管理员权限关联表';

drop table if exists bcd_role_menu_ref;

/*==============================================================*/
/* Table: bcd_role_menu_ref                                     */
/*==============================================================*/
create table bcd_role_menu_ref
(
   role_menu_id         int comment '主键id',
   role_id              int comment '角色id',
   menu_ids             varchar(255) comment '菜单ids(用,分隔)',
   state                int comment '状态',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间'
);

alter table bcd_role_menu_ref comment '角色系统菜单关联表';

drop table if exists bcd_admin_operate_log;

/*==============================================================*/
/* Table: bcd_admin_operate_log                                 */
/*==============================================================*/
create table bcd_admin_operate_log
(
   log_id               int comment '日志主键',
   admin_id             int comment '操作员id',
   admin_name           varchar(64) comment '操作员姓名',
   operator_type        int comment '操作类型（1增加，2修改，3删除，4查询）',
   state                int comment '操作状态（0失败，1成功）',
   ip                   varchar(64) comment 'ip地址',
   content              varchar(255) comment '操作内容',
   add_time             datetime comment '添加时间'
);

alter table bcd_admin_operate_log comment '管理员操作日志表';



