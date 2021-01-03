package longestcommonsubsequence;

import java.io.File;

public class PathReader {

    public static String[] read(String pathName) {
        final File folder = new File(pathName);
        File[] fileList = folder.listFiles();
        String[] fileNames = new String[fileList.length];
        for (int i = 0; i < fileList.length; i++) {
            fileNames[i] = fileList[i].getName();
        }
      
        return fileNames;
    }
}

