import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        try {
            List<Item> records = HtmlParser.parseHtml("https://www.scalemates.com/kits/news.php");
            File file = new File("D://Учеба//3 курс//6 сем//ТП//Мои лабы//project_03_parse//1.html");
            List <Item> recordsFile = HtmlParser.parseHtml(file);
            CsvCreator.saveRecords("1.txt",records);
            CsvCreator.saveRecords("2.txt", recordsFile);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
