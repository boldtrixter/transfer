/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.service;

import com.pb.business.entity.Person;
import com.pb.business.entity.Transfertable;
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
public interface BusinessService {
    List<Transfertable> getAllMakers(String userToken) throws ServerException;
    public ServerResponse checkData(Data data) throws ServerException;
    public ServerResponse deleteTransfer(String transferId) throws ServerException;
    public ServerResponse getSmsPassword(String phone) throws ServerException;
    public AuthorizationResponse verifyUser(UserData user) throws ServerException;
    public Person getUserDeTails(String phone)throws ServerException;
    public String hiberTest();
}
