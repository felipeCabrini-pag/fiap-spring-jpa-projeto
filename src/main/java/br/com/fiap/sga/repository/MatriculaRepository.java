package br.com.fiap.sga.repository;

import br.com.fiap.sga.entity.Disciplina;
import br.com.fiap.sga.entity.Matricula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    boolean existsByAlunoIdAndDisciplinaId(long alunoId, Long disciplinaId);

    interface MatriculasPorCursoProjection {
        String getCurso();
        Long getTotal();
    }

    @Query( """
            select c.nome as curso, count(m) from  Matricula m
            join m.disciplina d
            join d.curso c
            group by c.nome
            order by count(m) desc
            """)
    List<MatriculasPorCursoProjection> totalPorCurso();
}
