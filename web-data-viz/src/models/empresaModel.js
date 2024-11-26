var database = require("../database/config");

function buscarOficina(idUsuario) {
  var instrucaoSql = `SELECT nome FROM oficina WHERE fkUsuario = ${idUsuario}`;

  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function buscarClientesVeiculos(idOficina) {
  var instrucaoSql = `
  select COUNT(c.idCliente) as TotalC,
    COUNT(v.idVeiculo) as TotalV
    FROM cliente as c
    LEFT JOIN veiculo as v
    ON v.fkCliente = c.idCliente
    WHERE fkOficina = ${idOficina};`;

  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function buscarCliente(cpf) {
  var instrucaoSql = `SELECT idCliente FROM cliente WHERE cpf = ${cpf};`;

  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function cadastrarVeiculo(idCliente, marca, modelo, ano, placa) {
  var instrucaoSql = `INSERT INTO veiculo (fkCliente, marca, modelo, ano, placa) VALUES (${idCliente}, '${marca}', '${modelo}', '${ano}', '${placa}');`;

  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function cadastrarCliente(idMecanico, nome, email, telefone, cpf) {
  var instrucaoSql = `INSERT INTO cliente (fkOficina, nome, email, telefone, cpf) VALUES 
    (${idMecanico}, '${nome}', '${email}', '${telefone}', '${cpf}');`;

  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function cadastrarEmpresa(idCliente, empresa, cnpj) {
  var instrucaoSql = `INSERT INTO empresa (razaoSocial, cnpj, fkDono) VALUES 
    ('${empresa}', '${cnpj}', ${idCliente});`;

  console.log("Executando a instrução SQL: \n" + instrucaoSql);
  return database.executar(instrucaoSql);
}

function buscarPorId(id) {
  var instrucaoSql = `SELECT * FROM empresa WHERE id = '${id}'`;

  return database.executar(instrucaoSql);
}

function listar(id) {
  var instrucaoSql = `SELECT idCliente, nome FROM cliente WHERE fkOficina = ${id};`;

  return database.executar(instrucaoSql);
}

function listarVeiculoCliente(id) {
  var instrucaoSql = `SELECT idVeiculo, CONCAT(v.marca, ' ',v.modelo, ' ', v.ano) as Veiculo FROM cliente as c  JOIN veiculo as v  ON v.fkCliente = c.idCliente WHERE idCliente = ${id};`;
  console.log(instrucaoSql);

  return database.executar(instrucaoSql);
}

function buscarTotalRendaConcluida(idOficina) {
  var instrucaoSql = `SELECT SUM(his.valor) as total FROM historico as his JOIN orcamento as o ON his.fkOrcamento = o.idOrcamento JOIN veiculo as v ON o.fkVeiculo = v.idVeiculo JOIN cliente as c ON v.fkCliente = c.idCliente JOIN oficina as ofc ON c.fkOficina = ofc.idOficina WHERE o.orcStatus = 'Concluido' and ofc.idOficina = ${idOficina};
`;
  
  console.log(instrucaoSql);

  return database.executar(instrucaoSql);
}

function buscarPorCnpj(cnpj) {
  var instrucaoSql = `SELECT * FROM empresa WHERE cnpj = '${cnpj}'`;

  return database.executar(instrucaoSql);
}

function cadastrar(razaoSocial, cnpj) {
  var instrucaoSql = `INSERT INTO empresa (razao_social, cnpj) VALUES ('${razaoSocial}', '${cnpj}')`;

  return database.executar(instrucaoSql);
}

function CadastrarOrcamento(fkVeiculo, dataOrcamento) {
  var instrucaoSql = `INSERT INTO orcamento (fkVeiculo, dataLancamento, orcStatus) VALUES (${fkVeiculo}, '${dataOrcamento}'  , 'Pendente');`;

  console.log('EXECUTANDO NO MYSQL: ', instrucaoSql)

  return database.executar(instrucaoSql);
}

function SelecionarOrcamento(fkVeiculo, dataOrcamento) {
  var instrucaoSql = `SELECT idOrcamento FROM orcamento WHERE fkVeiculo = ${fkVeiculo} and dataLancamento = '${dataOrcamento}';`;

  console.log('EXECUTANDO NO MYSQL: ', instrucaoSql)

  return database.executar(instrucaoSql);
}

function SelecionarOrcamentoCompleto(idOficina) {
  var instrucaoSql = `SELECT o.idOrcamento as id, c.nome as cliente, o.orcStatus as status, o.dataLancamento as data, CONCAT(v.marca, ' ',v.modelo, ' ', v.ano) as veiculo,CONCAT('R$',(SUM(his.valor))) as total,GROUP_CONCAT(s.descricao) as servicos FROM veiculo as v JOIN orcamento as o ON o.fkVeiculo = v.idVeiculo JOIN historico as his ON his.fkOrcamento = o.idOrcamento JOIN servico as s ON his.fkServico = s.idServico JOIN cliente as c ON v.fkCliente = c.idCliente WHERE c.fkOficina = ${idOficina} GROUP BY o.idOrcamento, c.nome, o.orcStatus, o.dataLancamento, v.marca, v.modelo, v.ano;`;

  console.log('EXECUTANDO NO MYSQL: ', instrucaoSql)

  var comando = database.executar(instrucaoSql);
  console.log(comando);

  return comando
}

function SelecionarIDsServicos(idOrcamento) {
  var instrucaoSql = `SELECT idServico from servico as s JOIN historico as his ON his.fkServico = s.idServico where his.fkOrcamento = ${idOrcamento};`;

  console.log('EXECUTANDO NO MYSQL: ', instrucaoSql)

  return database.executar(instrucaoSql);
}

function selecionarDadosServicos(idOficina) {
  var instrucaoSql = `select his.tipo as servico, COUNT(his.tipo) as total, SUM(his.valor) as valor FROM historico as his JOIN orcamento as o ON his.fkOrcamento = o.idOrcamento JOIN veiculo as v  ON o.fkVeiculo = v.idVeiculo JOIN cliente as c ON v.fkCliente = c.idCliente WHERE c.fkOficina = ${idOficina} GROUP BY his.tipo ORDER BY his.tipo asc;`;

  console.log('EXECUTANDO NO MYSQL: ', instrucaoSql)

  return database.executar(instrucaoSql);
}

function CadastrarServico(descricao) {
  var instrucaoSql = `INSERT INTO servico(descricao) VALUES ('${descricao}');`;

  console.log('EXECUTANDO NO MYSQL: ', instrucaoSql)

  return database.executar(instrucaoSql);
}

function SelecionarServico(descricao) {
  var instrucaoSql = `SELECT idServico from servico where descricao = '${descricao}';`;

  return database.executar(instrucaoSql);
}

function CadastrarHistorico(fkOrcamento, fkServico, tipo, valor) {
  var instrucaoSql = `INSERT INTO historico(fkOrcamento, fkServico, valor, tipo) VALUES (${fkOrcamento}, ${fkServico}, ${valor}, '${tipo}') `;

  return database.executar(instrucaoSql);
}

function deleteHistorico(idOrcamento) {
  var instrucaoSql = `DELETE FROM historico WHERE fkOrcamento = ${idOrcamento};`

  console.log('REALIZANDO O COMANDO: ', instrucaoSql)

  return database.executar(instrucaoSql)
}

function deleteServico(idServico) {
  var instrucaoSql = `DELETE FROM servico WHERE idServico = ${idServico};`

  console.log('REALIZANDO O COMANDO: ', instrucaoSql)

  return database.executar(instrucaoSql)
}

function deleteOrcamento(idOrcamento) {
  var instrucaoSql = `DELETE FROM orcamento WHERE idOrcamento = ${idOrcamento};`

  console.log('REALIZANDO O COMANDO: ', instrucaoSql)

  return database.executar(instrucaoSql)
}

function updateOrcamento(idOrcamento, status) {
  var instrucaoSql = `UPDATE orcamento SET orcStatus = '${status}' WHERE idOrcamento = ${idOrcamento}`

  console.log('REALIZANDO O COMANDO: ', instrucaoSql)

  return database.executar(instrucaoSql)
}


module.exports = {
  cadastrarVeiculo,
  cadastrarCliente,
  cadastrarEmpresa,
  buscarOficina,
  buscarClientesVeiculos,
  buscarCliente,
  buscarPorCnpj,
  buscarPorId,
  cadastrar,
  listar,
  listarVeiculoCliente,
  CadastrarOrcamento,
  SelecionarOrcamento,
  SelecionarOrcamentoCompleto,
  CadastrarServico,
  SelecionarServico,
  CadastrarHistorico,
  selecionarDadosServicos,
  SelecionarIDsServicos,
  buscarTotalRendaConcluida,
  deleteHistorico,
  deleteServico,
  deleteOrcamento,
  updateOrcamento,
};
