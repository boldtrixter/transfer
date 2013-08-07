/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.json.entity;

/**
 *
 * @author Dmitry
 */
public class Data {

    private String token; // токен сессии
    private String name; // наименование
    private String units; // ед. измерения
    private int count; // количество
    private double price; //цена
    private double totalPrice; //стоимость = цена * количество
    private String scanCode; // штрихкод
    private String standNumber; // номеркузова последние 6 цифр
    private String description; //описание
    private String comment; // комментарий
    private Sender sender; // отправитель
    private Receiver receiver; // получатель
  
    public String getToken() {
        return token;
    }

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }

    public String getStandNumber() {
        return standNumber;
    }

    public void setStandNumber(String standNumber) {
        this.standNumber = standNumber;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }
        
}
