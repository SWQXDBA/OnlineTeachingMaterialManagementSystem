drop
database if exists TeachingManagement;
create
database if not exists TeachingManagement;
use
TeachingManagement;
create table user
(
    userId   int primary key auto_increment,
    userName varchar(50) unique,
    passWord varchar(50),
    isAdmin  bit
);
create table books
(
    bookId     int primary key auto_increment,
    bookName   varchar(50),
    author     varchar(50),
    remarks    varchar(100),
    userid     int default null,
    isBorrowed bit default 0,
    body       mediumtext
);
-- 43.248.186.196:8081/OnlineTeachingMaterialManagementSystem-1.0-SNAPSHOT/login.html