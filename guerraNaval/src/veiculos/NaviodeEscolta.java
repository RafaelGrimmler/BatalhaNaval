package veiculos;

public class NaviodeEscolta extends Veiculo {
    private static String nome = "NaviodeEscolta";
    private static String tiro = "cascata"; 
    private static int tam = 3;

    public NaviodeEscolta(int x, int y) {
        super(tam, nome, tiro, x, y);
    }
}
