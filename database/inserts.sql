-- Use o banco de dados recém-criado
USE mechanic_cloud;

-- Inserções de Dados

-- 1. Estado
INSERT INTO estado (id_estado, estado) VALUES
(1, 'São Paulo');

-- 2. Cidade (no mesmo estado)
INSERT INTO cidade (id_cidade, cidade, fk_estado) VALUES
(1, 'Campinas', 1);

-- 3. Endereços (2 endereços na mesma cidade)
-- Endereço 1 (para a Oficina)
INSERT INTO endereco (id_endereco, cep, logradouro, numero, complemento, unidade, bairro, fk_cidade) VALUES
(1, '13010-000', 'Rua da Oficina, 123', '123', 'Fundos', 'A', 'Centro', 1);

-- Endereço 2 (um endereço adicional na mesma cidade)
INSERT INTO endereco (id_endereco, cep, logradouro, numero, complemento, unidade, bairro, fk_cidade) VALUES
(2, '13020-000', 'Avenida Principal, 456', '456', 'Sala 10', 'B', 'Cambuí', 1);

-- 4. Oficina (com fk_endereco referenciando o primeiro endereço)
INSERT INTO oficina (id_oficina, oficina, razao_social, cnpj, fk_endereco) VALUES
(1, 'Oficina Mecânica Central', 'Oficina Mecânica Central Ltda.', '12345678000190', 1);

-- 5. Usuários (2 usuários com IDs UUID formatados como CHAR(36), referenciando a oficina criada)
INSERT INTO usuario (id_usuario, nome, sobrenome, data_nascimento, cpf, senha, fk_oficina) VALUES
('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'Ana', 'Souza', '1990-05-15', '123456789', 'senha_hash_ana', 1),
('f0e9d8c7-b6a5-4321-fedc-ba9876543210', 'Carlos', 'Pereira', '1985-11-22', '987654321', 'senha_hash_carlos', 1);

-- Nota: As tabelas 'fabricante', 'modelo', 'empresa', 'filial', 'veiculo', 'ordem_servico', 'servico', e 'servico_item' não foram solicitadas para inserção de dados neste momento.