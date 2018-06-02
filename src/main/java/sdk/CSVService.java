package sdk;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVService {


    public static void write(String filepath)throws IOException,
    CsvDataTypeMismatchException,
    CsvRequiredFieldEmptyException {

        try 
            
         {
            Writer writer = Files.newBufferedWriter(Paths.get(filepath),StandardOpenOption.CREATE
            ,StandardOpenOption.APPEND);

           /*  ColumnPositionMappingStrategy mappingStrategy = 
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(DataEntry.class);
            StatefulBeanToCsv<DataEntry> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withMappingStrategy(mappingStrategy)
                    .build();

            List<DataEntry> data = new ArrayList<>();
            data.add(new DataEntry("aaaaa"));
            data.add(new DataEntry("bbbbbbbb"));

            beanToCsv.write(data); */
            CSVWriter cwriter = new CSVWriter(writer, ',');
            String[] entries = "first#second#third".split("#");
            cwriter.writeNext(entries);
            
            writer.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    
}