/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.binocular.sdk;

/**
 *
 * @author felix
 */
public class HttpResponse {

    private Integer statusCode;
    private String responseData;

    public HttpResponse(Integer statusCode, String responseData) {
        this.statusCode = statusCode;
        this.responseData = responseData;
    }
    
    public HttpResponse() {
        
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

}
