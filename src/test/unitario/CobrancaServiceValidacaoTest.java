package unitario;

import model.*;
import service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CobrancaServiceValidacaoTest {
    private final CobrancaService service = new CobrancaService();

    @Test void categoriaNulaLancaExcecao() {
        assertThrows(NullPointerException.class, () -> service.calcularTarifa(null));
    }
    @Test void tarifaNulaLancaExcecao() {
        assertThrows(NullPointerException.class, () -> service.cobrar(null, null));
    }
}
