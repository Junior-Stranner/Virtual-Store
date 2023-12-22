package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.PermissaoRepository;
import br.com.jujubaprojects.lojavirtual.entity.Permissao;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

@Service
public class PermissaoPessoaService {

   
    
    @Autowired 
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarTodos(){
        return this.permissaoRepository.findAll();
    }

    public void vincularPessoaPermissaoCliente(Pessoa pessoaCliente) {
    }


  
 /*    public ResponseEntity<?> inserir(Permissao permissao){
        try {
       List<Permissao> permissaoes = this.permissaoRepository.findAll();
       boolean permissaoExistente = permissaoes.stream().anyMatch(permiExistente -> permiExistente.getNome().equals(permissao.getNome()));

       if(permissao.getNome().isEmpty()){

         return ResponseEntity.badRequest().body("Permissão não pode estar vazio !");
       }else if(permissaoExistente){
         return ResponseEntity.badRequest().body("Pernissão já existente !");

       }else{
          this.permissaoRepository.save(permissao);
       return new ResponseEntity<>("Permissão cadastrada com sucesso !",HttpStatus.CREATED);

       }
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
          return ResponseEntity.internalServerError().body("Erro ao cadastrar a permissão ...");
        }
      
    }

    public ResponseEntity<?> alterar(Permissao permissao){

    Optional<Permissao> permisOptional = this.permissaoRepository.findById(permissao.getId());

    if(permisOptional.isPresent()){
        Permissao permissaoExistete = permisOptional.get();
        permissaoExistete.setNome(permissao.getNome());
        permissaoExistete.setDataCriacao(permissao.getDataCriacao());
        permissaoExistete.setDataAtualizacao(LocalDateTime.now());

        this.permissaoRepository.save(permissaoExistete);
        return ResponseEntity.ok().body("Marca atualizada com sucesso !");
    }
    throw new EntityNotFoundException("Marca não encontrada !");

   }

     public ResponseEntity<?> excluir(Long id){
       Permissao permissao = this.permissaoRepository.findById(id).get();
      this.permissaoRepository.delete(permissao);
      return ResponseEntity.ok().body("permissao excluido com sucesso !");
     }*/
}
