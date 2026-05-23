package br.com.fiapbank.application;

import br.com.fiapbank.model.Conta;

public class AutorizacaoService {
    private Conta conta;

    public AutorizacaoService(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta para autorização não pode ser nula.");
        }
        this.conta = conta;
    }

    public Boolean autorizar(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            return Boolean.FALSE;
        }
        return this.conta.getContaAcesso().autorizar(senha);
    }
}
