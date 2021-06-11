/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerranaval;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class menuSair extends JFrame implements ActionListener{
    
    private Container tela = getContentPane();
    private int modo;
    private String nomeJogador;
    private int dificuldade;
    
    public menuSair(int modo, String nomeJogador, int dificuldade ){
        super("Guerra Naval");
        tela.setLayout(new FlowLayout(1, 15, 25));
        
        this.modo = modo;
        this.nomeJogador = nomeJogador;
        this.dificuldade = dificuldade;
        colocarBotoes();
        
        setSize(300, 100);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void colocarBotoes(){
        JButton reiniciar = new JButton("Reiniciar Jogo");
        JButton alterar = new JButton("Alterar Jogo");
        
        reiniciar.setActionCommand("reiniciar");
        reiniciar.addActionListener(this);
        reiniciar.setBackground(Color.WHITE);
        
        alterar.setActionCommand("alterar");
        alterar.addActionListener(this);
        alterar.setBackground(Color.WHITE);
        
        tela.add(reiniciar);
        tela.add(alterar);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if("reiniciar".equals(comando)){
            GuerraNaval telaprincipal = new GuerraNaval(modo, nomeJogador, dificuldade);
            telaprincipal.setVisible(true);
            telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            this.tela.setVisible(false);
            this.dispose();
        }else{
            menuJogar telaprincipal = new menuJogar();
            telaprincipal.setVisible(true);
            telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            tela.setVisible(false);
            this.dispose();
        }
    }
    
    
}
