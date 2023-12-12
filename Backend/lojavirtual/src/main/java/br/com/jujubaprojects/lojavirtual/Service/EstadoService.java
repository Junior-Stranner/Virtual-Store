package br.com.jujubaprojects.lojavirtual.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Estado inserir( Estado estado){
    //    estado.setDataCriacao(LocalDateTime.now()); // Configura a data de criação manualmente
         Estado estadoNovo = this.estadoRepository.saveAndFlush(estado);
         return estadoNovo;
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
