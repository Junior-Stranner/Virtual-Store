package br.com.jujubaprojects.lojavirtual.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.lojavirtual.entity.Pessoa;


public interface PessoaClienteRepository extends JpaRepository<Pessoa , Long> {
    
 //   List<Permissao> findByNomeCliente(String nome);
}
