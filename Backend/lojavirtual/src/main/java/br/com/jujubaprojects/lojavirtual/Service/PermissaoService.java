package br.com.jujubaprojects.lojavirtual.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PermissaoRepository;
import br.com.jujubaprojects.lojavirtual.entity.Permissao;

@Service
public class PermissaoService {
    
    @Autowired 
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarTodos(){
        return this.permissaoRepository.findAll();
    }

    public ResponseEntity<?> inserir(Permissao permissao){
       List<Permissao> permissaoes = this.permissaoRepository.findAll();
       boolean permissaoExistente = permissaoes.stream().anyMatch(permiExistente -> permiExistente.getNome().equals(permissao.getNome()));

       if(permissao.getNome().isEmpty()){

         return ResponseEntity.badRequest().body("Permissão não pode estar vazio !");
       }else if(permissaoExistente){
         return ResponseEntity.badRequest().body("Pernissão já existente !");

       }else{
          this.permissaoRepository.save(permissao);
       return new ResponseEntity<>("Permissão cadastrada com sucesso !",HttpStatus.CREATED);

       }
    }
}
