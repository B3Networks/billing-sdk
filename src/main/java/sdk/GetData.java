package sdk;

public class GetData {
    

    private String url;
    private String type;
    private String apiKey;

    private GetData(GetDataBuilder builder){
        this.url=builder.url;
        this.type=builder.type;
    }

    public static class GetDataBuilder{
        private String url;
        private String type;
        private String apiKey;

        public GetDataBuilder(String apiKey){
            this.apiKey=apiKey;
        }

        public GetDataBuilder withUrl(String url){
            this.url=url;
            return this;
        };

        public GetDataBuilder withType(String type){
            this.type=type;
            return this;
        };

        public GetData build(){
            return new GetData(this);
        }
    }

}