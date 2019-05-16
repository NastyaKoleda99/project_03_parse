import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class CsvCreator
{
    public static void saveRecords(String filePath, List<Item> recordsToSave)
    {
        try
        {
            FileWriter writer = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println("\"scalematesUrl\",\"brand\",\"brandCatno\"," +
                    "\"name\",\"scale\",\"description\",\"boxartUrl\",\"year\"");
            for(Item record : recordsToSave)
            {
                printWriter.println(record.toString());
            }
            printWriter.flush();
            printWriter.close();
        }
        catch (Exception ex)
        {
           ex.printStackTrace();
        }

    }
}
