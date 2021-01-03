package longestcommonsubsequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    int xlength;
    int ylength;
    
    public String[] read(String fileName) {
        String[] sequences = new String[2];
        String[] elements;
        xlength = 0;
        ylength = 0;
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(new File(fileName)));
            String firstLine = reader.readLine();
            if (firstLine != null) {
                elements = firstLine.split(" ");
                xlength = Integer.parseInt(elements[0]);
                ylength = Integer.parseInt(elements[1]);
                String secondLine = reader.readLine();
                sequences = secondLine.split(" ");
                if (sequences[0].length() != xlength || sequences[1].length() != ylength) {
                    System.out.println("Las longitudes de las strings no se corresponden con los parametros del fichero");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error ItemReader::read (FileNotFound) " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error ItemReader::read (IO) " + ex.getMessage());
        }
        
        return sequences;
    }

    public int getXlength() {
        return xlength;
    }

    public int getYlength() {
        return ylength;
    }
}
