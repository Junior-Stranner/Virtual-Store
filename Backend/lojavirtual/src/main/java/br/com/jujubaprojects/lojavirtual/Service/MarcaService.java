package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.MarcaRepository;
import br.com.jujubaprojects.lojavirtual.entity.Marca;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> buscarTodos(){
        return this.marcaRepository.findAll();
    }
    
    public ResponseEntity<?> inserir(Marca marca){

        List<Marca> marcas = this.marcaRepository.findAll();
        boolean marcaExistente = marcas.stream().anyMatch(mExiststente -> mExiststente.getNome().equals(marca.getNome()));
       
        if(marca.getNome().isEmpty()){
            return ResponseEntity.ofNullable("digite o nome da Marca !");

        }else if(marcaExistente){
            
            return ResponseEntity.badRequest().body("Marca já existente !");
        }else{
               this.marcaRepository.save(marca);
            return new ResponseEntity<>("Marca Cadastrada com sucesso ! " , HttpStatus.CREATED);
        }
   }

   public ResponseEntity<?> alterar(Marca marca, Long id){

    Optional<Marca> marcaaOptional = this.marcaRepository.findById(marca.getId());

    if(marcaaOptional.isPresent()){
        Marca marcaExistente = marcaaOptional.get();
        marcaExistente.setNome(marca.getNome());
        marcaExistente.setDataCriacao(marca.getDataCriacao());
        marcaExistente.setDataAtualizacao(LocalDateTime.now());

        this.marcaRepository.save(marcaExistente);
        return ResponseEntity.ok().body("Marca atualizada com sucesso !");
    }
    throw new EntityNotFoundException("Marca não encontrada !");

   }

     public ResponseEntity<?> excluir(Long id){
       Marca marca = this.marcaRepository.findById(id).get();
      this.marcaRepository.delete(marca);
      return ResponseEntity.ok().body("pessoa excluido com sucesso !");
    }
}
