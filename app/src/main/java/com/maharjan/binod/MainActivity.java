package com.maharjan.binod;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.maharjan.binod.restapiclient.apicontroller.AppLog;
import com.maharjan.binod.restapiclient.apicontroller.AsyncRestClient;
import com.maharjan.binod.restapiclient.apicontroller.FailureReason;
import com.maharjan.binod.restapiclient.apicontroller.HttpTaskListener;
import com.maharjan.binod.restapiclient.apicontroller.ServerConnectorDTO;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for GET
        ServerConnectorDTO connectorDTO=new ServerConnectorDTO();
        connectorDTO.setUrlToConnect("http://www.binod-maharjan.com.np/laugh/index.php/appconfig/controller?id=1&format=json");
        AsyncRestClient apiFetchTask=new AsyncRestClient(AsyncRestClient.RequestMethod.GET, connectorDTO, new HttpTaskListener() {
            @Override
            public void onApiResponseSuccess(int taskId, String response) {

                // response from server
                AppLog.logString(response);


            }

            @Override
            public void onApiResponseFailure(int taskId, FailureReason reason) {
             AppLog.logString("failed reason"+reason.toString());

            }

            @Override
            public void onApiResponseStart(int taskId) {
                // put ProgressDialog here to indicate data is pulling from server

            }

            @Override
            public void onApiResponseCancel(int taskId) {

            }
        });

        apiFetchTask.execute();
    }



    // for POST


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
