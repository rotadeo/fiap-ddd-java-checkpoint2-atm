package br.com.fiapbank.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta extends BaseEntity {
    protected Cliente cliente;
    protected Dinheiro saldo;
    protected Double taxa;
    protected StatusConta status;
    protected LocalDate dataAbertura;
    protected ContaAcesso contaAcesso;
    protected List<Movimentacao> movimentacoes;

    public Conta(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo, Double taxaMensal) {
        super();
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente é obrigatório para abrir uma conta.");
        }
        if (contaAcesso == null) {
            throw new IllegalArgumentException("A conta de acesso é obrigatória.");
        }
        if (saldo == null) {
            throw new IllegalArgumentException("O saldo inicial não pode ser nulo.");
        }
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.saldo = saldo;
        this.taxa = taxaMensal != null ? taxaMensal : 0.0;
        this.status = StatusConta.ATIVA;
        this.dataAbertura = LocalDate.now();

        this.movimentacoes = new ArrayList<>();
    }

    public final void realizarSaque(Dinheiro valor) {
        this.sacar(valor);
        this.registrarMovimentacao(valor, TipoMovimentacao.SAQUE);
        this.aplicarRegraDeTaxa();
    }

    public void realizarDeposito(Dinheiro valor) {
        this.depositar(valor);
        this.registrarMovimentacao(valor, TipoMovimentacao.DEPOSITO);
    }

    public void sacar(Dinheiro valor) {
        if (valor == null || !valor.isMaiorOuIgualQue(new Dinheiro(BigDecimal.ZERO))) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        if (!this.saldo.isMaiorOuIgualQue(valor)) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }
        this.saldo = this.saldo.subtrair(valor);
    }

    private void depositar(Dinheiro valor) {
        if (valor == null || !valor.isMaiorOuIgualQue(new Dinheiro(java.math.BigDecimal.ZERO))) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }
        this.saldo = this.saldo.somar(valor);
    }

    protected void registrarMovimentacao(Dinheiro valor, TipoMovimentacao tipo) {
        Movimentacao novaMovimentacao = new Movimentacao(LocalDateTime.now(), valor, tipo);
        this.movimentacoes.add(novaMovimentacao);
    }

    protected abstract void aplicarRegraDeTaxa();

    public Dinheiro getSaldo() {
        return this.saldo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public LocalDate getDataAbertura() {
        return this.dataAbertura;
    }

    public StatusConta getStatus() {
        return this.status;
    }

    public List<Movimentacao> getMovimentacoes() {
        return new ArrayList<>(this.movimentacoes);
    }

    public ContaAcesso getContaAcesso() {
        return this.contaAcesso;
    }
}
