package br.com.fiap.sga.repository;


import br.com.fiap.sga.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
}
