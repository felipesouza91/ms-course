--liquibase formatted sql

--changeset felipe.santana:03

CREATE TABLE IF NOT EXISTS tb_user_role (
    role_id serial not null,
    user_id serial not null,
    CONSTRAINT tb_user_role_tb_user FOREIGN KEY (user_id)
                REFERENCES tb_role (id) ON DELETE CASCADE,
    CONSTRAINT tb_user_role_tb_role FOREIGN KEY (role_id)
                REFERENCES tb_role (id) ON DELETE CASCADE
);

