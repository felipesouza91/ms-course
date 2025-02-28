--liquibase formatted sql

--changeset felipe.santana:01

CREATE TABLE IF NOT EXISTS tb_role (
    id serial primary key,
    role_name varchar(255) not null unique
);

