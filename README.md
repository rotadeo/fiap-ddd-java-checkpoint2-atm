# 🏦 FIAP Bank ATM - Versão Beta (DDD & OOP)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Arquitetura](https://img.shields.io/badge/Architecture-DDD-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Finalizado-success?style=for-the-badge)

Este projeto consiste na evolução (versão "Beta") do simulador de Caixa Eletrônico (ATM) do **FIAP Bank**. O objetivo principal desta versão foi refatorar um código procedural para uma arquitetura robusta baseada em **Orientação a Objetos (OOP)** e princípios de **Domain-Driven Design (DDD)**.

Projeto desenvolvido como parte da avaliação do **Checkpoint 2** da disciplina de Engenharia de Software da FIAP.

---

## 🏗️ Decisões Arquiteturais

O sistema foi redesenhado sob regras rigorosas de arquitetura de software para garantir segurança, coesão e baixo acoplamento:

1. **Abolição de Tipos Primitivos:** O domínio do sistema ignora completamente tipos primitivos (`int`, `double`, `boolean`). Toda a lógica numérica e condicional utiliza **Classes Wrapper** (`Integer`, `Double`, `Boolean`) e `BigDecimal` para garantir precisão absoluta nas operações financeiras.
2. **Separação de Responsabilidades:** O projeto abandona o *Default Package* e adota uma estrutura em 4 camadas de software isoladas.
3. **Fail-Fast & Autovalidação:** Entidades e *Value Objects* nunca nascem em estado inválido. Construtores são blindados e utilizam Expressões Regulares (Regex) para garantir a integridade dos dados (ex: Senhas obrigatórias de 6 dígitos e nomes contendo apenas letras e sobrenome).

---

## 📂 Estrutura de Camadas (Packages)

O projeto está dividido nos seguintes pacotes dentro de `src/br/fiap/bank/atm`:

* **`model` (Domínio):** O coração da aplicação. Contém as regras de negócio, invariantes financeiras, *Value Objects* imutáveis (`Dinheiro`, `ContaAcesso`, `Movimentacao`) e as Entidades (`Cliente`, `Conta`). Esta camada não possui dependências de interface visual.
* **`application` (Orquestração):** Contém os *Services* (`ContaService`, `AutorizacaoService`) e Factories responsáveis por intermediar as ordens do usuário e enviá-las ao domínio.
* **`presentation` (Apresentação):** A única camada autorizada a interagir com o usuário (`Scanner`, `System.out`). Responsável pelo fluxo do menu, validações de *input* contínuo (loop) e exibição amigável de extratos.
* **`infrastructure` (Infraestrutura):** Responsável pela simulação de persistência. Contém o `ContaRepository`, atuando como um banco de dados em memória para armazenar a conta instanciada durante o ciclo de vida da aplicação.

---

## 🧩 Padrões de Projeto Aplicados (Design Patterns)

* **Template Method:** Implementado na classe abstrata `Conta` no método `realizarSaque()`. Define o algoritmo rígido de saque (validação de saldo -> débito -> registro no extrato) e delega a cobrança de tarifas (`aplicarRegraDeTaxa()`) para as subclasses concretas (`ContaCorrente` e `ContaPoupanca`) via polimorfismo.
* **Singleton:** Aplicado na `ContaFactory` para garantir que apenas uma única instância da fábrica seja alocada em memória durante toda a execução da aplicação, economizando recursos computacionais.
* **Factory Method:** Centraliza e encapsula a complexidade da instanciação das contas, retornando a abstração (`Conta`) para o resto do sistema.

---

## ✨ Funcionalidades

- [x] **Setup de Abertura de Conta:** Simulação do gerente criando uma conta com validação estrita de Nome (apenas letras, obriga sobrenome), Senha (exatamente 6 dígitos numéricos) e saldo inicial.
- [x] **Fluxo de Acesso Seguro:** O terminal ATM bloqueia a conta temporariamente e encerra a sessão após 3 tentativas de senha incorretas.
- [x] **Consultar Saldo:** Exibição do saldo mascarado e formatado financeiramente.
- [x] **Depósitos e Saques:** Operações seguras que checam invariantes (ex: impede saque com saldo insuficiente).
- [x] **Histórico de Movimentações:** Extrato completo contendo Data/Hora exatas (`LocalDateTime`), Tipo de Transação (`DEPOSITO`, `SAQUE`, `TAXA`, `RENDIMENTO`) e o valor da operação protegido por imutabilidade.

---

## 🚀 Como Executar o Projeto

**Pré-requisitos:** Java 11 ou superior instalado na máquina.

1. Clone este repositório:
   ```bash
   git clone [https://github.com/rotadeo/fiap-ddd-java-checkpoint2-atm](https://github.com/rotadeo/fiap-ddd-java-checkpoint2-atm)

2. Navegue até a pasta raiz do código fonte (`src`).

3. Compile as classes Java ou abra o projeto na sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code).

4. Execute a classe principal localizada no pacote de aplicação: `br.com.fiapbank.Main`

5. Siga as instruções no console para realizar o Setup da conta inicial e, em seguida, opere o Caixa Eletrônico.

## 👨‍💻 Autor
- Rodrigo Cardoso Tadeo - Desenvolvimento e Arquitetura * RM: [562010]
