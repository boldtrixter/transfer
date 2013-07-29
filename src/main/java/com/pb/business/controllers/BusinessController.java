/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.controllers;

import Entity.Transfertable;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.AuthorizationResponse;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.json.entity.User;
import com.pb.business.layer.business.BusinessLayer;
import java.util.List;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dmitry
 */

@Controller
@RequestMapping(value = "/webresources/rest")
public class BusinessController {

    @Autowired
    private BusinessLayer business;
    private static final Logger logger = Logger.getLogger(BusinessController.class);

    public BusinessController() {
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Transfertable> getAllMakers() {
        //logger.debug("Log smth");
        return business.getAllMakers();
    }
    
    @RequestMapping(value = "/setSale", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ServerResponse setUnit(@RequestBody Data d) throws ServerException {
        
        return business.checkData(d);
        
    }
    
    @RequestMapping(value = "/deleteTransfer/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ServerResponse deleteTransfer(@PathVariable(value = "id") String transferId) throws ServerException{
        return business.deleteTransfer(transferId);
    }
    
    @RequestMapping(value = "/getSmsPassword/{phone}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AuthorizationResponse getSmsPassword(@PathVariable(value = "phone") String phone) throws ServerException{
        return business.getSmsPassword(phone);
    }
    
    @RequestMapping(value = "/verifyUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ServerResponse verifyUser(@RequestBody User user) throws ServerException{
        return business.verifyUser(user);
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
