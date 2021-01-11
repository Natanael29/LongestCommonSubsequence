package longestcommonsubsequence;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LCSTest {
    private String [] sequences;
    private String result1;
    
    private String[] sequences1;
    private String result2;
    
    public LCSTest() {
    }

    @Before
    public void setUp() {
        sequences = new String[2];
        sequences[0] = "AGGTAB";
        sequences[1] = "GXTXAYB";
        result1 = "BATG";
        
        sequences1 = new String[2];
        sequences1[0] = "ABCDGH";
        sequences1[1] = "AEDFHR";
        result2 = "HDA";
    }
    
    @Test
    public void testTabulation1() {
        System.out.println("Test: Tabulation 1");
        
        String result = SolveTabulation.solveTabulation(sequences, sequences[0].length(), sequences[1].length());
        
        assertEquals("Resultado incorrecto: Las cadenas no coinciden", result1, result);
    }
    
    @Test
    public void testTabulation2() {
        System.out.println("Test: Tabulation 2");
        
        String result = SolveTabulation.solveTabulation(sequences1, sequences1[0].length(), sequences1[1].length());
        
        assertEquals("Resultado incorrecto: Las cadenas no coinciden", result2, result);
    }
    
    @Test
    public void testMemoization1() {
        System.out.println("Test: Memoization 1");
        
        String result = SolveMemoization.solveMemoization(sequences, sequences[0].length(), sequences[1].length());
        
        assertEquals("Resultado incorrecto: Las cadenas no coinciden", result1, result);
    }
    
    @Test
    public void testMemoization2() {
        System.out.println("Test: Memoization 2");
        
        String result = SolveMemoization.solveMemoization(sequences1, sequences1[0].length(), sequences1[1].length());
        
        assertEquals("Resultado incorrecto: Las cadenas no coinciden", result2, result);
    }
}