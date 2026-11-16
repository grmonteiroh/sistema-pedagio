package br.ufmg.pedagio.service;

import br.ufmg.pedagio.model.Transacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransacaoService {
    private final List<Transacao> transacoes = new ArrayList<>();

    public void registrar(Transacao transacao) {
        transacoes.add(Objects.requireNonNull(transacao, "Transacao obrigatoria"));
    }

    public List<Transacao> listar() { return List.copyOf(transacoes); }
}
