package br.com.centerhelp.dominio.servico.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SERVICO")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_SERVICO")
    @SequenceGenerator(name = "SQ_SERVICO",sequenceName = "SQ_SERVICO")
    @Column(name = "ID_SERVICO")
    private Long id;
    @Column(name = "NM_SERVICO")
    private String nome;
    @Column(name = "DS_SERVICO")
    private String descricao;
    @Column(name = "DT_ABERTURA_SERVICO")
    private LocalDateTime abertura;
    @Column(name = "DT_AUTORIZACAO_SERVICO")
    private LocalDateTime autorizacao;
    @Column(name = "DT_INICIO_SERVICO")
    private LocalDateTime inicio;
    @Column(name = "DT_FIM_SERVICO")
    private LocalDateTime fim;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(
            name = "ID_TP_SERVICO",
            referencedColumnName = "ID_TP_SERVICO",
            foreignKey = @ForeignKey(
                    name = "FK_TP_SERVICO",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private TipoServico tipo;

    public Servico() {
    }

    public Long getId() {
        return id;
    }

    public Servico setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Servico setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Servico setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public Servico setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
        return this;
    }

    public LocalDateTime getAutorizacao() {
        return autorizacao;
    }

    public Servico setAutorizacao(LocalDateTime autorizacao) {
        this.autorizacao = autorizacao;
        return this;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public Servico setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
        return this;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public Servico setFim(LocalDateTime fim) {
        this.fim = fim;
        return this;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public Servico setTipo(TipoServico tipo) {
        this.tipo = tipo;
        return this;
    }

    public Servico(Long id, String nome, String descricao, LocalDateTime abertura, LocalDateTime autorizacao, LocalDateTime inicio, LocalDateTime fim, TipoServico tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.abertura = abertura;
        this.autorizacao = autorizacao;
        this.inicio = inicio;
        this.fim = fim;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", abertura=" + abertura +
                ", autorizacao=" + autorizacao +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", tipo=" + tipo +
                '}';
    }
}
