package br.com.fiapbank.application;

import br.com.fiapbank.application.AutorizacaoService;
import br.com.fiapbank.application.ContaFactory;
import br.com.fiapbank.application.ContaService;
import br.com.fiapbank.infrastructure.ContaRepository;
import br.com.fiapbank.model.*;
import br.com.fiapbank.presentation.TerminalBancarioController;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SETUP DO SISTEMA (SIMULAÇÃO DE ABERTURA DE CONTA) ===");

        try {
            // 1. CAPTURANDO DADOS REAIS DO USUÁRIO
            System.out.print("Digite o nome do titular da conta: ");
            String nomeInput = scanner.nextLine();
            Cliente clienteTitular = new Cliente(nomeInput);

            System.out.print("Crie uma senha numérica de 6 dígitos para o ATM: ");
            String senhaInput = scanner.nextLine();
            // Se a senha não tiver 6 números, o Fail-Fast vai estourar o erro aqui!
            ContaAcesso acesso = new ContaAcesso(senhaInput);

            System.out.print("Digite o valor do depósito inicial (Ex: 500.00): R$ ");
            String saldoInput = scanner.nextLine();
            Dinheiro saldoInicial = new Dinheiro(new BigDecimal(saldoInput));

            System.out.println("\nQual tipo de conta deseja abrir?");
            System.out.println("[ 1 ] Conta Corrente (Com taxa de saque)");
            System.out.println("[ 2 ] Conta Poupança (Sem taxa, com rendimento mensal)");
            System.out.print("Escolha: ");
            int tipoConta = Integer.parseInt(scanner.nextLine());

            // 2. CRIANDO A CONTA COM A FÁBRICA
            ContaFactory factory = ContaFactory.getInstance();
            Conta minhaConta;

            if (tipoConta == 1) {
                minhaConta = factory.criarContaCorrente(clienteTitular, acesso, saldoInicial);
                System.out.println("\n[OK] Conta Corrente criada com sucesso!");
            } else if (tipoConta == 2) {
                minhaConta = factory.criarContaPoupanca(clienteTitular, acesso, saldoInicial);
                System.out.println("\n[OK] Conta Poupança criada com sucesso!");

                System.out.println("[SIMULAÇÃO] Avançando o tempo em 30 dias...");

                ContaPoupanca poupanca = (ContaPoupanca) minhaConta;
                poupanca.renderJuros(0.5); // Rende 0.5% ao mês

                System.out.println("[OK] O rendimento automático mensal foi creditado na conta!");
            } else {
                throw new IllegalArgumentException("Opção de conta inválida.");
            }

            // 3. SALVANDO NA INFRAESTRUTURA (BANCO DE DADOS EM MEMÓRIA)
            ContaRepository repository = new ContaRepository();
            repository.salvar(minhaConta);
            System.out.println("Conta criada e salva no banco de dados com sucesso!\n");

            // ------------------------------------------------------------------

            System.out.println("Iniciando o sistema FIAP Bank ATM (Versão Beta)...");
            System.out.println("Carregando dependências e injetando serviços...\n");

            // 4. RECUPERANDO A CONTA DA INFRAESTRUTURA PARA O CAIXA ELETRÔNICO
            Conta contaAtivaDoCaixa = repository.obterContaSimulacao();

            // 5. INJETANDO DEPENDÊNCIAS
            ContaService contaService = new ContaService(contaAtivaDoCaixa);
            AutorizacaoService autorizacaoService = new AutorizacaoService(contaAtivaDoCaixa);
            TerminalBancarioController terminal = new TerminalBancarioController(contaService, autorizacaoService);

            // 6. LIGANDO O CAIXA
            terminal.exibirMenuPrincipal();

        } catch (RuntimeException e) {
            System.err.println("\nErro de Validação ou Negócio: " + e.getMessage());
            System.err.println("Por favor, reinicie o sistema e siga as regras.");
        } finally {
            scanner.close();
        }
    }
}
