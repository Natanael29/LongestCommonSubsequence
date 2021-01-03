package longestcommonsubsequence;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LCSTest {
    private String [] sequences;
    private String result1;
    
    public LCSTest() {
    }

    @Before
    public void setUp() {
        sequences = new String[2];
        sequences[0] = "AGGTAB";
        sequences[1] = "GXTXAYB";
        result1 = "BATG";
    }
    
    @Test
    public void testTabulation() {
        System.out.println("Test: Tabulation");
        
        String result = SolveTabulation.solveTabulation(sequences, sequences[0].length(), sequences[1].length());
        
        assertEquals("Resultado incorrecto: Las cadenas no coinciden", result1, result);
    }
    
    @Test
    public void testMemoization() {
        System.out.println("Test: Memoization");
        
        String result = SolveMemoization.solveMemoization(sequences, sequences[0].length(), sequences[1].length());
        
        assertEquals("Resultado incorrecto: Las cadenas no coinciden", result1, result);
    }
}