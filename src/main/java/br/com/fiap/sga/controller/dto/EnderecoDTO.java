package br.com.fiap.sga.controller.dto;

import br.com.fiap.sga.entity.Endereco;
import jakarta.validation.constraints.NotBlank;

public class EnderecoDTO {

    @NotBlank
    private String rua;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;

    public EnderecoDTO() {}

    public EnderecoDTO(String rua, String cidade, String estado, String cep) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public EnderecoDTO(Endereco endereco) {
        this.rua = endereco.getRua();
        this.cidade = endereco.getCidade();
        this.cep = endereco.getCep();
        this.estado = endereco.getEstado();
    }

    public String getRua() { return this.rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getCidade() { return this.cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return this.estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCep() { return this.cep; }
    public void setCep(String cep) { this.cep = cep; }
}
