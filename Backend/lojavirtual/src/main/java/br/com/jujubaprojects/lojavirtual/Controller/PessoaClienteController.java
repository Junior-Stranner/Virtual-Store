package br.com.jujubaprojects.lojavirtual.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.lojavirtual.Service.PessoaClienteService;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

@RestController
@RequestMapping("/api/cliente")
public class PessoaClienteController {
    
    @Autowired
    private PessoaClienteService pessoaClienteService;

    @PostMapping("/")
    public Pessoa inserir(@RequestBody Pessoa pessoaCliente){
        return this.pessoaClienteService.registrar(pessoaCliente);
    }
/*     @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody  Pessoa pessoa){
        return this.pessoaService.alterar(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
         return this.pessoaService.excluir(id);
    }
*/
}
