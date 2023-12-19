package br.com.jujubaprojects.lojavirtual.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.lojavirtual.Repository.ProdutoRepository;
import br.com.jujubaprojects.lojavirtual.entity.Pessoa;
import br.com.jujubaprojects.lojavirtual.entity.Produto;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos(){
        return this.produtoRepository.findAll();
    }

    public ResponseEntity<?> inserir( Produto produto){

        List<Produto> produtos = this.produtoRepository.findAll();
        boolean produtoExistente = produtos.stream().anyMatch(pExistente -> pExistente.getNome().equals(produto.getNome()));

        if(produto.getValorCusto().isEmpty() || produto.getValorVenda().isEmpty() || produto.getNome().isEmpty()){
          
            return ResponseEntity.ofNullable("Valor e venda não pode etsa vazio !");
        }else if(produtoExistente){
           
            return ResponseEntity.badRequest().body("Produto já existente !");
      }else {
        this.produtoRepository.save(produto);
        return new ResponseEntity<>("Produto cadastrado com sucesso !",HttpStatus.CREATED);
       }
  }

      public ResponseEntity<?> alterar(Produto produto){

        Optional<Produto> produtoOptional = this.produtoRepository.findById(produto.getId());

        if(produtoOptional.isPresent()){
            Produto produtoExistente = produtoOptional.get();
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setCategoria(produto.getCategoria());
            produtoExistente.setMarca(produto.getMarca());
            produtoExistente.setValorCusto(produto.getValorCusto());
            produtoExistente.setValorVenda(produto.getValorVenda());
            produtoExistente.setDataCriacao(produto.getDataCriacao());
            produtoExistente.setDataAtualizacao(LocalDateTime.now());

            this.produtoRepository.save(produtoExistente);
            return ResponseEntity.ok().body("Produto atualizado com sucesso ! ");
        }else{
            
            throw new EntityNotFoundException("Produto não encontrdo !");
        }
      }

        public ResponseEntity<?> excluir(Long id){
       Produto produto = this.produtoRepository.findById(id).get();
      this.produtoRepository.delete(produto);
      return ResponseEntity.ok().body("produto excluido com sucesso !");
    }
}
