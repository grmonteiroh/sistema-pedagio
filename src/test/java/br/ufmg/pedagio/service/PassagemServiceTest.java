package br.ufmg.pedagio.service;

import br.ufmg.pedagio.model.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class PassagemServiceTest {
    private final TransacaoService registro = new TransacaoService();
    private final PassagemService service = new PassagemService(
        new IdentificacaoService(), new ClassificacaoService(), new CobrancaService(), registro);

    @Test void fluxoCompletoComTag() {
        LocalDateTime data = LocalDateTime.of(2026, 11, 14, 10, 0);
        Veiculo veiculo = new Veiculo("ABC1D23", CategoriaVeiculo.CARRO,
            new Tag("TAG001", new BigDecimal("30.00")));
        Transacao t = service.processar(veiculo, data);
        assertEquals(StatusCobranca.PAGA, t.getStatus());
        assertEquals(FormaIdentificacao.TAG, t.getFormaIdentificacao());
        assertEquals(CategoriaVeiculo.CARRO, t.getCategoria());
        assertEquals("ABC1D23", t.getPassagem().getVeiculo().getPlaca());
        assertEquals(data, t.getPassagem().getDataHora());
        assertEquals(0, new BigDecimal("10.00").compareTo(t.getValor()));
        assertEquals(1, registro.listar().size());
    }

    @Test void fluxoSemTag() {
        Transacao t = service.processar(
            new Veiculo("MOT3F45", CategoriaVeiculo.MOTOCICLETA, null), LocalDateTime.now());
        assertEquals(StatusCobranca.PENDENTE, t.getStatus());
        assertEquals(FormaIdentificacao.PLACA_INFORMADA, t.getFormaIdentificacao());
    }

    @Test void vintePassagensSemPerda() {
        Set<UUID> ids = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            Transacao t = service.processar(
                new Veiculo("PLACA" + i, CategoriaVeiculo.CARRO, null), LocalDateTime.now());
            ids.add(t.getPassagem().getId());
        }
        assertEquals(20, registro.listar().size());
        assertEquals(20, ids.size());
    }
}
