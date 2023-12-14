package br.com.jujubaprojects.lojavirtual.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.lojavirtual.entity.Marca;

public interface MarcaRepository extends JpaRepository < Marca, Long > {
    
}
