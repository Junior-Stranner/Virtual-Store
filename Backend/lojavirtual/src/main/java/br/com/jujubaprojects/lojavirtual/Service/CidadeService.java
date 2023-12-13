package br.com.jujubaprojects.lojavirtual.Service;

import java.util.List;

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
         Cidade cidadeNova = this.cidadeRepository.save(cidade);
         return cidadeNova;
    }
   
    public ResponseEntity<?> alterar(Cidade cidade){
         if (cidade != null) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void excluir(Long id){
       Cidade cidade = this.cidadeRepository.findById(id).get();
       this.cidadeRepository.delete(cidade);
    }
    
}
