package br.com.jujubaprojects.lojavirtual.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "tb_pessoa")

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

   @OneToMany(mappedBy = "pessoa", orphanRemoval = true , cascade = CascadeType.ALL)
 //  @Setter(value = AccessLevel.NONE)
   private List<PermissaoPessoa> permissaoPessoas;

   public Pessoa(){

   }


   public Pessoa(long id, String nome, String cpf, String email, String senha, String endereco, String cep,
         LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, Cidade cidade) {
      this.id = id;
      this.nome = nome;
      this.cpf = cpf;
      this.email = email;
      this.senha = senha;
      this.endereco = endereco;
      this.cep = cep;
      this.dataCriacao = dataCriacao;
      this.dataAtualizacao = dataAtualizacao;
      this.cidade = cidade;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getCpf() {
      return cpf;
   }

   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getSenha() {
      return senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public String getEndereco() {
      return endereco;
   }

   public void setEndereco(String endereco) {
      this.endereco = endereco;
   }

   public String getCep() {
      return cep;
   }

   public void setCep(String cep) {
      this.cep = cep;
   }

   public LocalDateTime getDataCriacao() {
      return dataCriacao;
   }

   public void setDataCriacao(LocalDateTime dataCriacao) {
      this.dataCriacao = dataCriacao;
   }

   public LocalDateTime getDataAtualizacao() {
      return dataAtualizacao;
   }

   public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
      this.dataAtualizacao = dataAtualizacao;
   }

   public Cidade getCidade() {
      return cidade;
   }

   public void setCidade(Cidade cidade) {
      this.cidade = cidade;
   }

    public void setPermissaoPessoas(List<PermissaoPessoa> NewPermissaoPessoas){    

       for (PermissaoPessoa pessoa : NewPermissaoPessoas) {
         pessoa.setPessoa(this);
       }
       this.permissaoPessoas = NewPermissaoPessoas;
   }
   
  }
