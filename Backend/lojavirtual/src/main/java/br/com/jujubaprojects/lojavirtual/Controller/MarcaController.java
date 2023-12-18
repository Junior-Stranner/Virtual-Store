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

import br.com.jujubaprojects.lojavirtual.Service.MarcaService;
import br.com.jujubaprojects.lojavirtual.entity.Marca;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {
    
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/")
    public List<Marca> buscarTodos(){
        return this.marcaService.buscarTodos();
    }

    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestBody Marca marca){
        return this.marcaService.inserir(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable("id") long id, Marca marca){
        return this.marcaService.alterar(marca, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
        return this.marcaService.excluir(id);
    }

}
