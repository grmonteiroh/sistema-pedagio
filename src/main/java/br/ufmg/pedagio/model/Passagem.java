package br.ufmg.pedagio.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Passagem {
    private final UUID id;
    private final LocalDateTime dataHora;
    private final Veiculo veiculo;

    public Passagem(Veiculo veiculo, LocalDateTime dataHora) {
        this.id = UUID.randomUUID();
        this.veiculo = Objects.requireNonNull(veiculo, "Veiculo obrigatorio");
        this.dataHora = Objects.requireNonNull(dataHora, "Data/hora obrigatoria");
    }

    public UUID getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public Veiculo getVeiculo() { return veiculo; }
}
