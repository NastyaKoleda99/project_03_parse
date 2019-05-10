import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlParser
{
    public static List<Item> parseHtml(String url) throws IOException
    {
        List<Item> records = new ArrayList<Item>(); // Наши товары
        Document doc = Jsoup.connect(url).get(); // Получаем нашу страничку
        String mainUrl ="https://www.scalemates.com";
        Elements mainElements = doc.getElementsByClass("ac"); //Получаем элемнты класса "ac"
        for(Element element: mainElements)
        {
            String scalematesUrl = element.child(0).attr("href").trim(); // Url
            String name = (element.child(1).select("a").text() + element.child(1)
                    .select("em").text()).trim(); // Название
            String textOfChild = element.child(1).html();
            String brandCatno =textOfChild.substring(textOfChild.lastIndexOf("<br>")+4,textOfChild
                    .lastIndexOf("<div")).trim();
            String[] brandAndScale = textOfChild.substring(textOfChild.indexOf("<br>")+4,textOfChild
                    .lastIndexOf("<br>")).split("\\s+\\d:");
            String brand = brandAndScale[0].trim() ;
            String scale = "none";
            if(brandAndScale.length == 2)
            {
                scale = brandAndScale[1].trim();
            }
            String boxartUrl = element.child(0).select("img").attr("src");
            boxartUrl = mainUrl + boxartUrl.substring(0,boxartUrl.lastIndexOf("-")).trim();
            String divText = element.child(1).select("div").last().html();
            String[] descriptionAndYear = divText.split("\\D?\\s+\\|\\s+");
            String description ="none";
            String year =descriptionAndYear[0];
            if(descriptionAndYear.length == 2)
            {
                description =descriptionAndYear[1];
            }
            records.add(new Item(scalematesUrl,brand,brandCatno,name,scale,description,boxartUrl,year));
        }
        return records;
    }

}
