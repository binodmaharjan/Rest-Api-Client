package com.maharjan.binod.restapiclient.apicontroller;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

/**
 * Created by root on 8/25/14.
 */
public class ServerConnector {

    private final String urlToConnect;
    private final String jsonDataString;
    private final List<BasicNameValuePair> dataListNameValuePair;
    private final ConnectionOptions headers;
    private final RequestParams params;


    public static class Builder {

        private  List<BasicNameValuePair> dataListNameValuePair;
        private final String urlToConnect;
        private  String jsonDataString;
        private ConnectionOptions headers=new ConnectionOptions.Builder("application/json").build();
        private RequestParams  params;


        public Builder(String urlToConnect) {
            this.urlToConnect = urlToConnect;
        }

        public Builder jsonData(String jsonDataString){
            this.jsonDataString=jsonDataString;
            return this;
        }
        @Deprecated
        public Builder params(List<BasicNameValuePair> dataListNameValuePair){
            this.dataListNameValuePair=dataListNameValuePair;
            return this;
        }

        public Builder addConnectionOptions(ConnectionOptions headers){
            this.headers=headers;
            return this;
        }

        public Builder addParams(RequestParams params){
            this.params=params;
            return this;
        }

        public ServerConnector build() {
            return new ServerConnector(this);
        }

    }

    private ServerConnector(Builder builder) {
        urlToConnect = builder.urlToConnect;
        jsonDataString = builder.jsonDataString;
        dataListNameValuePair = builder.dataListNameValuePair;
        headers=builder.headers;
        this.params=builder.params;
    }

    public String getUrlToConnect() {
        return urlToConnect;
    }

    public String getJsonDataString() {
        return jsonDataString;
    }

    @Deprecated
    public List<BasicNameValuePair> getDataListNameValuePair() {
        return dataListNameValuePair;
    }

    public ConnectionOptions getHeaders() {
        return headers;
    }

    public RequestParams getParams() {
        return params;
    }
}
