package sdk;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.HttpRequest;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Service
public class GetDataCommand implements Command {

    String apiPath ="https://api.b3networks.com/data/open/billing/%s/%d?date=%s&apiKey=%s";
    //args list = apiKey  type  date
    //output to a csv
    public void execute(List<String> args){
        System.out.println("here get command");
        try {
            String apiKey = args.get(0);
            String type = args.get(1);

            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		    interceptors.add(new HeaderRequestInterceptor("x-credential-api-key", apiKey));

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setInterceptors(interceptors);
            int i =0;
            boolean found =false;
            while (!found){
                ListEntries list = restTemplate.getForObject(this.getURL(type,i,"2018-05-06",apiKey), ListEntries.class);
                System.out.println(list.nextId );
                if (list.entries.size()==0) found=true;
                for (Entry extension :list.entries ){
                    
                }
                i++;
            }
            CSVService.write("out.csv");
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    private String getURL(String type,int nextId,String date,String apiKey){

        return String.format(this.apiPath, type, nextId,date,apiKey);
    };
}