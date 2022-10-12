-- DROP TABLE IF EXISTS drone;
--
-- CREATE TABLE drone
-- (
--     `id`               BIGINT(20) NOT NULL AUTO_INCREMENT,
--     `created_at`       DATETIME DEFAULT NULL,
--     `updated_at`       DATETIME DEFAULT NULL,
--     `battery_capacity` DECIMAL(5, 2)  NOT NULL,
--     `model`            VARCHAR(255)   NOT NULL,
--     `remain_weight`    DECIMAL(19, 2) NOT NULL,
--     `serial_number`    VARCHAR(255)   NOT NULL,
--     `state`            VARCHAR(255)   NOT NULL,
--     `weight_limit`     DECIMAL(19, 2) NOT NULL,
--     PRIMARY KEY (`id`)
-- );
--
-- DROP TABLE IF EXISTS medication;
--
--
-- CREATE TABLE medication
-- (
--     `id`           bigint(20) NOT NULL AUTO_INCREMENT,
--     `created_at`   datetime      DEFAULT NULL,
--     `updated_at`   datetime      DEFAULT NULL,
--     `code`         varchar(255)   NOT NULL,
--     `image_byte`   longblob,
--     `image_name`   varchar(3000) DEFAULT NULL,
--     `image_source` longtext,
--     `image_type`   varchar(3000) DEFAULT NULL,
--     `name`         varchar(255)   NOT NULL,
--     `weight`       decimal(19, 2) NOT NULL,
--     `drone_id`     bigint(20) DEFAULT NULL,
--     PRIMARY KEY (`id`),
--     KEY            `FK80t3pqfskh7acq47fs7596fa3` (`drone_id`)
-- );
--
-- DROP TABLE IF EXISTS event_audit;
--
-- CREATE TABLE event_audit
-- (
--     `id`               bigint(20) NOT NULL AUTO_INCREMENT,
--     `created_at`       datetime DEFAULT NULL,
--     `updated_at`       datetime DEFAULT NULL,
--     `battery_capacity` decimal(5, 2) NOT NULL,
--     `drone_id`         bigint(20) NOT NULL,
--     PRIMARY KEY (`id`)
-- );

DROP TABLE IF EXISTS drone;
DROP TABLE IF EXISTS medication;
DROP TABLE IF EXISTS event_audit;

create table drone
(
    id               bigint         not null auto_increment,
    created_at       datetime,
    updated_at       datetime,
    battery_capacity decimal(5, 2)  not null,
    model            varchar(255)   not null,
    remain_weight    decimal(19, 2) not null,
    serial_number    varchar(255)   not null,
    state            varchar(255)   not null,
    weight_limit     decimal(19, 2) not null,
    primary key (id)
);
create table event_audit
(
    id               bigint        not null auto_increment,
    created_at       datetime,
    updated_at       datetime,
    battery_capacity decimal(5, 2) not null,
    drone_id         bigint        not null,
    primary key (id)
);
create table medication
(
    id           bigint         not null auto_increment,
    created_at   datetime,
    updated_at   datetime,
    code         varchar(255)   not null,
    image_byte   longblob,
    image_name   varchar(3000),
    image_source longtext,
    image_type   varchar(3000),
    name         varchar(255)   not null,
    weight       decimal(19, 2) not null,
    drone_id     bigint,
    primary key (id)
);
alter table medication
    add constraint FK80t3pqfskh7acq47fs7596fa3 foreign key (drone_id) references drone (id);
