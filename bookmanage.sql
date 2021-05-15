create database if not exists bookmanage;
use bookmanage;

-- 执行以下建表操作，如果数据表已经存在会先删除
-- user_info;
drop table if exists user_info;
create table if not exists user_info(
userid varchar(20),
userpassword varchar(20),
grade tinyint default 0,
email varchar(20),
phone char(11),
question1 varchar(30),
answer1 varchar(20),
question2 varchar(30),
answer2 varchar(20),
primary key(userid))engine=innodb default charset=utf8mb4;

insert into user_info(userid,userpassword,grade,email,phone,question1,answer1)
values('user123456','userpw123456',0,'2220940000@qq.com','19959370000','最喜欢的地方','香格里拉');

-- record
drop table if exists record;
create table if not exists record(
recordserial varchar(20),
bookid varchar(20),
bookname varchar(40),
userid varchar(20),
borrowdate date,
backdate date,
isreturn int default 1,
primary key(recordserial)
)ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
-- select * from record;


-- popular_book
drop table if exists popular_book;
create table if not exists popular_book(
bookid varchar(20),
bookname varchar(50),
author varchar(20),
publisher varchar(30),
publishtime date,
borrowcount int default 0,
primary key(bookid))engine=INNODB default charset=utf8mb4;

-- literary_novel

drop table if exists literary_novel;
create table if not exists literary_novel(
bookid varchar(20),
bookname varchar(50) not null,
author varchar(20) default 'unknown',
publisher varchar(40) default 'unknown',
publishtime date default '2000-1-1',
total int unsigned default 0,
surplus int unsigned default 0,
primary key(bookid))engine=innodb default charset=utf8mb4;


insert into literary_novel(bookid,bookname,publisher,publishtime,total,surplus)
values('lite201908230019','鲁迅文集','文艺出版','2017-6-9',11,10);
insert into literary_novel(bookid,bookname,publisher,publishtime,total,surplus)
values('lite201908230050','我的大学','人民邮电','2017-6-9',11,10);
insert into literary_novel(bookid,bookname,publisher,publishtime,total,surplus)
values('lite201708230031','在人间','人民邮电','2017-6-9',11,10);
insert into literary_novel(bookid,bookname,publisher,publishtime,total,surplus)
values('lite201801230021','童年','人民邮电','2017-10-9',11,10);
insert into literary_novel(bookid,bookname,publisher,publishtime,total,surplus)
values('lite201904230011','活着','新华出版社','2019-1-20',11,10);
insert into literary_novel(bookid,bookname,publisher,publishtime,total,surplus)
values('lite201907270011','冰心文集','新华出版社','2019-1-20',11,10);
insert into literary_novel(bookid,bookname,publisher,publishtime,total,surplus)
values('lite201907270067','','','2019-1-14',1,1);
-- select * from literary_novel;

-- history_biography

drop table if exists history_biography;
create table if not exists history_biography(
bookid varchar(20),
bookname varchar(50) not null,
author varchar(20) default 'unknown',
publisher varchar(40) default 'unknown',
publishtime date default '2000-1-1',
total int unsigned default 0,
surplus int unsigned default 0,
primary key(bookid));

insert into history_biography(bookid,bookname,publisher,publishtime,total,surplus)
values('hist_2019082300019','历史百科全书','文艺出版','2017-6-9',11,10);
insert into history_biography(bookid,bookname,publisher,publishtime,total,surplus)
values('hist_2019082300050','邓小平传','人民邮电','2017-6-9',11,10);
insert into history_biography(bookid,bookname,publisher,publishtime,total,surplus)
values('hist_2017082300031','知青岁月','人民邮电','2017-6-9',11,10);
insert into history_biography(bookid,bookname,publisher,publishtime,total,surplus)
values('hist_2018012300021','宋史','人民邮电','2017-10-9',11,10);
insert into history_biography(bookid,bookname,publisher,publishtime,total,surplus)
values('hist_2019042300011','开元盛世','新华出版社','2019-1-20',11,10);
-- select * from history_biography;

-- computer_science
drop table if exists computer_science;
create table if not exists computer_science(
bookid varchar(20),
bookname varchar(50) not null,
author varchar(20) default 'unknown',
publisher varchar(40) default 'unknown',
publishtime date default '2000-1-1',
total int unsigned default 0,
surplus int unsigned default 0,
primary key(bookid))engine=innodb default charset=utf8mb4;


-- romantic_faction
drop table if exists romantic_faction;
create table if not exists romantic_faction(
bookid varchar(20),
bookname varchar(50) not null,
author varchar(20) default '未知',
publisher varchar(40),
publishtime date,
total int unsigned default 0,
surplus int unsigned default 0,
primary key(bookid));
delete from romantic_faction where bookid='roma_2018082300001';


insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201908230019','骄阳似我','文艺出版','2017-6-9',11,10);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201908230050','你是我的最爱','人民邮电','2017-6-9',11,10);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201708230031','遇见你，此生无憾','人民邮电','2017-6-9',11,10);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201801230021','邂逅','人民邮电','2017-10-9',11,10);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201904230011','你是我的最爱','新华出版社','2019-1-20',11,10);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201904230091','最美好的年纪遇见你','凤凰文艺','2019-6-20',11,10);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201908230012','最美丽的地方','新华出版社','2018-12-20',11,11);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201908230014','那一年，我们最美好的回忆','人民日报','2018-4-20',11,11);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201908230015','快乐时光','人民日报','2018-4-20',11,11);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201808230044','从你的世界走过','人民邮电','2018-6-9',10,10);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201908230028','那一年，我们最美好的回忆','人民日报','2018-4-20',11,11);
insert into romantic_faction(bookid,bookname,publisher,publishtime,total,surplus)
values('roma201908230026','因为遇见你','文艺出版','2018-4-20',11,11);
-- select * from romantic_faction;

-- serial_number
drop table if exists serial_number;
create table if not exists serial_number(
kinds char(20),
latestserial varchar(20),
primary key(kinds))engine=INNODB default charset=utf8;


insert into serial_number(kinds,latestserial)
values('history_biography','hist201908020001');
insert into serial_number(kinds,latestserial)
values('literary_novel','lite201708020001');
insert into serial_number(kinds,latestserial)
values('romantic_faction','roma201908220001');
insert into serial_number(kinds,latestserial)
values('computer_science','comp201908220001');
insert into serial_number(kinds,latestserial)
values('record','reco201908220001');


-- create table if not exists admin_apply(
-- idcard varchar(18),
-- realname varchar(10),
-- applyinfo varchar(500),
-- primary key(idcard))engine=innodb default charset=utf8mb4;
-- select * from admin_apply;