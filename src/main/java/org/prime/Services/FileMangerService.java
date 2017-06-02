package org.prime.Services;

import org.prime.DTOs.pageDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public static ArrayList<pageDTO> read(String sourceFile) throws FileNotFoundException {
        ArrayList<pageDTO> ret = new ArrayList<pageDTO>();
        JSONParser parser = new JSONParser();
        JSONArray arr = null;

        try {
            arr = (JSONArray) parser.parse(new FileReader(sourceFile));
        } catch (IOException ex) {
            System.out.println(ex);
//            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            System.out.println(ex);
//            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(Object ob : arr) {
            JSONObject jsonObject = (JSONObject) ob;

            String url = (String) jsonObject.get("url");
            String title = (String) jsonObject.get("title");
            HashMap<String, ArrayList<String>> content = (HashMap<String, ArrayList<String>>) jsonObject.get("content");

            ret.add(new pageDTO(url, title, content));
        }

        return ret;
    }
}
