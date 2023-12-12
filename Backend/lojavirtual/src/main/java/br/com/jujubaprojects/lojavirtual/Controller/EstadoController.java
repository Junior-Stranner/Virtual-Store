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

import br.com.jujubaprojects.lojavirtual.Service.EstadoService;
import br.com.jujubaprojects.lojavirtual.entity.Estado;

@RestController
@RequestMapping("/estado")
public class EstadoController {
    
    @Autowired
    private EstadoService estadoService;

    @GetMapping("/")
    public List<Estado> buscarTodos(){
        return this.estadoService.buscarTodos();
    }

    @PostMapping("/")
    public Estado inserir(@RequestBody Estado estado){
         return this.estadoService.inserir(estado);
    }
   
    @PutMapping("/")
    public ResponseEntity<Estado> alterar(@RequestBody Estado estado) {
       this.estadoService.alterar(estado);
        
        if (estado != null) {
            return ResponseEntity.ok(this.estadoService.alterar(estado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> excluir(@PathVariable("id") Long id){
       this.estadoService.excluir(id);
       return ResponseEntity.ok().build();
    }
}
