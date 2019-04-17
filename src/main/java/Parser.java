import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Document getPage() throws IOException {
        String url = "http://www.pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()){
            return matcher.group();
        }
        throw new Exception("Can't extract date from string!");
    }

    public static void main(String[] args) throws Exception {
        Document page = getPage();
        //css query language
        Element tableWeather = page.select("table[class = wt]").first();
        Elements names = tableWeather.select("tr[class = wth]");
        Elements values = tableWeather.select("tr[valign =top]");

        for (Element name : names) {
            String dateString = name.select("th[id = dt]").text();
            String date = getDateFromString(dateString);
            System.out.println(date + "        Weather event       Tempreature         Preasure        Humidity        Wind");
        }

    }
}