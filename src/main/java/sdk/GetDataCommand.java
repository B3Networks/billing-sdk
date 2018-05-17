package sdk;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetDataCommand implements Command {

    String apiPath ="https://api.b3networks.com/data/open/billing/%s/%d?date=%s&apiKey=%s";
    //args list = apiKey  type  date
    //output to a csv
    public void execute(List<String> args){
        System.out.println("here get command");
        try {
            CSVService.write("out.csv");
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}