/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.business;

import Entity.Transfertable;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import java.util.List;

/**
 *
 * @author user
 */
public interface BusinessLayer {
    List<Transfertable> getAllMakers();
    public ServerResponse checkData(Data data) throws ServerException;
    public ServerResponse deleteTransfer(String transferId) throws ServerException;
}
