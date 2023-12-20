package br.com.jujubaprojects.lojavirtual.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.lojavirtual.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
    

    List<Permissao> findByNome(String nome);

}
