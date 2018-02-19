package com.mkyong.data;

/**
 * Created by D-QU92GL on 19/02/2018.
 */
public class CommonPOJO {

    private String service1Data;
    private String service2Data;

    public String getService1Data() {
        return service1Data;
    }

    public void setService1Data(String service1Data) {
        this.service1Data = service1Data;
    }

    public String getService2Data() {
        return service2Data;
    }

    public void setService2Data(String service2Data) {
        this.service2Data = service2Data;
    }

    @Override
    public String toString() {
        return " service1Data : " + service1Data
        +" \n , service2Data : " + service2Data;
    }
}
