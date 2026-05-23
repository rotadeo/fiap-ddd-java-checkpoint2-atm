package br.com.fiapbank.model;

import br.com.fiapbank.model.exceptions.ValorInvalidoException;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, 0.0);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
    }

    public void renderJuros(Double taxaRendimentoPercentual) {
        if (taxaRendimentoPercentual == null || taxaRendimentoPercentual <= 0.0) {
            throw new ValorInvalidoException("A taxa de rendimento deve ser maior que zero.");
        }
        BigDecimal fator = BigDecimal.valueOf(taxaRendimentoPercentual).divide(new BigDecimal("100"));
        BigDecimal valorRendimento = this.saldo.getValor().multiply(fator);

        Dinheiro rendimento = new Dinheiro(valorRendimento);

        this.saldo = this.saldo.somar(rendimento);
        this.registrarMovimentacao(rendimento, TipoMovimentacao.RENDIMENTO);
    }
}
