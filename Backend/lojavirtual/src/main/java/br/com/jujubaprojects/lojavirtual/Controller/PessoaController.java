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

import br.com.jujubaprojects.lojavirtual.Service.PessoaService;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/")
    public List<Pessoa> buscarTodos(){
        return this.pessoaService.buscarTodos();
    }


    @PostMapping("/")
    public ResponseEntity<?> inserir(@RequestBody Pessoa pessoa){
      return  this.pessoaService.inserir(pessoa);
    }

    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody  Pessoa pessoa){
        return this.pessoaService.alterar(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
         return this.pessoaService.excluir(id);
    }

}
