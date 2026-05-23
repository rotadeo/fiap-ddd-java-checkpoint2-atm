package br.com.fiapbank.model;

import br.com.fiapbank.model.exceptions.ValorInvalidoException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Movimentacao {
    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private Dinheiro valor;

    public Movimentacao(LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        if (dataHora == null) {
            throw new ValorInvalidoException("A data e hora da movimentação são obrigatórias.");
        }
        if (valor == null) {
            throw new ValorInvalidoException("O valor da movimentação é obrigatório.");
        }
        if (tipo == null) {
            throw new ValorInvalidoException("O tipo de movimentação é obrigatório.");
        }
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Dinheiro getValor() {
        return valor;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimentacao that = (Movimentacao) obj;
        return Objects.equals(dataHora, that.dataHora) &&
                tipo == that.tipo &&
                Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataHora, tipo, valor);
    }
}
