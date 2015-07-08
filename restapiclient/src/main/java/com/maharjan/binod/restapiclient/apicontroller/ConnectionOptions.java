package com.maharjan.binod.restapiclient.apicontroller;

/**
 * Created by binod on 6/14/15.
 */
public class ConnectionOptions {

    private final String contentType;
    private final String language;
    private final int connectionTime;


   public static class Builder{
        private  String contentType="application/json";
        private String language="en-US";
       private int connectionTime=10000;
        public Builder(String contentType){
            this.contentType=contentType;
        }

        public Builder addLanguage(String language){
            this.language=language;
            return this;
        }

       public Builder setConnectionTime(int connectionTime){
           this.connectionTime=connectionTime;
           return this;
       }
        public ConnectionOptions build() {
            return new ConnectionOptions(this);
        }


    }

    private ConnectionOptions(Builder builder) {
        contentType = builder.contentType;
        language = builder.language;
        connectionTime=builder.connectionTime;
    }

    public String getContentType() {
        return contentType;
    }

    public String getLanguage() {
        return language;
    }

    public int getConnectionTime() {
        return connectionTime;
    }
}
