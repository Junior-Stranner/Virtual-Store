package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.EstadoRepository;
import br.com.jujubaprojects.lojavirtual.entity.Estado;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> buscarTodos(){
        return this.estadoRepository.findAll();
    }

    public ResponseEntity<?> inserir( Estado estado){

        List<Estado> estados = this.estadoRepository.findAll();
        boolean estadoExistente = estados.stream().anyMatch(eExistente ->  eExistente.getNome().equals(estado.getNome()) );
        //    estado.setDataCriacao(LocalDateTime.now()); // Configura a data de criação manualmente

        if (estado.getNome().isEmpty()) {
            System.out.println("Nome da estado é nulo!");
            return ResponseEntity.badRequest().body("Digite o nome da Estado!");

     } else if(estadoExistente){
            return new ResponseEntity<>("Não é possivel criar estados com o mesmo nome" , HttpStatus.BAD_REQUEST);
        
        }else{
            this.estadoRepository.save(estado);
       return new ResponseEntity<>("Estado criado com sucesso !!", HttpStatus.CREATED); 
        }
        
    }
   
    public ResponseEntity<?> alterar(Estado estado){
//   estado.setDataAtualizacao(LocalDateTime.now()); // Configura a data de atualização manualmente
       Optional<Estado> estadoOptional = this.estadoRepository.findById(estado.getId());
        

       if(estadoOptional.isPresent()){
        Estado estadoExistente = estadoOptional.get();

        estadoExistente.setNome(estado.getNome());
        estadoExistente.setSigla(estado.getSigla());
        estadoExistente.setDataCriacao(estado.getDataCriacao());
        
        estado.setDataAtualizacao(LocalDateTime.now());

           this.estadoRepository.save(estadoExistente);
        return  ResponseEntity.ok().body("estado Atualizado com succeso !!");
       }else{
        throw new EntityNotFoundException("Cidade não encontrada");
       }
        
       
    }

    public ResponseEntity<?> excluir(Long id){
       Estado estado = this.estadoRepository.findById(id).get();
       this.estadoRepository.delete(estado);
       return ResponseEntity.ok().body("Estado excluido com sucesso !");
    }

}
