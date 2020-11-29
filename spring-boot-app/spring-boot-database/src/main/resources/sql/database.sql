show databases;
use foobar;
create table if not exists user
(
    uid        int auto_increment,
    username   varchar(255) null,
    password   varchar(255) null,
    user_state int          not null,
    constraint user_pk primary key (uid)
);
create table if not exists home_page
(
    id        int auto_increment,
    author_id int          not null,
    title     varchar(255) null,
    content   varchar(255) null,
    constraint home_page_pk primary key (id)
);

insert into user(username, password, user_state)
values ('instance2 foobar', 'instance2 foobar', 1);
insert into home_page(author_id, title, content)
values (1, 'instance2 foobar title', 'instance2 foobar content');