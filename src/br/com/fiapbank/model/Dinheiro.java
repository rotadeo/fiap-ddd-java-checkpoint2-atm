package br.com.fiapbank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Dinheiro {
    private BigDecimal valor;

    public Dinheiro(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor monetário não pode ser nulo.");
        }
        this.valor = valor.setScale(2, RoundingMode.HALF_UP);
    }

    public Dinheiro somar(Dinheiro outroValor) {
        if (outroValor == null) {
            throw new IllegalArgumentException("O valor para soma não pode ser nulo.");
        }
        return new Dinheiro(this.valor.add(outroValor.valor));
    }

    public Dinheiro subtrair(Dinheiro outroValor) {
        if (outroValor == null) {
            throw new IllegalArgumentException("O valor para subtração não pode ser nulo.");
        }
        return new Dinheiro(this.valor.subtract(outroValor.valor));
    }

    public Boolean isMaiorOuIgualQue(Dinheiro outroValor) {
        if (outroValor == null) {
            throw new IllegalArgumentException("O valor para comparação não pode ser nulo");
        }
        return this.valor.compareTo(outroValor.valor) >= 0;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Dinheiro outroDinheiro = (Dinheiro) obj;
        return this.valor.compareTo(outroDinheiro.valor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor.doubleValue());
    }

    @Override
    public String toString() {
        return "R$ " + valor.toString();
    }
}
