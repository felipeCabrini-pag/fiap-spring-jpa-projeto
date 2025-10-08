package br.com.fiap.sga.repository;

import br.com.fiap.sga.entity.Disciplina;
import br.com.fiap.sga.entity.Matricula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {

    //para ter paginacao, é obrigatório retornar Page<Entidade> e o ultimo parametro ser Pageable
    Page<Disciplina> findByCursoId(Long id, Pageable pageable);

}
