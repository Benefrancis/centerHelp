package br.com.centerhelp.dominio.servico.model;


import jakarta.persistence.*;

@Entity
@Table(name = "TB_TP_SERVICO",
uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_NOME_TIPO_SERVICO",
                columnNames = "NM_TP_SERVICO"
        )
  }
)
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TP_SERVICO")
    @SequenceGenerator(name = "SQ_TP_SERVICO", sequenceName = "SQ_TP_SERVICO", initialValue = 1, allocationSize = 20)
    @Column(name = "ID_TP_SERVICO")
    private Long id;

    @Column(name = "NM_TP_SERVICO")
    private String nome;


    public TipoServico(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoServico() {
    }

    public Long getId() {
        return id;
    }

    public TipoServico setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoServico setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return nome.toUpperCase();
    }
}
