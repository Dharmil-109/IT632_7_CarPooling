package com.ds.carpooling.model;

public class Car {

    String modelName, carNumber, licenceNumber, licenceImg;

    public String getModelName() {
        return modelName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getLicenceImg() {
        return licenceImg;
    }

    public Car(String modelName, String carNumber, String licenceNumber,String licenceImg)
    {
        this.modelName=modelName;
        this.carNumber=carNumber;
        this.licenceNumber=licenceNumber;
        this.licenceImg=licenceImg;
    }
}
