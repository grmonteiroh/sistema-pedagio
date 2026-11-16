package br.ufmg.pedagio.service;

import br.ufmg.pedagio.model.CategoriaVeiculo;
import br.ufmg.pedagio.model.Veiculo;
import java.util.Objects;

public class ClassificacaoService {
    public CategoriaVeiculo classificar(Veiculo veiculo) {
        return Objects.requireNonNull(veiculo, "Veiculo obrigatorio").getCategoria();
    }
}
