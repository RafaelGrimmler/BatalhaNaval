package veiculos;

import java.util.ArrayList;

public class Submarino extends Veiculo {
    private static String nome = "Submarino";
    private static String tiro = "disparounico"; 
    private static int tam = 2;
    private ArrayList <String> imgMorto = new ArrayList<>();

    public Submarino(int x, int y) {
        super(tam, nome, tiro, x, y);
        addImg();
    }
    
    public static String getImgVivo(int index){
        return "icones/submarino"+index+".png";
    }
    
    public String getImgMorto(int index){
        return "icones/submarino"+index+"abatido.png";
    }
    
    public static String getIcon(int index){
        if(index == 1)
            return "icones/sm.png";
        return "icones/sm2.png";
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
        imgMorto.add("icones/submarino1abatido.png");
        imgMorto.add("icones/submarino2abatido.png");
    }
}
