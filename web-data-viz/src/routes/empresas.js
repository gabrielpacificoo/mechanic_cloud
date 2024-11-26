var express = require("express");
var router = express.Router();

var empresaController = require("../controllers/empresaController");

router.get("/SelecionarOrcCompleto/:id", function (req, res) {
  empresaController.SelecionarOrcamentoCompleto(req, res);
});

router.get("/selecionarDadosServicos/:id", function (req, res) {
  empresaController.selecionarDadosServicos(req, res);
});

//Recebendo os dados do html e direcionando para a função cadastrar de usuarioController.js
router.get("/:empresaId", function (req, res) {
  empresaController.buscarOficina(req, res);
});

router.put("/cadastrarVeiculo", function (req, res) {
  empresaController.cadastrarVeiculo(req, res);
});

router.post("/cadastrarCliente", function (req, res) {
  empresaController.cadastrarCliente(req, res);
});

router.post("/cadastrar", function (req, res) {
    empresaController.cadastrar(req, res);
})

router.get("/buscarTotalRendaConcluida/:id", function (req, res) {
  empresaController.buscarTotalRendaConcluida(req, res);
});
router.get("/buscar", function (req, res) {
    empresaController.buscarPorCnpj(req, res);
});

router.get("/buscar/:id", function (req, res) {
  empresaController.buscarPorId(req, res);
});

router.post("/listar", function (req, res) {
  empresaController.listar(req, res);
});

router.post("/buscarTotalClientesVeiculos", function (req, res) {
  empresaController.buscarTotalClientesVeiculos(req, res);
});


router.post("/listarVeiculoCliente", function (req, res) {
  empresaController.listarVeiculoCliente(req, res);
});

router.post("/CadastrarOrcamento", function (req, res) {
  empresaController.CadastrarOrcamento(req, res);
});

router.get("/SelecionarOrcamento/:id:data", function (req, res) {
  empresaController.SelecionarOrcamento(req, res);
});

router.post("/CadastrarServico", function (req, res) {
  empresaController.CadastrarServico(req, res);
});

router.delete("/deletarOrcamento/:id", function (req, res) {
  empresaController.deletarOrcamentoCompleto(req, res);
})

router.put("/updateOrcamento/:id/:status", function (req, res) {
  empresaController.updateOrcamento(req, res);
})

module.exports = router;