/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerranaval;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import veiculos.Caca;
import veiculos.NaviodeEscolta;
import veiculos.PortaAviao;
import veiculos.Submarino;


public final class GuerraNaval extends JFrame implements ActionListener{
    //BOTÕES PRINCIPAIS
    private JButton sair, dica, disparoUnico, cascataHorizontal, cascataVertical, tiroEstrela;
    private JPanel painelBotoesCima, painelMapas, painelEsquerda, painelDireita, painelMapaEsquerda, painelMapaDireita, painelcima;
    private Container tela;
    //BOTÕES MAPA
    private final JButton[][] botaoMapaEsquerda = new JButton[10][10]; 
    private final JButton[][] botaoMapaDireita = new JButton[10][10]; 
    private JButton aviao, porta, navio, sub;
    //VARIAVEIS
    private String tipoDisparo = "disparounico";
    private String auxiliar;
    private final String nomeJogador;
    private final int tipoJogo; // SE == 0, JOGO ALEATORIO  --- --- SE == 1, JOGO DEFINIDO
    private int modo;
    private int []quant = new int[4];
    private ArrayList <Integer> coordenadaPlayer = new ArrayList<>();
    private ArrayList <Integer> coordenadaComp = new ArrayList<>();
    private int quantidadeVeiculosComp;
    private int quantidadeVeiculosPlayer;
    private int marcarAtaque;
    private int quantDicas;
    private ArrayList <String> comandos = new ArrayList<>();
    // VEICULOS
    private Caca cacaPlayer, cacaComputador;
    private NaviodeEscolta navioescoltaPlayer, navioescoltaComputador;
    private PortaAviao portaaviaoPlayer, portaaviaoComputador;
    private Submarino submarinoPlayer, submarinoComputador;
    
    
    public GuerraNaval(int tipoJogo, String nomeJogador){
        super("Guerra Naval");
        this.quantDicas = 3;
        this.marcarAtaque = 0;
        this.quantidadeVeiculosPlayer = 4;
        this.quantidadeVeiculosComp = 4;
        this.nomeJogador = nomeJogador;
        this.tipoJogo = tipoJogo;
        // Inicializa tela
        iniciarTelaPrincipal();
        botarVeiculos();
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
        JPanel auxiliar, painelbaixo, auxiliar1, paineldireita, auxiliar2, painelesquerda, auxiliar3;
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
        if(tipoJogo == 0){
            painelcima = new JPanel();
            auxiliar3 = new JPanel();
            painelcima.setLayout(new GridLayout(6, 0));
            painelcima.add(auxiliar3);        
            painelEsquerda.add(painelcima, BorderLayout.NORTH);
        }else{
            painelcima = new JPanel();
            auxiliar3 = new JPanel(new FlowLayout());
            ImageIcon img1 = new ImageIcon(Caca.getIcon(1));
            ImageIcon img2 = new ImageIcon(PortaAviao.getIcon(2));
            ImageIcon img3 = new ImageIcon(NaviodeEscolta.getIcon(2));
            ImageIcon img4 = new ImageIcon(Submarino.getIcon(2));
            this.modo = 1;
            this.quant[0] = 1;
            this.quant[1] = 1;
            this.quant[2] = 1;
            this.quant[3] = 1;
            aviao = new JButton(img1);
            aviao.setActionCommand("M.av");
            aviao.addActionListener(this);
            porta = new JButton(img2);
            porta.setActionCommand("M.pa");
            porta.addActionListener(this);
            navio = new JButton(img3);
            navio.setActionCommand("M.ne");
            navio.addActionListener(this);
            sub = new JButton(img4);
            sub.setActionCommand("M.sm");
            sub.addActionListener(this);
            painelcima.setLayout(new FlowLayout(1, 25, 5));
            auxiliar3.add(aviao);     
            auxiliar3.add(porta); 
            auxiliar3.add(navio); 
            auxiliar3.add(sub); 
            painelcima.add(auxiliar3); 
            painelEsquerda.add(painelcima, BorderLayout.NORTH);
        }
        //// ADICIONA O PAINEL AONDE ESTARA OS BOTÕES DO MAPA
        painelMapaEsquerda = new JPanel();
        painelMapaEsquerda.setLayout(new GridLayout(11,11));
        criaBotoesMapaEsquerdaEDireita("esquerda");
        painelEsquerda.add(painelMapaEsquerda, BorderLayout.CENTER);
    }
       
    public void criarMapaDireita(){
        /// -- ESTAO FUNÇÃO SERVE PARA DEFINIR O ESPAÇO DO BORDERLAYOUT.CENTER
        /// ONDE ESTARÁ LOCALIZADO O MAPAS DA DIREITA.
        JPanel auxiliar, painelbaixo, auxiliar1, paineldireita, auxiliar2, painelesquerda, auxiliar3, painelcimaDir;
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
        painelcimaDir = new JPanel();
        auxiliar3 = new JPanel();
        painelcimaDir.setLayout(new GridLayout(6, 0));
        painelcimaDir.add(auxiliar3);        
        painelDireita.add(painelcimaDir, BorderLayout.NORTH);
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
        String comando;
        int contadorComando = 0;
        if( "esquerda".equals(opcao) ){
            for( int i = 0; i < 11; i++ ){
                if( i == 0 )
                    numeros[i] = new JLabel("  ");
                else
                    numeros[i] = new JLabel("  " + i);
                painelMapaEsquerda.add(numeros[i]);
            }
            for( int i = 0; i < 10; i++ ){
                letra = "" + c++;
                letras[i] = new JLabel(letra);
                painelMapaEsquerda.add(letras[i]);
                for( int j = 0; j < 10; j++ ){
                    botaoMapaEsquerda[i][j] = new JButton();
                    botaoMapaEsquerda[i][j].setBackground(personalizadaEsq);
                    painelMapaEsquerda.add(botaoMapaEsquerda[i][j]);
                }
            }
        }
        else{
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
        //CASO TIPODEJOGO = DEFINIR
        if(this.tipoJogo == 1){
            dica.setEnabled(false);
            disparoUnico.setEnabled(false);
            disparoUnico.setBackground(Color.WHITE);
            disparoUnico.setForeground(Color.black);
            cascataHorizontal.setEnabled(false);
            cascataVertical.setEnabled(false);
            tiroEstrela.setEnabled(false);
        }
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if( comando.contains("P.") ){
            transformaPadraoBotoes();
            if("dica".equals(tipoDisparo)){
                this.tipoDisparo = this.auxiliar;
                desfazerAnimacaoDica();
            }
            //-------------------------------
            switch (comando) {
                case "P.sair":
                    System.exit(0);
                case "P.dica":
                    this.auxiliar = this.tipoDisparo;
                    dica.setBackground(Color.DARK_GRAY);
                    dica.setForeground(Color.WHITE);
                    tipoDisparo = "dica";
                    animacaoDica();
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
            if("dica".equals(tipoDisparo)){
                AnimacaoDica animation = new AnimacaoDica(contadorx, contadory);
                return;
            }
            if( "disparounico".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
                verificaDisparo(contadorx, contadory);
            }else if( "cascatahorizontal".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
                verificaDisparo(contadorx, contadory);
                if( contadory + 1 < 10 ){
                    botaoMapaDireita[contadorx][contadory+1].setActionCommand("");
                    botaoMapaDireita[contadorx][contadory+1].setBackground(Color.DARK_GRAY);
                    verificaDisparo(contadorx, contadory + 1);
                }
            }else if( "cascatavertical".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
                verificaDisparo(contadorx, contadory);
                if( contadorx - 1 > -1 ){
                    botaoMapaDireita[contadorx-1][contadory].setActionCommand("");
                    botaoMapaDireita[contadorx-1][contadory].setBackground(Color.DARK_GRAY);
                    verificaDisparo(contadorx - 1, contadory);
                }
            }else if( "tiroestrela".equals(tipoDisparo) ){
                botaoMapaDireita[contadorx][contadory].setActionCommand("");
                botaoMapaDireita[contadorx][contadory].setBackground(Color.DARK_GRAY);
                verificaDisparo(contadorx, contadory);
                if( contadorx - 1 > -1 ){
                    botaoMapaDireita[contadorx-1][contadory].setActionCommand("");
                    botaoMapaDireita[contadorx-1][contadory].setBackground(Color.DARK_GRAY);
                    verificaDisparo(contadorx - 1, contadory);
                }
                if( contadorx + 1 < 10 ){
                    botaoMapaDireita[contadorx+1][contadory].setActionCommand("");
                    botaoMapaDireita[contadorx+1][contadory].setBackground(Color.DARK_GRAY);
                    verificaDisparo(contadorx + 1, contadory);
                }
                if( contadory + 1 < 10 ){
                    botaoMapaDireita[contadorx][contadory+1].setActionCommand("");
                    botaoMapaDireita[contadorx][contadory+1].setBackground(Color.DARK_GRAY);
                    verificaDisparo(contadorx, contadory + 1);
                }
                if( contadory - 1 > -1 ){
                    botaoMapaDireita[contadorx][contadory-1].setActionCommand("");
                    botaoMapaDireita[contadorx][contadory-1].setBackground(Color.DARK_GRAY);
                    verificaDisparo(contadorx, contadory - 1);
                }
        }
        if(this.quantidadeVeiculosComp == 0){
            JOptionPane.showMessageDialog(null, "O Jogador ganhou!!", "Vencedor", JOptionPane.WARNING_MESSAGE);
            fimdeJogo(1);
        }
        if( ( this.quantidadeVeiculosPlayer == 1 && portaaviaoPlayer.isStatus() ) && (this.quantidadeVeiculosComp == 1 && portaaviaoComputador.isStatus())){
                compDisparar();
        }else if( this.quantidadeVeiculosComp == 1 && portaaviaoComputador.isStatus()){
            if(portaaviaoComputador.verificaRound())
                compDisparar();
            portaaviaoComputador.subirRound();
        }else if( this.quantidadeVeiculosPlayer == 1 && portaaviaoPlayer.isStatus() ){
            compDisparar();
            compDisparar();
        }else
            compDisparar();
        
        }else if( comando.contains("B.") ){
            Color personalizadaDir = new Color(225,255,175);
            int continuar = 0, confirmar = 0;
            boolean prox = false;
            int posicao, contadorx = 0, contadory = 0, limite = 9;
            posicao = Integer.parseInt(comando.replaceAll("[^0-9]", ""));
            for(int i = 0; i < posicao; i++ ){
                contadory = contadory + 1;
                if( i == limite ){
                    limite = limite + 10;
                    contadory = 0;
                    contadorx = contadorx + 1;
                }
            }
            if(this.modo == 1){
               for(int i =0 ; i < coordenadaPlayer.size(); i = i + 2 ){
                    if(contadorx == coordenadaPlayer.get(i) && contadory == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                    if(contadorx == coordenadaPlayer.get(i) && contadory + 1 == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                } 
            }else if(this.modo == 2){
                for(int i =0 ; i < coordenadaPlayer.size(); i = i + 2 ){
                    if(contadorx == coordenadaPlayer.get(i) && contadory == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                    if(contadorx == coordenadaPlayer.get(i) && contadory + 1 == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                    if(contadorx == coordenadaPlayer.get(i) && contadory + 2 == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                    if(contadorx == coordenadaPlayer.get(i) && contadory + 3 == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                } 
            }else if(this.modo == 3){
                for(int i =0 ; i < coordenadaPlayer.size(); i = i + 2 ){
                    if(contadorx == coordenadaPlayer.get(i) && contadory == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                    if(contadorx == coordenadaPlayer.get(i) && contadory + 1 == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                    if(contadorx == coordenadaPlayer.get(i) && contadory + 2 == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                } 
            }else{
                for(int i =0 ; i < coordenadaPlayer.size(); i = i + 2 ){
                    if(contadorx == coordenadaPlayer.get(i) && contadory == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                    if(contadorx == coordenadaPlayer.get(i) && contadory + 1 == coordenadaPlayer.get(i+1)){
                        continuar++;
                    }
                } 
            }
            if(this.modo == 1 && contadory < 9 && continuar == 0){
                cacaPlayer = new Caca(contadorx, contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory + 1);
                botaoMapaEsquerda[contadorx][contadory++].setIcon(new ImageIcon(Caca.getImgVivo(1)));
                botaoMapaEsquerda[contadorx][contadory].setIcon(new ImageIcon(Caca.getImgVivo(2)));
                quant[0] = 0;
                prox = true;
            }else if(this.modo == 2 && contadory < 7 && continuar == 0){
                portaaviaoPlayer = new PortaAviao(contadorx, contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory + 1);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory + 2);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory + 3);
                botaoMapaEsquerda[contadorx][contadory].setIcon(new ImageIcon(PortaAviao.getImgVivo(1)));
                botaoMapaEsquerda[contadorx][contadory + 1].setIcon(new ImageIcon(PortaAviao.getImgVivo(2)));
                botaoMapaEsquerda[contadorx][contadory + 2].setIcon(new ImageIcon(PortaAviao.getImgVivo(3)));
                botaoMapaEsquerda[contadorx][contadory + 3].setIcon(new ImageIcon(PortaAviao.getImgVivo(4)));
                quant[1] = 0;
                prox = true;
            }else if(this.modo == 3 && contadory < 8 && continuar == 0 ){
                navioescoltaPlayer = new NaviodeEscolta(contadorx, contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory + 1);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory + 2);
                botaoMapaEsquerda[contadorx][contadory].setIcon(new ImageIcon(NaviodeEscolta.getImgVivo(1)));
                botaoMapaEsquerda[contadorx][contadory + 1].setIcon(new ImageIcon(NaviodeEscolta.getImgVivo(2)));
                botaoMapaEsquerda[contadorx][contadory + 2].setIcon(new ImageIcon(NaviodeEscolta.getImgVivo(3)));
                quant[2] = 0;
                prox = true;
            }else if(this.modo == 4 && contadory < 9 && continuar == 0 ){
                submarinoPlayer = new Submarino(contadorx, contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory);
                coordenadaPlayer.add(contadorx);
                coordenadaPlayer.add(contadory + 1);
                botaoMapaEsquerda[contadorx][contadory].setIcon(new ImageIcon(Submarino.getImgVivo(1)));
                botaoMapaEsquerda[contadorx][contadory + 1].setIcon(new ImageIcon(Submarino.getImgVivo(2)));
                quant[3] = 0;
                prox = true;
            }
            if( prox ){
                for(int i = 0; i < 4; i++ ){
                    if(quant[i] == 1){
                        if(this.modo == 1)
                            aviao.setVisible(false);
                        if(this.modo == 2)
                            porta.setVisible(false);
                        if(this.modo == 3)
                            navio.setVisible(false);
                        if(this.modo == 4)
                            sub.setVisible(false);
                        this.modo = i + 1;
                        if(this.modo == 1)
                            aviao.setIcon(new ImageIcon(Caca.getIcon(1)));
                        if(this.modo == 2)
                            porta.setIcon(new ImageIcon(PortaAviao.getIcon(1)));
                        if(this.modo == 3)
                            navio.setIcon(new ImageIcon(NaviodeEscolta.getIcon(1)));
                        if(this.modo == 4)
                            sub.setIcon(new ImageIcon(Submarino.getIcon(1)));
                        confirmar++;
                        break;
                    }
                }
            if( confirmar == 0 ){
                aviao.setVisible(false);
                porta.setVisible(false);
                navio.setVisible(false);
                sub.setVisible(false);
                for(int i =0; i < 10; i ++){
                    for(int j = 0; j<10; j++){
                        botaoMapaEsquerda[i][j].setEnabled(true);
                        botaoMapaDireita[i][j].setEnabled(true);
                        botaoMapaDireita[i][j].setBackground(personalizadaDir);
                        botaoMapaEsquerda[i][j].setActionCommand("S");
                    }
                }
                dica.setEnabled(true);
                disparoUnico.setBackground(Color.DARK_GRAY);
                disparoUnico.setForeground(Color.WHITE);
                disparoUnico.setEnabled(true);
                cascataHorizontal.setEnabled(true);
                cascataVertical.setEnabled(true);
                tiroEstrela.setEnabled(true);
                painelcima.setLayout(new FlowLayout(1, 25, 25));
            }
        }
        }else if( comando.contains("M.") ){
            if( "M.av".equals(comando) ){
                if(this.modo == 2)
                    porta.setIcon(new ImageIcon(PortaAviao.getIcon(2)));
                if(this.modo == 3)
                    navio.setIcon(new ImageIcon(NaviodeEscolta.getIcon(2)));
                if(this.modo == 4)
                    sub.setIcon(new ImageIcon(Submarino.getIcon(2)));
                this.modo = 1;
                aviao.setIcon(new ImageIcon(Caca.getIcon(1)));
            }else if("M.pa".equals(comando)){
                if(this.modo == 1)
                    aviao.setIcon(new ImageIcon(Caca.getIcon(2)));
                if(this.modo == 3)
                    navio.setIcon(new ImageIcon(NaviodeEscolta.getIcon(2)));
                if(this.modo == 4)
                    sub.setIcon(new ImageIcon(Submarino.getIcon(2)));
                this.modo = 2;
                porta.setIcon(new ImageIcon(PortaAviao.getIcon(1)));
            }else if("M.ne".equals(comando)){
                if(this.modo == 1)
                    aviao.setIcon(new ImageIcon(Caca.getIcon(2)));
                if(this.modo == 2)
                    porta.setIcon(new ImageIcon(PortaAviao.getIcon(2)));
                if(this.modo == 4)
                    sub.setIcon(new ImageIcon(Submarino.getIcon(2)));
                this.modo = 3;
                navio.setIcon(new ImageIcon(NaviodeEscolta.getIcon(1)));
            }else{
                if(this.modo == 1)
                    aviao.setIcon(new ImageIcon(Caca.getIcon(2)));
                if(this.modo == 2)
                    porta.setIcon(new ImageIcon(PortaAviao.getIcon(2)));
                if(this.modo == 3)
                    navio.setIcon(new ImageIcon(NaviodeEscolta.getIcon(2)));
                this.modo = 4;
                sub.setIcon(new ImageIcon(Submarino.getIcon(1)));
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
    
    public void verificaDisparo(int x, int y){
        for(int i = 0; i < coordenadaComp.size(); i = i + 2){
            if(x == coordenadaComp.get(i) && y == coordenadaComp.get(i+1)){
                if(cacaComputador.verficaPos(x, y) && cacaComputador.isStatus()){
                    botaoMapaDireita[x][y].setIcon(new ImageIcon(cacaComputador.procuraImg(x, y)));
                    botaoMapaDireita[x][y].setActionCommand("");
                    coordenadaComp.remove(i);
                    coordenadaComp.remove(i);
                    cacaComputador.removeCoordenada(x, y);
                    if(!cacaComputador.isStatus())
                        this.quantidadeVeiculosComp--;
                    return;
                }else if(portaaviaoComputador.verficaPos(x, y) && portaaviaoComputador.isStatus()){
                    botaoMapaDireita[x][y].setIcon(new ImageIcon(portaaviaoComputador.procuraImg(x, y)));
                    botaoMapaDireita[x][y].setActionCommand("");
                    coordenadaComp.remove(i);
                    coordenadaComp.remove(i);
                    portaaviaoComputador.removeCoordenada(x, y);
                    if(!portaaviaoComputador.isStatus())
                        this.quantidadeVeiculosComp--;
                    return;
                }else if(submarinoComputador.verficaPos(x, y) && submarinoComputador.isStatus()){
                    botaoMapaDireita[x][y].setIcon(new ImageIcon(submarinoComputador.procuraImg(x, y)));
                    botaoMapaDireita[x][y].setActionCommand("");
                    coordenadaComp.remove(i);
                    coordenadaComp.remove(i);
                    submarinoComputador.removeCoordenada(x, y);
                    if(!submarinoComputador.isStatus())
                        this.quantidadeVeiculosComp--;
                    return;
                }else if( navioescoltaComputador.verficaPos(x, y) && navioescoltaComputador.isStatus()) {
                    botaoMapaDireita[x][y].setIcon(new ImageIcon(navioescoltaComputador.procuraImg(x, y)));
                    botaoMapaDireita[x][y].setActionCommand("");
                    coordenadaComp.remove(i);
                    coordenadaComp.remove(i);
                    navioescoltaComputador.removeCoordenada(x, y);
                    if(!navioescoltaComputador.isStatus())
                        this.quantidadeVeiculosComp--;
                    return;
                }
            }
        }
    }
    
    public void compDisparar(){
        int posx, posy;
        Random rand = new Random();
        while(true){
            posx = rand.nextInt(10);
            posy = rand.nextInt(10);
            if("S".equals(botaoMapaEsquerda[posx][posy].getActionCommand())){
                break;
            }
        }
        verificaDanoVeiculosPlayer();
        if(marcarAtaque == 1){
            posx = cacaPlayer.getPosicao(0);
            posy = cacaPlayer.getPosicao(1);
        }
        if(marcarAtaque == 2){
            posx = portaaviaoPlayer.getPosicao(0);
            posy = portaaviaoPlayer.getPosicao(1);
        }
        if(marcarAtaque == 3){
            posx = navioescoltaPlayer.getPosicao(0);
            posy = navioescoltaPlayer.getPosicao(1);
        }
        if(marcarAtaque == 4){
            posx = submarinoPlayer.getPosicao(0);
            posy = submarinoPlayer.getPosicao(1);
        }
        if(cacaComputador.isStatus()){
            botaoMapaEsquerda[posx][posy].setBackground(Color.DARK_GRAY);
            botaoMapaEsquerda[posx][posy].setActionCommand("N");
            verificaDisparadoComp(posx, posy);
            if( posx - 1 > -1 ){
                botaoMapaEsquerda[posx-1][posy].setActionCommand("N");
                botaoMapaEsquerda[posx-1][posy].setBackground(Color.DARK_GRAY);
                verificaDisparadoComp(posx - 1, posy);
            }
            if( posx + 1 < 10 ){
                botaoMapaEsquerda[posx+1][posy].setActionCommand("N");
                botaoMapaEsquerda[posx+1][posy].setBackground(Color.DARK_GRAY);
                verificaDisparadoComp(posx + 1, posy);
            }
            if( posy + 1 < 10 ){
                botaoMapaEsquerda[posx][posy+1].setActionCommand("N");
                botaoMapaEsquerda[posx][posy+1].setBackground(Color.DARK_GRAY);
                verificaDisparadoComp(posx, posy + 1);
            }
            if( posy - 1 > -1 ){
                botaoMapaEsquerda[posx][posy-1].setActionCommand("N");
                botaoMapaEsquerda[posx][posy-1].setBackground(Color.DARK_GRAY);
                verificaDisparadoComp(posx, posy - 1);
            }
        }else if(navioescoltaComputador.isStatus()){
            if(rand.nextInt(2) == 1){
                botaoMapaEsquerda[posx][posy].setActionCommand("N");
                botaoMapaEsquerda[posx][posy].setBackground(Color.DARK_GRAY);
                verificaDisparadoComp(posx, posy);
                if( posy + 1 < 10 ){
                    botaoMapaEsquerda[posx][posy+1].setActionCommand("N");
                    botaoMapaEsquerda[posx][posy+1].setBackground(Color.DARK_GRAY);
                    verificaDisparadoComp(posx, posy + 1);
                }
            }else{
                botaoMapaEsquerda[posx][posy].setActionCommand("N");
                botaoMapaEsquerda[posx][posy].setBackground(Color.DARK_GRAY);
                verificaDisparadoComp(posx, posy);
                if( posx - 1 > -1 ){
                    botaoMapaEsquerda[posx-1][posy].setActionCommand("N");
                    botaoMapaEsquerda[posx-1][posy].setBackground(Color.DARK_GRAY);
                    verificaDisparadoComp(posx - 1, posy);
                }
            }
        }else if(submarinoComputador.isStatus()){
            botaoMapaEsquerda[posx][posy].setActionCommand("N");
            botaoMapaEsquerda[posx][posy].setBackground(Color.DARK_GRAY);
            verificaDisparadoComp(posx, posy);
        }else if(portaaviaoComputador.isStatus()){
            botaoMapaEsquerda[posx][posy].setActionCommand("N");
            botaoMapaEsquerda[posx][posy].setBackground(Color.DARK_GRAY);
            verificaDisparadoComp(posx, posy);
        }
        if(this.quantidadeVeiculosPlayer == 0){
            JOptionPane.showMessageDialog(null, "O Computador ganhou!!", "Vencedor", JOptionPane.WARNING_MESSAGE);
            fimdeJogo(0);
        }
    }
    
    public void verificaDisparadoComp(int x, int y){
        for(int i = 0; i < coordenadaPlayer.size(); i = i + 2){
            if(x == coordenadaPlayer.get(i) && y == coordenadaPlayer.get(i+1)){
                if(cacaPlayer.verficaPos(x, y) && cacaPlayer.isStatus()){
                    botaoMapaEsquerda[x][y].setIcon(new ImageIcon(cacaPlayer.procuraImg(x, y)));
                    coordenadaPlayer.remove(i);
                    coordenadaPlayer.remove(i);
                    cacaPlayer.removeCoordenada(x, y);
                    if(!cacaPlayer.isStatus()){
                        this.quantidadeVeiculosPlayer--;
                        verificaQuantVeiculos(1);
                    }
                    return;
                }else if(portaaviaoPlayer.verficaPos(x, y) && portaaviaoPlayer.isStatus()){
                    botaoMapaEsquerda[x][y].setIcon(new ImageIcon(portaaviaoPlayer.procuraImg(x, y)));
                    coordenadaPlayer.remove(i);
                    coordenadaPlayer.remove(i);
                    portaaviaoPlayer.removeCoordenada(x, y);
                    if(!portaaviaoPlayer.isStatus()){
                        this.quantidadeVeiculosPlayer--;
                        verificaQuantVeiculos(2);
                    }
                    return;
                }else if(submarinoPlayer.verficaPos(x, y) && submarinoPlayer.isStatus()){
                    botaoMapaEsquerda[x][y].setIcon(new ImageIcon(submarinoPlayer.procuraImg(x, y)));
                    coordenadaPlayer.remove(i);
                    coordenadaPlayer.remove(i);
                    submarinoPlayer.removeCoordenada(x, y);
                    if(!submarinoPlayer.isStatus()){
                        this.quantidadeVeiculosPlayer--;
                        verificaQuantVeiculos(4);
                    }
                    return;
                }else if( navioescoltaPlayer.verficaPos(x, y) && navioescoltaPlayer.isStatus()) {
                    botaoMapaEsquerda[x][y].setIcon(new ImageIcon(navioescoltaPlayer.procuraImg(x, y)));
                    coordenadaPlayer.remove(i);
                    coordenadaPlayer.remove(i);
                    navioescoltaPlayer.removeCoordenada(x, y);
                    if(!navioescoltaPlayer.isStatus()){
                        this.quantidadeVeiculosPlayer--;
                        verificaQuantVeiculos(3);
                    }
                    return;
                }
            }
        }
    }
    
    public boolean verificaDanoVeiculosPlayer(){
        if(cacaPlayer.isStatus()){
            if(cacaPlayer.marcarAtaque()){
                marcarAtaque = 1;
                return true;
            }
        }
        if(portaaviaoPlayer.isStatus()){
            if(portaaviaoPlayer.marcarAtaque()){
                marcarAtaque = 2;
                return true;
            }
        }
        if(navioescoltaPlayer.isStatus()){
            if(navioescoltaPlayer.marcarAtaque()){
                marcarAtaque = 3;
                return true;
            }
        }
        if(submarinoPlayer.isStatus()){
            if(submarinoPlayer.marcarAtaque()){
                marcarAtaque = 4;
                return true;
            }
        }
        marcarAtaque = 0;
        return false;
    }
    
    public void verificaQuantVeiculos(int veiculo){
        if(veiculo == 1)
            tiroEstrela.setEnabled(false);
        else if(veiculo == 2){
            if( !submarinoPlayer.isStatus() )
                disparoUnico.setEnabled(false);
        }else if(veiculo == 3){
            cascataVertical.setEnabled(false);
            cascataHorizontal.setEnabled(false);
        }else if(veiculo == 4){
            if( !portaaviaoPlayer.isStatus() )
                disparoUnico.setEnabled(false);
        }
        if( "tiroestrela".equals(tipoDisparo) && veiculo == 1 ){
            tiroEstrela.setBackground(Color.WHITE);
            tiroEstrela.setForeground(Color.BLACK);
            tiroEstrela.setEnabled(false);
            if( portaaviaoPlayer.isStatus() || submarinoPlayer.isStatus() ){
                disparoUnico.setBackground(Color.DARK_GRAY);
                disparoUnico.setForeground(Color.WHITE);
                tipoDisparo = "disparounico";
            }else if( navioescoltaPlayer.isStatus() ){
                cascataHorizontal.setBackground(Color.DARK_GRAY);
                cascataHorizontal.setForeground(Color.WHITE);
                tipoDisparo = "cascatahorizontal";
            }
        }else if( "disparounico".equals(tipoDisparo) && ( veiculo == 2 || veiculo == 4 )){
            disparoUnico.setBackground(Color.WHITE);
            disparoUnico.setForeground(Color.BLACK);
            disparoUnico.setEnabled(false);
            if( portaaviaoPlayer.isStatus() || submarinoPlayer.isStatus() ){
                disparoUnico.setBackground(Color.DARK_GRAY);
                disparoUnico.setForeground(Color.WHITE);
                disparoUnico.setEnabled(true);
                tipoDisparo = "disparounico";
            }else if( navioescoltaPlayer.isStatus() ){
                cascataHorizontal.setBackground(Color.DARK_GRAY);
                cascataHorizontal.setForeground(Color.WHITE);
                tipoDisparo = "cascatahorizontal";
            }else if( cacaPlayer.isStatus() ){
                tiroEstrela.setBackground(Color.DARK_GRAY);
                tiroEstrela.setForeground(Color.WHITE);
                tipoDisparo = "tiroestrela";
            }
        }else if( ("cascatahorizontal".equals(tipoDisparo) || "cascatavertical".equals(tipoDisparo)) && veiculo == 3){
            cascataHorizontal.setBackground(Color.WHITE);
            cascataHorizontal.setForeground(Color.BLACK);
            cascataHorizontal.setEnabled(false);
            cascataVertical.setBackground(Color.WHITE);
            cascataVertical.setForeground(Color.BLACK);
            cascataVertical.setEnabled(false);
            if( portaaviaoPlayer.isStatus() || submarinoPlayer.isStatus() ){
                disparoUnico.setBackground(Color.DARK_GRAY);
                disparoUnico.setForeground(Color.WHITE);
                disparoUnico.setEnabled(true);
                tipoDisparo = "disparounico";
            }else if( cacaPlayer.isStatus() ){
                tiroEstrela.setBackground(Color.DARK_GRAY);
                tiroEstrela.setForeground(Color.WHITE);
                tipoDisparo = "tiroestrela";
            }
        }
    }
    
    // -------
    
    public void fimdeJogo(int vencedor){
        transformaPadraoBotoes();
        dica.setEnabled(false);
        disparoUnico.setEnabled(false);
        cascataHorizontal.setEnabled(false);
        cascataVertical.setEnabled(false);
        tiroEstrela.setEnabled(false);
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++){
                botaoMapaDireita[i][j].removeActionListener(this);
                botaoMapaEsquerda[i][j].removeActionListener(this);
            }
    }
    
    public void animacaoDica(){
        int contador =0;
        for(int i =0 ; i < 10 ; i++ ){
            for(int j =0; j < 10; j++ ){
                comandos.add(""+botaoMapaDireita[i][j].getActionCommand());
                botaoMapaDireita[i][j].setActionCommand("" + contador++);
            }
        }
    }
    
    public void desfazerAnimacaoDica(){
        for(int i =0 ; i < 10 ; i++ ){
            for(int j =0; j < 10; j++ ){
                botaoMapaDireita[i][j].setActionCommand("" + comandos.get(0));
                comandos.remove(0);
            }
        }
    }
    
    // --------- POEM VEICULOS EM CAMPO NA PRIMEIRA ITERAÇÃO
    
    public void botarVeiculos(){
        if(tipoJogo == 0){
            adicionarCacas(true);
            adicionarEscoltas(true);
            adicionaPortaAvioes(true);
            adicionaSubmarinos(true);
            for(int i =0; i < 10; i ++){
                for(int j = 0; j<10; j++){
                    botaoMapaEsquerda[i][j].setActionCommand("S");
                }
            }
        }else{
            int contadorComando = 0;
            for(int i = 0; i < 10; i++)
                for(int j = 0; j < 10; j++){
                    botaoMapaDireita[i][j].setEnabled(false);
                    botaoMapaDireita[i][j].setBackground(Color.LIGHT_GRAY);
                    botaoMapaEsquerda[i][j].setActionCommand("B." + contadorComando++);
                    botaoMapaEsquerda[i][j].addActionListener(this);
                }
        }
        adicionarCacas(false);
        adicionarEscoltas(false);
        adicionaPortaAvioes(false);
        adicionaSubmarinos(false);
    }
    
    public void adicionarCacas(boolean jogador){
        int posx, posy;
        Random rand = new Random();
        posx = rand.nextInt(10);
        posy = rand.nextInt(9);
        if(jogador){
            cacaPlayer = new Caca(posx, posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy + 1);
            botaoMapaEsquerda[posx][posy].setIcon(new ImageIcon(Caca.getImgVivo(1)));
            botaoMapaEsquerda[posx][posy + 1].setIcon(new ImageIcon(Caca.getImgVivo(2)));
        }else{
            cacaComputador = new Caca(posx, posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy + 1);
        }
    }
    
    public void adicionarEscoltas(boolean jogador){
        int posx, posy;
        int confirmar;
        Random rand = new Random();
        posx = rand.nextInt(10);
        while(true){
            posy = rand.nextInt(10);
            confirmar = 0;
            if( posy < 8 ){
                if(jogador){
                    for(int i = 0; i < 4; i = i + 2 ){
                        if( posx == cacaPlayer.getPosicao(i) && posy == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaPlayer.getPosicao(i) && posy + 1 == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaPlayer.getPosicao(i) && posy + 2 == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                    }
                }else{
                    for(int i = 0; i < 4; i = i + 2 ){
                        if( posx == cacaComputador.getPosicao(i) && posy == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaComputador.getPosicao(i) && posy + 1 == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaComputador.getPosicao(i) && posy + 2 == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                    }
                }
                if(confirmar == 0)
                    break;
            }
        }
        if(jogador){
            navioescoltaPlayer = new NaviodeEscolta(posx, posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy + 1);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy + 2);
            botaoMapaEsquerda[posx][posy].setIcon(new ImageIcon(NaviodeEscolta.getImgVivo(1)));
            botaoMapaEsquerda[posx][posy + 1].setIcon(new ImageIcon(NaviodeEscolta.getImgVivo(2)));
            botaoMapaEsquerda[posx][posy + 2].setIcon(new ImageIcon(NaviodeEscolta.getImgVivo(3)));
        }else{
            navioescoltaComputador = new NaviodeEscolta(posx, posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy + 1);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy + 2);
        }
    }
            
    public void adicionaPortaAvioes(boolean jogador){
        int posx, posy;
        int confirmar;
        Random rand = new Random();
        while(true){
            posx = rand.nextInt(10);
            posy = rand.nextInt(10);
            confirmar = 0;
            if( posy < 7 ){
                if(jogador){
                    for(int i = 0; i < 4; i = i + 2 ){
                        if( posx == cacaPlayer.getPosicao(i) && posy == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaPlayer.getPosicao(i) && posy + 1 == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaPlayer.getPosicao(i) && posy + 2 == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaPlayer.getPosicao(i) && posy + 3 == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                    }
                    for(int i = 0; i < 6; i = i + 2 ){
                        if( posx == navioescoltaPlayer.getPosicao(i) && posy == navioescoltaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaPlayer.getPosicao(i) && posy + 1 == navioescoltaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaPlayer.getPosicao(i) && posy + 2 == navioescoltaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaPlayer.getPosicao(i) && posy + 3 == navioescoltaPlayer.getPosicao(i+1) )
                            confirmar++;
                    }
                }else{
                    for(int i = 0; i < 4; i = i + 2 ){
                        if( posx == cacaComputador.getPosicao(i) && posy == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaComputador.getPosicao(i) && posy + 1 == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaComputador.getPosicao(i) && posy + 2 == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaComputador.getPosicao(i) && posy + 3 == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                    }
                    for(int i = 0; i < 6; i = i + 2 ){
                        if( posx == navioescoltaComputador.getPosicao(i) && posy == navioescoltaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaComputador.getPosicao(i) && posy + 1 == navioescoltaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaComputador.getPosicao(i) && posy + 2 == navioescoltaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaComputador.getPosicao(i) && posy + 3 == navioescoltaComputador.getPosicao(i+1) )
                            confirmar++;
                    }
                }
                if(confirmar == 0)
                    break;
            }
        }
        if(jogador){
            portaaviaoPlayer = new PortaAviao(posx, posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy + 1);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy + 2);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy + 3);
            botaoMapaEsquerda[posx][posy++].setIcon(new ImageIcon(PortaAviao.getImgVivo(1)));
            botaoMapaEsquerda[posx][posy++].setIcon(new ImageIcon(PortaAviao.getImgVivo(2)));
            botaoMapaEsquerda[posx][posy++].setIcon(new ImageIcon(PortaAviao.getImgVivo(3)));
            botaoMapaEsquerda[posx][posy].setIcon(new ImageIcon(PortaAviao.getImgVivo(4)));
        }else{
            portaaviaoComputador = new PortaAviao(posx, posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy + 1);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy + 2);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy + 3);
        }
    }
    
    public void adicionaSubmarinos(boolean jogador){
        int posx, posy;
        int confirmar;
        Random rand = new Random();
        while(true){
            posx = rand.nextInt(10);
            posy = rand.nextInt(10);
            confirmar = 0;
            if( posy < 9 ){
                if(jogador){
                    for(int i = 0; i < 4; i = i + 2 ){
                        if( posx == cacaPlayer.getPosicao(i) && posy == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaPlayer.getPosicao(i) && posy + 1 == cacaPlayer.getPosicao(i+1) )
                            confirmar++;
                    }
                    for(int i = 0; i < 6; i = i + 2 ){
                        if( posx == navioescoltaPlayer.getPosicao(i) && posy == navioescoltaPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaPlayer.getPosicao(i) && posy + 1 == navioescoltaPlayer.getPosicao(i+1) )
                            confirmar++;
                    }
                    for(int i = 0; i < 8; i = i + 2 ){
                        if( posx == portaaviaoPlayer.getPosicao(i) && posy == portaaviaoPlayer.getPosicao(i+1) )
                            confirmar++;
                        if( posx == portaaviaoPlayer.getPosicao(i) && posy + 1 == portaaviaoPlayer.getPosicao(i+1) )
                            confirmar++;
                    }
                }else{
                    for(int i = 0; i < 4; i = i + 2 ){
                        if( posx == cacaComputador.getPosicao(i) && posy == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == cacaComputador.getPosicao(i) && posy + 1 == cacaComputador.getPosicao(i+1) )
                            confirmar++;
                    }
                    for(int i = 0; i < 6; i = i + 2 ){
                        if( posx == navioescoltaComputador.getPosicao(i) && posy == navioescoltaComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == navioescoltaComputador.getPosicao(i) && posy + 1 == navioescoltaComputador.getPosicao(i+1) )
                            confirmar++;
                    }
                    for(int i = 0; i < 8; i = i + 2 ){
                        if( posx == portaaviaoComputador.getPosicao(i) && posy == portaaviaoComputador.getPosicao(i+1) )
                            confirmar++;
                        if( posx == portaaviaoComputador.getPosicao(i) && posy + 1 == portaaviaoComputador.getPosicao(i+1) )
                            confirmar++;
                    }
                }
                if(confirmar == 0)
                    break;
            }
        }
        if(jogador){
            submarinoPlayer = new Submarino(posx, posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy);
            coordenadaPlayer.add(posx);
            coordenadaPlayer.add(posy + 1);
            botaoMapaEsquerda[posx][posy++].setIcon(new ImageIcon(Submarino.getImgVivo(1)));
            botaoMapaEsquerda[posx][posy].setIcon(new ImageIcon(Submarino.getImgVivo(2)));
        }else{
            submarinoComputador = new Submarino(posx, posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy);
            coordenadaComp.add(posx);
            coordenadaComp.add(posy + 1);
        }
    }
   
    
    public static void main(String[] args) {
        menuPrincipal guiMenu = new menuPrincipal();
        guiMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
    }
    
    class AnimacaoDica extends Thread{
        
        private int tempo;
        private int posx;
        private int posy;
        private int posxcima;
        private int posxbaixo;
        private int posydir;
        private int posyesq;
        private int speed;
        private ArrayList <Color> corcima = new ArrayList<>();
        private ArrayList <Color> cordireita = new ArrayList<>();
        private ArrayList <Color> coresquerda = new ArrayList<>();
        private ArrayList <Color> corbaixo = new ArrayList<>();
        private ArrayList <Color> cordentro = new ArrayList<>();
        private Color cor;
        
        public AnimacaoDica(int x, int y){
            this.speed = 60;
            this.tempo = 0;
            this.posx = x;
            this.posy = y;
            atribuirPos(x, y);
            start();
            verificarDica(x, y);
            for(int i = x - 1; i >= 0; i--)
                corcima.add(botaoMapaDireita[i][y].getBackground());
            for(int i = x + 1; i <= 9; i++)
                corbaixo.add(botaoMapaDireita[i][y].getBackground());
            for(int i = y - 1; i >= 0; i--)
                coresquerda.add(botaoMapaDireita[x][i].getBackground());
            for(int i = y + 1; i <= 9; i++)
                cordireita.add(botaoMapaDireita[x][i].getBackground());
            cordentro.add(botaoMapaDireita[x][y].getBackground());
            for(int i =0 ; i < 10 ; i++ ){
                for(int j =0; j < 10; j++ ){
                    botaoMapaDireita[i][j].setActionCommand("");
                }
            }
        }
        
        public void verificarDica(int x, int y){
            for(int i = x; i >= 0; i--){
                for(int j = 0; j < coordenadaComp.size(); j = j + 2){
                    if(i == coordenadaComp.get(j) && y == coordenadaComp.get(j + 1)){
                        cor = new Color(0, 250, 0);
                        return;
                    }
                }
            }
            for(int i = x + 1; i <= 9; i++){
                for(int j = 0; j < coordenadaComp.size(); j = j + 2){
                    if(i == coordenadaComp.get(j) && y == coordenadaComp.get(j + 1)){
                        cor = new Color(0, 250, 0);
                        return;
                    }
                }
            }
            for(int i = y; i >= 0; i--){
                for(int j = 0; j < coordenadaComp.size(); j = j + 2){
                    if(x == coordenadaComp.get(j) && i == coordenadaComp.get(j + 1)){
                        cor = new Color(0, 250, 0);
                        return;
                    }
                }
            }
            for(int i = y + 1; i <= 9; i++){
                for(int j = 0; j < coordenadaComp.size(); j = j + 2){
                    if(x == coordenadaComp.get(j) && i == coordenadaComp.get(j + 1)){
                        cor = new Color(0, 250, 0);
                        return;
                    }
                }
            }
            cor = new Color(250, 0, 0);
        }
        
        public void atribuirPos(int x, int y){
            posxcima = x - 1;
            posxbaixo = x + 1;
            posydir = y + 1;
            posyesq = y - 1;
        }
        
        @Override
        public void run(){
            for( ; ; ){
                if( tempo == 0 ){
                    botaoMapaDireita[posx][posy].setBackground(cor);
                    tempo++;
                }
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException ex) {
                    Logger.getLogger(menuJogar.class.getName()).log(Level.SEVERE, null, ex);
                }
                if( (posxcima >= 0 || posxbaixo <= 9 || posyesq >= 0 || posydir <= 9) && tempo == 1 ){
                    if(posxcima >= 0)
                        botaoMapaDireita[posxcima][posy].setBackground(cor);
                    if(posxbaixo <= 9)
                        botaoMapaDireita[posxbaixo][posy].setBackground(cor);
                    if(posyesq >= 0)
                        botaoMapaDireita[posx][posyesq].setBackground(cor);
                    if(posydir <= 9)
                        botaoMapaDireita[posx][posydir].setBackground(cor);
                }else{
                    if(tempo == 1){
                        botaoMapaDireita[posx][posy].setBackground(cordentro.get(0));
                        atribuirPos(posx, posy);
                        tempo++;
                    }
                    if( (posxcima >= 0 || posxbaixo <= 9 || posyesq >= 0 || posydir <= 9) && tempo == 2 ){
                        if(posxcima >= 0){
                            botaoMapaDireita[posxcima][posy].setBackground(corcima.get(0));
                            corcima.remove(0);
                        }
                        if(posxbaixo <= 9){
                            botaoMapaDireita[posxbaixo][posy].setBackground(corbaixo.get(0));
                            corbaixo.remove(0);
                        } 
                        if(posyesq >= 0){
                            botaoMapaDireita[posx][posyesq].setBackground(coresquerda.get(0));
                            coresquerda.remove(0);
                        } 
                        if(posydir <= 9){
                            botaoMapaDireita[posx][posydir].setBackground(cordireita.get(0));
                            cordireita.remove(0);
                        }
                    }
                    if( posxcima < 0 && posxbaixo > 9 && posyesq < 0 && posydir > 9 ){
                        for(int i =0 ; i < 10 ; i++ ){
                            for(int j =0; j < 10; j++ ){
                                botaoMapaDireita[i][j].setActionCommand("" + comandos.get(0));
                                comandos.remove(0);
                            }
                        }
                        break;
                    }
                }
                posxcima--;
                posxbaixo++;
                posyesq--;
                posydir++;
            }
            dica.setBackground(Color.WHITE);
            dica.setForeground(Color.BLACK);
            if("disparounico".equals(auxiliar)){
                disparoUnico.setBackground(Color.DARK_GRAY);
                disparoUnico.setForeground(Color.WHITE);
                tipoDisparo = "disparounico";
            }
            else if("cascatahorizontal".equals(auxiliar)){
                cascataHorizontal.setBackground(Color.DARK_GRAY);
                cascataHorizontal.setForeground(Color.WHITE);
                tipoDisparo = "cascatahorizontal";
            }
            else if("cascatavertical".equals(auxiliar)){
                cascataVertical.setBackground(Color.DARK_GRAY);
                cascataVertical.setForeground(Color.WHITE);
                tipoDisparo = "cascatavertical";
            }
            else if("tiroestrela".equals(auxiliar)){
                tiroEstrela.setBackground(Color.DARK_GRAY);
                tiroEstrela.setForeground(Color.WHITE);
                tipoDisparo = "tiroestrela";
            }
        }
    }
}
