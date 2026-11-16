package br.ufmg.pedagio;

import br.ufmg.pedagio.model.*;
import br.ufmg.pedagio.service.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        TransacaoService registro = new TransacaoService();
        PassagemService passagemService = new PassagemService(
            new IdentificacaoService(), new ClassificacaoService(), new CobrancaService(), registro);

        Veiculo carro = new Veiculo("ABC1D23", CategoriaVeiculo.CARRO,
            new Tag("TAG001", new BigDecimal("30.00")));
        Veiculo onibus = new Veiculo("BUS2E34", CategoriaVeiculo.ONIBUS,
            new Tag("TAG002", new BigDecimal("5.00")));
        Veiculo moto = new Veiculo("MOT3F45", CategoriaVeiculo.MOTOCICLETA, null);

        System.out.println("=== SISTEMA AUTOMATIZADO DE PEDAGIO ===");
        for (Veiculo veiculo : new Veiculo[]{carro, onibus, moto}) {
            Transacao t = passagemService.processar(veiculo, LocalDateTime.now());
            System.out.printf("Placa: %s | Categoria: %s | Tarifa: R$ %s | Identificacao: %s | Status: %s%n",
                veiculo.getPlaca(), t.getCategoria(), t.getValor(), t.getFormaIdentificacao(), t.getStatus());
            if (veiculo.getTag() != null) {
                System.out.println("Saldo restante da tag: R$ " + veiculo.getTag().getSaldo());
            }
        }
        System.out.println("Transacoes registradas: " + registro.listar().size());
    }
}
