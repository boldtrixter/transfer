/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.message;

/**
 *
 * @author Dmitry
 */
public class AuthorizationResponse {
    
   private String res;
   private String note;
   private String token;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
