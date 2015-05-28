package com.maharjan.binod.restapiclient.apicontroller;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpGetServerConnector {

	private String serverResponse;
	private ServerConnectorDTO serverConnectorDto;
	
	
	public HttpGetServerConnector(ServerConnectorDTO connector){
		this.serverConnectorDto = connector;
	}
	

	public String connectServerWithHttpGetRequest(){
		
		AppLog.logString("*************CONNECTING SERVER*******");
		AppLog.logString("url to connect:: "+serverConnectorDto.getUrlToConnect());
		AppLog.logString("name value pair:: "+serverConnectorDto.getDataListNameValuePair());
		AppLog.logString("json obj :: "+serverConnectorDto.getJsonDataString());

		HttpGet httpGetRequest = new HttpGet(serverConnectorDto.getUrlToConnect());

        HttpParams httpParameters = new BasicHttpParams();
// Set the timeout  in milliseconds until a connection is established.(10 seconds)
        int timeoutConnection = 10000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
// Set the default socket timeout (SO_TIMEOUT)
// in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = 10000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient httpClient = new DefaultHttpClient(httpParameters);

		HttpResponse httpResponse;
		
		
		

		try {
			httpGetRequest.addHeader("Accept", "Application/json");
			httpResponse = httpClient.execute(httpGetRequest);
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			
			String message = httpResponse.getStatusLine().getReasonPhrase();
			AppLog.logString("server response code::"+responseCode+ ":: message:: "+message);
			
			if (responseCode==200) {
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					InputStream is = entity.getContent();
					String streamResponse = convertStreamToString(is);
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
			e.printStackTrace();
			serverResponse= "";
		} catch (IOException e) {
			e.printStackTrace();
			serverResponse= "";
		}catch (Exception e) {
			e.printStackTrace();
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
