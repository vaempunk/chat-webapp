create table chats (cooldown_ms bigint not null, id bigserial not null, name varchar(255), primary key (id));
create table messages (chat_id bigint, id bigserial not null, sender_id bigint, sent_at timestamp(6) with time zone, text varchar(255), primary key (id));
create table roles (id bigserial not null, name varchar(255) check (name in ('USER','MODERATOR','ADMIN')), primary key (id));
create table users (id bigserial not null, role_id bigint, password varchar(255), username varchar(255), primary key (id));
alter table if exists messages add constraint FK64w44ngcpqp99ptcb9werdfmb foreign key (chat_id) references chats;
alter table if exists messages add constraint FK4ui4nnwntodh6wjvck53dbk9m foreign key (sender_id) references users;
alter table if exists users add constraint FKp56c1712k691lhsyewcssf40f foreign key (role_id) references roles;
