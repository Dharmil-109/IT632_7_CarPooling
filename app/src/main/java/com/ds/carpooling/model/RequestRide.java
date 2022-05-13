package com.ds.carpooling.model;

public class RequestRide {
    String name,contact,sourceAddress,destinationAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public RequestRide(String name, String contact, String sourceAddress, String destinationAddress) {
        this.name = name;
        this.contact = contact;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
    }
}
