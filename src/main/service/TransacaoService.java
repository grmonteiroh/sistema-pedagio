package service;

import model.Transacao;
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
