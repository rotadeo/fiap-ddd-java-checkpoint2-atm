package br.com.fiapbank.model;

import br.com.fiapbank.model.exceptions.ValorInvalidoException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente extends BaseEntity{
    private static final String REGEX_NOME_VALIDO = "^[a-zA-ZÀ-ÿ\\s]+$";
    private String nomeCompleto;

    public Cliente(String nomeCompleto) {
        super();
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new ValorInvalidoException("O nome completo do cliente é obrigatório.");
        }
        if (!Pattern.matches(REGEX_NOME_VALIDO, nomeCompleto)) {
            throw new ValorInvalidoException("Nome inválido! O titular deve conter apenas letras e espaços (sem números ou símbolos).");
        }
        String[] partesDoNome = nomeCompleto.trim().split("\\s+");
        if (partesDoNome.length < 2) {
            throw new ValorInvalidoException("Nome incompleto! Por favor, insira o nome e pelo menos um sobrenome.");
        }
        this.nomeCompleto = nomeCompleto.trim();
    }

    public String obterPrimeiroNome() {
        return this.nomeCompleto.split(" ")[0];
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return Objects.equals(nomeCompleto, cliente.nomeCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCompleto);
    }
}
