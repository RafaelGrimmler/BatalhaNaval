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
    public Container tela = getContentPane();
    public JTextField nome;
    public JButton jogoAleatorio, definirJogo, menu, sair;
    public JPanel []painelaux1 = new JPanel[2];
    public JLabel mensagem;
    private String nomeJogador;
    private int opcaoJogo;
        
    public menuJogar(){
        super("Guerra Naval");
        tela.setLayout(new GridLayout(2, 0));
        
        paineis();
               
        setSize(400, 200);
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
        
        // ADD BOTOES
        paineis[1] = new JPanel(new FlowLayout(1,5,25));
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
        
        paineis[1].add(jogoAleatorio);
        paineis[1].add(definirJogo);
        paineis[1].add(menu);
        paineis[1].add(sair);
        //
       
        tela.add(paineis[0]);
        tela.add(paineis[1]);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        nomeJogador = nome.getText();
        System.out.println(nomeJogador + " ");
        
        String comando = e.getActionCommand();
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
                        GuerraNaval telaprincipal = new GuerraNaval(0, nomeJogador);
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
                        GuerraNaval telaprincipal = new GuerraNaval(1, nomeJogador);
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
