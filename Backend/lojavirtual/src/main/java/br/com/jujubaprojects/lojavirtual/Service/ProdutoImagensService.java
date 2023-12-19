package br.com.jujubaprojects.lojavirtual.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.jujubaprojects.lojavirtual.Repository.ProdutoImagensRepository;
import br.com.jujubaprojects.lojavirtual.Repository.ProdutoRepository;
import br.com.jujubaprojects.lojavirtual.entity.Produto;
import br.com.jujubaprojects.lojavirtual.entity.ProdutoImagens;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoImagensService {
    
    @Autowired
    private ProdutoImagensRepository produtoImagensRepository;

     @Autowired
     private ProdutoRepository produtoRepository;

    
    public List<ProdutoImagens> buscarTodos(){
        return this.produtoImagensRepository.findAll();

    }

    public ResponseEntity<?> inserir(Long idProduto, MultipartFile file){
        Optional<Produto> produtoOptional = this.produtoRepository.findById(idProduto);



       if (produtoOptional.isPresent()) {
        Produto produto = produtoOptional.get();
        ProdutoImagens produtoImagens = new ProdutoImagens();

		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
                String nomeImagem = String.valueOf(produto.getId()) + file.getOriginalFilename();
				Path caminho = Paths.get("c:/imagens/" +nomeImagem );
				Files.write(caminho, bytes);
                produtoImagens.setNome(nomeImagem);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
   
        produtoImagens.setProduto(produto);
        produtoImagens.setDataCriacao(LocalDateTime.now());
        this.produtoImagensRepository.save(produtoImagens);
       return new ResponseEntity<>("produtoImagens adicionada com sucesso", HttpStatus.CREATED);
    }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado com o ID fornecido.");
    }
   
}

    public ResponseEntity<?> alterar(ProdutoImagens produtoImagens){

        Optional<ProdutoImagens> produtoImagensOptional = this.produtoImagensRepository.findById(produtoImagens.getId());
     //   Categoria categoriaExistente = null;

        if(produtoImagensOptional.isPresent()){
        ProdutoImagens  produtoImagensExistente = produtoImagensOptional.get();

        produtoImagensExistente.setNome(produtoImagens.getNome());
        produtoImagensExistente.setDataCriacao(produtoImagens.getDataCriacao());
        
        produtoImagensExistente.setDataAtualizacao(LocalDateTime.now());

           this.produtoImagensRepository.save(produtoImagensExistente);
             return ResponseEntity.ok().body("produtoImagens atualizada com sucesso !!");
        }else {
            throw new EntityNotFoundException("produtoImagens não encontrada ! ");
           // return ResponseEntity.badRequest().body("Categoria não encontrada !");

        }
    }

    public ResponseEntity<?> excluir(Long id){
       ProdutoImagens produtoImagens  = this.produtoImagensRepository.findById(id).get();
      this.produtoImagensRepository.delete(produtoImagens);
      return ResponseEntity.ok().body("produtoImagens excluido com sucesso !");
    }

}
