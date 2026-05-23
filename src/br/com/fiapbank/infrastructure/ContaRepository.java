package br.com.fiapbank.infrastructure;

import br.com.fiapbank.model.Conta;
import br.com.fiapbank.model.exceptions.ValorInvalidoException;

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
            throw new ValorInvalidoException("Erro de Infraestrutura: Nenhuma conta foi localizada no banco de dados em memória.");
        }
        return this.contasCadastradas.get(0);
    }
}
