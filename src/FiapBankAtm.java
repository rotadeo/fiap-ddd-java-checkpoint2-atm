import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiapBankAtm {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String nomeUsuario;
        int indiceEspaco;
        String primeiroNomeUsuario;

        System.out.println("=== CADASTRO DE USUÁRIO ===");

        do {
            System.out.println("Informe o Nome Completo (nome e sobrenome): ");
            nomeUsuario = input.nextLine();
            indiceEspaco = nomeUsuario.strip().indexOf(" ");

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

        System.out.println("=== LOGIN DE USUÁRIO ===");

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


    }
}
