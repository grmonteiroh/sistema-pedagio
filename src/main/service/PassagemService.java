package service;

import model.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class PassagemService {
    private final IdentificacaoService identificacao;
    private final ClassificacaoService classificacao;
    private final CobrancaService cobranca;
    private final TransacaoService transacoes;

    public PassagemService(IdentificacaoService identificacao, ClassificacaoService classificacao,
                           CobrancaService cobranca, TransacaoService transacoes) {
        this.identificacao = Objects.requireNonNull(identificacao);
        this.classificacao = Objects.requireNonNull(classificacao);
        this.cobranca = Objects.requireNonNull(cobranca);
        this.transacoes = Objects.requireNonNull(transacoes);
    }

    public Transacao processar(Veiculo veiculo, LocalDateTime dataHora) {
        Passagem passagem = new Passagem(veiculo, dataHora);
        FormaIdentificacao forma = identificacao.identificar(veiculo);
        CategoriaVeiculo categoria = classificacao.classificar(veiculo);
        Tarifa tarifa = cobranca.calcularTarifa(categoria);
        StatusCobranca status = cobranca.cobrar(veiculo.getTag(), tarifa);
        Transacao transacao = new Transacao(passagem, categoria, tarifa.getValor(), forma, status);
        transacoes.registrar(transacao);
        return transacao;
    }
}
