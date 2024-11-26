var empresaModel = require("../models/empresaModel");

function buscarOficina(req, res) {
  var idUsuario = req.params.idUsuario;

  empresaModel
    .buscarOficina(idUsuario)
    .then((resultado) => {
      if (resultado.length > 0) {
        res.status(200).json(resultado);
      } else {
        res.status(204).json([]);
      }
    })
    .catch(function (erro) {
      console.log(erro);
      console.log("Houve um erro ao buscar os aquarios: ", erro.sqlMessage);
      res.status(500).json(erro.sqlMessage);
    });
}

function cadastrarVeiculo(req, res) {
  var idCliente = req.body.idCliente;
  var marca = req.body.marca;
  var modelo = req.body.modelo;
  var ano = req.body.ano;
  var placa = req.body.placa;

  empresaModel
    .cadastrarVeiculo(idCliente, marca, modelo, ano, placa)
    .then((resultado) => {
      if (resultado.length > 0) {
        res.status(200).json(resultado);
      } else {
        res.status(204).json([]);
      }
    })
    .catch(function (erro) {
      console.log(erro);
      console.log(
        "Houve um erro ao cadastrar o veículo do Cliente: ",
        erro.sqlMessage
      );
      res.status(500).json(erro.sqlMessage);
    });
}

function cadastrarCliente(req, res) {
  chkEmpresa = req.body.chkEmpresa;
  idMecanico = req.body.idMecanico;
  nome = req.body.nome;
  email = req.body.email;
  telefone = req.body.telefone;
  cpf = req.body.cpf;
  empresa = req.body.empresa;
  cnpj = req.body.cnpj;

  empresaModel
    .cadastrarCliente(idMecanico, nome, email, telefone, cpf)
    .then((resultado) => {
      console.log("Passei o Cadastro do Cliente, indo pro Buscar Cliente");

      if (chkEmpresa == true) {
        empresaModel
          .buscarCliente(cpf)
          .then((resultadoCliente) => {
            if (resultadoCliente.length > 0) {
              res.status(200).json(resultadoCliente);

              var idCliente = resultadoCliente[0].idCliente;
              empresaModel
                .cadastrarEmpresa(idCliente, empresa, cnpj)
                .then((resultadoCliente) => {
                  if (resultadoCliente.length > 0) {
                    res.status(200).json(resultadoCliente);
                  } else {
                    res.status(204).json([]);
                  }
                })
                .catch(function (erro) {
                  console.log(erro);
                  console.log(
                    "Houve um erro ao cadastrar a empresa do Cliente: ",
                    erro.sqlMessage
                  );
                  res.status(500).json(erro.sqlMessage);
                });
            } else {
              res.status(200).json(resultado);
            }
          })
          .catch(function (erro) {
            console.log(erro);
            console.log(
              "Houve um erro ao select no ID do Cliente: ",
              erro.sqlMessage
            );
            res.status(500).json(erro.sqlMessage);
          });
      } else {
        console.log("Cliente não possuí empresa");
        res.status(204).json([]);
      }
    })
    .catch(function (erro) {
      console.log(erro);
      console.log("Houve um erro ao cadastrar o Cliente: ", erro.sqlMessage);
      res.status(500).json(erro.sqlMessage);
    });
}

function buscarPorCnpj(req, res) {
  var cnpj = req.query.cnpj;

  empresaModel.buscarPorCnpj(cnpj).then((resultado) => {
    res.status(200).json(resultado);
  });
}

function listar(req, res) {
  var id = req.body.id;

  empresaModel.listar(id).then((resultado) => {
    res.status(200).json(resultado);
  });
}

function listarVeiculoCliente(req, res) {
  var id = req.body.id;

  console.log(id);

  empresaModel.listarVeiculoCliente(id).then((resultado) => {
    res.status(200).json(resultado);
  });
}

function buscarPorId(req, res) {
  var id = req.query.id;

  empresaModel.buscarPorId(id).then((resultado) => {
    res.status(200).json(resultado);
  });
}

function cadastrar(req, res) {
  var cnpj = req.body.cnpj;
  var razaoSocial = req.body.razaoSocial;

  empresaModel.buscarPorCnpj(cnpj).then((resultado) => {
    if (resultado.length > 0) {
      res
        .status(401)
        .json({ mensagem: `a empresa com o cnpj ${cnpj} já existe` });
    } else {
      empresaModel.cadastrar(razaoSocial, cnpj).then((resultado) => {
        res.status(201).json(resultado);
      });
    }
  });
}

function buscarTotalClientesVeiculos(req, res) {
  var idUsuario = req.body.id;

  empresaModel
    .buscarClientesVeiculos(idUsuario)
    .then((resultado) => {
      console.log(resultado);
      if (resultado.length > 0) {
        res.status(200).json(resultado);
      } else {
        res.status(204).json([]);
      }
    })
    .catch(function (erro) {
      console.log(erro);
      console.log("Houve um erro ao buscar os aquarios: ", erro.sqlMessage);
      res.status(500).json(erro.sqlMessage);
    });
}

function buscarTotalRendaConcluida(req, res) {
  var idOficina = req.params.id;

  empresaModel
    .buscarTotalRendaConcluida(idOficina)
    .then((resultado) => {
      if (resultado.length > 0) {
        res.status(200).json({resultado});
      } else {
        res.status(204).json([]);
      }
    })
    .catch(function (erro) {
      console.log(erro);
      console.log(
        "Houve um erro ao buscar a renda da oficina: ",
        erro.sqlMessage
      );
      res.status(500).json(erro.sqlMessage);
    });
}

// Cadastro de Orçamento completo;
function CadastrarOrcamento(req, res) {
  var fkVeiculo = req.body.fkVeiculo;
  var dataOrcamento = req.body.data;
  var idOrcamento = 0;

  empresaModel
    .SelecionarOrcamento(fkVeiculo, dataOrcamento)
    .then((orcamentos) => {
      if (orcamentos.length > 0) {
        res.json({
          error: true,
        });
        return;
      }

      empresaModel
        .CadastrarOrcamento(fkVeiculo, dataOrcamento)
        .then(function () {
          empresaModel
            .SelecionarOrcamento(fkVeiculo, dataOrcamento)
            .then((newOrcamento) => {
              console.log(newOrcamento);
              res.json({
                error: false,
                idOrcamento: newOrcamento[0].idOrcamento,
              });
            });
        })
        .catch(function (erro) {
          console.log(erro);
          console.log(
            "Houve um erro ao cadastrar o orçamento: ",
            erro.sqlMessage
          );
          res.status(500).json(erro.sqlMessage);
        });
    });
}

function SelecionarOrcamento(req, res) {
  var fkVeiculo = req.params.id;
  var dataOrcamento = req.params.data;

  empresaModel
    .SelecionarOrcamento(fkVeiculo, dataOrcamento)
    .then((resultado) => {
      res.status(200).json(resultado);
    });
}

function SelecionarOrcamentoCompleto(req, res) {
  var id = req.params.id;

  empresaModel.SelecionarOrcamentoCompleto(id).then(function (resultado) {
    console.log(`\nResultado oficinas: ${JSON.stringify(resultado)}`);
    res.json({ resultado });
  });
}

function selecionarDadosServicos(req, res) {
  var id = req.params.id;

  empresaModel.selecionarDadosServicos(id).then(function (resultado) {
    console.log(`\nResultado oficinas: ${JSON.stringify(resultado)}`);
    res.json({ resultado });
  });
}

function CadastrarServico(req, res) {
  var descricao = req.body.descricao;
  var valor = req.body.valor;
  var tipo = req.body.tipo;
  var fkOrcamento = req.body.fkOrcamento;

  var fkServico = 0;

  empresaModel.CadastrarServico(descricao).then((ok) => {
    empresaModel.SelecionarServico(descricao).then((resposta) => {
      fkServico = resposta[0].idServico;

      empresaModel
        .CadastrarHistorico(fkOrcamento, fkServico, tipo, valor)
        .then(() => {
          console.log("Serviço e Histórico cadastraco com Sucesso");
        });
    });
  });
}

function deletarOrcamentoCompleto(req, res) {
  var idOrcamento = req.params.id;
  // hist orc servico

  deletarHistorico(idOrcamento).then(() => {
    deletarOrcamento(idOrcamento).then(() => {
      deletarServico(idOrcamento, res);
    }  );
  });
}

function deletarOrcamento(id) {
  return empresaModel.deleteOrcamento(id);

  // empresaModel.deletarOrcamento(idOrcamento)
  // .then((ok) => {
  // });
}

function deletarHistorico(id) {
  return empresaModel.deleteHistorico(id);
}

function deletarServico(id, res) {
  empresaModel.SelecionarIDsServicos(id).then((resposta) => {
    console.log(resposta);

    for (var i = 0; i < resposta.length; i++) {
      var servicoAtual = resposta[i].idServico;

      empresaModel.deleteServico(servicoAtual).then(() => {
        console.log(`${i + 1}° Serviço deletado`);
      });
    }

    res.json({
      mensagem: "Exclusão realizada com sucesso",
    });
  });
}

function updateOrcamento(req, res) {
  var id = req.params.id;
  var status = req.params.status;

  console.log(id, status)

  empresaModel.updateOrcamento(id, status)
  .then(() => {
    res.json({
      mensagem: "Status alterado com sucesso"
    })
  })
}

module.exports = {
  cadastrarVeiculo,
  cadastrarCliente,
  buscarOficina,
  buscarPorCnpj,
  buscarPorId,
  cadastrar,
  listar,
  listarVeiculoCliente,
  buscarTotalClientesVeiculos,
  buscarTotalRendaConcluida,
  CadastrarOrcamento,
  SelecionarOrcamento,
  SelecionarOrcamentoCompleto,
  CadastrarServico,
  selecionarDadosServicos,
  deletarOrcamento,
  deletarHistorico,
  updateOrcamento,
  deletarOrcamentoCompleto,
};
