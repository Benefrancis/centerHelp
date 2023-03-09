package br.com.centerhelp.dominio.equipamento.model;


import jakarta.persistence.*;

@Entity
@Table(name = "TB_TP_EQUIPAMENTO", uniqueConstraints = @UniqueConstraint(name = "UK_NM_TP_EQUIPAMENTO", columnNames = "NM_TP_EQUIPAMENTO") )
public class TipoEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TP_EQUIPAMENTO")
    @SequenceGenerator(name = "SQ_TP_EQUIPAMENTO", sequenceName = "SQ_TP_EQUIPAMENTO", initialValue = 1, allocationSize = 20)
    @Column(name = "ID_TP_EQUIPAMENTO")
    private Long id;

    @Column(name = "NM_TP_EQUIPAMENTO")
    private String nome;

    public TipoEquipamento() {
    }

    public TipoEquipamento(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public TipoEquipamento setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public TipoEquipamento setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String toString() {
        return nome.equals("") ? "" : nome.toUpperCase();
    }
}
