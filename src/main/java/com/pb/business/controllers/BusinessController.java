/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.controllers;

import Entity.Transfertable;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.layer.business.BusinessLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
@RequestMapping(value = "/web")
public class BusinessController {

    @Autowired
    private BusinessLayer business;

    public BusinessController() {
    }

    @RequestMapping(value = "/goods/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Transfertable> getAllMakers() {
        return business.getAllMakers();
    }
    
    @RequestMapping(value = "/post/", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ServerResponse setUnit(@RequestBody Data d) throws ServerException {
        
        return business.checkData(d);
        
    }
    
    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public ServerResponse getExeption(ServerException ex){
        ServerResponse sr = new ServerResponse();
        sr.setRef(ex.getRef());
        sr.setNote(ex.getMessage());
        return sr;
    }

}
