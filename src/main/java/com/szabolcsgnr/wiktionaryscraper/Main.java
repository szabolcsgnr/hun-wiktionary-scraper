package com.szabolcsgnr.wiktionaryscraper;

import org.jsoup.internal.StringUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.String.format;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        PageRetriever pageRetriever = new PageRetriever();
        PageParser pageParser = new PageParser();

        log.info("Retrieving first page");
        String page = pageRetriever.loadPage("https://hu.wiktionary.org/w/index.php?title=Kateg%C3%B3ria:magyar_hat%C3%A1roz%C3%B3sz%C3%B3k");
        int i = 0;
        List<String> words = new LinkedList<>();
        while (true) {
            i++;
            log.info(format("Parsing page %d", i));
            PageObject pageObject = pageParser.parse(page);
            log.info(format("Parsing page %d : found %d words", i, pageObject.getWords().size()));
            words.addAll(pageObject.getWords());
            if (StringUtil.isBlank(pageObject.getNextPageUrl())) {
                break;
            } else {
                log.info(format("Found next page %s", pageObject.getNextPageUrl()));
                page = pageRetriever.loadPage("https://hu.wiktionary.org" + pageObject.getNextPageUrl());
            }
        }

        log.info(format("Scraping pages done... Found %d words", words.size()));

        PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));
        words.forEach(word -> pw.write(word + "\n"));

        pw.close();
    }
}
