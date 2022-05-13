package com.ds.carpooling.model;

public class OfferRideClass {

    String sourceAdd, destinationAdd, seats, luggage;

    public String getSourceAdd() {
        return sourceAdd;
    }

    public void setSourceAdd(String sourceAdd) {
        this.sourceAdd = sourceAdd;
    }

    public String getDestinationAdd() {
        return destinationAdd;
    }

    public void setDestinationAdd(String destinationAdd) {
        this.destinationAdd = destinationAdd;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage;
    }

    public OfferRideClass(String sourceAdd, String destinationAdd, String seats, String luggage) {
        this.sourceAdd = sourceAdd;
        this.destinationAdd = destinationAdd;
        this.seats = seats;
        this.luggage = luggage;
    }
}
