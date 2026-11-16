package unitario;

import model.*;
import service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;

class ClassificacaoServiceTest {
    private final ClassificacaoService service = new ClassificacaoService();

    @ParameterizedTest
    @EnumSource(CategoriaVeiculo.class)
    void classificaPelaCategoriaInformada(CategoriaVeiculo categoria) {
        Veiculo veiculo = new Veiculo("ABC1D23", categoria, null);
        assertEquals(categoria, service.classificar(veiculo));
    }
    @Test void veiculoNuloLancaExcecao() {
        assertThrows(NullPointerException.class, () -> service.classificar(null));
    }
}
