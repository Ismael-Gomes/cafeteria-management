-- phpMyAdmin SQL Dump
-- version 5.2.1deb3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 06/08/2024 às 15:24
-- Versão do servidor: 8.0.39-0ubuntu0.24.04.1
-- Versão do PHP: 8.3.6

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
-- Procedimentos
--
CREATE DEFINER=`ismael`@`localhost` PROCEDURE `InserirSuporte` (IN `p_cpf_suporte` CHAR(11), IN `p_cpf_cliente` CHAR(11), IN `p_descricao_problema` TEXT, IN `p_nome_cliente` VARCHAR(100))   BEGIN
    DECLARE v_nome_cliente VARCHAR(100)$$

--
-- Funções
--
CREATE DEFINER=`root`@`localhost` FUNCTION `contar_produtos` (`categoria_nome` VARCHAR(255)) RETURNS INT DETERMINISTIC BEGIN
    DECLARE total INT$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `admin`
--

CREATE TABLE `admin` (
  `matricula` varchar(12) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nome` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `acesso` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `senha` varchar(20) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `admin`
--

INSERT INTO `admin` (`matricula`, `email`, `nome`, `acesso`, `senha`) VALUES
('202119010007', 'ismaelcraft74@gmail.com', 'Ismael Gomes', 'Ver, Inserir, Modificar, Deletar', 'IsmaeL123');

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `cpf` char(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `nome_conta` varchar(30) NOT NULL,
  `sexo` char(1) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `senha` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`cpf`, `nome`, `nome_conta`, `sexo`, `data_nascimento`, `email`, `senha`) VALUES
('29453844004', 'Kaique Vieira', 'KAIQUEZÃO', 'M', '2006-06-10', 'kaique.vieira.dev@gmail.com', 'Kaique12'),
('71349267457', 'Ismael Gomes', 'LOTSUZO', 'M', '2006-01-03', 'ismael.silva.dev@gmail.com', 'IsmaeL29');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `id_func` int NOT NULL,
  `nome` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `cpf` char(11) COLLATE utf8mb4_general_ci NOT NULL,
  `numero_tele` char(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `salario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE `produto` (
  `id` int NOT NULL,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descricao` text COLLATE utf8mb4_general_ci,
  `preco` decimal(10,2) NOT NULL,
  `quantidade` int NOT NULL,
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `categoria` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `codigo` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto`
--

INSERT INTO `produto` (`id`, `nome`, `descricao`, `preco`, `quantidade`, `data_criacao`, `categoria`, `codigo`) VALUES
(2, 'Coxinha', 'Coxinha de Frango', 3.00, 90, '2024-06-05 16:22:39', 'Lanche', 1),
(3, 'Coxinha', 'Coxinha de carne', 4.00, 0, '2024-06-06 02:38:56', 'Lanche', 2);

--
-- Acionadores `produto`
--
DELIMITER $$
CREATE TRIGGER `after_delete_produto` AFTER DELETE ON `produto` FOR EACH ROW BEGIN
    INSERT INTO produto_auditoria (id_produto, acao, detalhes)
    VALUES (OLD.id, 'DELETE', CONCAT('Nome: ', OLD.nome, ', Preço: ', OLD.preco))$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_insert_produto` AFTER INSERT ON `produto` FOR EACH ROW BEGIN
    INSERT INTO produto_auditoria (id_produto, acao, detalhes)
    VALUES (NEW.id, 'INSERT', CONCAT('Nome: ', NEW.nome, ', Preço: ', NEW.preco))$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_update_produto` AFTER UPDATE ON `produto` FOR EACH ROW BEGIN
    INSERT INTO produto_auditoria (id_produto, acao, detalhes)
    VALUES (NEW.id, 'UPDATE', CONCAT('Nome: ', OLD.nome, ' -> ', NEW.nome, ', Preço: ', OLD.preco, ' -> ', NEW.preco))$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_before_insert_produto` BEFORE INSERT ON `produto` FOR EACH ROW BEGIN
    IF NEW.quantidade < 0 THEN
        SET NEW.quantidade = 0$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_before_update_produto` BEFORE UPDATE ON `produto` FOR EACH ROW BEGIN
    IF NEW.quantidade < 0 THEN
        SET NEW.quantidade = 0$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto_auditoria`
--

CREATE TABLE `produto_auditoria` (
  `id` int NOT NULL,
  `id_produto` int DEFAULT NULL,
  `acao` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `detalhes` text COLLATE utf8mb4_general_ci
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
(26, 4, 'INSERT', '2024-06-06 02:39:33', 'Nome: Coxinha, Preço: 4.00'),
(27, 4, 'DELETE', '2024-08-01 12:57:17', 'Nome: Coxinha, Preço: 4.00'),
(28, 2, 'UPDATE', '2024-08-03 13:26:06', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(29, 3, 'UPDATE', '2024-08-03 14:05:52', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(30, 2, 'UPDATE', '2024-08-03 17:34:40', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(31, 2, 'UPDATE', '2024-08-03 17:40:21', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(32, 2, 'UPDATE', '2024-08-03 17:40:54', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(33, 2, 'UPDATE', '2024-08-03 17:45:20', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(34, 2, 'UPDATE', '2024-08-03 17:46:15', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(35, 2, 'UPDATE', '2024-08-03 17:46:15', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(36, 2, 'UPDATE', '2024-08-03 17:50:35', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(37, 2, 'UPDATE', '2024-08-03 17:51:50', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(38, 2, 'UPDATE', '2024-08-03 17:56:39', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(39, 2, 'UPDATE', '2024-08-03 17:56:39', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(40, 2, 'UPDATE', '2024-08-03 17:59:56', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(41, 2, 'UPDATE', '2024-08-03 17:59:56', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(42, 2, 'UPDATE', '2024-08-03 18:01:59', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(43, 2, 'UPDATE', '2024-08-03 18:02:19', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(44, 2, 'UPDATE', '2024-08-03 18:02:19', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(45, 2, 'UPDATE', '2024-08-03 18:04:14', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(46, 2, 'UPDATE', '2024-08-03 18:04:14', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(47, 2, 'UPDATE', '2024-08-03 18:05:24', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(48, 2, 'UPDATE', '2024-08-03 18:05:24', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(49, 2, 'UPDATE', '2024-08-03 18:05:47', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(50, 2, 'UPDATE', '2024-08-03 18:05:47', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(51, 2, 'UPDATE', '2024-08-03 18:43:49', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(52, 2, 'UPDATE', '2024-08-03 18:43:49', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(53, 3, 'UPDATE', '2024-08-03 18:45:11', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(54, 3, 'UPDATE', '2024-08-03 18:45:11', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(55, 3, 'UPDATE', '2024-08-03 18:46:12', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(56, 3, 'UPDATE', '2024-08-03 18:52:53', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(57, 3, 'UPDATE', '2024-08-03 18:53:53', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(58, 3, 'UPDATE', '2024-08-03 18:55:17', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(59, 3, 'UPDATE', '2024-08-03 18:56:58', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(60, 3, 'UPDATE', '2024-08-03 18:57:04', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(61, 3, 'UPDATE', '2024-08-03 19:04:20', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(62, 3, 'UPDATE', '2024-08-03 19:04:52', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(63, 3, 'UPDATE', '2024-08-03 19:04:52', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(64, 3, 'UPDATE', '2024-08-03 19:05:12', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(65, 3, 'UPDATE', '2024-08-03 19:05:38', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(66, 3, 'UPDATE', '2024-08-03 19:05:38', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(67, 3, 'UPDATE', '2024-08-03 19:06:45', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(68, 3, 'UPDATE', '2024-08-03 19:06:55', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(69, 3, 'UPDATE', '2024-08-03 19:07:10', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(70, 3, 'UPDATE', '2024-08-03 19:07:59', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(71, 3, 'UPDATE', '2024-08-03 19:07:59', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(72, 3, 'UPDATE', '2024-08-03 19:09:37', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(73, 3, 'UPDATE', '2024-08-03 19:10:12', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(74, 3, 'UPDATE', '2024-08-03 19:10:12', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(75, 3, 'UPDATE', '2024-08-03 19:10:47', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(76, 3, 'UPDATE', '2024-08-03 19:10:47', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(77, 3, 'UPDATE', '2024-08-03 19:11:29', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(78, 3, 'UPDATE', '2024-08-03 19:11:29', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(79, 3, 'UPDATE', '2024-08-03 19:13:47', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(80, 3, 'UPDATE', '2024-08-03 19:13:47', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(81, 3, 'UPDATE', '2024-08-03 19:14:17', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(82, 3, 'UPDATE', '2024-08-03 19:14:17', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(83, 3, 'UPDATE', '2024-08-03 19:16:07', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(84, 3, 'UPDATE', '2024-08-03 19:16:07', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(85, 3, 'UPDATE', '2024-08-03 19:17:59', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(86, 3, 'UPDATE', '2024-08-03 19:17:59', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(87, 3, 'UPDATE', '2024-08-03 19:18:18', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(88, 3, 'UPDATE', '2024-08-03 19:18:42', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(89, 3, 'UPDATE', '2024-08-03 19:18:42', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(90, 2, 'UPDATE', '2024-08-03 19:21:06', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(91, 2, 'UPDATE', '2024-08-03 19:21:06', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(92, 3, 'UPDATE', '2024-08-03 19:21:37', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(93, 2, 'UPDATE', '2024-08-03 19:21:45', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(94, 2, 'UPDATE', '2024-08-03 19:22:02', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(95, 2, 'UPDATE', '2024-08-03 19:22:02', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(96, 2, 'UPDATE', '2024-08-03 19:23:11', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(97, 2, 'UPDATE', '2024-08-03 19:24:05', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(98, 2, 'UPDATE', '2024-08-03 19:24:05', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(99, 2, 'UPDATE', '2024-08-03 19:25:29', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(100, 2, 'UPDATE', '2024-08-03 19:25:30', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(101, 2, 'UPDATE', '2024-08-03 19:59:26', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(102, 2, 'UPDATE', '2024-08-03 19:59:26', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(103, 2, 'UPDATE', '2024-08-03 20:00:22', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(104, 2, 'UPDATE', '2024-08-03 20:00:22', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(105, 2, 'UPDATE', '2024-08-03 20:03:43', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(106, 3, 'UPDATE', '2024-08-03 20:04:40', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(107, 3, 'UPDATE', '2024-08-03 20:04:40', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(108, 2, 'UPDATE', '2024-08-04 13:15:36', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(109, 3, 'UPDATE', '2024-08-04 13:15:50', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(110, 2, 'UPDATE', '2024-08-04 13:22:23', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(111, 2, 'UPDATE', '2024-08-04 13:33:19', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(112, 3, 'UPDATE', '2024-08-04 13:48:30', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(113, 2, 'UPDATE', '2024-08-04 13:48:30', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(114, 3, 'UPDATE', '2024-08-04 13:50:13', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(115, 2, 'UPDATE', '2024-08-04 13:50:13', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(116, 3, 'UPDATE', '2024-08-04 14:28:28', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(117, 2, 'UPDATE', '2024-08-04 14:28:28', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(118, 2, 'UPDATE', '2024-08-04 15:04:04', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(119, 2, 'UPDATE', '2024-08-04 15:04:04', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(120, 3, 'UPDATE', '2024-08-04 15:04:34', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(121, 3, 'UPDATE', '2024-08-04 15:04:34', 'Nome: Coxinha -> Coxinha, Preço: 4.00 -> 4.00'),
(122, 2, 'UPDATE', '2024-08-05 12:22:40', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(123, 2, 'UPDATE', '2024-08-05 12:22:40', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(124, 2, 'UPDATE', '2024-08-05 12:24:24', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(125, 2, 'UPDATE', '2024-08-05 12:24:24', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(126, 2, 'UPDATE', '2024-08-05 12:26:29', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(127, 2, 'UPDATE', '2024-08-05 12:26:29', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(128, 2, 'UPDATE', '2024-08-05 12:26:41', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(129, 2, 'UPDATE', '2024-08-05 12:26:41', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(130, 2, 'UPDATE', '2024-08-05 12:29:19', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(131, 2, 'UPDATE', '2024-08-05 12:29:19', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(132, 2, 'UPDATE', '2024-08-05 12:32:23', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00'),
(133, 2, 'UPDATE', '2024-08-05 12:32:23', 'Nome: Coxinha -> Coxinha, Preço: 3.00 -> 3.00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `suporte`
--

CREATE TABLE `suporte` (
  `id` int NOT NULL,
  `cpf_suporte` char(11) NOT NULL,
  `cpf_cliente` char(11) DEFAULT NULL,
  `nome_cliente` varchar(100) DEFAULT NULL,
  `descricao_problema` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Despejando dados para a tabela `suporte`
--

INSERT INTO `suporte` (`id`, `cpf_suporte`, `cpf_cliente`, `nome_cliente`, `descricao_problema`) VALUES
(1, '71349267457', '71349267457', 'Ismael Gomes', 'Problema com o produto'),
(2, '71349267457', '71349267457', 'Ismael Gomes', 'tive'),
(3, '71349267457', '71349267457', 'ismael', 'nssajsajsijaokswpsspallsl'),
(4, '71349267457', '71349267457', 'Ismael Gomes', 'ohejaci'),
(5, '71349267457', '71349267457', 'Ismael Gomes', 'isso'),
(6, '71349267457', '71349267457', 'Ismael Gomes', 'tive'),
(7, '71349267457', '71349267457', 'Ismael Gomes', 'tive'),
(8, '71349267457', '71349267457', 'Ismael Gomes', 'podemos'),
(9, '71349267457', '71349267457', 'Ismael Gomes', 'possui'),
(10, '71349267457', '71349267457', 'Ismael Gomes', 'optamos');

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda`
--

CREATE TABLE `venda` (
  `id` int NOT NULL,
  `produto_id` int DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `data_venda` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `venda`
--

INSERT INTO `venda` (`id`, `produto_id`, `quantidade`, `data_venda`) VALUES
(48, 3, 99, '2024-08-03 20:04:41'),
(51, 2, 1, '2024-08-04 13:48:30'),
(52, 2, 1, '2024-08-04 13:50:13'),
(53, 2, 1, '2024-08-04 14:28:29'),
(54, 2, 1, '2024-08-04 15:04:05'),
(55, 3, 1, '2024-08-04 15:04:34'),
(56, 2, 1, '2024-08-05 12:22:40'),
(57, 2, 1, '2024-08-05 12:24:24'),
(58, 2, 1, '2024-08-05 12:26:30'),
(59, 2, 1, '2024-08-05 12:26:41'),
(60, 2, 1, '2024-08-05 12:29:19'),
(61, 2, 1, '2024-08-05 12:32:23');

--
-- Acionadores `venda`
--
DELIMITER $$
CREATE TRIGGER `after_venda_insert` AFTER INSERT ON `venda` FOR EACH ROW BEGIN
    UPDATE produto SET quantidade = quantidade - NEW.quantidade WHERE id = NEW.produto_id$$
DELIMITER ;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`matricula`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cpf`);

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
-- Índices de tabela `suporte`
--
ALTER TABLE `suporte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cpf_cliente` (`cpf_cliente`);

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
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `produto_auditoria`
--
ALTER TABLE `produto_auditoria`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=134;

--
-- AUTO_INCREMENT de tabela `suporte`
--
ALTER TABLE `suporte`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `suporte`
--
ALTER TABLE `suporte`
  ADD CONSTRAINT `suporte_ibfk_1` FOREIGN KEY (`cpf_cliente`) REFERENCES `cliente` (`cpf`);

--
-- Restrições para tabelas `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
