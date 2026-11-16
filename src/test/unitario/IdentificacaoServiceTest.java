package unitario;

import model.*;
import service.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class IdentificacaoServiceTest {
    private final IdentificacaoService service = new IdentificacaoService();

    @Test void veiculoComTagIdentificadoPorTag() {
        Veiculo veiculo = new Veiculo("ABC1D23", CategoriaVeiculo.CARRO,
            new Tag("TAG001", new BigDecimal("30.00")));
        assertEquals(FormaIdentificacao.TAG, service.identificar(veiculo));
    }
    @Test void veiculoSemTagIdentificadoPorPlaca() {
        Veiculo veiculo = new Veiculo("MOT3F45", CategoriaVeiculo.MOTOCICLETA, null);
        assertEquals(FormaIdentificacao.PLACA_INFORMADA, service.identificar(veiculo));
    }
    @Test void tagSemSaldoAindaIdentificaPorTag() {
        Veiculo veiculo = new Veiculo("BUS2E34", CategoriaVeiculo.ONIBUS,
            new Tag("TAG002", BigDecimal.ZERO));
        assertEquals(FormaIdentificacao.TAG, service.identificar(veiculo));
    }
    @Test void veiculoNuloLancaExcecao() {
        assertThrows(NullPointerException.class, () -> service.identificar(null));
    }
}
