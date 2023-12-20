package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PessoaClienteRepository;
import br.com.jujubaprojects.lojavirtual.Repository.PessoaRepository;
import br.com.jujubaprojects.lojavirtual.dto.PessoaClienteRequestDTO;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

@Service
public class PessoaClienteService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

   
    
    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
        pessoa.setDataCriacao(LocalDateTime.now());
        Pessoa objetoNovo = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(objetoNovo);
        //emailService.enviarEmailTexto(objetoNovo.getEmail(), "Cadastro na Loja Tabajara", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
   //     Map<String, Object> proprMap = new HashMap<>();
   //     proprMap.put("nome", objetoNovo.getNome());
   //     proprMap.put("mensagem", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
    //    emailService.enviarEmailTemplate(objetoNovo.getEmail(), "Cadastro na Loja Tabajara", proprMap);
        return objetoNovo;
    }

}
