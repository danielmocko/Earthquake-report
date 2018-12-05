package com.example.android.quakereport;

public class Earthquake {

    private  double magnitude;
    private  String place;
    private  long date;

    public Earthquake(double magnitude ,String place, long date ){
        this.magnitude=magnitude;
        this.place=place;
        this.date=date;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public long getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

}
