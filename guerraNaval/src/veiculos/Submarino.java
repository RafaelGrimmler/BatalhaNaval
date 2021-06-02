package veiculos;

public class Submarino extends Veiculo {
    private static String nome = "Submarino";
    private static String tiro = "disparounico"; 
    private static int tam = 2;

    public Submarino(int x, int y) {
        super(tam, nome, tiro, x, y);
    }
}
