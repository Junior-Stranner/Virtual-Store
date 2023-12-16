package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PessoaRepository;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodos(){
        return this.pessoaRepository.findAll();
    }

    public ResponseEntity<?> inserir(Pessoa pessoa){
        List<Pessoa> pessoas = this.pessoaRepository.findAll();
        boolean pessoaExistente = pessoas.stream().anyMatch(pExistente -> pExistente.getCpf().equals(pessoa.getCpf()));

        if(pessoaExistente){
          
            return ResponseEntity.badRequest().body("Pessoa Existente");
        }
        this.pessoaRepository.save(pessoa);
        return new ResponseEntity<>("Pessoa criada com sucesso ! ", HttpStatus.CREATED);

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
           return ResponseEntity.ok().body("Pessoa alterada com sucesso");
        }else{
           throw new EntityNotFoundException("Pessoa não encontrada !");
        }
        
    }

    public void excluir(Pessoa pessoa){
   //   Pessoa pessoa = this.pessoaRepository.findById(id).get();
      this.pessoaRepository.delete(pessoa);
    }
}
