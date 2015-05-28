package com.maharjan.binod.restapiclient.apicontroller;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class HttpPostServerConnector {

    private final String TAG = HttpPostServerConnector.class.getName();
    private String serverResponse;
    private ServerConnectorDTO serverConnectorDto;

    public HttpPostServerConnector(ServerConnectorDTO connector){

        this.serverConnectorDto = connector;

    }



    public String connectServerWithHttpPostRequest() throws Exception {

        AppLog.logString(TAG, "*************CONNECTING SERVER*******");
        AppLog.logString(TAG, "url to connect:: " + serverConnectorDto.getUrlToConnect());
        AppLog.logString(TAG,"name value pair:: "+serverConnectorDto.getDataListNameValuePair());
        AppLog.logString(TAG,"json obj :: "+serverConnectorDto.getJsonDataString());



        HttpPost httpPostRequest = new HttpPost(serverConnectorDto.getUrlToConnect());
        httpPostRequest.setHeader("Accept", "application/json");

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse;


        if(serverConnectorDto.getDataListNameValuePair()!=null)
        {


            try {

                httpPostRequest.setEntity(new UrlEncodedFormEntity(serverConnectorDto.getDataListNameValuePair()));

            }catch(UnsupportedEncodingException e){

                serverResponse = "";

                AppLog.logError(TAG,e);

            }catch (Exception e) {

                AppLog.logError(TAG,e);

                serverResponse = "";

            }

        }else{
            AppLog.logString(TAG,"datalistname value pair::NULL");
        }



        if(serverConnectorDto.getJsonDataString()!=null)

        {

            try {

                StringEntity entity;

                entity = new StringEntity(serverConnectorDto.getJsonDataString());

                AppLog.logString("json obj entity:: "+entity);

                httpPostRequest.setHeader("Content-Type", "application/json");

                httpPostRequest.setEntity(entity);


            }catch(UnsupportedEncodingException e){

                serverResponse = "";

                AppLog.logError(TAG,e);
            }catch (Exception e) {

                AppLog.logError(TAG,e);

                serverResponse = "";

            }

        }else{

            AppLog.logString("Json IS NULL ");

        }



        if(serverConnectorDto.getFileEntity()!=null)
        {

            try {

                for (int i=0;i<serverConnectorDto.getFileEntity().size();i++)
                {

                    httpPostRequest.setEntity(serverConnectorDto.getFileEntity().get(i));
                }


            }catch (Exception e)
            {
                e.printStackTrace();

            }


        }


        if(serverConnectorDto.getHttpEntityList()!=null && serverConnectorDto.getHttpEntityList().size()>0)
        {

            try {

                for (int i=0;i<serverConnectorDto.getHttpEntityList().size();i++)
                {
                    httpPostRequest.setEntity(serverConnectorDto.getHttpEntityList().get(i));
                }


            }catch (Exception e)
            {
                e.printStackTrace();

            }
        }




        AppLog.logString("http Request::"+httpPostRequest.toString());
        AppLog.logString("http Request Entity ::"+httpPostRequest.getEntity().toString());







        try {

            httpResponse = httpClient.execute(httpPostRequest);

            AppLog.logString("Http Response:"+ httpResponse.toString());

            int responseCode = httpResponse.getStatusLine().getStatusCode();

            String message = httpResponse.getStatusLine().getReasonPhrase();

            AppLog.logString("server response code::"+responseCode+ ":: message:: "+message);


            if (responseCode==200) {

                HttpEntity entity = httpResponse.getEntity();

                if (entity != null) {

                    InputStream is = entity.getContent();

                    String streamResponse =convertStreamToString(is);
                    serverResponse = streamResponse;
                    is.close();
                } else {
                    AppLog.logString(" httpresponse entity null");
                    serverResponse= "";
                }

            }else{

                serverResponse= "";

            }

        } catch (ClientProtocolException e) {

            AppLog.logError(TAG,e);
            serverResponse= "";

        } catch (IOException e) {

            AppLog.logError(TAG,e);
            serverResponse= "";

        }

        return serverResponse;

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
