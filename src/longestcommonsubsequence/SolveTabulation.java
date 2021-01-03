package longestcommonsubsequence;

public class SolveTabulation {
    public static String solveTabulation(String[] sequences, int xlength, int ylength) {
        String xsequence = sequences[0];
        String ysequence = sequences[1];
        String taken = "";

        // Matriz de valores
        int matrix[][] = new int[ylength + 1][xlength + 1];
        
        //Fase 1 del algoritmo: Rellenamos la tabla
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0;
                } else if (ysequence.charAt(i - 1) == xsequence.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        
        //Fase 2 del algoritmo: Identificamos valores elegidos
        int i = ylength;
        int j = xlength;
        while(i > 0 && j > 0) {
            if (ysequence.charAt(i - 1) == xsequence.charAt(j - 1)) {
                taken += ysequence.charAt(i - 1);
                i -= 1;
                j -= 1;
            } else if (matrix[i - 1][j] > matrix[i][j - 1]) {
                i -= 1;
            } else {
                j -= 1;
            }
        }
        
        int lcs_length = matrix[ylength][xlength];
        
        return taken;
    }
}