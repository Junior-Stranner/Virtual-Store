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
@RequestMapping("/api/estado")
public class EstadoController {
    
    @Autowired
    private EstadoService estadoService;

    @GetMapping("/")
    public List<Estado> buscarTodos(){
        return this.estadoService.buscarTodos();
    }

    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestBody Estado estado){
       return this.estadoService.inserir(estado);
     // return ResponseEntity.ok().body("Estado criado com sucesso !");
    }
   
    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody Estado estado) {
        return this.estadoService.alterar(estado);
       
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id){
       this.estadoService.excluir(id);
       return ResponseEntity.ok().body("Estado excluido com sucesso !");
    }
}
