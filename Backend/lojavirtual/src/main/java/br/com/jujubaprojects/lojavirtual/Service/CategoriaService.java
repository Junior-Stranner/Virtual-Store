package br.com.jujubaprojects.lojavirtual.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.CategoriaRepository;
import br.com.jujubaprojects.lojavirtual.entity.Categoria;
import br.com.jujubaprojects.lojavirtual.entity.Estado;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository catogoriaRepository;

    public List<Categoria> buscarTodos(){
        return this.catogoriaRepository.findAll();

    }

    public ResponseEntity<?> inserir(Categoria categoria){

       List<Categoria> categorias = this.catogoriaRepository.findAll();
        boolean categoriaExistente = categorias.stream().anyMatch(cExistente  -> cExistente.getNome().equals(categoria.getNome()));

        if(categoriaExistente){
           
            return ResponseEntity.badRequest().body("não é possível criar categorias com o mesmo nome !");
        }else{
              this.catogoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoria adicionada com sucesso", HttpStatus.CREATED);

        }
        
    }

}
