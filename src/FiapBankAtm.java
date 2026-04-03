import java.util.Scanner;

public class FiapBankAtm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String nomeUsuario;
        int indiceEspaco;
        String primeiroNomeUsuario;

        System.out.println("\n=== FIAP BANK ATM - CADASTRO DE USUÁRIO ===");

        do {
            System.out.println("Informe o Nome Completo (nome e sobrenome): ");
            nomeUsuario = input.nextLine().strip();
            indiceEspaco = nomeUsuario.indexOf(" ");

            if (indiceEspaco == -1) {
                System.out.println("Erro: Você digitou apenas um nome. Por favor, inclua o sobrenome");
            }

        } while (indiceEspaco == -1);

        primeiroNomeUsuario = nomeUsuario.substring(0, indiceEspaco);
        System.out.println("Bem-Vindo " + primeiroNomeUsuario.toUpperCase());


        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_+=?><]).{8,}$";
        String senhaForte;

        do {
            System.out.println("\nInforme um senha forte: ");
            senhaForte = input.nextLine();

            if (!senhaForte.matches(regex)) {
                System.out.println("Erro: A senha não atende aos requisitos de segurança.");
            }

        } while (!senhaForte.matches(regex));

        System.out.println("Senha cadastrada com sucesso para o usuário " + nomeUsuario.toUpperCase());
        System.out.println("Cadastro finalizado!");

        System.out.println("\n=== FIAP BANK ATM - LOGIN DE USUÁRIO ===");

        int contadorTentativas = 0;
        String senhaLoggin;

        do {
            System.out.println("Informe sua senha: ");
            senhaLoggin = input.nextLine();

            if (senhaLoggin.equals(senhaForte)) {
                break;
            } else {
                System.out.println("Senha Incorreta");
                contadorTentativas ++;
            }

            if (contadorTentativas == 3) {
                System.out.println("ACESSO BLOQUEADO");
                System.exit(0);
            }

        } while (true);

        System.out.println("Login efetuado com sucesso");


        String menuPrincipal = """
               \n=== FIAP BANK ATM - MENU PRINCIPAL ===
               [1] Consultar Saldo
               [2] Fazer Depósito
               [3] Fazer Saque
               [4] Sair
               ======================================
               """;

        double saldo = 0.0;
        boolean sistemaAtivo = true;

        while (sistemaAtivo) {
            System.out.println(menuPrincipal);
            System.out.println("Selecione uma opção: ");

            if (input.hasNextInt()) {
                int opcaoMenu = input.nextInt();
                input.nextLine();

                switch (opcaoMenu) {
                    case 1:
                        System.out.printf("=== Saldo Atual: R$%.2f ===%n", saldo);
                        break;

                    case 2:
                        System.out.println("Informe o valor do depósito: R$");
                        if (input.hasNextDouble()) {
                            double deposito = input.nextDouble();
                            input.nextLine();
                            if (deposito <= 0) {
                                System.out.println("Erro: Valor de depósito inválido. O valor deve ser maior que zero.");
                            } else {
                                saldo += deposito;
                                System.out.printf("=== R$%.2f Depositado com sucesso ===%n", deposito);
                            }
                        } else {
                            System.out.println("Erro: Por favor, digite apenas números.");
                            input.nextLine();
                        } break;

                    case 3:
                        System.out.println("Informe o valor do saque: ");
                        if (input.hasNextDouble()) {
                            double saque = input.nextDouble();
                            input.nextLine();
                            if (saque <= 0) {
                                System.out.println("Erro: Valor de saque inválido");
                            } else if (saque > saldo) {
                                System.out.println("Erro: Saldo insuficiente para esta operação");
                            } else {
                                saldo -= saque;
                                System.out.printf("=== R$%.2f Sacado com sucesso ===%n", saque);
                            }
                        } else {
                            System.out.println("Erro: Por favor, digite apenas números.");
                            input.nextLine();
                        } break;

                    case 4:
                        System.out.println("O FIAP Bank agradece sua preferência!");
                        sistemaAtivo = false;
                        break;

                    default:
                        System.out.println("Opção inválida. Escolha uma opção válida");
                        break;

                }
            } else {
                System.out.println("Erro: Por favor, digite apenas números.");
                input.nextLine();
            }
        }
        input.close();
    }
}
