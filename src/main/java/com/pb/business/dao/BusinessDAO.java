/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.dao;

import com.pb.business.entity.Person;
import com.pb.business.entity.Token;
import com.pb.business.entity.Transfertable;
import com.pb.business.json.entity.Data;
import com.pb.business.message.ServerResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface BusinessDAO {
    List<Transfertable> getAllMakers();
    ServerResponse addTransfer(Data data);
    void saveToken(String token, String time);
    Token getToken(String token);
    void updateToken(Date datechange, String token);
    void deleteToken(String token);
    void hiberTest();

    public Person getPersonByPhone(String phoneNumber);
}
