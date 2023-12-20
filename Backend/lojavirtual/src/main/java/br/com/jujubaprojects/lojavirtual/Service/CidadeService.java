package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.CidadeRepository;
import br.com.jujubaprojects.lojavirtual.entity.Cidade;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CidadeService {

    @Autowired
    CidadeRepository cidadeRepository;

       public List<Cidade> buscarTodos(){
        return this.cidadeRepository.findAll();
    }

    public ResponseEntity<?> inserir( Cidade cidade){
       // cidade.setDataCriacao(LocalDateTime.now());

         List<Cidade> cidades = this.cidadeRepository.findAll();
        boolean cidadeExistente = cidades.stream().anyMatch(ciExistente -> ciExistente.getNome().equals(cidade.getNome()));

        if (cidade.getNome().isEmpty()) {
            System.out.println("Nome da cidade é nulo!");
            return ResponseEntity.badRequest().body("Digite o nome da cidade!");

      } else if(cidadeExistente){
          
            return ResponseEntity.badRequest().body("cidade Existente");
        }else{
        this.cidadeRepository.save(cidade);
        return new ResponseEntity<>("cidade criada com sucesso ! ", HttpStatus.CREATED);

        }

    }
   
    public ResponseEntity<?> alterar(Cidade cidade, long id){
      //  cidade.setDataAtualizacao(LocalDateTime.now());

       Optional<Cidade> cidadeOptional = this.cidadeRepository.findById(id);

       if(cidadeOptional.isPresent()){
        Cidade cidadeExistente = cidadeOptional.get();

        cidadeExistente.setNome(cidade.getNome());
        cidadeExistente.setDataCriacao(cidade.getDataCriacao());
        cidadeExistente.setEstado(cidade.getEstado());

        //define a data de atualização
        cidadeExistente.setDataAtualizacao(LocalDateTime.now());

        this.cidadeRepository.save(cidadeExistente);
        return ResponseEntity.ok().body("Cidade atualizada com sucesso !");

       }else{ 

        throw new EntityNotFoundException("Cidade não encontrada");
       // return ResponseEntity.notFound().build(); 
       

       }
    }

    public ResponseEntity<?> excluir(long id){
       Cidade cidade = this.cidadeRepository.findById(id).get();
       this.cidadeRepository.delete(cidade);
       return ResponseEntity.ok().body("cidade excluido com sucesso !");
    }
    
}
