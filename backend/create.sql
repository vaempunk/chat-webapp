create table chats (
    id bigserial not null,
    name varchar(255),
    cooldown_ms bigint not null,
    primary key (id)
);
create table messages (
    id bigserial not null,
    text varchar(255),
    sent_at timestamp(6) with time zone,
    chat_id bigint,
    sender_id bigint,
    primary key (id)
);
create table roles (
    id bigserial not null,
    name varchar(255) check (name in ('USER', 'MODERATOR', 'ADMIN')),
    primary key (id)
);
create table users (
    id bigserial not null,
    username varchar(255),
    password varchar(255),
    role_id bigint,
    primary key (id)
);
alter table if exists messages
add constraint FK64w44ngcpqp99ptcb9werdfmb foreign key (chat_id) references chats;
alter table if exists messages
add constraint FK4ui4nnwntodh6wjvck53dbk9m foreign key (sender_id) references users;
alter table if exists users
add constraint FKp56c1712k691lhsyewcssf40f foreign key (role_id) references roles;