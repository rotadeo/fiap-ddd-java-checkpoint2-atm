# 🏦 FIAP Bank ATM - Versão Resiliente (DDD & OOP)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Arquitetura](https://img.shields.io/badge/Architecture-DDD-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Checkpoint%203-success?style=for-the-badge)

Este projeto consiste na evolução contínua do simulador de Caixa Eletrônico (ATM) do **FIAP Bank**. Após uma refatoração profunda para o **Domain-Driven Design (DDD)** no Checkpoint 2, esta nova versão (Checkpoint 3) introduz **Contratos de Interface** e uma arquitetura rigorosa de **Resiliência e Tolerância a Falhas**, erradicando quebras de sistema (crashes) e exceções genéricas.

Projeto desenvolvido para a disciplina de Engenharia de Software da FIAP (Turma 2ESPG).

---

## 🏗️ Evolução Arquitetural (Checkpoint 3)

O sistema foi elevado a um novo patamar de robustez, seguindo práticas de Engenharia de Software Avançada:

1. [cite_start]**Contratos Sólidos (Interfaces):** Introdução da interface `Autorizavel`, estabelecendo um contrato rígido para qualquer entidade que exija autenticação no ecossistema do banco, garantindo previsibilidade e segurança no acesso [cite: 295-299, 337].
2. **Linguagem Ubíqua nas Falhas:** Substituição de exceções genéricas do Java (`IllegalArgumentException`) por **Unchecked Domain Exceptions** (ex: `SaldoInsuficienteException`, `ValorInvalidoException`). [cite_start]O domínio agora "fala" o idioma do negócio mesmo quando as regras são violadas [cite: 305-314, 337].
3. **Resiliência de Interface (Anti-Crash):** A camada de apresentação (`presentation`) foi blindada com blocos `try/catch`. [cite_start]Tentativas de burlar regras de negócio agora resultam em mensagens amigáveis e retorno seguro ao menu principal, impedindo a exibição de Stack Traces para o cliente [cite: 278-279, 320-323, 337].

---

## 📂 Estrutura de Camadas (Packages)

O projeto adota uma estrutura em 4 camadas de software isoladas (`src/br/fiap/bank/atm`):

* **`model` (Domínio):** O coração da aplicação. Contém as regras de negócio, *Value Objects* imutáveis, e as Entidades.
   * **`interfaces`:** Contratos do sistema (ex: `Autorizavel`).
   * **`exceptions`:** Árvore de exceções customizadas de negócio (`RuntimeException`).
* **`application` (Orquestração):** *Services* e *Factories* que intermedeiam as ordens do usuário e as enviam ao domínio.
* **`presentation` (Apresentação):** Interação com o usuário (`Scanner`), responsável por capturar as intenções e tratar os erros de domínio graciosamente, mantendo o loop da sessão ativo.
* **`infrastructure` (Infraestrutura):** Simulação de persistência (Banco de Dados em Memória).

---

## 🧩 Padrões de Projeto e Conceitos Aplicados

* **Template Method:** Define o algoritmo rígido de saque na classe mãe abstrata `Conta` e delega a cobrança de tarifas (`aplicarRegraDeTaxa()`) para as subclasses concretas via polimorfismo.
* **Fail-Fast & Autovalidação:** Entidades que se autoprotegem nos construtores, bloqueando dados inválidos no momento da instanciação.
* **Singleton & Factory Method:** Criação centralizada de objetos (contas) com controle de instância única em memória.
* **Programação Orientada a Interfaces:** Desacoplamento do sistema de autenticação, dependendo de abstrações (`Autorizavel`) em vez de implementações concretas.

---

## ✨ Funcionalidades do Terminal

- [x] **Setup Dinâmico:** Escolha entre abertura de **Conta Corrente** (com taxa de saque) ou **Conta Poupança** (com simulação de rendimento).
- [x] **Autenticação Segura:** Bloqueio automático da conta após 3 tentativas inválidas de senha.
- [x] **Operações de Caixa:** Depósitos e Saques protegidos por validações de saldo e valores negativos.
- [x] **Extrato Imutável:** Histórico de movimentações detalhado (`LocalDateTime`), registrando `DEPOSITO`, `SAQUE`, `TAXA` e `RENDIMENTO`.

---

## 🚀 Como Executar o Projeto

**Pré-requisitos:** Java 11 ou superior instalado.

1. Clone este repositório:
   ```bash
   git clone [https://github.com/rotadeo/fiap-ddd-java-checkpoint2-atm.git)
   
2. Navegue até a pasta raiz do código-fonte.

3. Compile as classes ou abra o projeto na sua IDE favorita.

4. Execute a classe principal: `br.fiap.bank.atm.Main`

5. Realize o Setup inicial e opere o terminal de autoatendimento.

## 👨‍💻 Autor
- Rodrigo Cardoso Tadeo - Desenvolvimento e Arquitetura * RM: [562010]

- Turma: 2ESPG - Engenharia de Software