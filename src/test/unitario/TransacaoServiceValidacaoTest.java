package unitario;

import model.*;
import service.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoServiceValidacaoTest {
    @Test void tentativaDeLimparNaoApagaRegistros() {
        TransacaoService service = new TransacaoService();
        Passagem passagem = new Passagem(new Veiculo("ABC1D23", CategoriaVeiculo.CARRO, null),
            LocalDateTime.now());
        service.registrar(new Transacao(passagem, CategoriaVeiculo.CARRO, new BigDecimal("10.00"),
            FormaIdentificacao.PLACA_INFORMADA, StatusCobranca.PENDENTE));
        assertThrows(UnsupportedOperationException.class, () -> service.listar().clear());
        assertEquals(1, service.listar().size());
    }
    @Test void transacaoNulaLancaExcecao() {
        TransacaoService service = new TransacaoService();
        assertThrows(NullPointerException.class, () -> service.registrar(null));
        assertTrue(service.listar().isEmpty());
    }
}
