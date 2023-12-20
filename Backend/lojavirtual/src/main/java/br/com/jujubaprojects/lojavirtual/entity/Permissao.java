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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_permissao")

public class Permissao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissao_sequence")
    @SequenceGenerator(name = "permissao_sequence", sequenceName = "permissao_sequence", allocationSize = 1)
    private long id;
    private String nome;

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


public Permissao(long id, String nome, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
    this.id = id;
    this.nome = nome;
    this.dataCriacao = dataCriacao;
    this.dataAtualizacao = dataAtualizacao;
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




}

