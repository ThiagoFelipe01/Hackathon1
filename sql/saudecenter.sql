-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19/06/2024 às 02:49
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

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

-- --------------------------------------------------------

--
-- Estrutura para tabela `agendamentos`
--

CREATE TABLE `agendamentos` (
  `id` int(11) NOT NULL,
  `idoso_id` int(11) DEFAULT NULL,
  `data_agendamento` date DEFAULT NULL,
  `horario` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `alertas`
--

CREATE TABLE `alertas` (
  `id` int(11) NOT NULL,
  `idoso_id` int(11) DEFAULT NULL,
  `mensagem` text DEFAULT NULL,
  `data_alerta` date DEFAULT NULL,
  `horario_alerta` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `historico_vacinacao`
--

CREATE TABLE `historico_vacinacao` (
  `id` int(11) NOT NULL,
  `idoso_id` int(11) DEFAULT NULL,
  `vacina_id` int(11) DEFAULT NULL,
  `data_vacinacao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `idosos`
--

CREATE TABLE `idosos` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `idade` int(11) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `historico_medico` text DEFAULT NULL,
  `alergias` text DEFAULT NULL,
  `condicoes_preexistentes` text DEFAULT NULL,
  `observacoes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `idosos`
--

INSERT INTO `idosos` (`id`, `nome`, `idade`, `endereco`, `telefone`, `historico_medico`, `alergias`, `condicoes_preexistentes`, `observacoes`) VALUES
(1, 'João da Silva', 78, 'Área rural, S/N, Cidade', '(44) 9999-9999', 'Histórico de hipertensão', 'Nenhuma', 'Diabetes', 'Mobilidade reduzida');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipo_usuario` enum('agente_saude','cuidador') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`, `tipo_usuario`) VALUES
(1, 'Agente de Saúde 1', 'agente1@saude.com', 'e7d80ffeefa212b7c5c55700e4f7193e', 'agente_saude'),
(2, 'Cuidador 1', 'cuidador1@familia.com', 'e7d80ffeefa212b7c5c55700e4f7193e', 'cuidador');

-- --------------------------------------------------------

--
-- Estrutura para tabela `vacinas`
--

CREATE TABLE `vacinas` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descricao` text DEFAULT NULL,
  `intervalo_recomendado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `vacinas`
--

INSERT INTO `vacinas` (`id`, `nome`, `descricao`, `intervalo_recomendado`) VALUES
(1, 'Gripe', 'Vacina contra a gripe sazonal', 12),
(2, 'Pneumonia', 'Vacina contra pneumonia', 24);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idoso_id` (`idoso_id`);

--
-- Índices de tabela `alertas`
--
ALTER TABLE `alertas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idoso_id` (`idoso_id`);

--
-- Índices de tabela `historico_vacinacao`
--
ALTER TABLE `historico_vacinacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idoso_id` (`idoso_id`),
  ADD KEY `vacina_id` (`vacina_id`);

--
-- Índices de tabela `idosos`
--
ALTER TABLE `idosos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `alertas`
--
ALTER TABLE `alertas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `historico_vacinacao`
--
ALTER TABLE `historico_vacinacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `idosos`
--
ALTER TABLE `idosos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `vacinas`
--
ALTER TABLE `vacinas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD CONSTRAINT `agendamentos_ibfk_1` FOREIGN KEY (`idoso_id`) REFERENCES `idosos` (`id`);

--
-- Restrições para tabelas `alertas`
--
ALTER TABLE `alertas`
  ADD CONSTRAINT `alertas_ibfk_1` FOREIGN KEY (`idoso_id`) REFERENCES `idosos` (`id`);

--
-- Restrições para tabelas `historico_vacinacao`
--
ALTER TABLE `historico_vacinacao`
  ADD CONSTRAINT `historico_vacinacao_ibfk_1` FOREIGN KEY (`idoso_id`) REFERENCES `idosos` (`id`),
  ADD CONSTRAINT `historico_vacinacao_ibfk_2` FOREIGN KEY (`vacina_id`) REFERENCES `vacinas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
