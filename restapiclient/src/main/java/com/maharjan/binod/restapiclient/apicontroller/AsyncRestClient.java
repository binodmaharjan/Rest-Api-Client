package com.maharjan.binod.restapiclient.apicontroller;

import android.os.AsyncTask;
import android.text.TextUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by binod on 12/23/14.
 */
public class AsyncRestClient extends AsyncTask<Void,Void,String> {

    public enum RequestMethod{
        GET,
        POST
    }

    private ServerConnectorDTO mServerConnectorDTO;
    private HttpTaskListener mHttpTaskListener;
    private RequestMethod method;
    private int taskId;

    /**
     * @param taskId the id for the task to distinguish from one another
     * @param serverConnectorDTO instance of {@link ServerConnectorDTO}. Can not be null
     *
     * @param listener the callback for task operation. Can be null
     * */

    public AsyncRestClient(int taskId, RequestMethod method, ServerConnectorDTO serverConnectorDTO, HttpTaskListener listener){

        if(serverConnectorDTO!=null){
            mServerConnectorDTO = serverConnectorDTO;
        }else {
            throw new NullPointerException(serverConnectorDTO.getClass().toString()+" cannot be null");
        }
        if(method!=null){
            this.method=method;
        }else {
            throw new NullPointerException("Request cannot be null");
        }

        mHttpTaskListener = listener;

        this.taskId = taskId;

    }


    /**
     * @param serverConnectorDTO instance of {@link ServerConnectorDTO}. Can not be null
     *
     * @param listener the callback for task operation. Can be null
     * */

    public AsyncRestClient(RequestMethod method, ServerConnectorDTO serverConnectorDTO, HttpTaskListener listener){

        if(serverConnectorDTO!=null){
            mServerConnectorDTO = serverConnectorDTO;
        }else {
            throw new NullPointerException(serverConnectorDTO.getClass().toString()+" cannot be null");
        }

        if(method!=null){
            this.method=method;
        }else {
            throw new NullPointerException("Request cannot be null");
        }

        mHttpTaskListener = listener;

        taskId = -1;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(mHttpTaskListener!=null)mHttpTaskListener.onApiResponseStart(taskId);

    }

    @Override
    protected String doInBackground(Void... params) {

        String serverResponse = null;

        switch (method){
            case GET:
                try {
                    serverResponse = new HttpGetServerConnector(mServerConnectorDTO).connectServerWithHttpGetRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
                break;

            case POST:
                try {
                    serverResponse = new HttpPostServerConnector(mServerConnectorDTO).connectServerWithHttpPostRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
                break;

            default:
                throw new NullPointerException("Request method cannot be null");
        }


        return serverResponse;
    }


    @Override
    protected void onCancelled() {
        super.onCancelled();
        if(mHttpTaskListener!=null)mHttpTaskListener.onApiResponseCancel(taskId);

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        AppLog.logString("response from server::" + result);

        if(TextUtils.isEmpty(result)){
            if(mHttpTaskListener!=null)mHttpTaskListener.onApiResponseFailure(taskId, FailureReason.COULD_NOT_CONNECT_SERVER);
        }else{
            try {
                 new JSONObject(result);
                if(mHttpTaskListener!=null)mHttpTaskListener.onApiResponseSuccess(taskId, result);
            } catch (JSONException e) {
                try {
                     new JSONArray(result);
                    if(mHttpTaskListener!=null)mHttpTaskListener.onApiResponseSuccess(taskId, result);
                } catch (JSONException e1) {
                    if(mHttpTaskListener!=null)mHttpTaskListener.onApiResponseFailure(taskId, FailureReason.BAD_JSON);
                }
            }


        }

    }



}
