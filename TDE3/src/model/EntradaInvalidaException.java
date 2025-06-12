package model;

public class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException() {
        super("Entrada inválida!");
    }

    public EntradaInvalidaException(String mensagem) {
        super(mensagem);
    }

    public EntradaInvalidaException(String campo, String regra) {
        super(String.format("Entrada inválida para %s: %s", campo, regra));
    }
}