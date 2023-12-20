package br.com.jujubaprojects.lojavirtual.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.lojavirtual.dto.PessoaClienteRequestDTO;

public interface PessoaClienteRepository extends JpaRepository<PessoaClienteRequestDTO , Long> {
    
}
