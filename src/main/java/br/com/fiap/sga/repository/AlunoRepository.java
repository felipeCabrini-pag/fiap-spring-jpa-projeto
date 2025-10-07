package br.com.fiap.sga.repository;

import br.com.fiap.sga.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

// primeiro campo qual entidade,
                                         // segundo diz qual tipo da primary key
public interface AlunoRepository extends JpaRepository<Aluno,Long> {

    Page<Aluno> findByNomeCOntainingIgnoreCase(String nome, Pageable pageable);


    interface AlunoRankingProjection {
        Long getId();
        String getNome();
        Long getTotal();
    }

    @Query(value = """
            SELECT a.id AS id, a.nome AS nome, COUNT(m.id) as total
            FROM ALUNO a
            join MATRICULA m on m.aluno_id = a.id
            GROUP BY a.id, a.nome
            ORDER BY total DESC
            """,
    countQuery = "SELECT COUNT(*) from ALUNO",
    nativeQuery = true)
    Page<AlunoRankingProjection> topAlunos(Pageable pageable);

}
