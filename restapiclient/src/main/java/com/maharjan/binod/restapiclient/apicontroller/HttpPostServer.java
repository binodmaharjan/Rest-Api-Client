package com.maharjan.binod.restapiclient.apicontroller;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HttpPostServer {

    private final String TAG = HttpPostServer.class.getName();
    private String serverResponse;
    private ServerConnector serverConnectorDto;

    public HttpPostServer(ServerConnector connector){

        this.serverConnectorDto = connector;

    }



    public String connectServerWithHttpPostRequest() throws Exception {

        AppLog.logString(TAG, "*************CONNECTING SERVER*******");
        AppLog.logString(TAG, "url to connect:: " + serverConnectorDto.getUrlToConnect());
        AppLog.logString(TAG, "name value pair:: " + serverConnectorDto.getDataListNameValuePair());
        AppLog.logString(TAG, "json obj :: " + serverConnectorDto.getJsonDataString());
        AppLog.logString(TAG, "json obj :: " + serverConnectorDto.getParams());

        URL url = new URL(serverConnectorDto.getUrlToConnect());
        final HttpURLConnection  urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", serverConnectorDto.getHeaders().getContentType());
            urlConnection.setRequestProperty("Language", serverConnectorDto.getHeaders().getLanguage());
            urlConnection.setConnectTimeout(serverConnectorDto.getHeaders().getConnectionTime());
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            if(serverConnectorDto.getParams()!=null){
                writer.write(getQuery(serverConnectorDto.getParams()));
            }else if(serverConnectorDto.getJsonDataString()!=null){
               writer.write(serverConnectorDto.getJsonDataString());
            }else{
                throw new NullPointerException("Post has no parameters");
            }
            writer.flush();
            writer.close();
            os.close();


            int responseCode = urlConnection.getResponseCode();
        AppLog.logString(TAG, "jresponseCode :: " + responseCode);



        AppLog.logString(TAG, "serverResponse :: " + serverResponse);

            if(responseCode== HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String streamResponse = convertStreamToString(in);
                serverResponse = streamResponse;
            }else{
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String streamResponse = convertStreamToString(in);
                AppLog.logString(TAG, "res[pmse :: " + streamResponse);
                serverResponse = "";
            }
            urlConnection.disconnect();
        return serverResponse;

    }

    private String getQuery(RequestParams params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Set set = params.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(me.getKey().toString(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(me.getValue().toString(), "UTF-8"));

            i.remove();
        }

        return result.toString();
    }

    private String convertStreamToString(final InputStream is) {

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                is.close();
            } catch (Exception e2) {
            }
        }

        return sb.toString();

    }

}
