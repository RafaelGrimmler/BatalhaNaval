/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerranaval;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public final class GuerraNaval extends JFrame implements ActionListener{
    //BOTÕES PRINCIPAIS
    public JButton sair, dica, disparoUnico, cascataHorizontal, cascataVertical, tiroEstrela;
    public JPanel painelBotoesCima, painelMapas, painelEsquerda, painelDireita, painelMapaEsquerda, painelMapaDireita;
    public Container tela;
    //BOTÕES MAPA
    public JButton[][] botaoMapaEsquerda = new JButton[10][10]; 
    public JButton[][] botaoMapaDireita = new JButton[10][10]; 
    //VARIAVEIS
    public String tipoDisparo = "disparounico";
    
    public GuerraNaval(){
        super("Guerra Naval");
        // Inicializa tela
        iniciarTelaPrincipal();
    }
    
    public void iniciarTelaPrincipal(){
        tela = getContentPane();
        tela.setLayout(new BorderLayout(8, 6));
        
        painelBotoesCima = new JPanel(); // CRIAÇÃO DO PAINEL DE CIMA
        painelBotoesCima.setLayout(new FlowLayout());
        
        painelMapas = new JPanel();        
        painelEsquerda = new JPanel();
        painelDireita = new JPanel();
        
        painelMapas.setLayout(new GridLayout(0, 2));
        painelEsquerda.setLayout(new BorderLayout());
        painelDireita.setLayout(new BorderLayout());
                
        criarMapaEsquerda();
        criarMapaDireita();
        
        painelMapas.add(painelEsquerda);
        painelMapas.add(painelDireita);
       
        criaBotoesCima();
       
        tela.add(painelBotoesCima, BorderLayout.NORTH);
        tela.add(painelMapas);
        
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);  
    }
    
    public void criarMapaEsquerda(){
        /// -- ESTAO FUNÇÃO SERVE PARA DEFINIR O ESPAÇO DO BORDERLAYOUT.CENTER
        /// ONDE ESTARÁ LOCALIZADO O MAPAS DA ESQUERDA.
        JPanel auxiliar, painelbaixo, auxiliar1, paineldireita, auxiliar2, painelesquerda, auxiliar3, painelcima;
        auxiliar = new JPanel();
        JLabel player = new JLabel("                                                        Jogador");
        painelbaixo = new JPanel();
        painelbaixo.setLayout(new GridLayout(8, 0));
        painelbaixo.add(auxiliar); 
        painelbaixo.add(player);        
        painelEsquerda.add(painelbaixo, BorderLayout.SOUTH);
        ////
        paineldireita = new JPanel();
        auxiliar1 = new JPanel();
        paineldireita.setLayout(new GridLayout(0, 5));
        paineldireita.add(auxiliar1);        
        painelEsquerda.add(paineldireita, BorderLayout.EAST);
        ////
        painelesquerda = new JPanel();
        auxiliar2 = new JPanel();
        painelesquerda.setLayout(new GridLayout(0, 5));
        painelesquerda.add(auxiliar2);        
        painelEsquerda.add(painelesquerda, BorderLayout.WEST);
        ////
        painelcima = new JPanel();
        auxiliar3 = new JPanel();
        painelcima.setLayout(new GridLayout(6, 0));
        painelcima.add(auxiliar3);        
        painelEsquerda.add(painelcima, BorderLayout.NORTH);
        //// ADICIONA O PAINEL AONDE ESTARA OS BOTÕES DO MAPA
        painelMapaEsquerda = new JPanel();
        painelMapaEsquerda.setLayout(new GridLayout(11,11));
        criaBotoesMapaEsquerdaEDireita("esquerda");
        painelEsquerda.add(painelMapaEsquerda, BorderLayout.CENTER);
    }
       
    public void criarMapaDireita(){
        /// -- ESTAO FUNÇÃO SERVE PARA DEFINIR O ESPAÇO DO BORDERLAYOUT.CENTER
        /// ONDE ESTARÁ LOCALIZADO O MAPAS DA DIREITA.
        JPanel auxiliar, painelbaixo, auxiliar1, paineldireita, auxiliar2, painelesquerda, auxiliar3, painelcima;
        auxiliar = new JPanel();
        JLabel player = new JLabel("                                                        Computador");
        painelbaixo = new JPanel();
        painelbaixo.setLayout(new GridLayout(8, 0));
        painelbaixo.add(auxiliar); 
        painelbaixo.add(player);           
        painelDireita.add(painelbaixo, BorderLayout.SOUTH);
        ////
        paineldireita = new JPanel();
        auxiliar1 = new JPanel();
        paineldireita.setLayout(new GridLayout(0, 5));
        paineldireita.add(auxiliar1);        
        painelDireita.add(paineldireita, BorderLayout.EAST);
        ////
        painelesquerda = new JPanel();
        auxiliar2 = new JPanel();
        painelesquerda.setLayout(new GridLayout(0, 5));
        painelesquerda.add(auxiliar2);        
        painelDireita.add(painelesquerda, BorderLayout.WEST);
        ////
        painelcima = new JPanel();
        auxiliar3 = new JPanel();
        painelcima.setLayout(new GridLayout(6, 0));
        painelcima.add(auxiliar3);        
        painelDireita.add(painelcima, BorderLayout.NORTH);
        /// PAINEL ONDE SERA ADICIONADO OS BOTÕES DO MAPA
        painelMapaDireita = new JPanel();
        painelMapaDireita.setLayout(new GridLayout(11,11));
        criaBotoesMapaEsquerdaEDireita("direita");
        painelDireita.add(painelMapaDireita, BorderLayout.CENTER);
    }
    
    public void criaBotoesMapaEsquerdaEDireita(String opcao){
        Color personalizadaEsq = new Color(174,238,238);
        Color personalizadaDir = new Color(225,255,175);
        JLabel [] numeros = new JLabel[11];
        JLabel [] letras = new JLabel[10];
        String letra;
        char c = 'A';
        if( "esquerda".equals(opcao) ){
            for( int i = 0; i < 11; i++ ){
                if( i == 0 )
                    numeros[i] = new JLabel("  ");
                else
                    numeros[i] = new JLabel("  " + i);
                painelMapaEsquerda.add(numeros[i]);
            }
            ImageIcon img1 = new ImageIcon("icones/navioescolta1abatido.png");
            ImageIcon img2 = new ImageIcon("icones/navioescolta2abatido.png");
            ImageIcon img3 = new ImageIcon("icones/navioescolta3abatido.png");
            for( int i = 0; i < 10; i++ ){
                letra = "" + c++;
                letras[i] = new JLabel(letra);
                painelMapaEsquerda.add(letras[i]);
                for( int j = 0; j < 10; j++ ){
                    botaoMapaEsquerda[i][j] = new JButton();
                    botaoMapaEsquerda[i][j].setBackground(personalizadaEsq);
                    if( j >= 0 && j <= 2 ){
                        if( j == 0 )
                            botaoMapaEsquerda[i][j].setIcon(img1);
                        if( j == 1 )
                            botaoMapaEsquerda[i][j].setIcon(img2);
                        if( j == 2 )
                            botaoMapaEsquerda[i][j].setIcon(img3);
                    }
                    painelMapaEsquerda.add(botaoMapaEsquerda[i][j]);
                }
            }
        }
        else{
            String comando;
            int contadorComando = 0;
            for( int i = 0; i < 11; i++ ){
                if( i == 0 )
                    numeros[i] = new JLabel("  ");
                else
                    numeros[i] = new JLabel("  " + i);
                painelMapaDireita.add(numeros[i]);
            }
            for( int i = 0; i < 10; i++ ){
                letra = "" + c++;
                letras[i] = new JLabel(letra);
                painelMapaDireita.add(letras[i]);
                for( int j = 0; j < 10; j++ ){
                    botaoMapaDireita[i][j] = new JButton();
                    botaoMapaDireita[i][j].setBackground(personalizadaDir);
                    comando = "" + contadorComando++;
                    botaoMapaDireita[i][j].setActionCommand(comando);
                    botaoMapaDireita[i][j].addActionListener(this);
                    painelMapaDireita.add(botaoMapaDireita[i][j]);
                }
            }
        }
    }
    
    public void criaBotoesCima(){
           // BOTÕES DO PAINEL DE CIMA
        sair = new JButton("Sair");
        sair.setBackground(Color.WHITE);
        dica = new JButton("Dica");
        dica.setBackground(Color.WHITE);
        disparoUnico = new JButton("Disparo Unico");
        disparoUnico.setBackground(Color.DARK_GRAY);
        disparoUnico.setForeground(Color.WHITE);
        cascataHorizontal = new JButton("Cascata Horizontal");
        cascataHorizontal.setBackground(Color.WHITE);
        cascataVertical = new JButton("Cascata Vertical");
        cascataVertical.setBackground(Color.WHITE);
        tiroEstrela = new JButton("Tiro Estrela");
        tiroEstrela.setBackground(Color.WHITE);
        // ATRIBUI UM COMANDO AOS BOTÕES
        sair.setActionCommand("P.sair");
        dica.setActionCommand("P.dica");
        disparoUnico.setActionCommand("P.disparounico");
        cascataHorizontal.setActionCommand("P.cascatahorizontal");
        cascataVertical.setActionCommand("P.cascatavertical");
        tiroEstrela.setActionCommand("P.tiroestrela");
        // ATRIBUI UM EVENTO AOS BOTÕES
        sair.addActionListener(this);
        dica.addActionListener(this);
        disparoUnico.addActionListener(this);
        cascataHorizontal.addActionListener(this);
        cascataVertical.addActionListener(this);
        tiroEstrela.addActionListener(this);
        // ADICIONA OS BOTÕES NO PAINEL E O PAINEL NA TELA
        painelBotoesCima.add(sair);
        painelBotoesCima.add(dica);
        painelBotoesCima.add(disparoUnico);
        painelBotoesCima.add(cascataHorizontal);
        painelBotoesCima.add(cascataVertical);
        painelBotoesCima.add(tiroEstrela);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if( comando.contains("P.") ){
            transformaPadraoBotoes();
            //-------------------------------
            switch (comando) {
                case "P.sair":
                    System.exit(0);
                case "P.dica":
                    dica.setBackground(Color.DARK_GRAY);
                    dica.setForeground(Color.WHITE);
                    break;
                case "P.disparounico":
                    disparoUnico.setBackground(Color.DARK_GRAY);
                    disparoUnico.setForeground(Color.WHITE);
                    tipoDisparo = "disparounico";
                    break;
                case "P.cascatahorizontal":
                    cascataHorizontal.setBackground(Color.DARK_GRAY);
                    cascataHorizontal.setForeground(Color.WHITE);
                    tipoDisparo = "cascatahorizontal";
                    break;
                case "P.cascatavertical":
                    cascataVertical.setBackground(Color.DARK_GRAY);
                    cascataVertical.setForeground(Color.WHITE);
                    tipoDisparo = "cascatavertical";
                    break;
                case "P.tiroestrela":
                    tiroEstrela.setBackground(Color.DARK_GRAY);
                    tiroEstrela.setForeground(Color.WHITE);
                    tipoDisparo = "tiroestrela";
                    break;
                default:
                    break;
            }
        }
        else if( comando.matches("-?\\d+") ){
            int posicao, contadorx = 0, contadory = 0, limite = 9;
            posicao = Integer.parseInt(comando);
            for(int i = 0; i < posicao; i++ ){
                contadory = contadory + 1;
                if( i == limite ){
                    limite = limite + 10;
                    contadory = 0;
                    contadorx = contadorx + 1;
                }
            }
            if( "disparounico".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
            }else if( "cascatahorizontal".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
                if( contadory + 1 < 10 ){
                    botaoMapaDireita[contadorx][contadory+1].setActionCommand("");
                    botaoMapaDireita[contadorx][contadory+1].setBackground(Color.DARK_GRAY);
                }
            }else if( "cascatavertical".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
                if( contadorx - 1 > -1 ){
                    botaoMapaDireita[contadorx-1][contadory].setActionCommand("");
                    botaoMapaDireita[contadorx-1][contadory].setBackground(Color.DARK_GRAY);
                }
            }else if( "tiroestrela".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
                if( contadorx - 1 > -1 ){
                    botaoMapaDireita[contadorx-1][contadory].setActionCommand("");
                    botaoMapaDireita[contadorx-1][contadory].setBackground(Color.DARK_GRAY);
                }
                if( contadorx + 1 < 10 ){
                    botaoMapaDireita[contadorx+1][contadory].setActionCommand("");
                    botaoMapaDireita[contadorx+1][contadory].setBackground(Color.DARK_GRAY);
                }
                if( contadory + 1 < 10 ){
                    botaoMapaDireita[contadorx][contadory+1].setActionCommand("");
                    botaoMapaDireita[contadorx][contadory+1].setBackground(Color.DARK_GRAY);
                }
                if( contadory - 1 > -1 ){
                    botaoMapaDireita[contadorx][contadory-1].setActionCommand("");
                    botaoMapaDireita[contadorx][contadory-1].setBackground(Color.DARK_GRAY);
                }
            }
        }
    }
    
    public void transformaPadraoBotoes(){
        // TODA VEZ QUE ACONTECER UMA AÇÃO NOS BOTOES REDEFINE A COR
        dica.setBackground(Color.WHITE);
        dica.setForeground(Color.BLACK);
        disparoUnico.setBackground(Color.WHITE);
        disparoUnico.setForeground(Color.BLACK);
        cascataHorizontal.setBackground(Color.WHITE);
        cascataHorizontal.setForeground(Color.BLACK);
        cascataVertical.setBackground(Color.WHITE);
        cascataVertical.setForeground(Color.BLACK);
        tiroEstrela.setBackground(Color.WHITE);
        tiroEstrela.setForeground(Color.BLACK);
    }
    
     public static void main(String[] args) {
        menuPrincipal guiMenu = new menuPrincipal();
        guiMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }
}
