package sdk;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    String apiPathNoDate ="https://api.b3networks.com/data/open/billing/%s/%d?apiKey=%s";
    String apiBilling = "https://apigateway-public.hoiio.com/billing/private/v3/wallet";
    //args list = apiKey  type  date
    //output to a csv
    public void execute(List<String> args){
        try {
            String apiKey = args.get(0);
            String type = args.get(1);
            SimpleDateFormat sdfSource = new SimpleDateFormat(
                    "yyyy-MM-dd");
            Date date = new Date();
            if (args.size()>=3){
                date = sdfSource.parse(args.get(2));
            }
            
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		    interceptors.add(new HeaderRequestInterceptor("x-credential-api-key", apiKey));

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setInterceptors(interceptors);
            int i =0;
            boolean found =false;
            System.out.println(i);
            while (!found){
                ListEntries list = null;
                if(args.size()>=3){
                    list = restTemplate.getForObject(this.getURL(type,i,sdfSource.format(date),apiKey)
                , ListEntries.class); 
                }
                else {
                    list = restTemplate.getForObject(String.format(this.apiPathNoDate, type, i,apiKey)
                , ListEntries.class);
                }
                
                /* ListEntries list = restTemplate.getForObject(String.format(this.apiPathNoDate, type, i,apiKey)
                , ListEntries.class); */
                if (list.entries.size()==0) {
                    found=true;
                }else {
                    CSVService.write(type+sdfSource.format(date)+".csv",list.entries);
                }

                i=list.nextId;
                
            }
            
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    private String getURL(String type,int nextId,String date,String apiKey){

        return String.format(this.apiPath, type, nextId,date,apiKey);
    };
}