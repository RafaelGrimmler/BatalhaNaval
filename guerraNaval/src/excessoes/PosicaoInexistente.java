package excessoes;

public class PosicaoInexistente extends IndexOutOfBoundsException {
    public PosicaoInexistente(String message) {
        super(message);
    }
}
