package sdk;

import java.util.*;
import org.springframework.web.client.RestTemplate;


public class DataEntry {
    public String name;

    public DataEntry(String aaa){
        this.name=aaa;
    }

    public String getName(){
        return this.name;
    }
}