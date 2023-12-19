package br.com.jujubaprojects.lojavirtual.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_produto")
@Data
public class Produto {
    
      @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_sequence")
    @SequenceGenerator(name = "pessoa_sequence", sequenceName = "pessoa_sequence", allocationSize = 1)
    private long id;
    private String nome;

    private String descricaoCurta;
    private String descricaoDetalhada;
    private String valorCusto;
    private String valorVenda;
 
    @CreatedDate
    @Column(name = "data_criacao" , updatable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy - HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime dataCriacao = LocalDateTime.now();

   @LastModifiedDate
    @Column(name = "data_atualizacao" , updatable = true)
    @DateTimeFormat(pattern = "dd/MM/yyyy - HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "marca_id")
    private Marca marca;

     @ManyToOne()
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
