INSERT INTO CARGO (cargo_name) VALUES ('developer'),('qa tester'),('product owner'),('scrum master');
INSERT INTO DEPARTAMENTO (departamento_name) VALUES ('finance'),('it'),('HR');
INSERT INTO FUNCIONARIO (funcionario_name,funcionario_document,cargo_id,funcionario_birthday) 
VALUES 
('Franklin Barreto','32545494810',1,'1984-09-28'),('João Kishi','32478547845',2,'1981-10-25');
INSERT INTO FUNCIONARIO_DEPARTAMENTO (departamento_id,funcionario_id,data_inicio) VALUES (1,1,'2020-04-19'),(1,2,'2020-05-24');