package com.szabolcsgnr.wiktionaryscraper;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class PageParserTest {
    @Test
    public void parse() throws IOException {
        String html = new String(getClass().getClassLoader().getResourceAsStream("test.htm").readAllBytes());

        PageParser pageParser = new PageParser();
        PageObject pageObject = pageParser.parse(html);

        assertThat(pageObject.getNextPageUrl()).endsWith("https://hu.wiktionary.org/w/index.php?title=Kateg%C3%B3ria:magyar_hat%C3%A1roz%C3%B3sz%C3%B3k&pagefrom=b%C3%A1rmikorra#mw-pages");
        assertThat(pageObject.getWords()).hasSize(200);
    }
}