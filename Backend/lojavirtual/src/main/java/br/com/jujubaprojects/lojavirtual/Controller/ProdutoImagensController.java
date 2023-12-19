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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.jujubaprojects.lojavirtual.Service.ProdutoImagensService;
import br.com.jujubaprojects.lojavirtual.entity.ProdutoImagens;

@RestController
@RequestMapping("/api/produtoImagens")
public class ProdutoImagensController {
    
    @Autowired
    private ProdutoImagensService produtoImagensService;

    @GetMapping("/")
    public List<ProdutoImagens> buscarTodos(){
        return this.produtoImagensService.buscarTodos();
    }

    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestParam("idProduto") Long idProduto,  @RequestParam("file") MultipartFile file){
      return  this.produtoImagensService.inserir(idProduto,file);
    }

    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody  ProdutoImagens produtoImagens){
        return this.produtoImagensService.alterar(produtoImagens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
         return this.produtoImagensService.excluir(id);
    }
}
