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

import br.com.jujubaprojects.lojavirtual.Service.PermissaoService;
import br.com.jujubaprojects.lojavirtual.entity.Permissao;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;
import br.com.jujubaprojects.lojavirtual.entity.Produto;

@RestController
@RequestMapping("/api/permissao")
public class PermissaoController {

    @Autowired
    private  PermissaoService permissaoService;
    

     @GetMapping("/")
    public List<Permissao> buscarTodos(){
        return this.permissaoService.buscarTodos();
    }

    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestBody Permissao permissao){
      return  this.permissaoService.inserir(permissao);
    }

    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody  Permissao permissao){
        return this.permissaoService.alterar(permissao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
         return this.permissaoService.excluir(id);
    }

}
