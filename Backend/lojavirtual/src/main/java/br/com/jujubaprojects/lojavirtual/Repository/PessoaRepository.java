package br.com.jujubaprojects.lojavirtual.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jujubaprojects.lojavirtual.entity.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa , Long>{
    
   @Query("SELECT p FROM Pessoa p JOIN p.permissoes perm WHERE perm.nome = 'cliente'")
    List<Pessoa> findAllClientes();
}
