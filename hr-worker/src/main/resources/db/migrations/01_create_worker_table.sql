--liquibase formatted sql

--changeset felipe.santana:01

CREATE TABLE IF NOT EXISTS tb_worker (
    id serial primary key,
    name varchar(255) not null,
    daily_income decimal(12,2)
);

