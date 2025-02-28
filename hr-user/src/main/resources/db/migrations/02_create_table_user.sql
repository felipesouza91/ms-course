--liquibase formatted sql

--changeset felipe.santana:02

CREATE TABLE IF NOT EXISTS tb_user (
    id serial primary key,
    name varchar(255) not null,
    email varchar(255) not null unique,
    password varchar(255) not null
);

