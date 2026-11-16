package model;

import java.util.Objects;

public class Veiculo {
    private final String placa;
    private final CategoriaVeiculo categoria;
    private final Tag tag;

    public Veiculo(String placa, CategoriaVeiculo categoria, Tag tag) {
        if (placa == null || placa.isBlank()) throw new IllegalArgumentException("Placa obrigatoria");
        this.placa = placa;
        this.categoria = Objects.requireNonNull(categoria, "Categoria obrigatoria");
        this.tag = tag;
    }

    public String getPlaca() { return placa; }
    public CategoriaVeiculo getCategoria() { return categoria; }
    public Tag getTag() { return tag; }
}
