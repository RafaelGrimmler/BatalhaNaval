/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerranaval;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GuerraNaval extends JFrame implements ActionListener{
    //BOTÕES PRINCIPAIS
    JButton sair, dica, disparoUnico, cascataHorizontal, cascataVertical, tiroEstrela;
    JPanel painelBotoesCima, painelMapas, painelMapaEsquerda, painelMapaDireita;
    Container tela;
    
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
        painelMapaEsquerda = new JPanel();
        painelMapaDireita = new JPanel();
        
        painelMapas.setLayout(new GridLayout(0, 2));
        painelMapaEsquerda.setBackground(Color.red);
        painelMapaDireita.setBackground(Color.BLUE);
        
        painelMapas.add(painelMapaEsquerda);
        painelMapas.add(painelMapaDireita);
              
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
        tela.add(painelBotoesCima, BorderLayout.NORTH);
        tela.add(painelMapas);
        
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);  
    }
    
    public static void main(String[] args) {
        menuPrincipal guiMenu = new menuPrincipal();
        guiMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if( comando.contains("P.") ){
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
            //-------------------------------
            if( "P.sair".equals(comando) ){
                System.exit(0);          
            }else if( "P.dica".equals(comando) ){
                dica.setBackground(Color.DARK_GRAY);
                dica.setForeground(Color.WHITE);
            }else if( "P.disparounico".equals(comando) ){
                disparoUnico.setBackground(Color.DARK_GRAY);
                disparoUnico.setForeground(Color.WHITE);
            }else if( "P.cascatahorizontal".equals(comando) ){
                cascataHorizontal.setBackground(Color.DARK_GRAY);
                cascataHorizontal.setForeground(Color.WHITE);
            }else if( "P.cascatavertical".equals(comando) ){
                cascataVertical.setBackground(Color.DARK_GRAY);
                cascataVertical.setForeground(Color.WHITE);
            }else if( "P.tiroestrela".equals(comando) ){
                tiroEstrela.setBackground(Color.DARK_GRAY);
                tiroEstrela.setForeground(Color.WHITE);
            }
        }
    }
    
}
