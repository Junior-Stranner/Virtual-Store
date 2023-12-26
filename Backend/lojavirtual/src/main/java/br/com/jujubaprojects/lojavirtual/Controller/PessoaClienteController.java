package br.com.jujubaprojects.lojavirtual.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.lojavirtual.Repository.PermissaoPessoaRepository;
import br.com.jujubaprojects.lojavirtual.Repository.PessoaClienteRepository;
import br.com.jujubaprojects.lojavirtual.Service.PessoaClienteService;


@RestController
@RequestMapping("/api/pessoaCliente")
public class PessoaClienteController {

    
    @Autowired
    private PessoaClienteService pessoaClienteService;

  
  
}
