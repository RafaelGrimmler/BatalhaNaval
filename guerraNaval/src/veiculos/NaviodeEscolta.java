package veiculos;

import java.util.ArrayList;

public class NaviodeEscolta extends Veiculo {
    private static String nome = "NaviodeEscolta";
    private static String tiro = "cascata"; 
    private static int tam = 3;
    private ArrayList <String> imgMorto = new ArrayList<>();

    public NaviodeEscolta(int x, int y) {
        super(tam, nome, tiro, x, y);
        addImg();
    }
    
    public static String getImgVivo(int index){
        return "icones/navioescolta"+index+".png";
    }
    
    public String getImgMorto(int index){
        return "icones/navioescolta"+index+"abatido.png";
    }
    
    public static String getIcon(int index){
        if(index == 1)
            return "icones/ne.png";
        return "icones/ne2.png";
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
        imgMorto.add("icones/navioescolta1abatido.png");
        imgMorto.add("icones/navioescolta2abatido.png");
        imgMorto.add("icones/navioescolta3abatido.png");
    }
}
