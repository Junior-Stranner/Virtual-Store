package br.com.jujubaprojects.lojavirtual.dto;

import org.springframework.beans.BeanUtils;

import br.com.jujubaprojects.lojavirtual.entity.Cidade;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;
import lombok.Data;

@Data
public class PessoaClienteRequestDTO {
    
    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String cep;
    private Cidade cidade;

    public Pessoa converter(PessoaClienteRequestDTO pessoaClienteRequestDTO){
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaClienteRequestDTO, pessoa);
        return pessoa;
    }
}
