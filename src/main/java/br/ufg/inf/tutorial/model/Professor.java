package br.ufg.inf.tutorial;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

@Entity
@Table(name = "professores")
public class Professor extends Usuario {
    @Column(name = "identificacao_funcional", nullable = false, unique = true)
    private String identificacaoFuncional;

    // Getters e setters
    public String getIdentificacaoFuncional() { return identificacaoFuncional; }
    public void setIdentificacaoFuncional(String identificacaoFuncional) { this.identificacaoFuncional = identificacaoFuncional; }
}