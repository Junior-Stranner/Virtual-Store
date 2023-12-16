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

import br.com.jujubaprojects.lojavirtual.Service.CategoriaService;
import br.com.jujubaprojects.lojavirtual.entity.Categoria;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> buscarTodos(){
        return this.categoriaService.buscarTodos();
    }
    
    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestBody Categoria categoria){
        return this.categoriaService.inserir(categoria);

    }

    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody Categoria categoria){
      return this.categoriaService.alterar(categoria);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") long id){
        this.categoriaService.excluir(id);
    }
}
