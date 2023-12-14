package br.com.jujubaprojects.lojavirtual.Service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.EstadoRepository;
import br.com.jujubaprojects.lojavirtual.entity.Estado;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> buscarTodos(){
        return this.estadoRepository.findAll();
    }

    public ResponseEntity<?> inserir( Estado estado){

        List<Estado> estados = this.estadoRepository.findAll();
        boolean estadoExistente = estados.stream().anyMatch(eExistente  -> eExistente.getNome().equals(estado.getNome()));
    //    estado.setDataCriacao(LocalDateTime.now()); // Configura a data de criação manualmente

        if(estadoExistente){
            return new ResponseEntity<>("Não é possivel criar estados com o mesmo nome" , HttpStatus.BAD_REQUEST);
        
        }else{
            this.estadoRepository.save(estado);
       return new ResponseEntity<>("Estado criado com sucesso !!", HttpStatus.CREATED); 
         
        }
    }
   
    public Estado alterar(Estado estado){
//   estado.setDataAtualizacao(LocalDateTime.now()); // Configura a data de atualização manualmente
        return this.estadoRepository.save(estado);
    }

    public void excluir(Long id){
       Estado estado = this.estadoRepository.findById(id).get();
       this.estadoRepository.delete(estado);
    }
}
