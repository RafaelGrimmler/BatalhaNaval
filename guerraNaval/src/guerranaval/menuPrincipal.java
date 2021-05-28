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

public class menuPrincipal extends JFrame implements ActionListener{
    
    public menuPrincipal(){
        super("Guerra Naval");
        Container tela = getContentPane();
        
        JPanel painelBotoesMenu = new JPanel();
        JPanel painelCima = new JPanel();
        JPanel painelBaixo = new JPanel();
        
        painelBotoesMenu.setLayout(new GridLayout(2, 0));
        painelBaixo.setLayout(new GridLayout(3, 0));
        
        painelCima.setLayout(new BorderLayout());
        painelCima.add(new imagemMenu());
        
        painelBotoesMenu.add(painelCima);
        painelBotoesMenu.add(painelBaixo);
        
        ImageIcon img = new ImageIcon("barco.png");
        
        JButton jogar = new JButton(img);
        JButton ranking = new JButton("Ranking");
        ranking.setBackground(Color.WHITE);
        ranking.setBorderPainted(false);
        JButton sair = new JButton("Sair");
        sair.setBackground(Color.WHITE);
        sair.setBorderPainted(false);
        
        jogar.setActionCommand("jogar");
        ranking.setActionCommand("ranking");
        sair.setActionCommand("sair");
        
        jogar.addActionListener(this);
        ranking.addActionListener(this);
        sair.addActionListener(this);
        
        painelBaixo.add(jogar);
        painelBaixo.add(ranking);
        painelBaixo.add(sair);
        tela.add(painelBotoesMenu);
        
        
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);  
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        if( "jogar".equals(comando) ){
            GuerraNaval telaprincipal = new GuerraNaval();
            telaprincipal.setVisible(true);
            dispose();
            telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        }
    }
    
}
