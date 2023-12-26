package br.com.jujubaprojects.lojavirtual.Service;



import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PermissaoRepository;
import br.com.jujubaprojects.lojavirtual.dto.PessoaClienteRequestDTO;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;


@Service
public class PessoaClienteService {

    
    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoService permissaoService;

    
    @Autowired
    private EmailService emailService;

    /*  public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO){
        Pessoa pessoaCliente = PessoaClienteRequestDTO.converter(new PessoaClienteRequestDTO());
          return this.permissaoRepository.save(pessoaCliente);
    }*/

    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {

     Pessoa pessoaCliente = PessoaClienteRequestDTO.converter(new PessoaClienteRequestDTO());
    // Definir a data de criação
    pessoaCliente.setDataCriacao(LocalDateTime.now());
    // Copiar as propriedades de pessoaClienteRequestDTO para pessoa
    BeanUtils.copyProperties(pessoaClienteRequestDTO, pessoaCliente);
    // Vincular permissão de cliente
    permissaoService.vincularPessoaPermissaoCliente(pessoaCliente);
     // Salvar a pessoa no repositório
    this.permissaoRepository.save(pessoaCliente);
    // Enviar e-mail de confirmação
    emailService.enviarEmailTexto(pessoaCliente.getEmail(),"Cadastro na loja jujuba",
     "O registro na loja foi realizado com sucesso. Use a opção de esqueceu a senha para gerar a senha de acesso");
    String email = "nome "+ pessoaCliente.getEmail();
    String mensagem = "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!";
    // Enviar e-mail com template
    emailService.enviarEmailTexto(email, "Cadastro na Loja Tabajara", mensagem);
    // Retornar a pessoa criada
    return pessoaCliente;
}

}
