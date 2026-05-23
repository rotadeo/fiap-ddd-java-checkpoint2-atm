package br.com.fiapbank.model;

import br.com.fiapbank.model.exceptions.ValorInvalidoException;
import br.com.fiapbank.model.interfaces.Autorizavel;

import java.util.Objects;
import java.util.regex.Pattern;

public class ContaAcesso implements Autorizavel {
    private static final Integer MAXIMO_TENTAIVAS = 3;
    private static final String REGEX_SENHA_ATM = "^\\d{6}$";

    private String senhaForte;
    private Integer tentativas;
    private Boolean bloqueado;

    public ContaAcesso(String senhaForte) {
        if (senhaForte == null || senhaForte.trim().isEmpty()) {
            throw new ValorInvalidoException("A senha não pode ser nula ou vazia.");
        }
        if (!Pattern.matches(REGEX_SENHA_ATM, senhaForte)) {
            throw new ValorInvalidoException("Formato inválido: A senha deve conter exatamente 6 dígitos numéricos.");
        }
        this.senhaForte = senhaForte;
        this.tentativas = 0;
        this.bloqueado = Boolean.FALSE;
    }

    @Override
    public Boolean autorizar(String senha) {
        if (this.bloqueado) {
            return Boolean.FALSE;
        }
        if (this.senhaForte.equals(senha)) {
            resetarTentativas();
            return Boolean.TRUE;
        } else {
            this.tentativas ++;
            if (this.tentativas > MAXIMO_TENTAIVAS) {
                this.bloqueado = Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    public void resetarTentativas() {
        this.tentativas = 0;
    }

    @Override
    public Boolean isBloqueado() {
        return this.bloqueado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContaAcesso that = (ContaAcesso) obj;
        return Objects.equals(senhaForte, that.senhaForte) &&
                Objects.equals(tentativas, that.tentativas) &&
                Objects.equals(bloqueado, that.bloqueado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senhaForte, tentativas, bloqueado);
    }
}
