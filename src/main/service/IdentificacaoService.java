package service;

import model.FormaIdentificacao;
import model.Veiculo;
import java.util.Objects;

public class IdentificacaoService {
    public FormaIdentificacao identificar(Veiculo veiculo) {
        Objects.requireNonNull(veiculo, "Veiculo obrigatorio");
        return veiculo.getTag() == null ? FormaIdentificacao.PLACA_INFORMADA : FormaIdentificacao.TAG;
    }
}
