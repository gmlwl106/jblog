--테이블, 시퀀스  삭제
--users 테이블 삭제
drop table users;
--users 시퀀스 삭제
drop sequence seq_users_no;
--blog 테이블 삭제
drop table blog;
--category 테이블 삭제
drop table category;
--category 시퀀스 삭제
drop sequence seq_category_no;
--post 테이블 삭제
drop table post;
--post 시퀀스 삭제
drop sequence seq_post_no;
--comments 테이블 삭제
drop table comments;
--comments 시퀀스 삭제
drop sequence seq_comments_no;


commit;
rollback;


--USERS---------------------------------------------------------------------------------------------------
--users 테이블 생성
create table users (
    userNo    number,
    id    varchar2(50)    unique  not null,
    userName  varchar2(100)   not null,
    password  varchar2(50)    not null,
    joinDate  date    not null,
    PRIMARY KEY (userNo)
);

--users 시퀀스 생성
create sequence seq_users_no
increment by 1
start with 1
nocache;


--users 출력
select userNo
        ,id
        ,userName
        ,password
        ,joinDate
from users;





--BLOG---------------------------------------------------------------------------------------------------
--blog 테이블 생성
create table blog (
    id    varchar2(50),
    blogTitle   varchar2(200)   not null,
    logoFile    varchar2(200),
    primary key(id),
    constraint blog_fk foreign key (id)
    references users(id)
);

--blog 출력
select  id
        ,blogTitle
        ,logoFile
from blog;

insert into blog
values('hee', 'hee의 블로그입니다.', '');

update blog
set logoFile = 'assets/images/spring-logo.jpg';



--CATEGORY---------------------------------------------------------------------------------------------------
--category 테이블 생성
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

--category 시퀀스 생성
create sequence seq_category_no
increment by 1
start with 1
nocache;

--category 출력
select  cateNo
        ,id
        ,cateName
        ,description
        ,regDate
from category;






--POST---------------------------------------------------------------------------------------------------
--post 테이블 생성
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

--post 시퀀스 생성
create sequence seq_post_no
increment by 1
start with 1
nocache;

--post 출력
select  postNo
        ,cateNo
        ,postTitle
        ,postContent
        ,regDate
from post;





--COMMENTS---------------------------------------------------------------------------------------------------
--comments 테이블 생성
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

--comments 시퀀스 생성
create sequence seq_comments_no
increment by 1
start with 1
nocache;

--comments 출력
select  cmtNo
        ,postNo
        ,userNo
        ,cmtContent
        ,regDate
from comments;