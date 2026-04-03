# FIAP Bank ATM

Este é um protótipo de terminal de autoatendimento (Caixa Eletrônico) desenvolvido em Java, que roda diretamente no console.

## Função do Projeto
O sistema simula o fluxo de operações básicas de um cliente em um caixa físico do banco. Ele é dividido nas seguintes funções principais:

* **Cadastro e Autenticação:** Permite ao usuário cadastrar uma senha forte (com validações de segurança) e realizar o login no terminal, bloqueando o acesso após 3 tentativas incorretas.
* **Operações Financeiras:** Após o login, o usuário tem acesso a um menu interativo onde pode:
    * Consultar o saldo atual.
    * Fazer depósitos.
    * Fazer saques.
* **Sistema à Prova de Falhas:** Todas as operações financeiras possuem travas de segurança (por exemplo, impedir saques de valores negativos ou depósitos de R$ 0,00).

O projeto foi criado para treinar os fundamentos de Java, como estruturas de repetição, lógica de controle de fluxo e validações de dados.

# Versão do Projeto
O projeto está em sua versão Alpha.