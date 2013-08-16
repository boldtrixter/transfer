/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.dao;


import com.pb.business.json.entity.TransferDetails;
import com.pb.business.message.ServerResponse;
import com.pb.transfer.Avtransf;
import com.pb.transfer.Person;
import com.pb.transfer.Status;
import com.pb.transfer.Token;
import com.pb.transfer.Transfer;
import java.util.Date;


/**
 *
 * @author Dmitry
 */
public interface BusinessDAO {

    ServerResponse addTransfer(Transfer transfer);
    void saveToken(String token, String time);
    Token getToken(String token);
    void updateToken(Token t);
    void deleteToken(Token token);
    String hiberTest();
    public Person getPersonByPhone(String phoneNumber);
    public boolean checkIp(String ip);
    public Status getStatusByTitle(String title);
}
