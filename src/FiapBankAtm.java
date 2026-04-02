import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiapBankAtm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String nomeUsuario;
        int indiceEspaco;
        String primeiroNomeUsuario;

        System.out.println("\n=== CADASTRO DE USUÁRIO ===");

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
        Pattern pattern = Pattern.compile(regex);

        String senhaForte;
        Matcher matcher;

        do {
            System.out.println("Informe um senha forte: ");
            senhaForte = input.nextLine();
            matcher = pattern.matcher(senhaForte);

            if (!matcher.matches()) {
                System.out.println("Erro: A senha não atende aos requisitos de segurança.");
            }

        } while (!matcher.matches());

        System.out.println("Senha cadastrada com sucesso para o usuário " + nomeUsuario.toUpperCase());
        System.out.println("Cadastro finalizado!");

        System.out.println("\n=== LOGIN DE USUÁRIO ===");

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
               === MENU PRINCIPAL ===
               [1] Consultar Saldo
               [2] Fazer Depósito
               [3] Fazer Saque
               [4] Sair
               """;

        double saldo = 0.0;

        Menu:
        while (true) {
            System.out.println(menuPrincipal);
            System.out.println("Selecione uma opção: ");
            int opcaoMenu = input.nextInt();
            input.nextLine();
            switch (opcaoMenu) {
                case 1:
                    System.out.printf("Saldo Atual: R$%.2f%n", saldo);
                    break;
                case 2:
                    System.out.println("Informe o valor do depósito: ");
                    double deposito = input.nextDouble();
                    input.nextLine();
                    if (deposito <= 0) {
                        System.out.println("Valor de depósito inválido");
                        break;
                    } else {
                        saldo += deposito;
                        System.out.printf("R$%.2f Depositado com sucesso%n", deposito);
                        break;
                    }
                case 3:
                    System.out.println("Informe o valor do saque: ");
                    double saque = input.nextDouble();
                    input.nextLine();
                    if (saque <= 0 || saque > saldo) {
                        System.out.println("Valor de saque inválido");
                        break;
                    } else {
                        saldo -= saque;
                        System.out.printf("R$%.2f Sacado com sucesso%n", saque);
                        break;
                    }
                case 4:
                    System.out.println("O FIAP Bank agradece sua preferência!");
                    break Menu;
                default:
                    System.out.println("Opção inválida. Escolha uma opção válida");

            }
        }

    }
}
