package br.ufmg.pedagio.service;

import br.ufmg.pedagio.model.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoServiceTest {
    @Test void consultaTransacoes() {
        TransacaoService service = new TransacaoService();
        assertTrue(service.listar().isEmpty());
        Passagem passagem = new Passagem(new Veiculo("ABC1D23", CategoriaVeiculo.CARRO, null),
            LocalDateTime.now());
        Transacao t = new Transacao(passagem, CategoriaVeiculo.CARRO, new BigDecimal("10.00"),
            FormaIdentificacao.PLACA_INFORMADA, StatusCobranca.PENDENTE);
        service.registrar(t);
        assertEquals(1, service.listar().size());
        assertSame(t, service.listar().get(0));
        assertThrows(UnsupportedOperationException.class, () -> service.listar().clear());
    }
}
