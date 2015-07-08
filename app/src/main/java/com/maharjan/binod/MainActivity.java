package com.maharjan.binod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.maharjan.binod.restapiclient.apicontroller.AppLog;
import com.maharjan.binod.restapiclient.apicontroller.AsyncRestClient;
import com.maharjan.binod.restapiclient.apicontroller.FailureReason;
import com.maharjan.binod.restapiclient.apicontroller.HttpTaskListener;
import com.maharjan.binod.restapiclient.apicontroller.RequestParams;
import com.maharjan.binod.restapiclient.apicontroller.ServerConnector;

import java.util.Iterator;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestParams params=new RequestParams();
        params.add("hello","ello");
        params.add("hello1","1ello");

        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        // for GET
      /*  ServerConnectorDTO connectorDTO = new ServerConnectorDTO();
        connectorDTO.setUrlToConnect("http://www.binod-maharjan.com.np/laugh/index.php/appconfig/controller?id=1&format=json");
*/



     /*   ServerConnector connector=new ServerConnector.Builder("http://www.binod-maharjan.com.np/laugh/index.php/appconfig/controller?id=1")
                                                     .build();

        AsyncRestClient apiFetchTask = new AsyncRestClient(AsyncRestClient.RequestMethod.GET, connector, new HttpTaskListener() {
            @Override
            public void onApiResponseSuccess(int taskId, String response) {
                // response from server
                AppLog.logString(response);

                Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onApiResponseFailure(int taskId, FailureReason reason) {
                AppLog.logString("failed reason" + reason.toString());
            }

            @Override
            public void onApiResponseStart(int taskId) {
                // put ProgressDialog here to indicate data is pulling from server
            }

            @Override
            public void onApiResponseCancel(int taskId) {
            }
        });
        apiFetchTask.execute();*/

        // for POST

                // request para
       RequestParams params1=new RequestParams();
        params1.add("id", "1");
        params1.add("name", "2");
        params1.add("age", "3");
        params1.add("fullName","RAm");

        ServerConnector connector=new ServerConnector.Builder("https://www.incentiveholidays.com/api/photocategory/image")
               .addParams(params1)
                .build();


        AppLog.logString("params object:::"+connector.getParams());



        AsyncRestClient postTask=new AsyncRestClient(AsyncRestClient.RequestMethod.POST,connector, new HttpTaskListener() {
            @Override
            public void onApiResponseSuccess(int taskId, String response) {

                AppLog.logString("response:::"+response);

            }

            @Override
            public void onApiResponseFailure(int taskId, FailureReason reason) {
                AppLog.logString("response:::"+reason.toString());

            }

            @Override
            public void onApiResponseStart(int taskId) {

            }

            @Override
            public void onApiResponseCancel(int taskId) {

            }
        });

        postTask.execute();


    }







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
