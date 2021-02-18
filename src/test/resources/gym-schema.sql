DROP TABLE IF EXISTS `gym` CASCADE;
DROP TABLE IF EXISTS `equipment` CASCADE;
create table gym (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null, type varchar(255) not null);
create table equipment (id bigint PRIMARY KEY AUTO_INCREMENT, price integer not null, type varchar(255) not null, gym_id bigint not null);
