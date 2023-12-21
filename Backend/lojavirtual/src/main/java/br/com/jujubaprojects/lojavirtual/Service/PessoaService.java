package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PessoaRepository;
import br.com.jujubaprojects.lojavirtual.dto.PessoaClienteRequestDTO;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoClienteService;

    
    @Autowired
    private EmailService emailService;


    public List<Pessoa> buscarTodos(){
        return this.pessoaRepository.findAll();

    }

    public List<Pessoa>  findAllClientes(Pessoa pessoaCliente) {
    // Criar uma nova instância de PessoaClienteRequestDTO
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
    return  this.pessoaRepository.findAllClientes();
}



    public ResponseEntity<?> inserir(Pessoa pessoa){

        try {

            if (pessoa.getCpf().isEmpty()) {
            pessoa.setCpf("ValorPadraoParaCPF");
        }
        List<Pessoa> pessoas = this.pessoaRepository.findAll();
        boolean pessoaExistente = pessoas.stream().anyMatch(pExistente -> pExistente.getCpf().equals(pessoa.getCpf()));


        if (pessoa.getNome().isEmpty()) {
            System.out.println("Nome da categoria é nulo!");
            return ResponseEntity.badRequest().body("Digite o nome da Pessoa!");

      } else if(pessoaExistente){
       

            return ResponseEntity.badRequest().body("Pessoa Existente");
        }else{
        this.pessoaRepository.save(pessoa);
        return new ResponseEntity<>("Pessoa criada com sucesso ! ", HttpStatus.CREATED);
        }
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao cadastrar ......");
        }
    }
      

    public ResponseEntity<?> alterar(Pessoa pessoa){

        Optional<Pessoa> pessoaOptional = this.pessoaRepository.findById(pessoa.getId());

        if(pessoaOptional.isPresent()){
          Pessoa pessoaExistente = pessoaOptional.get();
          pessoaExistente.setNome(pessoa.getNome());
          pessoaExistente.setCpf(pessoa.getCpf());
          pessoaExistente.setEmail(pessoa.getEmail());
          pessoaExistente.setSenha(pessoa.getSenha());
          pessoaExistente.setEndereco(pessoa.getEndereco());
          pessoaExistente.setCep(pessoa.getCep());
//          pessoaExistente.setCidade(pessoa.getCidade());
         pessoaExistente.setDataCriacao(pessoa.getDataCriacao());
         pessoaExistente.setDataAtualizacao(LocalDateTime.now());

           this.pessoaRepository.save(pessoaExistente);
           return ResponseEntity.ok().body("Pessoa alterada com sucesso !");
        }else{
           throw new EntityNotFoundException("Pessoa não encontrada !");
        }
        
    }

    public ResponseEntity<?> excluir(Long id){
       Pessoa pessoa = this.pessoaRepository.findById(id).get();
      this.pessoaRepository.delete(pessoa);
      return ResponseEntity.ok().body("pessoa excluido com sucesso !");
    }
}
