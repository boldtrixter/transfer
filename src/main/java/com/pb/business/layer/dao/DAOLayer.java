/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.dao;

import Entity.Person;
import Entity.Transfertable;
import java.util.List;

/**
 *
 * @author user
 */
public interface DAOLayer {
    List<Transfertable> getAllMakers();
}
