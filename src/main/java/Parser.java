import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {
    private static Document getPage() throws IOException {
        String url = "http://www.pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    public static void main(String[] args) throws IOException {
        Document page = getPage();
        //css query language
        Element tableWeather = page.select("table[class = wt]").first();
        Elements names = tableWeather.select("tr[class = wth]");
        Elements values = tableWeather.select("tr[valign =top]");
        String date = "";
        System.out.println("        Weather event       Tempreature         Preasure        Humidity        Wind");
    }
}