-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 24/06/2024 às 06:13
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `saudecenter`
--
CREATE DATABASE IF NOT EXISTS `saudecenter` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `saudecenter`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `agendamentos`
--

DROP TABLE IF EXISTS `agendamentos`;
CREATE TABLE `agendamentos` (
  `id` int(10) UNSIGNED NOT NULL,
  `idoso_id` int(10) UNSIGNED NOT NULL,
  `data_agendamento` date DEFAULT NULL,
  `horario` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `alertas`
--

DROP TABLE IF EXISTS `alertas`;
CREATE TABLE `alertas` (
  `id` int(10) UNSIGNED NOT NULL,
  `idoso_id` int(10) UNSIGNED NOT NULL,
  `mensagem` text DEFAULT NULL,
  `data_alerta` date DEFAULT NULL,
  `horario_alerta` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `historico_vacinacao`
--

DROP TABLE IF EXISTS `historico_vacinacao`;
CREATE TABLE `historico_vacinacao` (
  `id` int(10) UNSIGNED NOT NULL,
  `idoso_id` int(10) UNSIGNED NOT NULL,
  `vacina_id` int(10) UNSIGNED NOT NULL,
  `data_vacinacao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `idosos`
--

DROP TABLE IF EXISTS `idosos`;
CREATE TABLE `idosos` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(255) NOT NULL,
  `idade` int(11) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `historico_medico` text DEFAULT NULL,
  `alergias` text DEFAULT NULL,
  `condicoes_preexistentes` text DEFAULT NULL,
  `observacoes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `knex_migrations`
--

DROP TABLE IF EXISTS `knex_migrations`;
CREATE TABLE `knex_migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `batch` int(11) DEFAULT NULL,
  `migration_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `knex_migrations`
--

INSERT INTO `knex_migrations` (`id`, `name`, `batch`, `migration_time`) VALUES
(1, '20240622213748_createIdosos.js', 1, '2024-06-24 04:12:13'),
(2, '20240622213911_createVacinas.js', 1, '2024-06-24 04:12:13'),
(3, '20240622230514_createHistorico_vacinacao.js', 1, '2024-06-24 04:12:14'),
(4, '20240623042729_createAgendamentos.js', 1, '2024-06-24 04:12:14'),
(5, '20240623042808_createAlertas.js', 1, '2024-06-24 04:12:14'),
(6, '20240623225057_createUsuarios.js', 1, '2024-06-24 04:12:14');

-- --------------------------------------------------------

--
-- Estrutura para tabela `knex_migrations_lock`
--

DROP TABLE IF EXISTS `knex_migrations_lock`;
CREATE TABLE `knex_migrations_lock` (
  `index` int(10) UNSIGNED NOT NULL,
  `is_locked` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `knex_migrations_lock`
--

INSERT INTO `knex_migrations_lock` (`index`, `is_locked`) VALUES
(1, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `vacinas`
--

DROP TABLE IF EXISTS `vacinas`;
CREATE TABLE `vacinas` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` text DEFAULT NULL,
  `intervalo_recomendado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `agendamentos_idoso_id_foreign` (`idoso_id`);

--
-- Índices de tabela `alertas`
--
ALTER TABLE `alertas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `alertas_idoso_id_foreign` (`idoso_id`);

--
-- Índices de tabela `historico_vacinacao`
--
ALTER TABLE `historico_vacinacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `historico_vacinacao_idoso_id_foreign` (`idoso_id`),
  ADD KEY `historico_vacinacao_vacina_id_foreign` (`vacina_id`);

--
-- Índices de tabela `idosos`
--
ALTER TABLE `idosos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `knex_migrations`
--
ALTER TABLE `knex_migrations`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `knex_migrations_lock`
--
ALTER TABLE `knex_migrations_lock`
  ADD PRIMARY KEY (`index`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `vacinas`
--
ALTER TABLE `vacinas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `agendamentos`
--
ALTER TABLE `agendamentos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `alertas`
--
ALTER TABLE `alertas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `historico_vacinacao`
--
ALTER TABLE `historico_vacinacao`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `idosos`
--
ALTER TABLE `idosos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `knex_migrations`
--
ALTER TABLE `knex_migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `knex_migrations_lock`
--
ALTER TABLE `knex_migrations_lock`
  MODIFY `index` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `vacinas`
--
ALTER TABLE `vacinas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD CONSTRAINT `agendamentos_idoso_id_foreign` FOREIGN KEY (`idoso_id`) REFERENCES `idosos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrições para tabelas `alertas`
--
ALTER TABLE `alertas`
  ADD CONSTRAINT `alertas_idoso_id_foreign` FOREIGN KEY (`idoso_id`) REFERENCES `idosos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrições para tabelas `historico_vacinacao`
--
ALTER TABLE `historico_vacinacao`
  ADD CONSTRAINT `historico_vacinacao_idoso_id_foreign` FOREIGN KEY (`idoso_id`) REFERENCES `idosos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `historico_vacinacao_vacina_id_foreign` FOREIGN KEY (`vacina_id`) REFERENCES `vacinas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
