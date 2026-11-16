package service;

import model.CategoriaVeiculo;
import model.Veiculo;
import java.util.Objects;

public class ClassificacaoService {
    public CategoriaVeiculo classificar(Veiculo veiculo) {
        return Objects.requireNonNull(veiculo, "Veiculo obrigatorio").getCategoria();
    }
}
