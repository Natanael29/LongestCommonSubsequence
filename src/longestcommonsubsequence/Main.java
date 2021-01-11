package longestcommonsubsequence;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.MutuallyExclusiveGroup;
import net.sourceforge.argparse4j.inf.Namespace;

public class Main {

    public static void main(String[] args) {
        Namespace arguments = parseArguments(args);
        if (arguments != null) {
            if (arguments.getString("file") != null) {
                FileReader reader = new FileReader();
                String fileName = arguments.getString("file");
                System.out.print(fileName + "\t");
                String[] sequences = reader.read(fileName);
                processArguments(arguments, sequences, reader.getXlength(), reader.getYlength());
            } else if (arguments.getString("directory") != null) {
                String pathName = arguments.getString("directory");
                String[] fileNames = PathReader.read(arguments.get("directory"));
                for (int i = 0; i < fileNames.length; i++) {
                    System.out.print(pathName + "/" + fileNames[i] + "\t");
                    FileReader reader = new FileReader();
                    String[] sequences = reader.read(pathName + "/" + fileNames[i]);
                    processArguments(arguments, sequences, reader.getXlength(), reader.getYlength());
                }
            }
        }
    }
    
    private static void processArguments(Namespace arguments, String[] sequences, int xlength, int ylength) {
        if (arguments.getBoolean("check") || arguments.getBoolean("tabulation") || arguments.getBoolean("memoization")) {
            long timeStart = 0;
            if (arguments.getBoolean("time")) {
                timeStart = System.currentTimeMillis();
            }
            
            String result = null;
            if (arguments.getBoolean("tabulation")) {
                result = SolveTabulation.solveTabulation(sequences, xlength, ylength);
            } else if (arguments.getBoolean("memoization")) {
                result = SolveMemoization.solveMemoization(sequences, xlength, ylength);
            } else if (arguments.getBoolean("check")) {
                String res1 = SolveTabulation.solveTabulation(sequences, xlength, ylength);
                String res2 = SolveMemoization.solveMemoization(sequences, xlength, ylength);
                if (res1.equals(res2)) {
                    System.out.println("Los resultados calculados por Memoization y Tabulation son iguales");
                    System.out.println("Memoization: " + res2);
                    System.out.println("Tabulation: " + res1);
                } else {
                    System.out.println("Los resultados calculados por Memoization y Tabulation son distintos");
                    System.out.println("Memoization: " + res2);
                    System.out.println("Tabulation: " + res1);
                }
            }
            
            String message = "";
            if (arguments.getBoolean("length")) {
                message += result.length() + "\t";
            }
            
            if (arguments.getBoolean("time")) {
                message += (System.currentTimeMillis()-timeStart) + "ms" + "\t";
            }
            
            if (arguments.getBoolean("display_taken")) {
                message += "[" + result + "]" + "\t";
            }
            System.out.println(message);
        }
    }
    
    private static Namespace parseArguments(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("Main").build();
        MutuallyExclusiveGroup group = parser.addMutuallyExclusiveGroup();
        group.addArgument("-d", "--directory")
                .nargs("?")
                .help("directory (process many files)")
                .action(Arguments.store());
        group.addArgument("-f", "--file")
                .nargs("?")
                .type(Arguments.fileType().acceptSystemIn().verifyCanRead())
                .help("file (process a single file)")
                .action(Arguments.store());
        parser.addArgument("-l", "--length")
                .help("Display length of LCS")
                .action(Arguments.storeTrue());
        parser.addArgument("-t", "--time")
                .help("Display time")
                .action(Arguments.storeTrue());
        parser.addArgument("-dt", "--display_taken")
                .help("Display taken characters")
                .action(Arguments.storeTrue());
        parser.addArgument("-c", "--check")
                .help("Check both results(Memoization and Tabulation) are the same")
                .action(Arguments.storeTrue());
        parser.addArgument("-sm", "--memoization")
                .help("Solve it with Memoization")
                .action(Arguments.storeTrue());
        parser.addArgument("-st", "--tabulation")
                .help("Solve it with Tabulation")
                .action(Arguments.storeTrue());
        
        try {
            return parser.parseArgs(args);
        } catch (ArgumentParserException ex) {
            System.out.println("ArgumentParserException:: " + ex.getMessage());
        }
        return null;
    }
}
