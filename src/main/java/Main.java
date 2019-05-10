import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        try {
            List records = HtmlParser.parseHtml("https://www.scalemates.com/kits/news.php");
            CsvCreator.saveRecords("1.txt",records);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
