package br.ufmg.pedagio.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tarifa {
    private final CategoriaVeiculo categoria;
    private final BigDecimal valor;

    public Tarifa(CategoriaVeiculo categoria, BigDecimal valor) {
        this.categoria = Objects.requireNonNull(categoria, "Categoria obrigatoria");
        this.valor = Objects.requireNonNull(valor, "Valor obrigatorio");
        if (valor.signum() <= 0) throw new IllegalArgumentException("Tarifa deve ser positiva");
    }

    public CategoriaVeiculo getCategoria() { return categoria; }
    public BigDecimal getValor() { return valor; }
}
