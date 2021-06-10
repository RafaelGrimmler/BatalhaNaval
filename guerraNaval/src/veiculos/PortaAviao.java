package veiculos;

import java.util.ArrayList;

public class PortaAviao extends Veiculo {
    private static String nome = "PortaAviao";
    private static String tiro = "disparounico"; 
    private static int tam = 4;
    private int round;
    private ArrayList <String> imgMorto = new ArrayList<>();

    public PortaAviao(int x, int y) {
        super(tam, nome, tiro, x, y);
        addImg();
        this.round = 1;
    }
     
    public void subirRound(){
        this.round++;
        if( this.round >= 2 )
            this.round = 2;
    }
    
    public boolean verificaRound(){
        if(this.round == 2){
            this.round = 0;
            return true;
        }
        return false;
    }
    
    public static String getImgVivo(int index){
        return "icones/portaavioes"+index+".png";
    }
    
    public String getImgMorto(int index){
        return "icones/portaavioes"+index+"abatido.png";
    }
    
    public static String getIcon(int index){
        if(index == 1)
            return "icones/pa.png";
        return "icones/pa2.png";
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
        imgMorto.add("icones/portaavioes1abatido.png");
        imgMorto.add("icones/portaavioes2abatido.png");
        imgMorto.add("icones/portaavioes3abatido.png");
        imgMorto.add("icones/portaavioes4abatido.png");
    }
}
