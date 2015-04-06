package com.purple_scout.mvd.webmodels;

import com.purple_scout.mvd.web.IJsonCollectionConvertable;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by abc123 on 2015-04-06.
 */
public class DeviceCollection implements IJsonCollectionConvertable {

    private List<Device> devices;
    private ICollectionState stateMachine;

    public DeviceCollection(ICollectionState stateMachine){
        devices = new ArrayList<>();
        this.stateMachine = stateMachine;
    }

    @Override
    public void createCollection(JSONArray jsonArr) {

        for(int i = 0; i < jsonArr.length(); i++){
            Device device = new Device();

            try {
                device.setVariables(jsonArr.getJSONObject(i));
            } catch (JSONException e) {

                device = null;
                e.printStackTrace();
            }

            if(device != null){
                devices.add(device);
            }
        }
    }

    @Override
    public String getUrl() {
        return stateMachine.getUrl();
    }

    @Override
    public Map<String, String> getDefaultMap() { //Här kan man adda alla JSON key/vals
        return stateMachine.getDataMapping();
    }

    @Override
    public String getAction() { //Om det är en POST/GET
        return stateMachine.getAction();
    }

    public List<Device> getDevices(){
        return devices;
    }
}
