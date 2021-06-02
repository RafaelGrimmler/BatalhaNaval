package veiculos;

import java.util.ArrayList;

import excessoes.PosicaoInexistente;

public abstract class Veiculo {
    private int tam;
    private int vida;
    private boolean status;
    private String nome;
    private String tiro;
    private ArrayList <Integer> coordenada = new ArrayList<Integer>();

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

    public Integer getPosicao(int posicao) {
        if ((posicao < 0) || (posicao > this.coordenada.size())) 
            throw new PosicaoInexistente("[Essa possicao nao existe]");

        return this.coordenada.get(posicao);
    }

    public void adicionaCoordenada(int x, int y) {
        for (int i = 0; i < this.tam; i++) {
            this.coordenada.add(x);
            this.coordenada.add(y++);
        }
    }

    public void removeCoordenada(int x, int y) {
        this.coordenada.remove(x);
        this.coordenada.remove(y);
    }

    public void removeCoordenada(int posicao) {
        if ((posicao < 0) || (posicao > this.coordenada.size()))
            throw new PosicaoInexistente("[Essa possicao nao existe]");

        this.coordenada.remove(posicao);
    }

    public void danoVeiculo() {
        this.vida--;
    }

    public boolean isStatus() {
        if (this.vida == 0)
            this.status = false;
        
        return this.status;
    }
}
