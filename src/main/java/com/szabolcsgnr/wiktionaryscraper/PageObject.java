package com.szabolcsgnr.wiktionaryscraper;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PageObject {
    private final String nextPageUrl;
    private final List<String> words;
}
