package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PessoaClienteRepository;
import br.com.jujubaprojects.lojavirtual.dto.PessoaClienteRequestDTO;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

@Service
public class PessoaClienteService {
    
    @Autowired
    private PessoaClienteRepository pessoaClienteRepository;

    public List<PessoaClienteRequestDTO> buscarTodos(){
        return this.pessoaClienteRepository.findAll();

    }

    public ResponseEntity<?> inserirPessoaCliente(PessoaClienteRequestDTO pessoaaClienteRequestDTO) {
        try {
            if (pessoaaClienteRequestDTO.getCpf().isEmpty()) {
                pessoaaClienteRequestDTO.setCpf("ValorPadraoParaCPF");
            }
    
            List<PessoaClienteRequestDTO> pessoaaClienteRequestDTOs = this.pessoaClienteRepository.findAll();
            boolean pessoaClienteExistente = pessoaaClienteRequestDTOs.stream().anyMatch(pExistente -> pExistente.getCpf().equals(pessoaaClienteRequestDTO.getCpf()));
    
            if (pessoaaClienteRequestDTO.getNome().isEmpty()) {
                System.out.println("Nome da pessoa é nulo!");
                return ResponseEntity.badRequest().body("Digite o nome da Pessoa!");
            } else if (pessoaClienteExistente) {
                return ResponseEntity.badRequest().body("Pessoa Existente");
            } else {
                this.pessoaClienteRepository.save(pessoaaClienteRequestDTO);
                return new ResponseEntity<>("Pessoa criada com sucesso ! ", HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        //    System.out.println("Erro ao cadastrar a pessoa....");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar a pessoa.");
        }
    }

    public ResponseEntity<?> inserir(Pessoa pessoa) {
        return null;
    }
    
    /*     public ResponseEntity<?> alterar(Pessoa pessoa){

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
    }*/
}
