-- Criação do banco de dados
DROP DATABASE IF EXISTS mechanic_cloud;
CREATE DATABASE mechanic_cloud;
USE mechanic_cloud;

-- 1. Tabela estado
CREATE TABLE estado (
    id_estado BIGINT PRIMARY KEY,
    estado VARCHAR(45)
);

-- 2. Tabela cidade
CREATE TABLE cidade (
    id_cidade BIGINT PRIMARY KEY,
    cidade VARCHAR(45),
    fk_estado BIGINT,
    FOREIGN KEY (fk_estado) REFERENCES estado(id_estado)
);

-- 3. Tabela endereco
CREATE TABLE endereco (
    id_endereco BIGINT PRIMARY KEY,
    cep CHAR(9),
    logradouro VARCHAR(60),
    numero VARCHAR(10),
    complemento VARCHAR(45),
    unidade VARCHAR(4),
    bairro VARCHAR(45),
    fk_cidade BIGINT,
    FOREIGN KEY (fk_cidade) REFERENCES cidade(id_cidade)
);

-- 4. Tabela oficina
CREATE TABLE oficina (
    id_oficina BIGINT PRIMARY KEY,
    oficina VARCHAR(45),
    razao_social VARCHAR(45),
    cnpj CHAR(14),
    fk_endereco BIGINT,
    FOREIGN KEY (fk_endereco) REFERENCES endereco(id_endereco)
);

-- 5. Tabela fabricante
CREATE TABLE fabricante (
    id_fabricante BIGINT PRIMARY KEY,
    fabricante VARCHAR(45),
    sigla VARCHAR(4),
    data_criacao DATE,
    data_atualizacao DATE
);

-- 6. Tabela modelo
CREATE TABLE modelo (
    id_modelo BIGINT PRIMARY KEY,
    modelo VARCHAR(45),
    ano_modelo YEAR,
    ano_fabricacao YEAR,
    fk_fabricante BIGINT,
    FOREIGN KEY (fk_fabricante) REFERENCES fabricante(id_fabricante)
);

-- 7. Tabela empresa
CREATE TABLE empresa (
    id_empresa BIGINT PRIMARY KEY,
    razao_social VARCHAR(45),
    cnpj CHAR(14),
    status BOOLEAN,
    data_modificacao DATE,
    email VARCHAR(45),
    fk_oficina BIGINT,
    FOREIGN KEY (fk_oficina) REFERENCES oficina(id_oficina)
);

-- 8. Tabela filial
CREATE TABLE filial (
    id_filial BIGINT PRIMARY KEY,
    status BOOLEAN,
    data_cadastro DATE,
    fk_endereco BIGINT,
    fk_empresa BIGINT,
    FOREIGN KEY (fk_endereco) REFERENCES endereco(id_endereco),
    FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
);

-- 9. Tabela usuario
CREATE TABLE usuario (
    id_usuario CHAR(36) PRIMARY KEY,
    nome VARCHAR(65),
    sobrenome VARCHAR(65),
    data_nascimento DATE,
    cpf CHAR(9),
    senha VARCHAR(45),
    fk_oficina BIGINT,
    FOREIGN KEY (fk_oficina) REFERENCES oficina(id_oficina)
);

-- 10. Tabela veiculo
CREATE TABLE veiculo (
    id_veiculo BIGINT PRIMARY KEY,
    placa VARCHAR(10),
    data_cadastro DATETIME,
    data_atualizacao DATETIME,
    data_ultima_visita DATE,
    fk_modelo BIGINT,
    fk_filial BIGINT,
    fk_empresa BIGINT,
    FOREIGN KEY (fk_modelo) REFERENCES modelo(id_modelo),
    FOREIGN KEY (fk_filial) REFERENCES filial(id_filial),
    FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
);

-- 11. Tabela ordem_servico
CREATE TABLE ordem_servico (
    id_ordem_servico BIGINT PRIMARY KEY,
    data_entrada DATE,
    data_saida_prevista DATE,
    data_saida_efetiva DATE,
    status VARCHAR(45),
    descricao_problema VARCHAR(400),
    valor_total DECIMAL(10,2),
    fk_veiculo BIGINT,
    fk_filial BIGINT,
    fk_empresa BIGINT,
    FOREIGN KEY (fk_veiculo) REFERENCES veiculo(id_veiculo),
    FOREIGN KEY (fk_filial) REFERENCES filial(id_filial),
    FOREIGN KEY (fk_empresa) REFERENCES empresa(id_empresa)
);

-- 12. Tabela servico
CREATE TABLE servico (
    id_servico BIGINT PRIMARY KEY,
    servico VARCHAR(45),
    descricao VARCHAR(400),
    categoria VARCHAR(45),
    valor_padrao DECIMAL(10,2)
);

-- 13. Tabela servico_item
CREATE TABLE servico_item (
    id_servico_item VARCHAR(45) PRIMARY KEY,
    valor_item DECIMAL(10,2),
    quantidade INT,
    valor_subtotal DECIMAL(10,2),
    descricao_servico VARCHAR(400),
    fk_ordem_servico BIGINT,
    fk_servico BIGINT,
    FOREIGN KEY (fk_ordem_servico) REFERENCES ordem_servico(id_ordem_servico),
    FOREIGN KEY (fk_servico) REFERENCES servico(id_servico)
);