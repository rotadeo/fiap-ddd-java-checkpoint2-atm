package br.com.fiapbank.application;

import br.com.fiapbank.model.Cliente;
import br.com.fiapbank.model.Conta;
import br.com.fiapbank.model.ContaAcesso;
import br.com.fiapbank.model.ContaCorrente;
import br.com.fiapbank.model.ContaPoupanca;
import br.com.fiapbank.model.Dinheiro;

public class ContaFactory {
    private static ContaFactory instance;

    private ContaFactory() {
    }

    public static ContaFactory getInstance() {
        if (instance == null) {
            instance = new ContaFactory();
        }
        return instance;
    }

    public Conta criarContaCorrente(Cliente cliente, ContaAcesso acesso, Dinheiro saldoInicial) {
        return new ContaCorrente(cliente, acesso, saldoInicial);
    }

    public Conta criarContaPoupanca(Cliente cliente, ContaAcesso acesso, Dinheiro saldoInicial) {
        return new ContaPoupanca(cliente, acesso, saldoInicial);
    }
}
