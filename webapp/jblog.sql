--���̺�, ������  ����
--users ���̺� ����
drop table users;
--users ������ ����
drop sequence seq_users_no;
--blog ���̺� ����
drop table blog;
--category ���̺� ����
drop table category;
--category ������ ����
drop sequence seq_category_no;
--post ���̺� ����
drop table post;
--post ������ ����
drop sequence seq_post_no;
--comments ���̺� ����
drop table comments;
--comments ������ ����
drop sequence seq_comments_no;


commit;
rollback;


--USERS---------------------------------------------------------------------------------------------------
--users ���̺� ����
create table users (
    userNo    number,
    id    varchar2(50)    unique  not null,
    userName  varchar2(100)   not null,
    password  varchar2(50)    not null,
    joinDate  date    not null,
    PRIMARY KEY (userNo)
);

--users ������ ����
create sequence seq_users_no
increment by 1
start with 1
nocache;


--users ���
select userNo
        ,id
        ,userName
        ,password
        ,joinDate
from users;





--BLOG---------------------------------------------------------------------------------------------------
--blog ���̺� ����
create table blog (
    id    varchar2(50),
    blogTitle   varchar2(200)   not null,
    logoFile    varchar2(200),
    primary key(id),
    constraint blog_fk foreign key (id)
    references users(id)
);

--blog ���
select  id
        ,blogTitle
        ,logoFile
from blog;

insert into blog
values('hee', 'hee�� ��α��Դϴ�.', '');

update blog
set logoFile = 'assets/images/spring-logo.jpg';



--CATEGORY---------------------------------------------------------------------------------------------------
--category ���̺� ����
create table category (
    cateNo  number,
    id  varchar2(50),
    cateName    varchar2(200)   not null,
    description varchar2(500),
    regDate date    not null,
    primary key(cateNo),
    constraint cate_fk foreign key (id)
    references blog(id)
);

--category ������ ����
create sequence seq_category_no
increment by 1
start with 1
nocache;

--category ���
select  cateNo
        ,id
        ,cateName
        ,description
        ,regDate
from category;






--POST---------------------------------------------------------------------------------------------------
--post ���̺� ����
create table post (
    postNo   number,
    cateNo  number,
    postTitle  varchar2(300)    not null,
    postContent  varchar2(4000),
    regDate date    not null,
    primary key(postNo),
    constraint post_fk foreign key (cateNo)
    references category(cateNo)
);

--post ������ ����
create sequence seq_post_no
increment by 1
start with 1
nocache;

--post ���
select  postNo
        ,cateNo
        ,postTitle
        ,postContent
        ,regDate
from post;





--COMMENTS---------------------------------------------------------------------------------------------------
--comments ���̺� ����
create table comments (
    cmtNo   number,
    postNo  number,
    userNo  number,
    cmtContent  varchar2(1000) not null,
    regDate date    not null,
    primary key(cmtNo),
    constraint cmt_user_fk foreign key (userNo)
    references users(userNo),
    constraint cmt_post_fk foreign key (postNo)
    references post(postNo)
);

--comments ������ ����
create sequence seq_comments_no
increment by 1
start with 1
nocache;

--comments ���
select  cmtNo
        ,postNo
        ,userNo
        ,cmtContent
        ,regDate
from comments;