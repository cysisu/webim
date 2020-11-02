DROP DATABASE IF EXISTS webim;
CREATE DATABASE webim DEFAULT CHARACTER SET utf8;
USE webim;
CREATE TABLE user(
	id int(10) auto_increment primary key,
	name char(50) ,
    password char(10),
    status char(10),
    sign char(100),
    avatar char(100)
);
insert user values (10000,'浮生一梦','1234','online','no zuo,no die','http://cdn.firstlinkapp.com/upload/2016_6/1465575923433_33812.jpg');
insert user values (null,'cq','1234','online','成功的道路上没有退路可言','http://tp1.sinaimg.cn/1571889140/180/40030060651/1');
insert user values (null,'cy','1234','online','我想看不一样的海','http://tva3.sinaimg.cn/crop.0.0.512.512.180/8693225ajw8f2rt20ptykj20e80e8weu.jpg');
insert user values (null,'微风拂柳','1234','online','年轻就是我的一切','http://tp4.sinaimg.cn/2145291155/180/5601307179/1');


create TABLE friend(
	id int (10) auto_increment primary key,
    myId int(10) comment '用户名',
    groupname char(20) comment '分组的名称',
    friendid char(10) comment '好友id'
);
insert friend values (1000,10000,'亲人',10001);
insert friend values (null,10000,'朋友',10002);
insert friend values (null,10000,'朋友',10003);
insert friend values (null,10001,'同学',10000);