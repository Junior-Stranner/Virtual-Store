package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.CategoriaRepository;
import br.com.jujubaprojects.lojavirtual.entity.Categoria;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository catogoriaRepository;

    public List<Categoria> buscarTodos(){
        return this.catogoriaRepository.findAll();

    }

    public ResponseEntity<?> inserir(Categoria categoria){

       List<Categoria> categorias = this.catogoriaRepository.findAll();
        boolean categoriaExistente = categorias.stream().anyMatch(cExistente -> cExistente.getNome() != null && categoria.getNome() != null &&
        cExistente.getNome().equals(categoria.getNome()));

         if(categoriaExistente){
           
            return ResponseEntity.badRequest().body("não é possível criar categorias com o mesmo nome !");
        }else{
              this.catogoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoria adicionada com sucesso", HttpStatus.CREATED);

        }
    }

    public ResponseEntity<?> alterar(Categoria categoria){

        Optional<Categoria> categoriaOptional = this.catogoriaRepository.findById(categoria.getId());
     //   Categoria categoriaExistente = null;

        if(categoriaOptional.isPresent()){
        Categoria  categoriaExistente = categoriaOptional.get();

        categoriaExistente.setNome(categoria.getNome());
        categoriaExistente.setDataCriacao(categoriaExistente.getDataCriacao());
        
        categoriaExistente.setDataAtualizacao(LocalDateTime.now());

           this.catogoriaRepository.save(categoriaExistente);
             return ResponseEntity.ok().body("Categoria atualizada com sucesso !!");
        }else {
            throw new EntityNotFoundException("Categoria não encontrada ! ");
           // return ResponseEntity.badRequest().body("Categoria não encontrada !");

        }
    }

    public void excluir(Long id){
       Categoria categoria  = this.catogoriaRepository.findById(id).get();
      this.catogoriaRepository.delete(categoria);
    }
}
