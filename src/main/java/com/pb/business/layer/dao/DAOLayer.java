/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.dao;

import Entity.Transfertable;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import java.util.List;

/**
 *
 * @author user
 */
public interface DAOLayer {
    List<Transfertable> getAllMakers();
    ServerResponse addTransfer(Data data);
}
