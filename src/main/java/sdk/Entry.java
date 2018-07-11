package sdk;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

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
    public double durationInSecond;
    public String answerTime;
    public String endTime;
    public String dest;
    public String source;
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
    public static boolean isNullOrBlank(String param) { 
        return param == null || param.trim().length() == 0;
    }

    public double getDurationInSecond(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        if (isNullOrBlank(this.answerTime)) return 0;
        if (isNullOrBlank(this.endTime)) return 0;
        LocalDateTime startdate = LocalDateTime.parse(this.answerTime,
         formatter);
        LocalDateTime enddate = LocalDateTime.parse(this.endTime,
         formatter);
        Duration dur = Duration.between(startdate, enddate);

        return dur.getSeconds();
    }

    public String getDest(){
        return this.dest;
    }
    public String getSource(){
        return this.source;
    }

    public String getAnswerTime(){
        return this.answerTime;
    }

    public String getEndTime(){
        return this.endTime;
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