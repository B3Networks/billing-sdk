package sdk;
import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
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
import java.util.Map;
import java.util.stream.Collectors;
public class CSVService {


    public static void write(String filepath,List<Entry> list)throws IOException,
    CsvDataTypeMismatchException,
    CsvRequiredFieldEmptyException {

        try 
            
         {
            Writer writer = Files.newBufferedWriter(Paths.get(filepath),StandardOpenOption.CREATE
            ,StandardOpenOption.APPEND);

            BeanToCsv bc = new BeanToCsv<Entry>();
            ColumnPositionMappingStrategy mappingStrategy = 
            		new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Entry.class);
            String[] columns = new String[]{"orgName","type","invoiceText","startOn","cost","currency","qty"};
            mappingStrategy.setColumnMapping(columns);
            CSVWriter csvWriter = new CSVWriter(writer, ',');

            
            Map<String,Long> grp = list.stream().collect(
                Collectors.groupingBy(
                    Entry::getUnique, Collectors.counting()
                )
            );
            
            List<Entry> newlist = new ArrayList<Entry>();
            for (String key : grp.keySet()){
                //iterate over keys
                System.out.println(key+" "+grp.get(key));
                Entry a = list.stream()
                .filter(x -> x.getUnique().equalsIgnoreCase(key))
                .findFirst()
                .get();
                a.qty=grp.get(key);
                newlist.add(a);
            }
        
            bc.write(mappingStrategy,csvWriter,newlist);
            double sum = newlist.stream().mapToDouble(Entry::getCost).sum();
            CSVWriter cwriter2 = new CSVWriter(writer, ',');
            String[] entries = {"Total", sum+""};
            cwriter2.writeNext(entries); 
            
            writer.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }

    
}