# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table abstract_item (
  id                        bigint not null,
  title                     varchar(255),
  short_description         varchar(255),
  description               varchar(255),
  img                       varchar(255),
  constraint pk_abstract_item primary key (id))
;

create table abstract_item_rating (
  id                        bigint not null,
  user_id                   bigint,
  item_id                   bigint,
  rating                    double,
  constraint pk_abstract_item_rating primary key (id))
;

create table book (
  id                        bigint not null,
  title                     varchar(255),
  author                    varchar(255),
  year                      varchar(255),
  publisher                 varchar(255),
  img                       varchar(255),
  short_description         varchar(255),
  description               varchar(255),
  constraint pk_book primary key (id))
;

create table book_rating (
  id                        bigint not null,
  user_id                   bigint,
  book_id                   bigint,
  rating                    double,
  constraint pk_book_rating primary key (id))
;

create table course (
  id                        bigint not null,
  title                     varchar(255),
  short_description         varchar(255),
  description               varchar(255),
  url                       varchar(255),
  img                       varchar(255),
  constraint pk_course primary key (id))
;

create table course_rating (
  id                        bigint not null,
  user_id                   bigint,
  course_id                 bigint,
  rating                    double,
  constraint pk_course_rating primary key (id))
;

create table user (
  user_id                   bigint not null,
  email                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  age                       integer,
  gender                    varchar(255),
  profession                varchar(255),
  default_locale            varchar(255),
  role                      varchar(255),
  constraint pk_user primary key (user_id))
;

create table video (
  id                        bigint not null,
  title                     varchar(255),
  short_description         varchar(255),
  description               varchar(255),
  url                       varchar(255),
  img                       varchar(255),
  constraint pk_video primary key (id))
;

create table video_rating (
  id                        bigint not null,
  user_id                   bigint,
  video_id                  bigint,
  rating                    double,
  constraint pk_video_rating primary key (id))
;

create sequence abstract_item_seq;

create sequence abstract_item_rating_seq;

create sequence book_seq;

create sequence book_rating_seq;

create sequence course_seq;

create sequence course_rating_seq;

create sequence user_seq;

create sequence video_seq;

create sequence video_rating_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists abstract_item;

drop table if exists abstract_item_rating;

drop table if exists book;

drop table if exists book_rating;

drop table if exists course;

drop table if exists course_rating;

drop table if exists user;

drop table if exists video;

drop table if exists video_rating;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists abstract_item_seq;

drop sequence if exists abstract_item_rating_seq;

drop sequence if exists book_seq;

drop sequence if exists book_rating_seq;

drop sequence if exists course_seq;

drop sequence if exists course_rating_seq;

drop sequence if exists user_seq;

drop sequence if exists video_seq;

drop sequence if exists video_rating_seq;

