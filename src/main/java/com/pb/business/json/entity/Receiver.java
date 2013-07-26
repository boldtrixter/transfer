/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.json.entity;

/**
 *
 * @author Dmitry
 */
public class Receiver {

    private String phoneNumber;
    private String dateTime;
    private Coords coords;
    private String radius;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
    
    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
