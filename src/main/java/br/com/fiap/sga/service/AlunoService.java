package br.com.fiap.sga.service;


import br.com.fiap.sga.entity.Aluno;
import br.com.fiap.sga.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public void salvarALuno(Aluno aluno ) {
        alunoRepository.save(aluno);
    }
}
