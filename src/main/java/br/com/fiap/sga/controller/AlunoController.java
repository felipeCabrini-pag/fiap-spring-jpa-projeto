package br.com.fiap.sga.controller;

import br.com.fiap.sga.controller.dto.AlunoCreateDTO;
import br.com.fiap.sga.controller.dto.EnderecoDTO;
import br.com.fiap.sga.entity.Aluno;
import br.com.fiap.sga.entity.Endereco;
import br.com.fiap.sga.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoCreateDTO> criar(@Valid @RequestBody AlunoCreateDTO dto) {
        EnderecoDTO e = dto.getEndereco();
        Endereco end = new Endereco();
        end.setRua(e.getRua());
        end.setCidade(e.getCidade());
        end.setEstado(e.getEstado());
        end.setCep(e.getCep());

        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setEndereco(end);

        AlunoCreateDTO alunoSalvoDTO = alunoService.salvarALuno(aluno);
        return ResponseEntity.created(URI.create("/alunos/" + alunoSalvoDTO.getId())).body(alunoSalvoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AlunoCreateDTO>> listarAlunos(
            @RequestParam(defaultValue = "") String nome, //pesquisar aluno por nome
            @RequestParam(defaultValue = "0") int page, // pagina para retornar
            @RequestParam(defaultValue = "10") int size, //quantidade de itens por pagina
            @RequestParam(defaultValue = "nome,asc") String sort) // como vai ser ordenado
     {
         String campoDaOrdenacao = sort.split(",")[0];
         Sort ordenacaoDaPaginacao = Sort.by(campoDaOrdenacao).ascending(); //prepara a ordenacao da paginacao
         if (sort.endsWith(",desc")) {
             ordenacaoDaPaginacao.descending();
         }

         Pageable paginacao = PageRequest.of(page,size,ordenacaoDaPaginacao);
         Page<AlunoCreateDTO> resultado = alunoService.listarAlunos(nome,paginacao);
         return ResponseEntity.ok(resultado);

    }

}
