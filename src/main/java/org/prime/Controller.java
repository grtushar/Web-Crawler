package org.prime;

import org.prime.Configuration.Configs;
import org.prime.Services.CrawlerService;
import org.prime.Services.FileMangerService;

import java.io.IOException;
import java.util.*;

/**
 * Created by Golam Rahman Tushar on 5/29/2017.
 */
public class Controller {
    public static void main(String[] args) {
        CrawlerService crawlerService = new CrawlerService();
        ArrayList<String> urls = FileMangerService.readList(
                Configs.inputFile,
                0,
                -1);


        int noOfUrls = urls.size();
        String output = "[";

        for(int i = 0; i < noOfUrls; i++) {
            String url = urls.get(i);
            String jsonData = crawlerService.getJsonDataFromUrl(url);
            if(i > 0) output += ",";
            output += "\n" + jsonData;
        }

        output += "\n" + "]";

        try {
            FileMangerService.writeString(Configs.outputFile, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
