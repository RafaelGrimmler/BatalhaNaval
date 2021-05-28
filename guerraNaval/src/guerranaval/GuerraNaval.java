/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerranaval;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GuerraNaval extends JFrame implements ActionListener{
    
    public GuerraNaval(){
        super("Guerra Naval");
        // Inicializa tela
        iniciarTelaPrincipal();
    }
    
    public void iniciarTelaPrincipal(){
        Container tela = getContentPane();
        tela.setLayout(new BorderLayout(8, 6));
        
        JPanel painelBotoesCima = new JPanel(); // CRIAÇÃO DO PAINEL DE CIMA
        painelBotoesCima.setLayout(new FlowLayout());
        
        JButton sair = new JButton("Sair");
        JButton dica = new JButton("Dica");
        JButton disparoUnico = new JButton("Disparo Unico");
        JButton cascataHorizontal = new JButton("Cascata Horizontal");
        JButton cascataVertical = new JButton("Cascata Vertical");
        JButton tiroEstrela = new JButton("Tiro Estrela");
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
            System.out.println("Aloo");
        }
    }
    
}
