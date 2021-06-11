package Ranking;

import java.io.*;
import java.util.TreeMap;

public class Empacotamento {
    
    public static void save(TreeMap<Double, String> player, String fileName) {
        File file = new File(fileName);
        
        try{
            file.delete();
            file.createNewFile();
            
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(file));
            objOutput.writeObject(player);
            objOutput.close();
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo");
        }
        
    }
    
    public static TreeMap<Double, String> load(String fileName) {
        TreeMap<Double, String> player = new TreeMap<>();
        
        try {
            File file = new File(fileName);
            
            if(file.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(file));
                player = (TreeMap<Double, String>) objInput.readObject();
                objInput.close();
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR");
        }
        return (player);
    }
}
