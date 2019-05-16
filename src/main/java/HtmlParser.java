import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlParser
{
    //Удаление html тэгов
    public static  String deleteTags (String html)
    {
        return Jsoup.parse(html).text();
    }
    public static List<Item> parseHtml(String url) throws IOException {
        String tempString;
        List<Item> records = new ArrayList<Item>(); // Наши товары
        Document doc = Jsoup.connect(url).get(); // Получаем нашу страничку
        String mainUrl = "https://www.scalemates.com";
        Elements mainElements = doc.getElementsByClass("ac"); //Получаем элемнты класса "ac"
        for (Element element : mainElements) {
            String scalematesUrl = element.child(0).attr("href").trim(); // Url
            tempString = (element.child(1).select("a").text() + element.child(1)
                    .select("em").text()).trim();
            String name = deleteTags(tempString).replace("\"", "'"); // Название
            String textOfChild = element.child(1).html();
            tempString = textOfChild.substring(textOfChild.lastIndexOf("<br>") + 4, textOfChild
                    .lastIndexOf("<div")).trim();
            String brandCatno = deleteTags(tempString);
            String brand = "";
            String scale = "";
            tempString = textOfChild.substring(textOfChild.indexOf("<br>") + 4 //получение строки с брэндом и масштабом
                    , textOfChild.lastIndexOf("<br>"));
            if (tempString.contains(":")) { //проверка на наличие масштаба
                String[] brandAndScale = tempString.split("\\s+\\d:");
                brand = deleteTags(brandAndScale[0].trim());
                scale = deleteTags(brandAndScale[1].trim());
            } else {
                brand = deleteTags(tempString.trim());
            }
            String boxartUrl = element.child(0).select("img").attr("src");
            boxartUrl = mainUrl + boxartUrl.substring(0, boxartUrl.lastIndexOf("-")).trim();
            String description = "";
            String year = "";
            String divText = element.child(1).select("div").last().html();
            if (divText.contains("|")) {
                String[] descriptionAndYear = divText.split("\\D?\\s+\\|\\s+");
                year = deleteTags(descriptionAndYear[0].trim());
                description = deleteTags(descriptionAndYear[1].trim());

            } else {
                year = deleteTags(divText.replaceAll("\\D+", "").trim());
            }
            records.add(new Item(scalematesUrl, brand, brandCatno, name, scale, description, boxartUrl, year));
        }
        return records;
    }
    public static List<Item> parseHtml(File file) throws IOException {
        String tempString;
        List<Item> records = new ArrayList<Item>(); // Наши товары
        Document doc = Jsoup.parse(file,"UTF-8"); // Получаем нашу страничку
        String mainUrl = "https://www.scalemates.com";
        Elements mainElements = doc.getElementsByClass("ac"); //Получаем элемнты класса "ac"
        for (Element element : mainElements) {
            String scalematesUrl = element.child(0).attr("href").trim(); // Url
            tempString = (element.child(1).select("a").text() + element.child(1)
                    .select("em").text()).trim();
            String name = deleteTags(tempString).replace("\"", "'"); // Название
            String textOfChild = element.child(1).html();
            tempString = textOfChild.substring(textOfChild.lastIndexOf("<br>") + 4, textOfChild
                    .lastIndexOf("<div")).trim();
            String brandCatno = deleteTags(tempString);
            String brand = "";
            String scale = "";
            tempString = textOfChild.substring(textOfChild.indexOf("<br>") + 4 //получение строки с брэндом и масштабом
                    , textOfChild.lastIndexOf("<br>"));
            if (tempString.contains(":")) { //проверка на наличие масштаба
                String[] brandAndScale = tempString.split("\\s+\\d:");
                brand = deleteTags(brandAndScale[0].trim());
                scale = deleteTags(brandAndScale[1].trim());
            } else {
                brand = deleteTags(tempString.trim());
            }
            String boxartUrl = element.child(0).select("img").attr("src");
            boxartUrl = mainUrl + boxartUrl.substring(0, boxartUrl.lastIndexOf("-")).trim();
            String description = "";
            String year = "";
            String divText = element.child(1).select("div").last().html();
            if (divText.contains("|")) {
                String[] descriptionAndYear = divText.split("\\D?\\s+\\|\\s+");
                year = deleteTags(descriptionAndYear[0].trim());
                description = deleteTags(descriptionAndYear[1].trim());

            } else {
                year = deleteTags(divText.replaceAll("\\D+", "").trim());
            }
            records.add(new Item(scalematesUrl, brand, brandCatno, name, scale, description, boxartUrl, year));
        }
        return records;
    }
}
