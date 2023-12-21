package br.com.jujubaprojects.lojavirtual.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jujubaprojects.lojavirtual.entity.Permissao;
import br.com.jujubaprojects.lojavirtual.entity.PermissaoPessoa;

public interface PermissaoPessoaRepository extends JpaRepository<Permissao, Long>{

    void save(PermissaoPessoa permissaoPessoa);

    
}
