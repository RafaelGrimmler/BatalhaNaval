package Ranking;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class Ranking {
    private static final int MAX = 15;
    
    public static void addPlayer(double time, String name, TreeMap<Double, String> player) {
        player.put(time, name);
    }
    
    public static void printPlayers(TreeMap<Double, String> player) {
        int i = 1;
        double key;
        String name;
        
        for (Map.Entry mapElement : player.entrySet()) {
            key = (double)mapElement.getKey();
            name = (String)mapElement.getValue();
            System.out.println(String.valueOf(i) + ".\t[Nome: " + name + "]\n\t[Tempo: " + key + "]\n");
            i++;
        }
    }
    
    public static void listOfPlayers(TreeMap<Double, String> player) {
        ArrayList<Double> saveKey = new ArrayList<>();
        int i = 1;
        double key;
        
        for (Map.Entry mapElement : player.entrySet()) {
            key = (double)mapElement.getKey();
            if (i > MAX) {
                saveKey.add(key);
            }
            i++;
        }
        
        for (i = 0; i < saveKey.size(); i++) {
            player.remove(saveKey.get(i));
        }
    }
}
