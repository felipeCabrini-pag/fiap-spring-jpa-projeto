
## 1) Visão Geral

A **Universidade Ágil** contratou sua equipe para desenvolver o **backend** de um **Sistema de Gestão Acadêmica (SGA)**. O objetivo é disponibilizar **APIs REST** que permitam o cadastro e a consulta de informações acadêmicas essenciais (alunos, endereço do aluno, departamentos, cursos, disciplinas, professores e matrículas), além de **relatórios** que auxiliem a gestão.

O sistema será usado por secretarias acadêmicas e coordenações de curso para **organizar o catálogo acadêmico**, **matricular alunos em disciplinas** e **emitir consultas paginadas e relatórios** com boa performance.

---

## 2) Objetivos de Negócio

* **Centralizar** dados de alunos e do catálogo acadêmico.
* **Garantir integridade** (ex.: impedir matrícula duplicada do mesmo aluno na mesma disciplina).
* **Oferecer consultas eficientes e paginadas** para listas potencialmente grandes.
* **Fornecer relatórios de gestão**, tanto com JPQL quanto com *native query*, demonstrando domínio de **Spring Data JPA**.

---

## 3) Escopo Funcional (o que o sistema deve fazer)

1. **Cadastro de Alunos** com **Endereço único** (relação 1:1).
2. **Catálogo Acadêmico**:

    * **Departamento** (1) —< **Curso** (N).
    * **Curso** (1) —< **Disciplina** (N).
    * **Disciplina** (N) — pertence a —> **Curso** (1).
3. **Docência**:

    * **Professor** (N) —< >— (N) **Disciplina** (*many-to-many*): uma disciplina pode ter vários professores e vice-versa.
4. **Matrículas**:

    * **Aluno** se matricula em **Disciplina**.
    * **Regra de negócio**: **não pode existir matrícula repetida** do mesmo aluno na mesma disciplina.
5. **Consultas e Relatórios**:

    * **Listar Alunos** com **paginação** e **filtro por nome** (busca parcial, *case-insensitive*).
    * **Listar Disciplinas de um Curso** com **paginação**.
    * **Relatório 1 (JPQL @Query)**: **total de matrículas por curso**.
    * **Relatório 2 (Native @Query)**: **ranking de alunos com mais matrículas (top N)**.

---

## 4) Modelo de Domínio (relacionamentos esperados)

* **Aluno** — **Endereço**: **1:1** (cada aluno possui exatamente um endereço; endereço não é compartilhado).
* **Departamento** — **Curso**: **1:N**.
* **Curso** — **Disciplina**: **1:N**; e **Disciplina** — **Curso**: **N:1**.
* **Professor** — **Disciplina**: **N:N** (tabela de junção).
* **Matrícula** liga **Aluno** (**N:1**) e **Disciplina** (**N:1**), com **constraint de unicidade** `(aluno, disciplina)`.


---

## 5) Regras de Negócio (critérios de aceite)

* **RN-001 — Matrícula única**: um mesmo `(aluno, disciplina)` só pode existir **uma vez**.

    * *Dado* um aluno A e uma disciplina D, *quando* tentar matricular A em D novamente, *então* o sistema deve **recusar** com erro **422** (violação de integridade de dados/regra).
* **RN-002 — Endereço 1:1**: cada aluno deve ter um **único** endereço vinculado; remover/desvincular o aluno deve **eliminar** o endereço órfão.
* **RN-003 — Busca por alunos**: o endpoint de listagem de alunos **deve aceitar paginação** (`page`, `size`) e **filtro por nome** (`findByNomeContainingIgnoreCase`).
* **RN-004 — Disciplinas por curso**: a API deve retornar disciplinas de um curso com **paginação** para preservar desempenho.
* **RN-005 — Relatórios**:

    * **JPQL**: somatório de matrículas **agrupado por curso**, ordenado por total (decrescente).
    * **Native**: **ranking de alunos** por número de matrículas, com **controle de `top N`** via paginação (`Pageable`) ou parâmetro equivalente.

---

## 6) Comportamento Esperado da API (alto nível)

* **Criação de Alunos** (`POST /alunos`): recebe nome, e-mail e um endereço. Retorna **201** com o recurso criado.
* **Listagem de Alunos** (`GET /alunos`): suporta `?nome=`, `?page=`, `?size=` e `?sort=`. Retorna **200** com `Page<T>`.
* **Ranking de Alunos** (`GET /alunos/ranking?limit=N` ou paginação): retorna alunos com mais matrículas (**native query**).
* **Listar Disciplinas por Curso** (`GET /disciplinas?cursoId=...&page=...&size=...`): retorna **200** com `Page<Disciplina>`.
* **Pesquisar Professores por Disciplina** (`GET /professores/por-disciplina?nome=...`): usa **JPQL** para navegar no N:N.
* **Efetuar Matrícula** (`POST /matriculas`): recebe `alunoId` e `disciplinaId`.

    * Se já existir, retorna **422** (ou **400**, conforme padronização) com mensagem clara.
    * Se `alunoId`/`disciplinaId` inexistentes, retorna **404**.
* **Relatório de Matrículas por Curso** (`GET /matriculas/relatorio/por-curso`): **JPQL**, com total agregado por curso.

---

## 7) Requisitos Técnicos

* **Java 24**, **Spring Boot 3.x**, **Spring Web**, **Spring Data JPA**, **H2** (dev), **Validation**, **Lombok** *(opcional)*.
* **Paginação** com `Pageable`/`Page<T>` em listagens abertas.
* **Consultas**:

    * **Derived query methods** (ex.: `findByNomeContainingIgnoreCase`).
    * **@Query (JPQL)** para relatórios/joins expressivos.
    * **@Query(nativeQuery = true)** para o ranking (com **Pageable**, não `LIMIT :param`).
* **Erros e validação**:

    * **400** para **validação** (ex.: `@NotBlank`, `@Email`).
    * **404** quando recurso relacionado não existir.
    * **422** para **violação de regra/constraint** (ex.: matrícula duplicada).
    * Corpo de erro padronizado (timestamp, status, mensagem, path, violations).