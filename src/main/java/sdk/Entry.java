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
    public String invoiceText;
    public String startOn;
    @JsonIgnoreProperties(value = { "y" })
    public String unique;

    public Long qty;

    public String getOrgName(){
        return this.orgName;
    }

    public String getType(){
        return this.type;
    }
   public String getInvoiceText(){
        String[] parts = this.invoiceText.split("-");
        
        if (parts.length>2 ){
            int second = this.invoiceText.indexOf("-", this.invoiceText.indexOf("-") + 1);
            return this.invoiceText.split("\\(")[0]+this.invoiceText.substring(second) ;
        } else
        return this.invoiceText.split("\\(")[0] ;
    }

    public String getStartOn(){
        return this.startOn;
    }

    public double getCost(){
        return this.cost;
    }

    public String getCurrency(){
        return this.currency;
    }

    public String getUnique(){
        return this.getOrgName()+this.getInvoiceText() +this.startOn;
    }
    public Long getQty(){
        return this.qty;
    }
}