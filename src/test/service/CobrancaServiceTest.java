package service;

import model.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CobrancaServiceTest {
    private final CobrancaService service = new CobrancaService();

    @Test void tarifaMotocicleta() {
        assertEquals(0, new BigDecimal("5.00").compareTo(service.calcularTarifa(CategoriaVeiculo.MOTOCICLETA).getValor()));
    }
    @Test void tarifaCarro() {
        assertEquals(0, new BigDecimal("10.00").compareTo(service.calcularTarifa(CategoriaVeiculo.CARRO).getValor()));
    }
    @Test void tarifaOnibus() {
        assertEquals(0, new BigDecimal("20.00").compareTo(service.calcularTarifa(CategoriaVeiculo.ONIBUS).getValor()));
    }
    @Test void tarifaCaminhao() {
        assertEquals(0, new BigDecimal("30.00").compareTo(service.calcularTarifa(CategoriaVeiculo.CAMINHAO).getValor()));
    }
    @Test void saldoSuficiente() {
        Tag tag = new Tag("T1", new BigDecimal("30.00"));
        assertEquals(StatusCobranca.PAGA, service.cobrar(tag, service.calcularTarifa(CategoriaVeiculo.CARRO)));
        assertEquals(0, new BigDecimal("20.00").compareTo(tag.getSaldo()));
    }
    @Test void saldoInsuficiente() {
        Tag tag = new Tag("T2", new BigDecimal("5.00"));
        assertEquals(StatusCobranca.PENDENTE, service.cobrar(tag, service.calcularTarifa(CategoriaVeiculo.ONIBUS)));
        assertEquals(0, new BigDecimal("5.00").compareTo(tag.getSaldo()));
    }
    @Test void semTag() {
        assertEquals(StatusCobranca.PENDENTE, service.cobrar(null, service.calcularTarifa(CategoriaVeiculo.CARRO)));
    }
    @Test void saldoExato() {
        Tag tag = new Tag("T3", new BigDecimal("10.00"));
        assertEquals(StatusCobranca.PAGA, service.cobrar(tag, service.calcularTarifa(CategoriaVeiculo.CARRO)));
        assertEquals(0, BigDecimal.ZERO.compareTo(tag.getSaldo()));
    }
}
