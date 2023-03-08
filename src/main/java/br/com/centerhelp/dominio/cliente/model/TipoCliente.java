package br.com.centerhelp.dominio.cliente.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_TP_CLIENTE")
public class TipoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TP_CLIENTE")
    @SequenceGenerator(name = "SQ_TP_CLIENTE", sequenceName = "SQ_TP_CLIENTE")
    @Column(name = "ID_TP_CLIENTE")
    private Long id;

    @Column(name = "NM_TP_CLIENTE")
    private String nome;

    public TipoCliente() {
    }

    public TipoCliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public TipoCliente setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoCliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return "TipoCliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
