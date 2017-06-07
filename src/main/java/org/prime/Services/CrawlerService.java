package org.prime.Services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.prime.Utility;

import java.io.IOException;
import java.util.*;

/**
 * Created by Golam Rahman Tushar on 5/29/2017.
 */
public class CrawlerService {

    public String getJsonDataFromUrl(String url) {
        String ret = "";
        Document doc = null;
        
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Elements elements = doc.body().select("*");

        HashMap<String, ArrayList<String>> tag_text = new HashMap<String, ArrayList<String>>();

        for (Element element : elements) {
            String tagName = Utility.getRidOfSpecialChar(element.tagName());
            if( Utility.isValidTag(tagName)) {
                String textValue = Utility.removeNonPrintableChars(element.ownText()).trim();
                textValue = Utility.getRidOfSpecialChar(textValue);
                if(!textValue.equals("")) {
                    if(tag_text.containsKey(tagName)) {
                        tag_text.get(tagName).add(textValue);
                    } else {
                        ArrayList<String> arr = new ArrayList<String>();
                        arr.add(textValue);
                        tag_text.put(tagName, arr);
                    }
                }
            }
        }

        String content = "{";

        Set set = tag_text.entrySet();
        Iterator i = set.iterator();

        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            if(content != "{") content += ",\n";
            content += "\"" + me.getKey() + "\": "; // + me.getValue();

            ArrayList<String> arr = (ArrayList<String>) me.getValue();
            content += "[";
            for(String str: arr) {
                content += "\"" + str + "\",";
            }
            content = content.substring(0,content.length() - 1);
            content += "]";
        }

        content += "\n}";

//        content = removeSpecialCharacters(content);
        content = Utility.removeNonPrintableChars(content);
        content = Utility.removeSpaces(content);

        String titleName = Utility.getRidOfSpecialChar(doc.title());
        ret += "{ \"url\" : \"" + url + "\",\n";
        ret += " \"title\" : \"" + titleName + "\",\n";
        ret += "\"content\" : " + content;
        ret += "\n}";
        ret = ret.trim();
        return ret;
    }
}
