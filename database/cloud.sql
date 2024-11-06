CREATE DATABASE mechanic;
USE mechanic;

CREATE TABLE usuario (
  idUsuario INT primary key auto_increment,
  nomeCompleto VARCHAR(60),
  email VARCHAR(60),
  senha VARCHAR(45),
  telefone CHAR(12)
);

CREATE TABLE oficina (
  idOficina INT primary key auto_increment,
  fkUsuario INT,
  CONSTRAINT fkOficinaUsuario FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario),
  nome VARCHAR(60),
  cnpj CHAR(14)
);

INSERT INTO usuario VALUES
  (DEFAULT, 'Roberto Cruz Dias', 'roberto@flexcars.com', '123@#Roberto', '11922346211'),
  (DEFAULT, 'Francisco Geosmar', 'geo@reformadora.com', '123@#Geosmar', '11951347866');

INSERT INTO oficina VALUES 
  (DEFAULT, 1, 'Flex Mecanica', '11222333000199'),
  (DEFAULT, 2, 'Geosmar Reformadora', '22555333000177');

-- Select para ver o Dono e suas oficinas
SELECT u.nomeCompleto as Dono,
  u.email as 'E-mail',
  u.senha as Senha,
  o.nome as Oficina,
  o.cnpj as CNPJ
  FROM oficina as o
  JOIN usuario as u
  ON o.fkUsuario = u.idUsuario;

-- Sessão dos dados de Software da Oficina;
CREATE TABLE empresa (
  idEmpresa INT primary key auto_increment,
  razaoSocial CHAR(60),
  cnpj CHAR(14)
)

CREATE TABLE cliente (
  idCliente INT primary key auto_increment,
  fkEmpresa INT,
  CONSTRAINT fkEmpresaCliente FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa),
  nome VARCHAR(60),
  email VARCHAR(60),
  telefone CHAR(12)
);

INSERT INTO empresa (razaoSocial, cnpj) VALUES 
('Alpha Tech Solutions', '12345678000101'),
('Beta Innovators', '23456789000102'),
('Gamma Enterprises', '34567890000103'),
('Delta Dynamics', '45678900000104');

SELECT * from empresa;
-- Clientes com empresa
INSERT INTO cliente (fkEmpresa, nome, email, telefone) VALUES 
(1, 'João Almeida', 'joao.almeida@example.com', '11987654321'),
(2, 'Mariana Silva', 'mariana.silva@example.com', '21987654322'),
(3, 'Pedro Rocha', 'pedro.rocha@example.com', '31987654323'),
(4, 'Larissa Costa', 'larissa.costa@example.com', '41987654324');

-- Clientes sem empresa
INSERT INTO cliente (fkEmpresa, nome, email, telefone) VALUES 
(NULL, 'Rafael Mendes', 'rafael.mendes@example.com', '51987654325'),
(NULL, 'Julia Martins', 'julia.martins@example.com', '61987654326');


SELECT * from cliente;
-- Select para ver 
SELECT c.nome as Cliente,
  c.email as 'E-mail',
  c.telefone as Telefone,
  IFNULL(e.razaoSocial, 'Sem empresa') as Empresa,
  IFNULL(e.cnpj, '') as CNPJ
  FROM cliente as c
  LEFT JOIN empresa as e
  ON c.fkEmpresa = e.idEmpresa;

CREATE TABLE veiculo (
  idVeiculo INT primary key auto_increment,
  fkCliente INT,
  CONSTRAINT fkVeiculoCliente FOREIGN KEY (fkCliente) REFERENCES cliente(idCliente),
  marca VARCHAR(45),
  modelo VARCHAR(45),
  ano YEAR,
  placa CHAR(7)
);

-- Entrada & Saída de veiculos
CREATE TABLE hospedagem (
  idHospedagem INT primary key auto_increment,
  fkVeiculo INT,
  CONSTRAINT fkVeiculoHospedagem FOREIGN KEY (fkVeiculo) REFERENCES veiculo(idVeiculo),
  dataEntrada DATE,
  dataSaida DATE,
  nomeMotorista VARCHAR(60),
  rg CHAR(9),
  cpf CHAR(9)
)

-- Veículos associados a clientes
INSERT INTO veiculo (fkCliente, marca, modelo, ano, placa) VALUES 
(1, 'Toyota', 'Corolla', 2019, 'ABC1234'),
(2, 'Honda', 'Civic', 2020, 'DEF5678'),
(3, 'Ford', 'Focus', 2018, 'GHI9012'),
(4, 'Chevrolet', 'Cruze', 2021, 'JKL3456'),
(2, 'Hyundai', 'Elantra', 2017, 'MNO7890'),
(3, 'Nissan', 'Sentra', 2016, 'PQR1234');

-- Veículos sem associação a clientes
INSERT INTO veiculo (fkCliente, marca, modelo, ano, placa) VALUES 
(NULL, 'Volkswagen', 'Jetta', 2015, 'STU5678'),
(NULL, 'Kia', 'Optima', 2014, 'VWX9012'),
(NULL, 'Mazda', '3', 2013, 'YZA3456'),
(NULL, 'Subaru', 'Impreza', 2012, 'BCD7890');

select * from veiculo;

-- Associando hospedagens a alguns veículos
INSERT INTO hospedagem (fkVeiculo, dataEntrada, dataSaida, nomeMotorista, rg, cpf) VALUES 
(1, '2024-01-05', '2024-01-10', 'João Almeida', '123456789', '111223330'),
(2, '2024-01-07', '2024-01-12', 'Mariana Silva', '234567890', '223344411'),
(3, '2024-01-09', '2024-01-14', 'Pedro Rocha', '345678901', '334445522'),
(4, '2024-01-11', '2024-01-16', 'Larissa Costa', '456789012', '445566633'),
(5, '2024-01-13', '2024-01-18', 'Mariana Silva', '567890123', '556667744'),
(6, '2024-01-15', '2024-01-20', 'Pedro Rocha', '678901234', '667778885');

SELECT CONCAT(v.marca, ' - ', v.modelo,', ', v.ano, ' (',v.placa,')') as Veiculo,
  h.dataEntrada as Entrada,
  h.dataSaida as Saída
  FROM veiculo as v
  JOIN hospedagem as h
  ON h.fkVeiculo = v.idVeiculo;

SELECT c.nome as Cliente,
  c.email as 'E-mail',
  IFNULL(e.razaoSocial, 'Sem empresa') as Empresa,
  IFNULL(e.cnpj, 'Sem CNPJ') as CNPJ,
  GROUP_CONCAT(IFNULL(CONCAT(v.marca, ' - ', v.modelo,', ', v.ano, ' (',v.placa,')'), 'Sem veículo')) as Veículo,
  IFNULL(h.dataEntrada, '') as 'Data de Entrada',
  IFNULL(h.dataSaida, '') as 'Data de Saída',
  IFNULL(h.nomeMotorista, '') as 'Motorista',
  IFNULL(h.rg, '') as 'RG do Motorista', 
  IFNULL(h.cpf, '') as 'CPF do Motorista'
  FROM cliente as c
  left JOIN empresa as e
  ON c.fkEmpresa = e.idEmpresa
  left JOIN veiculo as v
  ON v.fkCliente = c.idCliente
  left JOIN hospedagem as h
  ON h.fkVeiculo = v.idVeiculo
  GROUP BY c.nome, c.email, e.razaoSocial, e.cnpj, h.dataEntrada, h.dataSaida, h.nomeMotorista,h.rg, h.cpf
  ORDER BY h.dataEntrada desc;

-- Sistema de Orçamentos
CREATE TABLE orcamento (
  idOrcamento INT primary key auto_increment,
  fkVeiculo INT,
  CONSTRAINT fkOrcamentoVeiculo FOREIGN KEY (fkVeiculo) REFERENCES veiculo(idVeiculo),
  dataLancamento DATE,
  orcStatus CHAR(9),
  CONSTRAINT chkStatus CHECK (orcStatus in ('Pendente', 'Aprovado', 'Concluido')) 
)

CREATE TABLE servico (
  idServico INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100)
);

CREATE TABLE historico (
  idHistorico INT AUTO_INCREMENT,
  fkServico INT,
  fkOrcamento INT,
  -- fkVeiculo INT,
  CONSTRAINT pkComposta PRIMARY KEY (idHistorico, fkServico, fkOrcamento),
  CONSTRAINT fkHistoricoServico FOREIGN KEY (fkServico) REFERENCES servico(idServico),
  CONSTRAINT fkHistoricoOrcamento FOREIGN KEY (fkOrcamento) REFERENCES orcamento(idOrcamento),
  -- CONSTRAINT fkHistoricoVeiculo FOREIGN KEY (fkVeiculo) REFERENCES veiculo(idVeiculo),
  valor FLOAT,
  tipo CHAR(15),
  CONSTRAINT chkTipo CHECK (tipo in ('Funilaria', 'Pintura', 'Mecanica'))
)

INSERT INTO orcamento VALUES 
  (default, 6, '2024-01-11', 'Pendente'),
  (default, 9, '2024-01-11', 'Pendente'),
  (default, 10, '2024-01-11', 'Pendente');


INSERT INTO servico VALUES 
  (DEFAULT, 'Porta Lateral LD Motorista (Azul/Prata/Verniz)'),
  (DEFAULT, 'Troca da Direção'),
  (DEFAULT, 'Parachoque Dianteiro'),
  (DEFAULT, 'Parachoque Dianteiro (Azul/Verniz)'),
  (DEFAULT, 'Parachoque Traseiro (Vermelho/Preto/Verniz)'),
  (DEFAULT, 'Parachoque Dianteiro (Vermelho/Preto/Verniz)'),
  (DEFAULT, 'Porta Dianteiro LExLD (Vermelho/Preto/Verniz)'),
  (DEFAULT, 'Troca da bomba de gasolina'),
  (DEFAULT, 'Porta Lateral LD Motorista (Branco/Verniz)');

INSERT INTO historico VALUES
  (DEFAULT, 1, 1, 300, 'Pintura'),
  (DEFAULT, 2, 1, 600, 'Mecanica'),
  (DEFAULT, 3, 1, 350, 'Funilaria'),
  (DEFAULT, 4, 1, 300, 'Pintura'),
  (DEFAULT, 5, 2, 300, 'Pintura'),
  (DEFAULT, 6, 2, 300, 'Pintura'),
  (DEFAULT, 7, 2, 750, 'Pintura'),
  (DEFAULT, 8, 3, 500, 'Mecanica'),
  (DEFAULT, 9, 3, 300, 'Pintura');

  SELECT CONCAT(v.marca, ' - ', v.modelo,', ', v.ano, ' (',v.placa,')') as Veiculo,
  o.dataLancamento as 'Data do Orçamento',
  o.orcStatus as 'Status do Orçamento',
  CONCAT('R$',hi.valor, ' - ', hi.tipo, ': ', s.descricao) 
  FROM veiculo as v
  JOIN orcamento as o
  ON o.fkVeiculo = v.idVeiculo
  JOIN historico as hi
  ON hi.fkOrcamento = o.idOrcamento
  JOIN servico as s
  ON hi.fkServico = s.idServico;

SELECT c.nome as Cliente,
  c.email as 'E-mail',
  IFNULL(e.razaoSocial, 'Sem empresa') as Empresa,
  IFNULL(e.cnpj, 'Sem CNPJ') as CNPJ,
  IFNULL(CONCAT(v.marca, ' - ', v.modelo,', ', v.ano, ' (',v.placa,')'), 'Sem veículo') as Veículo,
  IFNULL(h.dataEntrada, '') as 'Data de Entrada',
  IFNULL(h.dataSaida, '') as 'Data de Saída',
  IFNULL(h.nomeMotorista, '') as 'Motorista',
  IFNULL(h.rg, '') as 'RG do Motorista', 
  IFNULL(h.cpf, '') as 'CPF do Motorista',
  o.dataLancamento as 'Data do Orçamento',
  o.orcStatus as 'Status do Orçamento',
  CONCAT('R$',hi.valor, ' - ', hi.tipo, ': ', s.descricao) as Orçamento
  FROM cliente as c
  left JOIN empresa as e
  ON c.fkEmpresa = e.idEmpresa
  left JOIN veiculo as v
  ON v.fkCliente = c.idCliente
  left JOIN hospedagem as h
  ON h.fkVeiculo = v.idVeiculo
  JOIN orcamento as o
  ON o.fkVeiculo = v.idVeiculo
  JOIN historico as hi
  ON hi.fkOrcamento = o.idOrcamento
  JOIN servico as s
  ON hi.fkServico = s.idServico
  -- GROUP BY c.nome, c.email, e.razaoSocial, e.cnpj, h.dataEntrada, h.dataSaida, h.nomeMotorista,h.rg, h.cpf, o.dataLancamento, o.orcStatus, hi.valor, hi.tipo, s.descricao
  ORDER BY h.dataEntrada desc;
