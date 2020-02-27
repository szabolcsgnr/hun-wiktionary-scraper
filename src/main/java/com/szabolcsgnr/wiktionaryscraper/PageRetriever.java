package com.szabolcsgnr.wiktionaryscraper;

import java.io.IOException;
import java.net.URL;

public class PageRetriever {
    public String loadPage(String urlString) {
        try {
            URL url = new URL(urlString);
            return new String(url.openStream().readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
