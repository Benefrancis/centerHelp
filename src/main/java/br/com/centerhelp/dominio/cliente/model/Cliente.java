package br.com.centerhelp.dominio.cliente.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE")
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NM_CLIENTE")
    private String nome;

    @Column(name = "EMAIL_CLIENTE")
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;
    @Embedded
    private Telefone telefone;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(
            name = "ID_TP_CLIENTE",
            referencedColumnName = "ID_TP_CLIENTE",
            foreignKey = @ForeignKey(
                    name = "FK_TP_CLIENTE",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private TipoCliente tipo;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, LocalDate nascimento, Telefone telefone, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Cliente setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Cliente setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Cliente setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public Cliente setTelefone(Telefone telefone) {
        this.telefone = telefone;
        return this;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public Cliente setTipo(TipoCliente tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cliente{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", nascimento=").append(nascimento);
        sb.append(", telefone=").append(telefone);
        sb.append(", tipo=").append(tipo);
        sb.append('}');
        return sb.toString();
    }
}
