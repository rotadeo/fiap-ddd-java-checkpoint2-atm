package br.com.fiapbank.presentation;

import br.com.fiapbank.application.AutorizacaoService;
import br.com.fiapbank.application.ContaService;
import br.com.fiapbank.model.Dinheiro;
import br.com.fiapbank.model.Movimentacao;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TerminalBancarioController {
    private ContaService contaService;
    private AutorizacaoService autorizacaoService;
    private Scanner scanner;

    public TerminalBancarioController(ContaService contaService, AutorizacaoService autorizacaoService) {
        if (contaService == null || autorizacaoService == null) {
            throw new IllegalArgumentException("Os serviços de conta e autorização são obrigatórios.");
        }
        this.contaService = contaService;
        this.autorizacaoService = autorizacaoService;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPrincipal() {
        System.out.println("=== FIAP BANK ATM ===");

        Boolean autenticado = Boolean.FALSE;
        Integer tentativasUI = 0;

        while (!autenticado && tentativasUI < 3) {
            System.out.print("Digite sua senha de 6 dígitos para acessar: ");
            String senhaDigitada = scanner.nextLine();

            autenticado = autorizacaoService.autorizar(senhaDigitada);

            if (!autenticado) {
                tentativasUI++;
                if (tentativasUI >= 3) {
                    System.out.println("Acesso Negado! Sua conta foi bloqueada por segurança devido ao excesso de tentativas.");
                    return;
                } else {
                    System.out.println("Senha incorreta! Você tem " + (3 - tentativasUI) + " tentativa(s) restante(s).\n");
                }
            }
        }

        System.out.println("\nLogin realizado com sucesso! Bem-vindo(a) ao FIAP Bank.");

        Integer opcao = 0;
        while (!opcao.equals(5)) {
            System.out.println("\n=======================");
            System.out.println("    MENU PRINCIPAL     ");
            System.out.println("=======================");
            System.out.println("[ 1 ] Consultar Saldo");
            System.out.println("[ 2 ] Fazer Depósito");
            System.out.println("[ 3 ] Fazer Saque");
            System.out.println("[ 4 ] Histórico de Movimentações");
            System.out.println("[ 5 ] Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        exibirSaldo();
                        break;
                    case 2:
                        realizarDeposito();
                        break;
                    case 3:
                        realizarSaque();
                        break;
                    case 4:
                        exibirMovimentacoes();
                        break;
                    case 5:
                        System.out.println("Sessão encerrada com segurança. Obrigado por utilizar o FIAP Bank!");
                        break;
                    default:
                        System.out.println("Opção inválida! Escolha um número de 1 a 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite apenas números inteiros.");
                opcao = 0;
            } catch (Exception e) {
                System.out.println("Operação cancelada: " + e.getMessage());
            }
        }
    }

    public void exibirSaldo() {
        Dinheiro saldoAtual = contaService.obterSaldo();
        System.out.println("\n--- SALDO ATUAL ---");
        System.out.println("Seu saldo disponível é: " + saldoAtual);
    }

    public void realizarDeposito() {
        System.out.println("\n--- OPERAÇÃO DE DEPÓSITO ---");
        System.out.print("Digite o valor que deseja depositar: R$ ");

        String inputValor = scanner.nextLine();
        BigDecimal valorConvertido = new BigDecimal(inputValor);

        Dinheiro valorDeposito = new Dinheiro(valorConvertido);

        contaService.realizarDeposito(valorDeposito);
        System.out.println("Depósito realizado com sucesso!");
    }

    public void realizarSaque() {
        System.out.println("\n--- OPERAÇÃO DE SAQUE ---");
        System.out.print("Digite o valor que deseja sacar: R$ ");

        String inputValor = scanner.nextLine();
        BigDecimal valorConvertido = new BigDecimal(inputValor);

        Dinheiro valorSaque = new Dinheiro(valorConvertido);

        contaService.realizarSaque(valorSaque);
        System.out.println("Retire as cédulas na boca do caixa. Saque efetuado com sucesso!");
    }

    public void exibirMovimentacoes() {
        System.out.println("\n--- HISTÓRICO DE MOVIMENTAÇÕES ---");

        List<Movimentacao> historico = contaService.obterMovimentacoes();

        if (historico.isEmpty()) {
            System.out.println("Não existem movimentações registradas nesta conta.");
            return;
        }

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (Movimentacao mov : historico) {
            String dataFormatada = mov.getDataHora().format(formatador);
            System.out.printf("[%s] Tipo: %-11s | Valor: %s%n",
                    dataFormatada,
                    mov.getTipo(),
                    mov.getValor());
        }
    }
}
