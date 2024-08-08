# Sequência de procedimentos a ser feito no MySQL:

# Passo 1:
* CREATE DATABASE Lanchonete;

# Passo 2:
* CREATE TABLE admin (
  matricula varchar(12) NOT NULL PRIMARY KEY,
  email varchar(100) DEFAULT NULL,
  nome varchar(100) DEFAULT NULL,
  acesso varchar(200) DEFAULT NULL,
  senha varchar(20) NOT NULL
  );

# Passo 3:
* INSERT INTO admin (matricula, email, nome, acesso, senha) VALUES
  ('`escolha`', '`escolha`', '`escolha`', 'Ver, Inserir, Modificar, Deletar', '`escolha`');

# Passo 4:
* CREATE TABLE cliente (
  cpf char(11) NOT NULL PRIMARY KEY,
  nome varchar(100) NOT NULL,
  nome_conta varchar(30) NOT NULL,
  sexo char(1) DEFAULT NULL,
  data_nascimento date DEFAULT NULL,
  email varchar(100) NOT NULL,
  senha char(8) NOT NULL
  );

# Passo 5:
INSERT INTO cliente VALUES ('`escolha`', '`escolha`', '`escolha`', 'M', '2006-06-10', '`escolha`', '`escolha`'),
('`escolha`', '`escolha`', '`escolha`', 'M', '2006-01-03', '`escolha`', '`escolha`');

# Passo 6:
* CREATE TABLE funcionario (
  id_func int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome varchar(30) NOT NULL,
  cpf char(11) NOT NULL,
  numero_tele char(11) DEFAULT NULL,
  salario double NOT NULL
  );

# Passo 7:
* INSERT INTO funcionario VALUES('`escolha`', '`escolha`', '`escolha`', `escolha`);

# Passo 8:
* CREATE TABLE produto (
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
codigo int NOT NULL,
categoria varchar(50) NOT NULL,
nome varchar(100) NOT NULL,
descricao text,
preco decimal(10,2) NOT NULL,
quantidade int NOT NULL,
data_criacao timestamp NULL DEFAULT CURRENT_TIMESTAMP
);

# Passo 9:
* INSERT INTO produto VALUES (NULL, 1, 'Lanche', 'Coxinha', 'Coxinha de Frango', 3.00, 90, NULL),
(NULL,  2, 'Lanche', 'Coxinha', 'Coxinha de carne', 4.00, 0, NULL);

# Passo 10:
* DELIMITER //
CREATE TRIGGER after_delete_produto AFTER DELETE ON produto FOR EACH ROW BEGIN
INSERT INTO produto_auditoria (id_produto, acao, detalhes)
VALUES (OLD.id, 'DELETE', CONCAT('Nome: ', OLD.nome, ', Preço: ', OLD.preco));
END;
//
DELIMITER ;

# Passo 11:
* DELIMITER //
CREATE TRIGGER after_insert_produto AFTER INSERT ON produto FOR EACH ROW BEGIN
INSERT INTO produto_auditoria (id_produto, acao, detalhes)
VALUES (NEW.id, 'INSERT', CONCAT('Nome: ', NEW.nome, ', Preço: ', NEW.preco));
END;
//
DELIMITER ;

# Passo 12:
* DELIMITER //
CREATE TRIGGER after_update_produto AFTER UPDATE ON produto FOR EACH ROW BEGIN
INSERT INTO produto_auditoria (id_produto, acao, detalhes)
VALUES (NEW.id, 'UPDATE', CONCAT('Nome: ', OLD.nome, ' -> ', NEW.nome, ', Preço: ', OLD.preco, ' -> ', NEW.preco));
END;
//
DELIMITER ;

# Passo 13:
* DELIMITER //
CREATE TRIGGER trg_before_insert_produto BEFORE INSERT ON produto FOR EACH ROW BEGIN
IF NEW.quantidade < 0 THEN
SET NEW.quantidade = 0;
END IF;
END;
//
DELIMITER ;

# Passo 14:
* DELIMITER //
CREATE TRIGGER trg_before_update_produto BEFORE UPDATE ON produto FOR EACH ROW BEGIN
IF NEW.quantidade < 0 THEN
SET NEW.quantidade = 0;
END IF;
END;
//
DELIMITER ;

# Passo 15:
* CREATE TABLE produto_auditoria (
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
id_produto int DEFAULT NULL,
acao varchar(50) DEFAULT NULL,
data_acao timestamp NULL DEFAULT CURRENT_TIMESTAMP,
detalhes text
)

# Passo 16:
* CREATE TABLE suporte (
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
cpf_suporte char(11) NOT NULL,
cpf_cliente char(11) DEFAULT NULL,
nome_cliente varchar(100) DEFAULT NULL,
descricao_problema varchar(2000) DEFAULT NULL
)

# Passo 17:
* ALTER TABLE suporte ADD KEY cpf_cliente (cpf_cliente);

# Passo 18:
* INSERT INTO suporte VALUES
(NULL, '`escolha`', '`escolha`', '`escolha`', '`escolha`'),
(NULL, '`escolha`', '`escolha`', '`escolha`', '`escolha`'),
(NULL, '`escolha`', '`escolha`', '`escolha`', '`escolha`')

# Passo 19:
* CREATE TABLE venda (
id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
produto_id int DEFAULT NULL,
quantidade int DEFAULT NULL,
data_venda timestamp NULL DEFAULT NULL
);

# Passo 20:
* ALTER TABLE venda ADD KEY produto_id (produto_id);

# Passo 21:
* INSERT INTO venda VALUES
(NULL, 2, 7, '2024-08-03 20:04:41'),
(NULL, 1, 3, '2024-08-04 13:48:30'),
(NULL, 1, 1, '2024-08-04 13:50:13'),
(NULL, 1, 2, '2024-08-04 14:28:29'),
(NULL, 1, 5, '2024-08-04 15:04:05'),
(NULL, 2, 1, '2024-08-04 15:04:34')

# Passo 22:
* DELIMITER //
CREATE TRIGGER after_venda_insert AFTER INSERT ON venda FOR EACH ROW BEGIN
UPDATE produto SET quantidade = quantidade - NEW.quantidade WHERE id = NEW.produto_id;
END;
//
DELIMITER ;

# Passo 23:
* ALTER TABLE suporte
ADD CONSTRAINT suporte_fk FOREIGN KEY (cpf_cliente) REFERENCES cliente (cpf);

# Passo 24:
* ALTER TABLE venda
ADD CONSTRAINT venda_fk FOREIGN KEY (produto_id) REFERENCES produto (id);

# Passo 25:
* DELIMITER //
CREATE PROCEDURE InserirSuporte (IN p_cpf_suporte CHAR(11), IN p_cpf_cliente CHAR(11), IN p_descricao_problema TEXT, IN p_nome_cliente VARCHAR(100))   
BEGIN
DECLARE v_nome_cliente VARCHAR(100);
END;
//
DELIMITER;

# Passo 26:
* DELIMITER //
CREATE FUNCTION contar_produtos (categoria_nome VARCHAR(255)) RETURNS INT DETERMINISTIC
BEGIN
DECLARE total INT;
SELECT COUNT(*) INTO total
FROM produtos
WHERE categoria = categoria_nome;
RETURN total;
END;
//
DELIMITER ;