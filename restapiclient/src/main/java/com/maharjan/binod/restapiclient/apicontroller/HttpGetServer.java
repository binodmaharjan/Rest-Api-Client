package com.maharjan.binod.restapiclient.apicontroller;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetServer {

	private String serverResponse;
	private ServerConnector serverConnectorDto;
	ConnectionOptions headers=new ConnectionOptions.Builder("Application/json").build();


	public HttpGetServer(ServerConnector connector,ConnectionOptions headers){
		this.serverConnectorDto = connector;
		this.headers=headers;
	}

	public HttpGetServer(ServerConnector connector){
		this.serverConnectorDto = connector;

	}
	

	public String connectServerWithHttpGetRequest(){
		
		AppLog.logString("*************CONNECTING SERVER*******");
		AppLog.logString("url to connect:: "+serverConnectorDto.getUrlToConnect());
		AppLog.logString("name value pair:: "+serverConnectorDto.getDataListNameValuePair());
		AppLog.logString("json obj :: "+serverConnectorDto.getJsonDataString());

		AppLog.logString("language :: "+serverConnectorDto.getHeaders().getLanguage());

		HttpURLConnection urlConnection=null;
		try {
			URL url = new URL(serverConnectorDto.getUrlToConnect());
			 urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("Accept", serverConnectorDto.getHeaders().getContentType());
			urlConnection.setRequestProperty("Language", serverConnectorDto.getHeaders().getLanguage());
			urlConnection.setConnectTimeout(serverConnectorDto.getHeaders().getConnectionTime());
			urlConnection.setRequestMethod("GET");
			urlConnection.setReadTimeout(10000);

			int responseCode = urlConnection.getResponseCode();
			if(responseCode== HttpURLConnection.HTTP_OK) {
				InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				String streamResponse = convertStreamToString(in);
				serverResponse = streamResponse;
			}else{
				serverResponse = "";
			}
//			readStream(in);
		} catch (IOException e) {
			e.printStackTrace();
			serverResponse = "";
		}finally {
			try {
				urlConnection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
