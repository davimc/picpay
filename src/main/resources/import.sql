INSERT INTO tb_person (first_name, last_name, cpf, person_type) VALUES ('Bob', 'Finger', '98410106078', 'N');
INSERT INTO tb_person (first_name, last_name, cpf, person_type) VALUES ('Ana', 'Frost', '32298763003', 'N');
INSERT INTO tb_person (legal_name, trade_name, cnpj, person_type) VALUES ('anapoli corp inc', 'Anapoli', '98894721000197', 'L');

INSERT INTO TB_USER (person_id, email,password) VALUES (1,'bob@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO TB_USER (person_id, email,password) VALUES (2,'ana@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO TB_USER (person_id, email,password) VALUES (2,'ana@picpay.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO TB_USER (person_id, email, password) VALUES (3,'anapoli@corp.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_USUARIO');
INSERT INTO tb_role (authority) VALUES ('ROLE_LOJISTA');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (4, 1);


