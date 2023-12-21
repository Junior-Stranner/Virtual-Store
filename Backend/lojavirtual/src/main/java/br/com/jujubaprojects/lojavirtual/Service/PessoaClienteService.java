package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PessoaRepository;
import br.com.jujubaprojects.lojavirtual.dto.PessoaClienteRequestDTO;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

@Service
public class PessoaClienteService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoClienteService;

    @Autowired
    private EmailService emailService;

   
    
   public Pessoa registrar(Pessoa pessoaCliente) {
    // Criar uma nova instância de Pessoa
    PessoaClienteRequestDTO pessoaClienteRequestDTO = new PessoaClienteRequestDTO();

    // Definir a data de criação
    pessoaCliente.setDataCriacao(LocalDateTime.now());

    // Copiar as propriedades de pessoaClienteRequestDTO para pessoa
    BeanUtils.copyProperties(pessoaClienteRequestDTO, pessoaCliente);

    // Salvar a pessoa no repositório
    this.pessoaRepository.save(pessoaCliente);

    // Vincular permissão de cliente
    permissaoClienteService.vincularPessoaPermissaoCliente(pessoaCliente);

    // Enviar e-mail de confirmação
    emailService.enviarEmailTexto(pessoaCliente.getEmail(),"Cadastro na loja jujuba",
     "O registro na loja foi realizado com sucesso. Use a opção de esqueceu a senha para gerar a senha de acesso");

    // Configurar dados para o envio de e-mail com template
 //   Map<String, Object> proprMap = new HashMap<>();
 //   proprMap.put("nome", pessoaCliente.getNome());
 //   proprMap.put("mensagem", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
    String email = "nome "+ pessoaCliente.getEmail();
    String mensagem = "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!";

    // Enviar e-mail com template
    emailService.enviarEmailTexto(email, "Cadastro na Loja Tabajara", mensagem);
    // Retornar a pessoa criada
    return pessoaCliente;
}


}
