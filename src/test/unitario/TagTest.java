package unitario;

import model.Tag;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TagTest {
    @Test void debitoComSaldoSuficiente() {
        Tag tag = new Tag("T1", new BigDecimal("15.00"));
        assertTrue(tag.debitar(new BigDecimal("10.00")));
        assertEquals(0, new BigDecimal("5.00").compareTo(tag.getSaldo()));
    }
    @Test void debitoComSaldoInsuficienteNaoAlteraSaldo() {
        Tag tag = new Tag("T2", new BigDecimal("9.99"));
        assertFalse(tag.debitar(new BigDecimal("10.00")));
        assertEquals(0, new BigDecimal("9.99").compareTo(tag.getSaldo()));
    }
    @Test void debitosSucessivos() {
        Tag tag = new Tag("T3", new BigDecimal("25.00"));
        assertTrue(tag.debitar(new BigDecimal("10.00")));
        assertTrue(tag.debitar(new BigDecimal("10.00")));
        assertFalse(tag.debitar(new BigDecimal("10.00")));
        assertEquals(0, new BigDecimal("5.00").compareTo(tag.getSaldo()));
    }
    @Test void debitoZeroOuNegativoLancaExcecao() {
        Tag tag = new Tag("T4", new BigDecimal("10.00"));
        assertThrows(IllegalArgumentException.class, () -> tag.debitar(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> tag.debitar(new BigDecimal("-1.00")));
        assertThrows(NullPointerException.class, () -> tag.debitar(null));
    }
    @Test void criacaoComDadosInvalidosLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Tag(null, BigDecimal.TEN));
        assertThrows(IllegalArgumentException.class, () -> new Tag("  ", BigDecimal.TEN));
        assertThrows(IllegalArgumentException.class, () -> new Tag("T5", new BigDecimal("-0.01")));
        assertThrows(NullPointerException.class, () -> new Tag("T5", null));
    }
}
