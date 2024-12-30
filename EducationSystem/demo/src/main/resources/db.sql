create database if not exists Education_System;
use Education_System;

-- 创建表[用户表-学生]
drop table if exists  student;
create table student(
                        student_id int primary key auto_increment comment '学号',
    username varchar(100) not null,
    password varchar(100) not null,
    createtime timestamp default current_timestamp comment '入学时间',
    `year` int default 4 comment '学年制',
    major varchar(30) comment '专业',
    `class_id` int default 1 comment '班级id',
    gender varchar(10) comment '性别',
    email varchar(30) comment '邮箱',
    phone varchar(20) comment '电话',
    updatetime timestamp default current_timestamp comment '上次更改信息时间',
    `state` int default 0 comment '账号错误登录次数',
    unfreezingtime bigint default 0 comment '账号冻结至xx时间'
) default charset 'utf8mb4';

-- 创建表[用户表-教师]
drop table if exists  teacher;
create table teacher(
    id int primary key auto_increment comment '教师编号',
    username varchar(100) not null,
    password varchar(100) not null,
    gender varchar(10) comment '性别',
    email varchar(30) comment '邮箱',
    phone varchar(20) comment '电话',
    updatetime timestamp default current_timestamp comment '上次更改信息时间',
    `state` int default 0 comment '账号错误登录次数',
    unfreezingtime bigint default 0 comment '账号冻结至xx时间'
) default charset 'utf8mb4' AUTO_INCREMENT = 1000;

-- 课程表
drop table if exists  course;
CREATE TABLE `course` (
    id int primary key auto_increment comment '课程id',
    `course_name` varchar(50) comment '课程名',
    `teacher_id` int comment '教师id',
    `class_id` int comment '班级id'
)default charset 'utf8mb4';

insert into course values(null,'数学','1','2103');
insert into course values(null,'语文','1','2103');
insert into course values(null,'数学','1','2102');
-- 成绩表
drop table if exists  grades;
CREATE TABLE `grades`(
    `id` INT comment '学生的学号',
    `course_id` INT comment '课程id',
    `semester` int comment '学期',
    `score` DECIMAL(5,2) comment '成绩'
)default charset 'utf8mb4';
insert into grades values (1,1,1,89);
insert into grades values (1,2,1,99);
insert into grades values (1,3,2,19);
insert into grades values (1,3,2,19);
insert into grades values (1,3,2,19);
-- 荣誉记录
drop table if exists  honor;
CREATE TABLE `honor` (
    `id` int primary key auto_increment comment '荣誉id',
    `student_id` INT not null,
    `honor_name` VARCHAR(100) comment '奖学金 / 证书 名称',
    award_date timestamp default current_timestamp comment '获奖时间',
    `type` int default 0  comment '荣誉类型 1奖学金 2证书 0其他荣誉',
    `state` int default 1 comment '荣誉项状态 0待审核 1审核通过 -1审核不通过'
)default charset 'utf8mb4';
insert into honor values (null,1,"校级奖学金一等奖",null,1,1);
insert into honor values (null,1,"心理证书",null,2,1);
drop table if exists  physical;
CREATE TABLE `physical` (
    `student_id` INT comment '学号',
    `semester` int comment '学期',
    `sprint` DECIMAL(4,2) comment '短跑50m成绩，10.14-10秒14',
    `run` DECIMAL(3,2) comment '长跑800m/1000m成绩，3.54-3分54秒',
    `sit_up` int comment '仰卧起坐/引体向上（个）',
    `jump` int comment '跳远cm',
    `height` int comment'身高cm',
    `weight` int comment '体重kg'
)default charset 'utf8mb4';
insert into physical values (1,1,9.8,3.55,38,186,150,40);
insert into physical values (1,2,9.8,3.55,38,186,150,40);
insert into physical values (1,3,9.8,3.55,38,186,150,40);
insert into physical values (2,1,9.8,3.55,30,186,165,60);