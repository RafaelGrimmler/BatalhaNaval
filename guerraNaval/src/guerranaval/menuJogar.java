/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerranaval;

import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class menuJogar extends JFrame implements ActionListener{
    private Container tela = getContentPane();
    private JTextField nome;
    private JButton jogoAleatorio, definirJogo, menu, sair;
    private JRadioButton [] niveis = new JRadioButton[3];
    private JPanel []painelaux1 = new JPanel[2];
    private JLabel mensagem;
    private String nomeJogador;
    private int dificuldade;
        
    public menuJogar(){
        super("Guerra Naval");
        tela.setLayout(new GridLayout(2, 0));
        
        paineis();
               
        setSize(400, 215);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void paineis(){
        JPanel[] paineis = new JPanel[2];
        paineis[0] = new JPanel(new GridLayout(2, 0));
                
        // GRID DO NOME
        JLabel nomeTx = new JLabel("              Nome: ");
        JLabel aux = new JLabel("              ");
        painelaux1[0] = new JPanel(new BorderLayout());
        mensagem = new JLabel("                 ");
        painelaux1[0].add(mensagem);
        painelaux1[1] = new JPanel(new BorderLayout());
        nome = new JTextField();
        painelaux1[1].add(nomeTx, BorderLayout.WEST);
        painelaux1[1].add(nome);
        painelaux1[1].add(aux, BorderLayout.EAST);
        paineis[0].add(painelaux1[0]);
        paineis[0].add(painelaux1[1]);
        //
        JPanel painelmeio = new JPanel(new FlowLayout(1, 10, 10));
        JPanel baixo = new JPanel(new FlowLayout(1, 10, 7));
        
        niveis[0] = new JRadioButton("Fácil");
        niveis[0].setSelected(true);
        niveis[0].setActionCommand("facil");
        niveis[0].addActionListener(this);
        this.dificuldade = 1;
        niveis[1] = new JRadioButton("Médio");
        niveis[1].setActionCommand("medio");
        niveis[1].addActionListener(this);
        niveis[2] = new JRadioButton("Difícil");
        niveis[2].setActionCommand("dificil");
        niveis[2].addActionListener(this);
        
        painelmeio.add(niveis[0]);
        painelmeio.add(niveis[1]);
        painelmeio.add(niveis[2]);
        
        // ADD BOTOES
        paineis[1] = new JPanel(new GridLayout(2,0));
        jogoAleatorio = new JButton("Jogo Aleatorio");
        jogoAleatorio.setBackground(Color.white);
        jogoAleatorio.setActionCommand("jogoaleatorio");
        jogoAleatorio.addActionListener(this);
        definirJogo = new JButton("Definir Jogo");
        definirJogo.setBackground(Color.white);
        definirJogo.setActionCommand("definirjogo");
        definirJogo.addActionListener(this);
        menu = new JButton("Menu");
        menu.setBackground(Color.white);
        menu.setActionCommand("menu");
        menu.addActionListener(this);
        sair = new JButton("Sair");
        sair.setBackground(Color.white);
        sair.setActionCommand("sair");
        sair.addActionListener(this);
        
        baixo.add(jogoAleatorio);
        baixo.add(definirJogo);
        baixo.add(menu);
        baixo.add(sair);
        paineis[1].add(painelmeio);
        paineis[1].add(baixo);
        //
       
        tela.add(paineis[0]);
        tela.add(paineis[1]);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        nomeJogador = nome.getText();
        String comando = e.getActionCommand();
        if( "facil".equals(comando) || "medio".equals(comando) || "dificil".equals(comando) ){
            if("facil".equals(comando)){
                niveis[0].setSelected(true);
                niveis[1].setSelected(false);
                niveis[2].setSelected(false);
                this.dificuldade = 1;
            }else if("medio".equals(comando)){
                niveis[0].setSelected(false);
                niveis[1].setSelected(true);
                niveis[2].setSelected(false);
                this.dificuldade = 2;
            }else{
                niveis[0].setSelected(false);
                niveis[1].setSelected(false);
                niveis[2].setSelected(true);
                this.dificuldade = 3;
            }
            return;
        }
        if( "menu".equals(comando) ){
            menuPrincipal telaprincipal = new menuPrincipal();
            telaprincipal.setVisible(true);
            telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            tela.setVisible(false);
            this.dispose();
        }else{
            if( "sair".equals(comando) )
                System.exit(0);
            else{
                if( "jogoaleatorio".equals(comando) ){
                    if( "".equals(nomeJogador) ){
                        mensagem.setText("                                                  Ensira um nome!");
                        mensagem.setForeground(Color.red);
                        nome.setBorder(new LineBorder(Color.RED, 3));
                        Cronometro medidor = new Cronometro(2);
                    }else{
                        GuerraNaval telaprincipal = new GuerraNaval(0, nomeJogador, dificuldade);
                        telaprincipal.setVisible(true);
                        telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                        tela.setVisible(false);
                        this.dispose();
                    }
                    
                }else{
                    if( "".equals(nomeJogador) ){
                        mensagem.setText("                                                  Ensira um nome!");
                        mensagem.setForeground(Color.red);
                        nome.setBorder(new LineBorder(Color.RED, 3));
                        Cronometro medidor = new Cronometro(2);
                    }else{
                        GuerraNaval telaprincipal = new GuerraNaval(1, nomeJogador, dificuldade);
                        telaprincipal.setVisible(true);
                        telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                        tela.setVisible(false);
                        this.dispose();
                    }
                }
            }
        }    
    }
    
    
    
    class Cronometro extends Thread{
        
        private final int tempo;
        
        public Cronometro(int tempo){
            this.tempo = tempo;
            start();
        }
        
        @Override
        public void run(){
            for(int i = 0; i < tempo; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(menuJogar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            mensagem.setText("");
            mensagem.setForeground(Color.WHITE);
            nome.setBorder(new LineBorder(Color.black));
        }
    }
}
