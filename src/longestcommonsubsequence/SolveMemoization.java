package longestcommonsubsequence;

import java.util.HashMap;
import java.util.Map;

public class SolveMemoization {
    public static String solveMemoization(String[] sequences, int xlength, int ylength) {
        String xsequence = sequences[0];
        String ysequence = sequences[1];
        String taken = "";

        Map<String, Integer> mem = new HashMap<>();
        
        int lcs_length = auxSolveMemoization(xsequence, ysequence, xlength, ylength, mem);
        
        //Fase 2 del algoritmo: Identificamos valores elegidos
        int i = ylength - 1;
        int j = xlength - 1;
        while(i >= 0 && j >= 0) {
            String key1 = getKey(i - 1, j);
            String key2 = getKey(i, j - 1);
            
            if (ysequence.charAt(i) == xsequence.charAt(j)) {
                taken += ysequence.charAt(i);
                i -= 1;
                j -= 1;
            } else {
                if (!mem.containsKey(key2) && mem.containsKey(key1)) {
                    i -= 1;
                } else if (!mem.containsKey(key1) && mem.containsKey(key2)) {
                    j -= 1;
                } else if (mem.get(key1) > mem.get(key2)) {
                    i -= 1;
                } else {
                    j -= 1;
                }
            }
        }
        return taken;
    }
    
    private static int auxSolveMemoization(String xsequence, String ysequence, int s1Length, int s2Length, Map<String, Integer> mem) {
        String key = getKey(s2Length - 1, s1Length - 1);
        
        if (!mem.containsKey(key)) {
            if (s1Length == 0 || s2Length == 0) {
                mem.put(key, 0);
                return 0;
            }
            
            if (xsequence.charAt(s1Length - 1) == ysequence.charAt(s2Length - 1)) {
                mem.put(key, 1 + auxSolveMemoization(xsequence, ysequence, s1Length - 1, s2Length - 1, mem));
            } else {
                mem.put(key, Math.max(
                        auxSolveMemoization(xsequence, ysequence, s1Length, s2Length - 1, mem),
                        auxSolveMemoization(xsequence, ysequence, s1Length - 1, s2Length, mem)));
            }
        }
        return mem.get(key);
    }
    
    private static String getKey(int m, int n) {
        return m + "-" + n;
    }
}
