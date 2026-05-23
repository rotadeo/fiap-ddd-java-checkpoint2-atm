package br.com.fiapbank.model;

import br.com.fiapbank.model.exceptions.SaldoInsuficienteException;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{
    private static final Dinheiro TAXA_DE_SAQUE = new Dinheiro(new BigDecimal("2.50"));

    public ContaCorrente(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, 0.0);
    }

    @Override
    protected void aplicarRegraDeTaxa() {
        if (!this.saldo.isMaiorOuIgualQue(TAXA_DE_SAQUE)) {
            throw new SaldoInsuficienteException("Saldo insuficiente para cobrir a taxa de saque.");
        }
        this.saldo = this.saldo.subtrair(TAXA_DE_SAQUE);
        this.registrarMovimentacao(TAXA_DE_SAQUE, TipoMovimentacao.TAXA);
    }
}
