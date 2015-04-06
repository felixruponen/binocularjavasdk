/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.binocular.sdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author felix
 */
public class HttpClient {

    private String apiKey;

    private static final String BASE_URL = "http://api.binocular.se/v1/";

    /**
     *
     * @param apiKey
     */
    public HttpClient(String apiKey) {
        this.apiKey = apiKey;
    }
    
    /**
     * Gets devicetypes
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDeviceTypes() throws IOException {
        return Utils.excuteGet(BASE_URL + "devicetypes" + "?api_key=" + apiKey);
    }

    /**
     * Gets a single device using it's unique device id
     * @param deviceId Id used to get device
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDevice(String deviceId) throws IOException {
        return Utils.excuteGet(BASE_URL + "devices/" + deviceId + "?api_key=" + apiKey);
    }    
    
    /**
     * Gets devices associated with a devicetype
     * @param deviceTypeId Devicetype id used to get devices
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDevices(String deviceTypeId) throws IOException {
        return Utils.excuteGet(BASE_URL + "devicetypes/" + deviceTypeId + "/devices" + "?api_key=" + apiKey);
    }

    /**
     * Gets data from device
     * @param deviceId Device id used to get data
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDeviceData(String deviceId) throws IOException {
        return this.getDeviceData(deviceId, null, null, null);
    }

    /**
     * Gets data from device
     * @param deviceId Device id used to get data
     * @param limit Limits the amount of entries returned
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDeviceData(String deviceId, Integer limit) throws IOException {
        return this.getDeviceData(deviceId, limit, null, null);
    }

    /**
     * Gets data from device
     * @param deviceId Device id used to get data
     * @param limit Limits the amount of entries returned
     * @param timestamp Gets all entries created AFTER timestamp. Must be of type JavaScript timestamp
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDeviceData(String deviceId, Integer limit, Integer timestamp) throws IOException {
        return this.getDeviceData(deviceId, limit, timestamp, null);
    }
        
    /**
     * Gets data from device 
     * @param deviceId Device id used to get data
     * @param limit Limits the amount of entries returned
     * @param timestamp Gets all entries created AFTER timestamp. Must be of type JavaScript timestamp
     * @param next Gets the next page of response
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDeviceData(String deviceId, Integer limit, Integer timestamp, String next) throws IOException {

        String url = BASE_URL + "devices/" + deviceId + "/data" + "?api_key=" + apiKey;

        if (limit != null) {
            url += "&limit=" + limit;
        }

        if (timestamp != null) {
            url += "&timestamp=" + timestamp;
        }

        if (next != null) {
            url += "&start=" + next;
        }

        return Utils.excuteGet(url);
    }
    
    /**
     * Gets a single data entry 
     * @param dataId Data id used to get entry
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDataEntry(String dataId) throws IOException {
        return Utils.excuteGet(BASE_URL + "data/" + dataId + "?api_key=" + apiKey);
    }
    
    /**
     * Gets flags set on given device
     * @param deviceId Device id used to get flags
     * @return HttpResponse object with response
     * @throws IOException
     */
    public HttpResponse getDeviceFlags(String deviceId) throws IOException {
        return Utils.excuteGet(BASE_URL + "devices/" + deviceId + "/flags" + "?api_key=" + apiKey);
    }
    
    /**
     * Inserts data entry on devie
     * @param deviceId Device id used to insert data
     * @param data Json data to insert. Must be of type json object
     * @return HttpResponse object with response
     */
    public HttpResponse insertData(String deviceId, String data) {        
        return Utils.excutePost(BASE_URL + "devices/" + deviceId + "/data" + "?api_key=" + apiKey, data);
    }
    
    /**
     * Activates a new device of a devicetype
     * @param deviceTypeId Devicetype id used to activate device
     * @return HttpResponse object with response
     */
    public HttpResponse activateDevice(String deviceTypeId) {        
        return Utils.excutePost(BASE_URL + "devicetypes/" + deviceTypeId + "/activate_device" + "?api_key=" + apiKey, "");
    }    

}
