package br.com.fiap.sga.repository;

import br.com.fiap.sga.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query(
            """
             select distinct p
             from Professor p
             join p.disciplinas d
             where lower(d.nome) like lower(concat('%', :nomeDisciplina, '%'))
            """)
    List<Professor> pesquisarPorDisciplina(@Param("nomeDisciplina") String asdasdas);
}
