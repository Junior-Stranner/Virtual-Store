package br.com.jujubaprojects.lojavirtual.dto;


import br.com.jujubaprojects.lojavirtual.entity.Cidade;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

public class PessoaClienteRequestDTO {

    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String cep;
    private Cidade cidade;

    public PessoaClienteRequestDTO() {

    }

    public PessoaClienteRequestDTO(Pessoa entity) {
      this.nome = entity.getNome();
      this.cpf = entity.getCpf();
      this.email = entity.getEmail();
      this.cep = entity.getCep();
      this.cidade = entity.getCidade();
    }

  /*   public static Pessoa converter(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaClienteRequestDTO, pessoa);
        return pessoa;
    }*/

}
