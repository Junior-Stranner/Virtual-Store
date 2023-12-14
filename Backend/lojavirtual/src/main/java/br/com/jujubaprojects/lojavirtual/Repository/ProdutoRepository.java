package br.com.jujubaprojects.lojavirtual.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.lojavirtual.entity.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{
    
}
