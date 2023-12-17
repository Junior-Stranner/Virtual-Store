package br.com.jujubaprojects.lojavirtual.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


}
