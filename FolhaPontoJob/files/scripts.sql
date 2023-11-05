DROP TABLE IF EXISTS funcionario;

CREATE table funcionario (
	matricula INT,
	nome TEXT,
	idade INT,
	primary key(matricula)
);

DROP TABLE IF EXISTS registro_ponto;

CREATE table registro_ponto (
	id SERIAL,
	data VARCHAR,
	funcionario_id INT,
	primary key(id)
);

DROP TABLE IF EXISTS folha_ponto;

CREATE table folha_ponto (
	id SERIAL,
	mes INT,
	ano INT,
	funcionario_id INT,
	registros_ponto TEXT,
	primary key(id)
);

INSERT INTO funcionario VALUES ('113883','Lukas Abernathy OK','58'),
('121928','Felicity Jacobson OK','56'),
('122683','Mr. Junius Wintheiser OK','33'),
('171171','Macy Willms NOK','35'),
('184218','Luna Moen IV NOK','35');

INSERT INTO registro_ponto (data, funcionario_id) VALUES
(TO_CHAR(NOW(), 'yyyy-MM-04 08:00:00'), 121928),
(TO_CHAR(NOW(), 'yyyy-MM-04 12:00:00'), 121928),
(TO_CHAR(NOW(), 'yyyy-MM-04 14:00:00'), 121928),
(TO_CHAR(NOW(), 'yyyy-MM-04 18:00:00'), 121928),
(TO_CHAR(NOW(), 'yyyy-MM-05 08:00:00'), 121928),
(TO_CHAR(NOW(), 'yyyy-MM-04 12:00:00'), 113883),
(TO_CHAR(NOW(), 'yyyy-MM-04 08:00:00'), 122683),
(TO_CHAR(NOW(), 'yyyy-MM-05 08:00:00'), 122683);