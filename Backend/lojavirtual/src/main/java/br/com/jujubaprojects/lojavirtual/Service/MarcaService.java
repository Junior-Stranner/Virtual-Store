package br.com.jujubaprojects.lojavirtual.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.MarcaRepository;
import br.com.jujubaprojects.lojavirtual.entity.Marca;

@Service
public class MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    public ResponseEntity<?> inserir(Marca marca){

        List<Marca> marcas = this.marcaRepository.findAll();
        boolean marcaExistente
       
        if(marca.getNome().isEmpty()){
            return ResponseEntity.ofNullable("digite o nome da Marca !");
        }
   }
}
