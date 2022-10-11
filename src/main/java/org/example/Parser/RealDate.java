package org.example.Parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RealDate {
    private Document page;
    private String realDate = page.select("span[class=top-bar__text]").text();
    private Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");
    public RealDate(Document page) {
        this.page = page;
    }

    private String getRealDate() {
        Matcher matcher = pattern.matcher(realDate);
        return matcher.group();
    }
}
