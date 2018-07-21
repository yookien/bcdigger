SET GLOBAL max_user_connections = 1000; 
drop table if exists bcd_admin;

/*==============================================================*/
/* Table: bcd_admin                                             */
/*==============================================================*/
create table bcd_admin
(
   id             int AUTO_INCREMENT comment '主键id',
   store_id		  int comment '所属门店id',		
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

insert into bcd_admin(`name`,nickname,`password`,add_time,update_time,state,store_id)
values
('王朝文','王朝文','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('徐湘平','徐湘平','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('梁青敏','梁青敏','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('曾雅婷','曾雅婷','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('陈颖','陈颖','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('付芒','付芒','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('蔡茜城','蔡茜城','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('张大庄','张大庄','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('李萍','李萍','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('TEST','TEST','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,1),
('NXHBBJ0001','北京奈雪西单大悦城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,58),
('NXHBBJ0002','北京奈雪西直门凯德店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,59),
('NXXNCD0002','成都奈雪万象城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,65),
('TGXNCD0002','成都台盖锦华万达广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,183),
('TGXNCD0001','成都台盖九方店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,182),
('NXHNDG0001','东莞奈雪汇一城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,74),
('TGHNDG0001','东莞台盖海德广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,188),
('NXHNFS0001','佛山奈雪岭南天地店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,76),
('NXHNFS0002','佛山奈雪中海环宇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,77),
('TGHNFZ0001','福州台盖东二环泰禾广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,189),
('NXHNGZ0003','广州奈雪花城汇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,81),
('NXHNGZ0007','广州奈雪基盛万科店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,85),
('NXHNGZ0010','广州奈雪乐峰广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,88),
('NXHNGZ0004','广州奈雪漫广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,82),
('NXHNGZ0008','广州奈雪同和金铂广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,86),
('NXHNGZ0001','广州奈雪万菱汇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,79),
('NXHNGZ0002','广州奈雪正佳广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,80),
('NXHNGZ0005','广州奈雪珠影星光城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,83),
('TGHNGZ0002','广州台盖k11店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,191),
('TGHNGZ0001','广州台盖百信广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,190),
('TGHNGZ0004','广州台盖基盛万科店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,193),
('NXHDHZ0004','杭州奈雪滨江宝龙店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,94),
('NXHDHZ0002','杭州奈雪大厦中央广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,92),
('NXHDHZ0003','杭州奈雪湖滨银泰店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,93),
('NXHDHZ0001','杭州奈雪万象城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,91),
('NXHDHZ0005','杭州奈雪萧山万象汇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,95),
('TGHDHZ0001','杭州台盖中央广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,194),
('NXHDNJ0001','南京奈雪德基广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,97),
('NXHDNJ0004','南京奈雪东方福来德店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,100),
('NXHDNJ0003','南京奈雪河西金鹰店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,99),
('NXHDNJ0002','南京奈雪仙林万达店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,98),
('TGHDNJ0003','南京台盖德基广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,199),
('TGHDNJ0002','南京台盖仙林万达店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,198),
('TGHNXM0002','厦门台盖中华城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,221),
('NXHDSH0004','上海奈雪环宇荟店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,104),
('NXHDSH0001','上海奈雪市百一店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,101),
('NXHDSH0002','上海奈雪长风大悦城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,102),
('TGHDSH0002','上海台盖广元西路店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,270),
('TGHDSH0004','上海台盖国金中心店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,272),
('TGHDSH0006','上海台盖静安大悦城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,205),
('TGHDSH0003','上海台盖友谊南方商城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,271),
('TGHDSH0001','上海台盖颛桥万达店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,269),
('LSHNSZ0002','深圳梨山海岸城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,12),
('LSHNSZ0001','深圳梨山天安云谷店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,11),
('NXHNSZ0024','深圳奈雪KKmall店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,39),
('NXHNSZ0027','深圳奈雪kkone店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,42),
('NXHNSZ0011','深圳奈雪八号仓店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,26),
('NXHNSZ0028','深圳奈雪布吉万象汇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,43),
('NXHNSZ0036','深圳奈雪大梅沙店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,51),
('NXHNSZ0048','深圳奈雪福田cocopark店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,115),
('NXHNSZ0005','深圳奈雪福田东海店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,20),
('NXHNSZ0034','深圳奈雪福田马成时代广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,49),
('NXHNSZ0001','深圳奈雪福田卓越店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,16),
('NXHNSZ0013','深圳奈雪海岸城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,28),
('NXHNSZ0010','深圳奈雪海雅缤纷城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,25),
('NXHNSZ0009','深圳奈雪后海君尚店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,24),
('NXHNSZ0017','深圳奈雪花园城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,32),
('NXHNSZ0003','深圳奈雪华强北九方店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,18),
('NXHNSZ0040','深圳奈雪华强北茂业店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,55),
('NXHNSZ0014','深圳奈雪华润万象天地店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,29),
('NXHNSZ0002','深圳奈雪欢乐海岸店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,17),
('NXHNSZ0012','深圳奈雪汇一城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,27),
('NXHNSZ0020','深圳奈雪金光华店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,35),
('NXHNSZ0023','深圳奈雪科技生态园店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,38),
('NXHNSZ0015','深圳奈雪来福士店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,30),
('NXHNSZ0038','深圳奈雪龙岗海航城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,53),
('NXHNSZ0026','深圳奈雪龙华九方店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,41),
('NXHNSZ0019','深圳奈雪绿景虹湾店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,34),
('NXHNSZ0008','深圳奈雪梅林卓悦汇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,23),
('NXHNSZ0006','深圳奈雪南山大冲1期店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,21),
('NXHNSZ0018','深圳奈雪南山京基店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,33),
('NXHNSZ0004','深圳奈雪南山茂业店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,19),
('NXHNSZ0042','深圳奈雪南山太古城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,57),
('NXHNSZ0025','深圳奈雪前海周大福店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,40),
('NXHNSZ0030','深圳奈雪沙井京基店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,45),
('NXHNSZ0043','深圳奈雪沙井天虹店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,110),
('NXHNSZ0039','深圳奈雪深业上城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,54),
('NXHNSZ0035','深圳奈雪天安云谷店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,50),
('NXHNSZ0033','深圳奈雪万科龙城广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,48),
('NXHNSZ0029','深圳奈雪万科天誉广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,44),
('NXHNSZ0022','深圳奈雪新城市广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,37),
('NXHNSZ0052','深圳奈雪雅宝cocopark店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,119),
('NXHNSZ0016','深圳奈雪壹方城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,31),
('NXHNSZ0007','深圳奈雪中心城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,22),
('TGHNSZ0039','深圳台盖布吉万象汇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,211),
('TGHNSZ0035','深圳台盖创投大厦超级物种店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,207),
('TGHNSZ0034','深圳台盖福田cocopark店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,181),
('TGHNSZ0006','深圳台盖福田东海店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,153),
('TGHNSZ0036','深圳台盖福田丰盛町店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,208),
('TGHNSZ0027','深圳台盖观澜湖店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,174),
('TGHNSZ0024','深圳台盖国创中心店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,171),
('TGHNSZ0010','深圳台盖海岸城旗舰店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,157),
('TGHNSZ0016','深圳台盖花园城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,163),
('TGHNSZ0041','深圳台盖华南城奥特莱斯店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,213),
('TGHNSZ0003','深圳台盖华强北九方店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,150),
('TGHNSZ0029','深圳台盖华强茂业店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,176),
('TGHNSZ0017','深圳台盖欢乐海岸店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,164),
('TGHNSZ0023','深圳台盖皇庭广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,170),
('TGHNSZ0012','深圳台盖汇一城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,159),
('TGHNSZ0014','深圳台盖金光华店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,161),
('TGHNSZ0022','深圳台盖科技生态园店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,169),
('TGHNSZ0018','深圳台盖来福士店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,165),
('TGHNSZ0004','深圳台盖乐淘里店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,151),
('TGHNSZ0033','深圳台盖龙岗海航城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,180),
('TGHNSZ0001','深圳台盖龙华cococity店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,148),
('TGHNSZ0026','深圳台盖龙华九方店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,173),
('TGHNSZ0015','深圳台盖绿景虹湾店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,162),
('TGHNSZ0005','深圳台盖绿景香颂店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,152),
('TGHNSZ0007','深圳台盖梅林卓悦汇店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,154),
('TGHNSZ0020','深圳台盖民治天虹店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,167),
('TGHNSZ0009','深圳台盖南山茂业店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,156),
('TGHNSZ0032','深圳台盖天安云谷店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,179),
('TGHNSZ0013','深圳台盖同泰广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,160),
('TGHNSZ0030','深圳台盖万科天誉广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,177),
('TGHNSZ0008','深圳台盖新沙天虹店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,155),
('TGHNSZ0042','深圳台盖雅宝cocopark店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,214),
('TGHNSZ0021','深圳台盖壹方城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,168),
('TGHNSZ0025','深圳台盖益田假日店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,172),
('TGHNSZ0019','深圳台盖中心城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,166),
('XNHNSZ0007','深圳喜年贡茶八号仓店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,231),
('XNHNSZ0012','深圳喜年贡茶大浪店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,236),
('NXHZWH0003','武汉奈雪光谷K11店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,122),
('NXHZWH0002','武汉奈雪汉街万达店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,121),
('NXHZWH0005','武汉奈雪菱角湖万达店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,124),
('NXHZWH0001','武汉奈雪新天地店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,120),
('NXHZWH0004','武汉奈雪宜家荟聚店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,123),
('TGHZWH0003','武汉台盖M+世纪都会广场店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,218),
('TGHZWH0001','武汉台盖壹方购物中心店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,216),
('NXXBXA0003','西安奈雪SKP店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,129),
('NXXBXA0001','西安奈雪高新万达店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,127),
('NXXBXA0002','西安奈雪益田假日店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,128),
('TGXBXA0001','西安台盖赛格店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,219),
('NXHZCS0002','长沙奈雪I city店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,72),
('NXHZCS0001','长沙奈雪梅溪湖店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,71),
('TGHZCS0001','长沙台盖国金中心店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,187),
('NXXNCQ0002','重庆奈雪万象城店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,68),
('NXXNCQ0003','重庆奈雪源著天街店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,69),
('TGXNCQ0001','重庆台盖北城天街店','646826576E5AB3C65D9FC4327E8B100C',now(),now(),1,185);


drop table if exists bcd_sys_menu;

/*==============================================================*/
/* Table: bcd_sys_menu                                          */
/*==============================================================*/
create table bcd_sys_menu
(
   id              int AUTO_INCREMENT comment '主键id',
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
   id              int AUTO_INCREMENT comment '主键id',
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
   id        int AUTO_INCREMENT comment '主键id',
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
   id         int AUTO_INCREMENT comment '主键id',
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
   id               int AUTO_INCREMENT comment '日志主键',
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
   id                   int not null AUTO_INCREMENT comment '主键id',
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
   id                   int not null AUTO_INCREMENT comment '主键id',
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
   id                   int not null AUTO_INCREMENT comment '主键id',
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
   id                   int not null AUTO_INCREMENT comment '主键id',
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


drop table if exists bcd_department;

/*==============================================================*/
/* Table: bcd_department                                        */
/*==============================================================*/

create table bcd_department
(
   id                   int not null AUTO_INCREMENT comment '主键id',
   name                 varchar(0) comment '部门名称',
   admin_id             int comment '负责人',
   parent_id            int comment '父id',
   left_id              int comment '左节点id',
   right_id             int comment '右节点id',
   display_order        int comment '显示顺序',
   add_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   state                int comment '状态',
   primary key (id)
);

alter table bcd_department comment '(公司部门表)';



drop table if exists bcd_store;

/*==============================================================*/
/* Table: bcd_store                                             */
/*==============================================================*/

CREATE TABLE `bcd_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `store_code` varchar(50) DEFAULT NULL COMMENT '门店编码',
  `chinese_name` varchar(50) DEFAULT NULL COMMENT '中文名称',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(100) DEFAULT NULL COMMENT '负责人手机',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `direction` varchar(255) DEFAULT NULL COMMENT '地区',
  `open_hour` varchar(10) DEFAULT NULL COMMENT '开业时间',
  `close_hour` varchar(10) DEFAULT NULL COMMENT '停业时间',
  `english_name` varchar(50) DEFAULT NULL,
  `english_address` varchar(255) DEFAULT NULL,
  `location_image` varchar(100) DEFAULT NULL COMMENT '位置示意图',
  `store_images` varchar(500) DEFAULT NULL COMMENT '门店主图',
  `store_province` varchar(50) DEFAULT NULL COMMENT '门店所属省',
  `store_city` varchar(50) DEFAULT NULL COMMENT '门店所属城市名',
  `city_area` varchar(50) DEFAULT NULL COMMENT '城市下的区域',
  `is_open` tinyint(4) DEFAULT '1' COMMENT '是否在开',
  `bmap_position` varchar(25) DEFAULT NULL COMMENT '百度位置',
  `bmap_positionX` decimal(12,6) DEFAULT NULL COMMENT '百度x位置',
  `bmap_positionY` decimal(12,6) DEFAULT NULL COMMENT '百度y位置',
  `search_url` varchar(100) DEFAULT NULL COMMENT '搜索地址',
  `bus` varchar(100) DEFAULT NULL COMMENT '公交情况',
  `subway` varchar(50) DEFAULT NULL COMMENT '地铁情况',
  `nearby` varchar(80) DEFAULT NULL COMMENT '附近建筑',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `store_image1` varchar(100) DEFAULT NULL COMMENT '店面图片1',
  `store_image2` varchar(100) DEFAULT NULL COMMENT '店面图片2',
  `store_image3` varchar(100) DEFAULT NULL COMMENT '店面图片3',
  `store_image4` varchar(100) DEFAULT NULL COMMENT '店面图片4',
  `appoint_limit` int(11) unsigned DEFAULT '0' COMMENT '预约限制',
  `store_type` int(10) unsigned DEFAULT '0' COMMENT '门店类型：0-自营店，1-写字楼店，2-商场店，3-加盟店',
  `is_booking` int(11) DEFAULT '0' COMMENT '是否预约',
  `open_time` datetime DEFAULT NULL COMMENT '开店时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `kingdee_cust_id` int(11) DEFAULT '0',
  `receive_stock_id`  int(11) NULL DEFAULT 0 COMMENT '收货仓库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店表'

alter table bcd_store comment '门店表';


drop table if exists bcd_goods_category;

/*==============================================================*/
/* Table: bcd_goods_category                                    */
/*==============================================================*/
create table bcd_goods_category
(
   id                   int not null AUTO_INCREMENT comment '主键id',
   cat_name             varchar(64) comment '分类名称',
   cat_desc             varchar(255) comment '描述',
   state                int comment '状态',
   left_id              int comment '左节点id',
   right_id             int comment '右节点id',
   display_order        int comment '显示顺序',
   parent_id            int comment '父节点id',
   inner_name           varchar(255) comment '全称',
   cat_image            varchar(255) comment '分类图片',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table bcd_goods_category comment '商品分类表';

drop table if exists bcd_goods;

/*==============================================================*/
/* Table: bcd_goods                                             */
/*==============================================================*/
CREATE TABLE `bcd_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cat_id` int(11) DEFAULT NULL COMMENT '分类id',
  `goods_no` varchar(32) DEFAULT NULL COMMENT '商品货号',
  `goods_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `state` int(11) DEFAULT NULL COMMENT '状态 0不可用 1可用',
  `small_image` varchar(64) DEFAULT NULL COMMENT '商品小图',
  `middle_image` varchar(64) DEFAULT NULL COMMENT '商品大图',
  `goods_image` varchar(64) DEFAULT NULL COMMENT '商品主图',
  `weight` double DEFAULT NULL COMMENT '重量',
  `unit` varchar(32) DEFAULT NULL COMMENT '单位',
  `model` varchar(64) DEFAULT NULL COMMENT '规格型号',
  `inner_name` varchar(255) DEFAULT NULL COMMENT '全名',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `price` int(11) DEFAULT NULL COMMENT '售价',
  `market_price` int(11) DEFAULT NULL COMMENT '市场价',
  `pur_price` int(11) DEFAULT NULL COMMENT '采购价',
  `goods_desc` text COMMENT '商品描述',
  `display_order` int(11) DEFAULT NULL COMMENT '显示顺序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `kingdee_cust_id` int(11) DEFAULT '0',
  `unit_cust_id`  int(11) DEFAULT '0',
  `default_vendor`  int(11) NULL DEFAULT 0 COMMENT '供应商',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

alter table bcd_goods comment '商品表';

drop table if exists bcd_goods_repository;

/*==============================================================*/
/* Table: bcd_goods_repository                                  */
/*==============================================================*/
create table bcd_goods_repository
(
   id                   int not null AUTO_INCREMENT comment '主键id',
   name                 varchar(64) comment '名称',
   store_id             int comment '所属店铺id',
   type                 int comment '类型
            5601 正常仓
            5602 辅料仓
            5603 退货仓',
   address              varchar(255) comment '地址',
   is_use               int comment '是否可用',
   capacity             int comment '容量',
   quantity             int comment '目前库存数',
   operator             int comment '管理人',
   state                int comment '状态',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table bcd_goods_repository comment '仓库表';

drop table if exists bcd_goods_store;

/*==============================================================*/
/* Table: bcd_goods_store                                       */
/*==============================================================*/
create table bcd_goods_store
(
   id                   int not null AUTO_INCREMENT comment '主键id',
   goods_id             int comment '商品id',
   repository_id        int comment '仓库id',
   store_id             int comment '店铺id',
   quantity             int comment '数量',
   perfect_quantity     int comment '理论库存',
   alert_quantity       int comment '预警库存',
   state                int comment '状态',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table bcd_goods_store comment '商品库存表';


drop table if exists bcd_goods_instore;

/*==============================================================*/
/* Table: bcd_goods_instore                                     */
/*==============================================================*/
create table bcd_goods_instore
(
   id                   int not null auto_increment comment '主键id',
   goods_order_id       int comment '订货单id',
   goods_order_item_id  int comment '订货明细id',
   goods_id             int comment '商品id',
   batch_no             varchar(64) comment '批次号',
   store_id             int comment '店铺id',
   repository_id        int comment '仓库id',
   state                int comment '状态,0待入库，1已审核，2审核不通过',
   pur_price            int comment '采购价',
   type                 int comment '入库类型 
            5000正常采购入库
            5001盘点入库
            5002退货入库
            5003调仓入库',
   in_quantity          int comment '入库数量',
   in_store_time        datetime comment '入库时间',
   add_time        datetime comment '添加时间',
   update_time        datetime comment '更新时间',
   operator             int comment '操作人',
   memo                 varchar(255) comment '备注',
   source_id            int comment '入库来源 0pc端 1手机端 2paid 3其他',
   primary key (id)
);

alter table bcd_goods_instore comment '商品入库明细表';


drop table if exists bcd_goods_outstore;

/*==============================================================*/
/* Table: bcd_goods_outstore                                    */
/*==============================================================*/
create table bcd_goods_outstore
(
   id                   int not null AUTO_INCREMENT comment '主键id',
   goods_id             int comment '商品id',
   batch_no             varchar(64) comment '出库批次',
   store_id             int comment '店铺id',
   type                 int comment '出库类型
            5100 正常出库
            5101 盘点出库
            5102 调仓出库',
   repository_id        int comment '出库仓库',
   out_quantity         int comment '出库数量',
   out_store_time       datetime comment '出库时间',
   operator             int comment '操作人',
   memo                 varchar(255) comment '备注',
   state                int comment '状态',
   source_id            int comment '出库来源 0pc端 1手机端 2paid 3其他',
   primary key (id)
);

alter table bcd_goods_outstore comment '商品出库表';

drop table if exists bcd_goods_purchase;

/*==============================================================*/
/* Table: bcd_goods_purchase                                    */
/*==============================================================*/
create table bcd_goods_purchase
(
   id                   int not null AUTO_INCREMENT comment '主键id',
   batch_no             varchar(0) comment '批次号',
   type                 int comment '采购类型',
   store_id             int comment '店铺id',
   operator             int comment '操作人',
   sum_quantity         int comment '采购总数量',
   memo                 varchar(0) comment '备注',
   state                int comment '状态',
   add_time             datetime comment '添加时间',
   update_time          datetime comment '更新时间',
   primary key (id)
);

alter table bcd_goods_purchase comment '商品采购表';

drop table if exists bcd_goods_purchase_item;

/*==============================================================*/
/* Table: bcd_goods_purchase_item                               */
/*==============================================================*/
create table bcd_goods_purchase_item
(
   id                   int not null AUTO_INCREMENT comment '主键id',
   purchase_id          int comment '采购单id',
   goods_id             int comment '商品id',
   goods_no             varchar(64) comment '商品货号',
   quantity             int comment '采购数量',
   pur_price            int comment '采购价格',
   state                int comment '状态',
   primary key (id)
);

alter table bcd_goods_purchase_item comment '采购明细表';

drop table if exists bcd_goods_order;

/*==============================================================*/
/* Table: bcd_goods_order                                       */
/*==============================================================*/
create table bcd_goods_order
(
   id                   int not null auto_increment,
   order_no            varchar(32),
   order_user_id        int,
   store_id             int,
   order_type           int,
   add_time             datetime,
   update_time          datetime,
   memo                 text,
   state                int,
   kingdee_cust_id      int,
   kingdee_cust_no    	varchar(64),
   `kingdee_purchase_cust_id`  int(11) NULL DEFAULT 0,
   `kingdee_purchase_cust_no`  varchar(64) NULL,
   primary key (id)
);

alter table bcd_goods_order comment '商品订货表';

drop table if exists bcd_goods_order_item;

/*==============================================================*/
/* Table: bcd_goods_order_item                                  */
/*==============================================================*/
create table bcd_goods_order_item
(
   id                   int not null auto_increment,
   goods_order_id             int,
   order_no             varchar(32),
   goods_id             int,
   goods_no             varchar(32),
   order_quantity       int,
   instore_quantity     int,
   instore_time         datetime,
   add_time             datetime,
   update_time          datetime,
   primary key (id)
);

alter table bcd_goods_order_item comment '订货明细表';

create table `bcd_meta_content` (
  `id` int(11) NOT NULL auto_increment,
  `define_id` int(11) DEFAULT 0,
  `content_desc` varchar(255) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `value` varchar(64) DEFAULT NULL,
  `Value1` varchar(64) DEFAULT NULL,
  `Value2` varchar(64) DEFAULT NULL,
  `Value3` varchar(64) DEFAULT NULL,
  `Value4` varchar(64) DEFAULT NULL,
  `Value5` varchar(64) DEFAULT NULL,
  `kingdee_cust_id` int(11) DEFAULT 0,
  `state` tinyint(4) DEFAULT 1 COMMENT '0:无效,1:有效',
  `add_Time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  primary KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据内容表';


create table `bcd_meta_define` (
  `id` int(11) NOT NULL auto_increment,
  `define_name` varchar(32) DEFAULT NULL,
  `define_desc` varchar(255) DEFAULT NULL,
  `state` tinyint(4) DEFAULT 1 COMMENT '0:无效,1:有效',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  primary KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元数据类型表';
