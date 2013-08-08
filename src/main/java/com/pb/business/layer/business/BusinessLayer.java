/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.business;

import Entity.Person;
import Entity.Transfertable;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.AuthorizationResponse;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.json.entity.UserData;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface BusinessLayer {
    List<Transfertable> getAllMakers(String userToken) throws ServerException;
    public ServerResponse checkData(Data data) throws ServerException;
    public ServerResponse deleteTransfer(String transferId) throws ServerException;
    public ServerResponse getSmsPassword(String phone) throws ServerException;
    public AuthorizationResponse verifyUser(UserData user) throws ServerException;
    public Person getUserDeTails(String phone)throws ServerException;
    public String hiberTest();
}
