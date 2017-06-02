package org.prime.DTOs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Golam Rahman Tushar on 6/2/2017.
 */
public class pageDTO {
    private String url, title;
    private HashMap<String, ArrayList<String>> content;

    public pageDTO(String url, String title, HashMap<String, ArrayList<String>> content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, ArrayList<String>> getContent() {
        return content;
    }

    public void setContent(HashMap<String, ArrayList<String>> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "pageDTO{" + "url=" + url + ", title=" + title + ", content=" + content + '}';
    }
}
