package com.maharjan.binod.restapiclient.apicontroller;

import org.apache.http.HttpEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/25/14.
 */
public class ServerConnectorDTO {

    private String urlToConnect;
    private String jsonDataString;
    private List<BasicNameValuePair> dataListNameValuePair;
    private ArrayList<FileEntity> fileEntity;
    private List<HttpEntity> httpEntityList;


    public String getUrlToConnect() {
        return urlToConnect;
    }

    public void setUrlToConnect(String urlToConnect) {
        this.urlToConnect = urlToConnect;
    }

    public String getJsonDataString() {
        return jsonDataString;
    }


    public void setJsonDataString(String jsonDataString) {
        this.jsonDataString = jsonDataString;
    }

    public List<BasicNameValuePair> getDataListNameValuePair() {
        return dataListNameValuePair;
    }

    public void setDataListNameValuePair(List<BasicNameValuePair> dataListNameValuePair) {
        this.dataListNameValuePair = dataListNameValuePair;
    }

    public ArrayList<FileEntity> getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(ArrayList<FileEntity> fileEntity) {
        this.fileEntity = fileEntity;
    }

    public List<HttpEntity> getHttpEntityList() {
        return httpEntityList;
    }

    public void setHttpEntityList(List<HttpEntity> httpEntityList) {
        this.httpEntityList = httpEntityList;
    }
}
