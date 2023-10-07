INSERT INTO TB_USER (first_name, last_name, person_type, email, cpf,password) VALUES ('Bob', 'Finger', 'NATURAL','bob@gmail.com', '98410106078','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO TB_USER (first_name, last_name, person_type, email, cpf,password) VALUES ('Ana', 'Frost', 'NATURAL','ana@gmail.com', '32298763003','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO TB_USER (legal_name, trade_name, person_type, email, cnpj, password) VALUES ('anapoli corp inc', 'Anapoli', 'LEGAL','anapoli@corp.com', '98894721000197','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
--
INSERT INTO tb_role (authority) VALUES ('ROLE_USUARIO');
INSERT INTO tb_role (authority) VALUES ('ROLE_LOJISTA');
--
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);

