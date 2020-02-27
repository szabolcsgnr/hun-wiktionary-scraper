package com.szabolcsgnr.wiktionaryscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageParser {
    public PageObject parse(String html) {
        Document document = Jsoup.parse(html);
        Elements categories = document.select("#mw-pages .mw-category-group ul");
        List<String> values = categories.stream().flatMap(this::valuesForCategory).collect(Collectors.toList());
        String nextPageUrl = getNextPageUrl(document);
        return new PageObject(nextPageUrl, values);
    }

    private String getNextPageUrl(Document document) {
        return document.getElementsMatchingOwnText("következő oldal").attr("href");
    }

    private Stream<String> valuesForCategory(Element category) {
        return category.getAllElements().select("li").stream().map(element -> {
            Element linkElement = element.getAllElements().get(1);
            Node title = linkElement.childNode(0);
            return title.toString();
        });
    }
}
