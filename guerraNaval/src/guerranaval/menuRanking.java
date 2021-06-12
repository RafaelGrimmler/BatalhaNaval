/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guerranaval;

import Ranking.Empacotamento;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;
import java.text.DecimalFormat;

public class menuRanking extends JFrame implements ActionListener{

    private Container tela = getContentPane();
    private JPanel painel;
    private JPanel []paineisDentro = new JPanel[16];
    private TreeMap<Double, String> player;
    
    public menuRanking(){
        super("Guerra Naval");
        
        painel = new JPanel(new GridLayout(17, 0));
        
        adicionaPrimeiroPainel();
        adicionaPaineis();
        adicionaNomes();
        adicionaBotoes();
        
        
        tela.add(painel);
        setSize(500, 650);
        setVisible(true);
        setLocationRelativeTo(null);
    }   
    
    public void adicionaBotoes(){
        JButton voltar = new JButton("Voltar");
        JButton Sair = new JButton("Sair");
        
        voltar.setBackground(Color.WHITE);
        Sair.setBackground(Color.WHITE);
        
        voltar.setActionCommand("voltar");
        Sair.setActionCommand("Sair");
        
        voltar.addActionListener(this);
        Sair.addActionListener(this);
        
        paineisDentro[15].add(voltar);
        paineisDentro[15].add(Sair);
        painel.add(paineisDentro[15]);
    }
    
    public void adicionaPrimeiroPainel(){
         JPanel primeiroPainel = new JPanel(new GridLayout(0, 4));
        JLabel id = new JLabel("     Top");
        JLabel nome = new JLabel("Nome");
        JLabel tempo = new JLabel("Tempo");
        JLabel difi = new JLabel("Dificuldade");
        primeiroPainel.add(id);
        primeiroPainel.add(nome);
        primeiroPainel.add(tempo);
        primeiroPainel.add(difi);
        painel.add(primeiroPainel);
    }
    
    public void adicionaNomes(){
        JLabel []id = new JLabel[15];
        JLabel []nome = new JLabel[15];
        JLabel []tempo = new JLabel[15];
        JLabel []dificuldade = new JLabel[15];
        DecimalFormat decimal = new DecimalFormat("0,00");
        double key;
        String name, dif;
        int contador = 1;
        player = Empacotamento.load("ranking.dat");
        ArrayList <Double> tempos = new ArrayList<Double>();
        ArrayList <String> nomes = new ArrayList<String>();
        for (Map.Entry mapElement : player.entrySet()) {
            key = (double)mapElement.getKey();
            name = (String)mapElement.getValue();
            tempos.add(key);
            nomes.add(name);
        }
        for(int i =0; i < 15; i++){
            id[i] = new JLabel("     " + contador++);
            if(tempos.size() > 0){
                name = nomes.get(0).substring(0, nomes.get(0).indexOf("$"));
                dif = nomes.get(0).substring(nomes.get(0).indexOf("$")+1);
                nome[i] = new JLabel(name);
                dificuldade[i] = new JLabel(dif);
                tempo[i] = new JLabel(decimal.format(tempos.get(0) * 100) + "");
                tempos.remove(0);
                nomes.remove(0);
            }else{
                nome[i] = new JLabel("--/--");
                dificuldade[i] = new JLabel("--/--");
                tempo[i] = new JLabel("--/--");
            }
                
            // ADICIONAR NO PAINEL
            paineisDentro[i].add(id[i]);
            paineisDentro[i].add(nome[i]);
            paineisDentro[i].add(tempo[i]);
            paineisDentro[i].add(dificuldade[i]);
            painel.add(paineisDentro[i]);
        }
    }
    
    public void adicionaPaineis(){
        for(int i =0; i < 15; i++){
            paineisDentro[i] = new JPanel(new GridLayout(0, 4));
        }
        paineisDentro[15] = new JPanel(new FlowLayout(1, 50, 1));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if("voltar".equals(comando)){
            menuPrincipal telaprincipal = new menuPrincipal();
            telaprincipal.setVisible(true);
            telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            this.tela.setVisible(false);
            this.dispose();
        }else
            System.exit(0);
    }
}
