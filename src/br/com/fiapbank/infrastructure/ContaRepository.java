package br.com.fiapbank.infrastructure;

import br.com.fiapbank.model.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaRepository {
    private List<Conta> contasCadastradas;

    public ContaRepository() {
        this.contasCadastradas = new ArrayList<>();
    }

    public void salvar(Conta conta) {
        if (conta != null) {
            this.contasCadastradas.add(conta);
        }
    }

    public Conta obterContaSimulacao() {
        if (this.contasCadastradas.isEmpty()) {
            throw new IllegalStateException("Nenhuma conta cadastrada no banco de dados.");
        }
        return this.contasCadastradas.get(0);
    }
}
