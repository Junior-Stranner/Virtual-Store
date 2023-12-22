package br.com.jujubaprojects.lojavirtual.entity;

import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_pessoa")
@Data
public class Pessoa {

     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_sequence")
    @SequenceGenerator(name = "pessoa_sequence", sequenceName = "pessoa_sequence", allocationSize = 1)
    private long id;

    private String nome;
    private String cpf = "cpfPadrão";
    private String email;
    private String senha;
    private String endereco;
    private String cep;

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
    @JoinColumn(name = "cidade_id")
   private Cidade cidade;

    @OneToMany(mappedBy = "pessoa")
    private List<Permissao> permissaoPessoa;


    public void setPermissaoPessoas(List<Permissao> listaPermissaoPessoas){    

       for (Permissao permissaoPessoa : listaPermissaoPessoas) {
         permissaoPessoa.setPessoa(this);
       }
       this.permissaoPessoa = listaPermissaoPessoas;
   }
   
  }
