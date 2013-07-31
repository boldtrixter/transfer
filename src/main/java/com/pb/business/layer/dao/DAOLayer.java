/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.dao;

import Entity.Token;
import Entity.Transfertable;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface DAOLayer {
    List<Transfertable> getAllMakers();
    ServerResponse addTransfer(Data data);
    void saveToken(String token, String time);
    Token getToken(String token);
    void updateToken(Date datechange, String token);
    void deleteToken(String token);
}
