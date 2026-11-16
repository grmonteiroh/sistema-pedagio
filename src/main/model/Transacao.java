package model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Transacao {
    private final UUID id;
    private final Passagem passagem;
    private final CategoriaVeiculo categoria;
    private final BigDecimal valor;
    private final FormaIdentificacao formaIdentificacao;
    private final StatusCobranca status;

    public Transacao(Passagem passagem, CategoriaVeiculo categoria, BigDecimal valor,
                     FormaIdentificacao formaIdentificacao, StatusCobranca status) {
        this.id = UUID.randomUUID();
        this.passagem = Objects.requireNonNull(passagem, "Passagem obrigatoria");
        this.categoria = Objects.requireNonNull(categoria, "Categoria obrigatoria");
        this.valor = Objects.requireNonNull(valor, "Valor obrigatorio");
        this.formaIdentificacao = Objects.requireNonNull(formaIdentificacao, "Forma obrigatoria");
        this.status = Objects.requireNonNull(status, "Status obrigatorio");
    }

    public UUID getId() { return id; }
    public Passagem getPassagem() { return passagem; }
    public CategoriaVeiculo getCategoria() { return categoria; }
    public BigDecimal getValor() { return valor; }
    public FormaIdentificacao getFormaIdentificacao() { return formaIdentificacao; }
    public StatusCobranca getStatus() { return status; }
}
