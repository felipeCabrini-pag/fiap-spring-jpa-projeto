package br.com.fiap.sga.service;


import br.com.fiap.sga.controller.dto.AlunoCreateDTO;
import br.com.fiap.sga.controller.dto.EnderecoDTO;
import br.com.fiap.sga.entity.Aluno;
import br.com.fiap.sga.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoCreateDTO salvarALuno(Aluno aluno ) {
        Aluno alunoSalvo = alunoRepository.save(aluno);
        AlunoCreateDTO alunoCreateDTO = new AlunoCreateDTO();
        alunoCreateDTO.setId(alunoSalvo.getId());
        alunoCreateDTO.setNome(alunoSalvo.getNome());
        alunoCreateDTO.setEmail(alunoSalvo.getEmail());
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(alunoSalvo.getEndereco().getCep());
        enderecoDTO.setCidade(alunoSalvo.getEndereco().getCidade());
        enderecoDTO.setEstado(alunoSalvo.getEndereco().getEstado());
        enderecoDTO.setRua(alunoSalvo.getEndereco().getRua());
        alunoCreateDTO.setEndereco(enderecoDTO);
        return alunoCreateDTO;
    }

    public Page<AlunoCreateDTO> listarAlunos(String nome, Pageable paginacao) {
        if(nome.isBlank()) {
           return alunoRepository.findAll(paginacao).map(AlunoCreateDTO::new); // retorna entidade, faz stream e itera para converter para DTO
        }
        return alunoRepository.findByNomeContainingIgnoreCase(nome, paginacao).map(AlunoCreateDTO::new);
    }
}
