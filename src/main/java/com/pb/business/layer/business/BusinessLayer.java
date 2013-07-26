/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.business;

import Entity.Transfertable;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.json.entity.User;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface BusinessLayer {
    List<Transfertable> getAllMakers();
    public ServerResponse checkData(Data data) throws ServerException;
    public ServerResponse deleteTransfer(String transferId) throws ServerException;
    public ServerResponse getSmsPassword(String phone) throws ServerException;
    public ServerResponse verifyUser(User user) throws ServerException;
}
