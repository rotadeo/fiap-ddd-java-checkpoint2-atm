# 🏦 FIAP Bank ATM - Core Banking & Architecture

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Arquitetura](https://img.shields.io/badge/Architecture-DDD-blue?style=for-the-badge)
![Padrões](https://img.shields.io/badge/Design%20Patterns-GoF-brightgreen?style=for-the-badge)

Um simulador de Caixa Eletrônico (ATM) construído do zero em Java, focado na aplicação prática de **Engenharia de Software Avançada**.

Este projeto foi desenvolvido com o objetivo de demonstrar a evolução de um sistema procedural para uma arquitetura robusta, aplicando os pilares da **Orientação a Objetos (OOP)**, **Domain-Driven Design (DDD)** e **Design Patterns**.

---

## 🎯 O Desafio de Negócio

O sistema simula o terminal de autoatendimento do FIAP Bank. Ele permite a abertura dinâmica de contas (Corrente e Poupança), autenticação segura de usuários e operações financeiras diárias. O grande diferencial deste projeto não é *o que* ele faz, mas *como* ele faz: o núcleo do sistema foi projetado para ser inviolável, tolerante a falhas e altamente escalável.

---

## 🏗️ Decisões de Arquitetura e Engenharia

Este projeto foi construído sob regras rigorosas de qualidade de código (Clean Code):

* **Domain-Driven Design (DDD):** O sistema é dividido em 4 camadas isoladas (`presentation`, `application`, `model` e `infrastructure`). O coração financeiro (`model`) não possui nenhuma dependência de interface gráfica ou console.
* **Erradicação de Tipos Primitivos:** Combate direto à *Primitive Obsession*. Não há uso de `int`, `double` ou `boolean` no domínio. O dinheiro é tratado de forma precisa com `BigDecimal` encapsulado em um Value Object (`Dinheiro`).
* **Autovalidação e Fail-Fast:** Entidades (`Cliente`, `Conta`) e Value Objects (`ContaAcesso`, `Movimentacao`) blindados. Construtores validam regras via Expressões Regulares (Regex) e bloqueiam a criação de estados inválidos no milissegundo zero.
* **Resiliência e Contratos:** Implementação de **Programação Orientada a Interfaces** (ex: `Autorizavel`). Substituição completa de exceções genéricas por uma árvore de **Unchecked Domain Exceptions** (`SaldoInsuficienteException`, `ValorInvalidoException`), garantindo que o sistema trate erros de negócio com elegância sem "crashar" o terminal.

---

## 🧩 Padrões de Projeto (Design Patterns)

Para evitar complexidade condicional (`if/else` encadeados) e garantir o reaproveitamento de código, os seguintes padrões GoF foram aplicados:

* **Template Method:** Centraliza o algoritmo rígido de transações financeiras na superclasse abstrata `Conta`, delegando a cobrança de tarifas bancárias para as subclasses (`ContaCorrente` e `ContaPoupanca`) via polimorfismo.
* **Factory Method:** Encapsula a complexidade da criação e montagem de contas, retornando a abstração para as camadas superiores.
* **Singleton:** Garante que a fábrica de contas (`ContaFactory`) possua uma instância única em memória, otimizando o consumo de recursos da aplicação.

---

## ✨ Funcionalidades Principais

- **Setup de Contas Dinâmico:** Simulação de *Backoffice* para abertura de Conta Corrente (com taxa de saque) e Conta Poupança (com simulação de rendimento/juros).
- **Autenticação Segura:** Controle de bloqueio automático de conta após 3 tentativas inválidas.
- **Operações Transacionais:** Depósitos e Saques protegidos por invariantes de domínio.
- **Extrato de Auditoria:** Histórico imutável de movimentações, formatado com `LocalDateTime`, identificando e separando o capital principal de `TAXA` e `RENDIMENTO`.

---

## 🚀 Como Executar o Projeto

**Pré-requisitos:** Java 11 ou superior.

1. Clone este repositório:
   ```bash
   git clone [https://github.com/rotadeo/fiap-ddd-java-checkpoint2-atm.git)

2. Navegue até a raiz do código-fonte (src).

3. Importe o projeto na sua IDE de preferência (IntelliJ, Eclipse, VS Code).

4. Execute a classe de startup localizada na camada de aplicação:
br.fiap.bank.atm.Main

5. Siga as instruções no console interativo para realizar o Setup da conta e operar o terminal de autoatendimento.

## 👨‍💻 Autor
- Rodrigo Cardoso Tadeo Estudante de Engenharia de Software na FIAP com foco em desenvolvimento de sistemas escaláveis e arquitetura corporativa.