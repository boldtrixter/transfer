/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.json.entity;

/**
 *
 * @author Dmitry
 */
public class TransferDetails {

    private String token;
    private String senderPhone;
    private String recipientPhone;
    private String carrierPhone;
    private String dateTime;
    private Coords coords;
    private String name = "1";
    private String photo;
    private String description;
    private String acceptanceType;
    private String callbackLink;

    public TransferDetails() {
    
    token = new String();
    senderPhone = new String();
    recipientPhone = new String();
    carrierPhone = new String();
    dateTime = new String();
    name = new String();
    photo = new String();
    description = new String();
    acceptanceType = new String();
    callbackLink = new String();
}

public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getCarrierPhone() {
        return carrierPhone;
    }

    public void setCarrierPhone(String carrrierPhone) {
        this.carrierPhone = carrrierPhone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAcceptanceType() {
        return acceptanceType;
    }

    public void setAcceptanceType(String acceptanceType) {
        this.acceptanceType = acceptanceType;
    }

    public String getCallbackLink() {
        return callbackLink;
    }

    public void setCallbackLink(String callbackLink) {
        this.callbackLink = callbackLink;
    }
     
}
