/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.service;

import com.pb.business.entity.Person;
import com.pb.business.entity.Transfertable;
import com.pb.business.message.ServerException;
import com.pb.business.message.AuthorizationResponse;
import com.pb.business.json.entity.TransferDetails;
import com.pb.business.message.ServerResponse;
import com.pb.business.json.entity.UserData;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface BusinessService {
    List<Transfertable> getAllMakers(String userToken) throws ServerException;
    public ServerResponse addTransfer(TransferDetails transferDetails, String ip) throws Exception;
    public ServerResponse deleteTransfer(String transferId) throws ServerException;
    public ServerResponse getSmsPassword(String phone) throws ServerException;
    public AuthorizationResponse verifyUser(UserData user) throws ServerException;
    public String hiberTest();
}
