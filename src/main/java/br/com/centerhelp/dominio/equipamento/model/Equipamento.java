package br.com.centerhelp.dominio.equipamento.model;

import br.com.centerhelp.dominio.cliente.model.Cliente;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "TB_EQUIPAMENTO")
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EQUIPAMENTO")
    @SequenceGenerator(name = "SQ_EQUIPAMENTO", sequenceName = "SQ_EQUIPAMENTO")
    private Long id;
    @Column(name = "NM_EQUIPAMENTO")
    private String nome;
    @Column(name = "NR_SERIE")
    private String numeroDeSerie;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "ID_TP_EQUIPAMENTO", referencedColumnName = "ID_TP_EQUIPAMENTO",
            foreignKey = @ForeignKey(name = "FK_TP_EQUIPAMENTO", value = ConstraintMode.CONSTRAINT)
    )
    private TipoEquipamento tipo;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE",
            foreignKey = @ForeignKey(name = "FK_EQUIPAMENTO_CLIENTE", value = ConstraintMode.CONSTRAINT)
    )
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public Equipamento setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Equipamento setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public Equipamento setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
        return this;
    }

    public TipoEquipamento getTipo() {
        return tipo;
    }

    public Equipamento setTipo(TipoEquipamento tipo) {
        this.tipo = tipo;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Equipamento setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public Equipamento(Long id, String nome, String numeroDeSerie, TipoEquipamento tipo, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.numeroDeSerie = numeroDeSerie;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public Equipamento() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Equipamento{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", numeroDeSerie='").append(numeroDeSerie).append('\'');
        sb.append(", tipo=").append(tipo);
        sb.append(", cliente=").append(cliente);
        sb.append('}');
        return sb.toString();
    }
}
