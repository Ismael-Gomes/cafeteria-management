-- phpMyAdmin SQL Dump
-- version 5.2.1deb1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 16/06/2024 às 18:25
-- Versão do servidor: 10.11.6-MariaDB-0+deb12u1
-- Versão do PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `lanchonete`
--

DELIMITER $$
--
-- Funções
--
CREATE DEFINER=`root`@`localhost` FUNCTION `contar_produtos` (`categoria_nome` VARCHAR(255)) RETURNS INT(11) DETERMINISTIC BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM produto WHERE categoria = categoria_nome;
    RETURN total;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `sexo` char(1) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `cpf` char(11) NOT NULL,
  `nome_conta` varchar(16) DEFAULT 'Indefinido.',
  `email` varchar(30) NOT NULL,
  `senha` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `nome`, `sexo`, `data_nascimento`, `cpf`, `nome_conta`, `email`, `senha`) VALUES
(2, 'Everton', 'M', '2000-02-24', '65429838212', 'Sasuke', 'everton@lancho.ifpb.infoS2', '22222222'),
(3, 'Matheus', 'M', '2001-04-21', '8372971638', 'Aphelhios', 'matheus@lancho.ifpb.infoS2', '33333333'),
(4, 'Kaique', 'M', '2002-02-17', '73527529733', 'Zeca', 'kaique@lancho.ifpb.infoS2', '44444444');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `id_func` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cpf` char(11) NOT NULL,
  `numero_tele` char(11) DEFAULT NULL,
  `salario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descricao` text DEFAULT NULL,
  `preco` decimal(10,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `data_criacao` timestamp NULL DEFAULT current_timestamp(),
  `categoria` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto`
--

INSERT INTO `produto` (`id`, `nome`, `descricao`, `preco`, `quantidade`, `data_criacao`, `categoria`) VALUES
(2, 'Coxinha', 'Coxinha de Frango', 3.00, 3, '2024-06-05 16:22:39', 'Lanche'),
(3, 'Coxinha', 'Coxinha de carne', 4.00, 100, '2024-06-06 02:38:56', 'Lanche'),
(4, 'Coxinha', 'Coxinha de carne', 4.00, 100, '2024-06-06 02:39:33', 'Lanche');

--
-- Acionadores `produto`
--
DELIMITER $$
CREATE TRIGGER `after_delete_produto` AFTER DELETE ON `produto` FOR EACH ROW BEGIN
    INSERT INTO produto_auditoria (id_produto, acao, detalhes)
    VALUES (OLD.id, 'DELETE', CONCAT('Nome: ', OLD.nome, ', Preço: ', OLD.preco));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_insert_produto` AFTER INSERT ON `produto` FOR EACH ROW BEGIN
    INSERT INTO produto_auditoria (id_produto, acao, detalhes)
    VALUES (NEW.id, 'INSERT', CONCAT('Nome: ', NEW.nome, ', Preço: ', NEW.preco));
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_update_produto` AFTER UPDATE ON `produto` FOR EACH ROW BEGIN
    INSERT INTO produto_auditoria (id_produto, acao, detalhes)
    VALUES (NEW.id, 'UPDATE', CONCAT('Nome: ', OLD.nome, ' -> ', NEW.nome, ', Preço: ', OLD.preco, ' -> ', NEW.preco));
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto_auditoria`
--

CREATE TABLE `produto_auditoria` (
  `id` int(11) NOT NULL,
  `id_produto` int(11) DEFAULT NULL,
  `acao` varchar(50) DEFAULT NULL,
  `data` timestamp NULL DEFAULT current_timestamp(),
  `detalhes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto_auditoria`
--

INSERT INTO `produto_auditoria` (`id`, `id_produto`, `acao`, `data`, `detalhes`) VALUES
(1, 1, 'INSERT', '2024-06-05 14:04:13', 'Nome: Coxinha, Preço: 3.00'),
(2, 1, 'DELETE', '2024-06-05 14:29:17', 'Nome: Coxinha, Preço: 3.00'),
(3, 2, 'INSERT', '2024-06-05 16:22:39', 'Nome: Coxinha, Preço: 3.00'),
(4, 2, 'UPDATE', '2024-06-06 02:13:07', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(5, 2, 'UPDATE', '2024-06-06 02:13:07', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(6, 2, 'UPDATE', '2024-06-06 02:14:16', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(7, 2, 'UPDATE', '2024-06-06 02:14:16', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(8, 2, 'UPDATE', '2024-06-06 02:14:33', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(9, 2, 'UPDATE', '2024-06-06 02:14:34', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(10, 2, 'UPDATE', '2024-06-06 02:14:45', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(11, 2, 'UPDATE', '2024-06-06 02:14:45', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(12, 2, 'UPDATE', '2024-06-06 02:18:04', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(13, 2, 'UPDATE', '2024-06-06 02:18:10', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(14, 2, 'UPDATE', '2024-06-06 02:18:22', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(15, 2, 'UPDATE', '2024-06-06 02:18:34', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(16, 2, 'UPDATE', '2024-06-06 02:18:34', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(17, 2, 'UPDATE', '2024-06-06 02:18:48', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(18, 2, 'UPDATE', '2024-06-06 02:18:59', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(19, 2, 'UPDATE', '2024-06-06 02:18:59', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(20, 2, 'UPDATE', '2024-06-06 02:19:08', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(21, 2, 'UPDATE', '2024-06-06 02:22:59', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(22, 2, 'UPDATE', '2024-06-06 02:22:59', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(23, 2, 'UPDATE', '2024-06-06 02:23:05', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(24, 2, 'UPDATE', '2024-06-06 02:23:05', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(25, 3, 'INSERT', '2024-06-06 02:38:56', 'Nome: Coxinha, Preço: 4.00'),
(26, 4, 'INSERT', '2024-06-06 02:39:33', 'Nome: Coxinha, Preço: 4.00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda`
--

CREATE TABLE `venda` (
  `id` int(11) NOT NULL,
  `produto_id` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `data_venda` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `venda`
--

INSERT INTO `venda` (`id`, `produto_id`, `quantidade`, `data_venda`) VALUES
(1, 2, 1, '2024-06-06 05:13:04'),
(3, 2, 1, '2024-06-06 05:14:14'),
(4, 2, 0, '2024-06-06 05:14:31'),
(5, 2, 1, '2024-06-06 05:14:43'),
(6, 2, 1, '2024-06-06 05:18:02'),
(7, 2, 1, '2024-06-06 05:18:09'),
(8, 2, 3, '2024-06-06 05:18:20'),
(9, 2, 2, '2024-06-06 05:18:32'),
(10, 2, 3, '2024-06-06 05:18:47'),
(11, 2, 2, '2024-06-06 05:18:57'),
(12, 2, 1, '2024-06-06 05:19:07'),
(13, 2, 1, '2024-06-06 05:22:58'),
(14, 2, 1, '2024-06-06 05:23:04');

--
-- Acionadores `venda`
--
DELIMITER $$
CREATE TRIGGER `after_venda_insert` AFTER INSERT ON `venda` FOR EACH ROW BEGIN
    UPDATE produto SET quantidade = quantidade - NEW.quantidade WHERE id = NEW.produto_id;
END
$$
DELIMITER ;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cliente`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`id_func`);

--
-- Índices de tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `produto_auditoria`
--
ALTER TABLE `produto_auditoria`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `produto_id` (`produto_id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `id_func` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `produto_auditoria`
--
ALTER TABLE `produto_auditoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
