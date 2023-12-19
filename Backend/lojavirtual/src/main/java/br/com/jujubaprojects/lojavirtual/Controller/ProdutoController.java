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

import br.com.jujubaprojects.lojavirtual.Service.ProdutoService;
import br.com.jujubaprojects.lojavirtual.entity.Produto;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    
    @GetMapping("/")
    public List<Produto> buscarTodos(){
        return this.produtoService.buscarTodos();
    }

    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestBody Produto produto){
      return  this.produtoService.inserir(produto);
    }

    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody  Produto produto){
        return this.produtoService.alterar(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
         return this.produtoService.excluir(id);
    }

}
