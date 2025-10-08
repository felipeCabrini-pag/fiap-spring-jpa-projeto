package br.com.fiap.sga.controller;

import br.com.fiap.sga.entity.Aluno;
import br.com.fiap.sga.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping()
    public ResponseEntity<Aluno> salvarAluno(@RequestBody Aluno aluno) {
        alunoService.salvarALuno(aluno);
        return ResponseEntity.ok(aluno);

    }
}
