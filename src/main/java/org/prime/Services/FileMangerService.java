package org.prime.Services;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Golam Rahman Tushar on 5/29/2017.
 */
public final class FileMangerService {
    public static ArrayList<String> readList(String sourceFile, int startFrom, int numberOfLines) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                if (counter < startFrom) {
                    counter++;
                } else {
                    if (counter == startFrom + numberOfLines) {
                        break;
                    }
                    line = line.trim();
                    if (line.equals("")) {
                        continue;
                    }
                    lines.add(line);
                    counter++;
                }
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            System.err.format("[Error]Failed to open file %s!", sourceFile);
            return null;
        }
    }
    public static void writeString(String destination, String content) throws IOException {
        File f = new File(destination);
        if (f.exists()) {
            f.delete();
        }
        FileWriter writer = new FileWriter(f, true);
        writer.write(content);
        writer.flush();
        writer.close();
    }
}
