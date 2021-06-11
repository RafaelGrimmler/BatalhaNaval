package veiculos;

import java.util.ArrayList;

public abstract class Veiculo {
    private final int tam;
    private int vida;
    private boolean status;
    private final String nome;
    private final String tiro;
    public ArrayList <Integer> coordenada = new ArrayList<>();

    Veiculo(int tam, String nome, String tiro, int x, int y) {
        this.tam = tam;
        this.vida = tam;
        this.status = true;
        this.nome = nome;
        this.tiro = tiro;
        this.adicionaCoordenada(x,y);
    }

    public String getTiro() {
        return this.tiro;
    }

    public String getNome() {
        return this.nome;
    }

    public int getTam() {
        return this.tam;
    }

    public void causarDano() {
        this.vida--;
    }

    public Integer getPosicao(int posicao) {
        return this.coordenada.get(posicao);
    }

    private void adicionaCoordenada(int x, int y) {
        for (int i = 0; i < this.tam; i++) {
            this.coordenada.add(x);
            this.coordenada.add(y++);
        }
    }

    public void removeCoordenada(int valorx, int valory) {
        for(int i=0; i<this.coordenada.size(); i = i + 2){
            if( valorx == coordenada.get(i) && valory == coordenada.get(i+1)){
                coordenada.remove(i);
                coordenada.remove(i);
            }
        }
    }
    
    public boolean verficaPos(int x, int y){
        for(int i=0; i<this.coordenada.size(); i = i + 2){
            if( x == coordenada.get(i) && y == coordenada.get(i+1)){
                return true;
            }
        }
        return false;
    }
    
    public void imprimiCoordenada(){
        for(int i=0; i<this.coordenada.size(); i = i + 2){
            System.out.println( coordenada.get(i) +"  "+ coordenada.get(i+1) +"   ");
        }
    }

    public boolean marcarAtaque(){
        int contador = 0;
        for(int i=0; i<this.coordenada.size(); i = i + 2){
            contador++;
        }
        if(contador == this.tam)
            return false;
        return true;
    }
    
    public boolean isStatus() {
        if (this.vida == 0)
            this.status = false;
        return this.status;
    }
    
}
