package veiculos;

public class Caca extends Veiculo {
    private static String nome = "Caca";
    private static String tiro = "tiroestrela";
    private static int tam = 2;
    
    public Caca(int x, int y) {
        super(tam, nome, tiro, x, y);
    }
}
