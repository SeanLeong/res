--------------------------------------------------------------------
--                创建库，表，约束，过程，用户，权限等脚本
--------------------------------------------------------------------
drop database res;
create database res;


select  fid, fname,normprice, realprice,fphoto  from resfood  where 1=1 limit 0 , 6
select  fid, fname,normprice, realprice,fphoto  from resfood  where 1=1 order by fid asc 

select * from resfood


use res;
select * from resadmin
create table resadmin(
    raid int primary key auto_increment,
    raname varchar(50),
    rapwd  varchar(50)
);

create table resuser(
	userid int  primary key auto_increment,
	username varchar(50),
	pwd varchar(50), 
	email varchar(500)
);
select * from resuser
update resuser  set pwd = 'fbc1b6c1376532db725be86c00e0cdf2025f42aa' where userid =5

21cefc0fd9de0047f10da05d8660a6bebb232d31 
3a83fa506a25e71af8062fd3cfc5da03b1be623b 



--normprice:原价  realprice:现价   description:简介 detail详细的

create table resfood(
	fid int  primary key auto_increment,
	fname varchar(50) ,  
	normprice numeric(8,2),
	realprice numeric(8,2),
	detail text,
	fphoto varchar(1000)
);
select * from resfood
select  fid, fname, normprice  from resfood  
	where 1=1 and normprice between 10 and 50 and fname like '%炒%'
	order by normprice desc

	

--订单表:   roid:订单号    userid:外键，下单的用户编号    ordertime:下单时间   uname:收货人姓名    deliverytype:送货方式   payment:支付方式, ps附言
--因为订单的详情表resorderitem依赖于resorder的主键    我们一般是直接查找最大的roid来确认
create table resorder(
	roid varchar(200)  primary key ,
	userid int,
	address varchar(500),
	tel varchar( 100),
	ordertime date,
	deliverytime date,   		--送货方式
	ps varchar( 2000),			--评论
	status int
);

select * from resorder where 1=1 and  userid = 
insert into resorder(roid,userid,address,tel,ordertime,deliverytime,ps,status) 


--订单表的下单人号与用户表中的客户编号有主外键关系. 
alter table resorder
	add constraint fk_resorder
	     foreign key(userid) references resuser(userid);
	     
--dealprice:成交价   roid:订单号   fid:商品号  num:数量
create table resorderitem(
	roiid int  primary key auto_increment,
	roid  varchar(200),
	fid   int,
	dealprice numeric(8,2),
	num     int
);
select * from resorderitem
--订单和订单详情一起查
select userid,fid,dealprice,num,tel,address,ordertime,deliverytime,ps,status from resorder,resorderitem 
	where resorder.roid=resorderitem.roid



alter table resorderitem 
   add constraint fk_resorderitem_roid
      foreign key(roid) references resorder( roid);
      
 alter table resorderitem
   add constraint fk_tbl_res_fid
      foreign key( fid ) references resfood( fid);
     

--临时表  不用管   
create table resorderitemtemp(
	roitid int  primary key auto_increment,
	fid   int,
	num     int,
	quittime date,
	userid int
);
      
      

