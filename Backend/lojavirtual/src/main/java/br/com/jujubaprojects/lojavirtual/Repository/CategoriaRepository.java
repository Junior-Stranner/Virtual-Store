package br.com.jujubaprojects.lojavirtual.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.lojavirtual.entity.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria , Long>{
    
}
