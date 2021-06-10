package veiculos;

import java.util.ArrayList;

public class Caca extends Veiculo {
    private static final String nome = "Caca";
    private static final String tiro = "tiroestrela";
    private static final int tam = 2;
    private ArrayList <String> imgMorto = new ArrayList<>();
    
    public Caca(int x, int y) {
        super(tam, nome, tiro, x, y);
        addImg();
    }
        
    public static String getImgVivo(int index){
        return "icones/aviao"+index+".png";
    }
        
    public static String getIcon(int index){
        if(index == 1)
            return "icones/av.png";
        return "icones/av2.png";
    }
    
    public String procuraImg(int x, int y){
        String img;
        int contador = 0;
        for(int i = 0; i < coordenada.size(); i = i + 2){
            if( x == coordenada.get(i) && y == coordenada.get(i+1)){
                img = this.imgMorto.get(contador);
                this.imgMorto.remove(contador);
                this.causarDano();
                return img;
            }
            contador++;
        }
        return "";
    }
        
    private void addImg(){
        imgMorto.add("icones/aviao1abatido.png");
        imgMorto.add("icones/aviao2abatido.png");
    }
}
