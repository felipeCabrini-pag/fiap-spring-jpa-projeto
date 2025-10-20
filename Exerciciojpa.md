# 🧰 Sistema de Oficina Mecânica — Cadastro de Mecânicos

## 📝 Contexto

Uma **oficina mecânica** quer começar a registrar seus funcionários de forma digital.  
Atualmente, todos os dados dos mecânicos são anotados em um caderno. Isso gera confusão:  
nomes duplicados, informações ilegíveis e nenhuma forma rápida de listar quem trabalha na oficina.

Para resolver isso, o dono pediu a criação de uma **API simples** que permita **cadastrar, listar, atualizar e excluir mecânicos**.  
Essa será a **primeira etapa do sistema digital da oficina**.

---

## 🎯 Objetivo do Exercício

Criar uma **API REST** usando **Spring Boot 3** e **Spring Data JPA**, com banco de dados **H2**, que permita gerenciar mecânicos.

 implementar o CRUD completo, com **validação de dados**, **paginação** e **filtro opcional por nome**.


---

## 🧩 Entidade: `Mecanico`

A aplicação deve conter uma entidade chamada `Mecanico`, com os seguintes campos:

| Campo | Tipo | Regras |
|--------|------|--------|
| `id` | Long | Gerado automaticamente |
| `nome` | String | Obrigatório (`@NotBlank`) |
| `email` | String | Obrigatório (`@Email`), único |
| `especialidade` | String | Obrigatório (`@NotBlank`) — ex: "Freios", "Motor", "Suspensão" |


