package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.CidadeRepository;
import br.com.jujubaprojects.lojavirtual.entity.Cidade;

@Service
public class CidadeService {

    @Autowired
    CidadeRepository cidadeRepository;

       public List<Cidade> buscarTodos(){
        return this.cidadeRepository.findAll();
    }

    public Cidade inserir( Cidade cidade){
        cidade.setDataCriacao(LocalDateTime.now());
         return  this.cidadeRepository.save(cidade);
    }
   
    public ResponseEntity<?> alterar(Cidade cidade, long id){
        cidade.setDataAtualizacao(LocalDateTime.now());

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
        
         return  ResponseEntity.notFound().build();

       }
    }

    public void excluir(long id){
       Cidade cidade = this.cidadeRepository.findById(id).get();
       this.cidadeRepository.delete(cidade);
    }
    
}
