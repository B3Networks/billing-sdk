package sdk;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {

    
    public int nextId;
    public String appName;
    public String type;
    public String orgName;
    public String orgUuid;
    public String orgCode;
    public String currency;
    public double cost;
    public double costRate;

    public String getAppName(){
        return this.appName;
    }
    public String getType(){
        return this.type;
    }
   
  
}