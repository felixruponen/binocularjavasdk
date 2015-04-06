package com.purple_scout.mvd;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.purple_scout.mvd.web.Fetcher;
import com.purple_scout.mvd.web.IJsonCollectionConvertable;
import com.purple_scout.mvd.web.OnDone;
import com.purple_scout.mvd.webmodels.Device;
import com.purple_scout.mvd.webmodels.DeviceCollection;
import com.purple_scout.mvd.webmodels.ICollectionState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //performAPost();
        performAFetch();
    }

    private void performAPost(){
        final IJsonCollectionConvertable manager = new DeviceCollection(new ICollectionState() {
            @Override
            public String getUrl() {
                return "http://api.binocular.se/v1/devices/5506b40d55b75359b88b5315/data?api_key=asd";
            }

            @Override
            public Map<String, String> getDataMapping() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("coffee_batches", "200");
                map.put("usage", "111");


                return map;
            }

            @Override
            public String getAction() {
                return "POST";
            }
        });

        Fetcher fetcher = new Fetcher();
        fetcher.setMap(manager.getDefaultMap());
        fetcher.setCollectionManager(manager);
        fetcher.setDone(new OnDone() {
            @Override
            public void done() {
                //Execute next link if chaining is applied.

                Log.i("Fetcher", "Done!");
            }
        });

        fetcher.execute();
    }

    private void performAFetch(){
        final IJsonCollectionConvertable manager = new DeviceCollection(new ICollectionState() {
            @Override
            public String getUrl() {
                return "http://api.binocular.se/v1/devicetypes/5506b40d55b75359b88b5314/devices?api_key=asd";
            }

            @Override
            public Map<String, String> getDataMapping() {
                return null; //Eftersom det är en GET har den ingen body
            }

            @Override
            public String getAction() {
                return "GET";
            }
        });

        Fetcher fetcher = new Fetcher();
        fetcher.setMap(manager.getDefaultMap());
        fetcher.setCollectionManager(manager);
        fetcher.setDone(new OnDone() {
            @Override
            public void done() {
                //Execute next link if chaining is applied.
                List<Device> devices = ((DeviceCollection)manager).getDevices();

                Log.i("Fetcher", "Done!");
            }
        });

        fetcher.execute();
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
