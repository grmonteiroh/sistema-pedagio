package br.ufmg.pedagio.service;

import br.ufmg.pedagio.model.CategoriaVeiculo;
import br.ufmg.pedagio.model.StatusCobranca;
import br.ufmg.pedagio.model.Tag;
import br.ufmg.pedagio.model.Tarifa;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class CobrancaService {
    private static final Map<CategoriaVeiculo, BigDecimal> VALORES = Map.of(
        CategoriaVeiculo.MOTOCICLETA, new BigDecimal("5.00"),
        CategoriaVeiculo.CARRO, new BigDecimal("10.00"),
        CategoriaVeiculo.ONIBUS, new BigDecimal("20.00"),
        CategoriaVeiculo.CAMINHAO, new BigDecimal("30.00")
    );

    public Tarifa calcularTarifa(CategoriaVeiculo categoria) {
        Objects.requireNonNull(categoria, "Categoria obrigatoria");
        return new Tarifa(categoria, VALORES.get(categoria));
    }

    public StatusCobranca cobrar(Tag tag, Tarifa tarifa) {
        Objects.requireNonNull(tarifa, "Tarifa obrigatoria");
        if (tag == null) return StatusCobranca.PENDENTE;
        return tag.debitar(tarifa.getValor()) ? StatusCobranca.PAGA : StatusCobranca.PENDENTE;
    }
}
