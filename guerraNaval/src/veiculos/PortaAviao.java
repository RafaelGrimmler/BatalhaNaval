package veiculos;

import excessoes.JogadaInvalida;

public class PortaAviao extends Veiculo {
    private static String nome = "PortaAviao";
    private static String tiro = "disparounico"; 
    private int round;
    private static int tam = 4;

    public PortaAviao(int x, int y) {
        super(tam, nome, tiro, x, y);
        this.round = 0;
    }

    public boolean permiteJogada() {
        if (this.round % 2 == 0) {
            this.round++;
            return true;
        }
        throw new JogadaInvalida("[Espere um turno]");
    }
     
}
