package br.com.fiap.sga.repository;

import br.com.fiap.sga.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


// primeiro campo qual entidade,
                                         // segundo diz qual tipo da primary key
public interface AlunoRepository extends JpaRepository<Aluno,Long> {

    //find method
    //jpa ele interpreta o nome do metodo  e gera o SQL
    Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    interface AlunoRankingProjection {
        Long getId();
        String getNomeAluno();
        Long getTotal();
    }

    //@Query tanto para JPQL quanto para Native QUERY (SQL PURO)
    @Query(value = """
            SELECT a.id AS id, a.nome AS nome, COUNT(m.id) as total
            FROM ALUNO a
            join MATRICULA m on m.aluno_id = a.id
            GROUP BY a.id, a.nome
            ORDER BY total DESC
            """,
    countQuery = "SELECT COUNT(*) from ALUNO", //usado para calcular o numero total para depois ser quebrado na paginacao
    nativeQuery = true) //parametro para ativar interpretacao como sql puro
    Page<AlunoRankingProjection> topAlunos(Pageable pageable);

}
