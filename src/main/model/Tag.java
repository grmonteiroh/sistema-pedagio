package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tag {
    private final String identificador;
    private BigDecimal saldo;

    public Tag(String identificador, BigDecimal saldo) {
        if (identificador == null || identificador.isBlank()) {
            throw new IllegalArgumentException("Identificador da tag obrigatorio");
        }
        Objects.requireNonNull(saldo, "Saldo obrigatorio");
        if (saldo.signum() < 0) throw new IllegalArgumentException("Saldo nao pode ser negativo");
        this.identificador = identificador;
        this.saldo = saldo;
    }

    public String getIdentificador() { return identificador; }
    public BigDecimal getSaldo() { return saldo; }

    public boolean debitar(BigDecimal valor) {
        Objects.requireNonNull(valor, "Valor obrigatorio");
        if (valor.signum() <= 0) throw new IllegalArgumentException("Debito deve ser positivo");
        if (saldo.compareTo(valor) < 0) return false;
        saldo = saldo.subtract(valor);
        return true;
    }
}
