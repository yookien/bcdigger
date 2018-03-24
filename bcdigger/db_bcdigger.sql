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