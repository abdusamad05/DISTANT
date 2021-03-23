create sequence hibernate_sequence start 1 increment 1;
create table category (
                          cat_id int4 not null,
                          categoryname varchar(255),
                          primary key (cat_id));

create table lesson (
                        id int8 not null,
                        filename varchar(255),
                        lessoncategory varchar(255),
                        lessonname varchar(255) not null,
                        text text not null,
                        user_id int8,
                        primary key (id));

create table user_role (
                           user_id int8 not null,
                           roles varchar(255));

create table usr (
                     user_id int8 not null,
                     activation_code varchar(255),
                     active boolean not null,
                     email varchar(255),
                     password varchar(255) not null,
                     username varchar(255) not null,
                     primary key (user_id));
alter table if exists lesson
    add constraint lesson_user_fk
        foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr;