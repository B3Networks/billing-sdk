package sdk;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListEntries {

    
    public int nextId;
    public List<Entry> entries;
    public ListEntries() {
    }

   
   
  
}