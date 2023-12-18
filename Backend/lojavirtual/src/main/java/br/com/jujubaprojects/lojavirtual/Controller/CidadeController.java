package br.com.jujubaprojects.lojavirtual.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.lojavirtual.Service.CidadeService;
import br.com.jujubaprojects.lojavirtual.entity.Cidade;
import br.com.jujubaprojects.lojavirtual.entity.Estado;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {
    
   @Autowired
   private CidadeService cidadeService;

 @GetMapping("/")
    public List<Cidade> buscarTodos(){
        return this.cidadeService.buscarTodos();
    }

    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestBody Cidade cidade){
         return this.cidadeService.inserir(cidade);
    }
   
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@RequestBody Cidade cidade ,@PathVariable long id) {
        return this.cidadeService.alterar(cidade, id);
       
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") long id){
       return this.cidadeService.excluir(id);
    }

}
