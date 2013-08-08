/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.controllers;

import com.pb.business.entity.Person;
import com.pb.business.entity.Transfertable;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.AuthorizationResponse;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.json.entity.UserData;
import com.pb.business.service.BusinessService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class MainController {

    @Autowired
    private BusinessService business;

    public MainController() {
    }

    @RequestMapping(value = "/do-something", method = RequestMethod.GET)
    @ResponseBody
    public Person doSomething(HttpServletRequest request) throws Exception {
        
        return business.getUserDeTails("+380934682670");
    }
    
    @RequestMapping(value = "/do-hiber", method = RequestMethod.GET)
    @ResponseBody
    public String doHiber(HttpServletRequest request) throws Exception {
        
//        String ip = request.getRemoteAddr();
//        String prefix = " access permitted!";
//        String msg = "You trying connect to PrivatTransfer from IP: " + ip + prefix;
//        return msg;
        
        return business.hiberTest();
    }

    @RequestMapping(value = "/goods/{userToken}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Transfertable> getAllMakers(@PathVariable String userToken) throws ServerException {
        //logger.debug("Log smth");
        return business.getAllMakers(userToken);
    }

    @RequestMapping(value = "/setSale", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ServerResponse addTransfer(@RequestBody Data d) throws ServerException {
        return business.checkData(d);
    }

    @RequestMapping(value = "/deleteTransfer/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ServerResponse deleteTransfer(@PathVariable(value = "id") String transferId) throws ServerException {
        return business.deleteTransfer(transferId);
    }

    @RequestMapping(value = "/getSmsPassword/{phone}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ServerResponse getSmsPassword(@PathVariable(value = "phone") String phone) throws ServerException {
        return business.getSmsPassword(phone);
    }

    @RequestMapping(value = "/verifyUser", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public AuthorizationResponse verifyUser(@RequestBody UserData user) throws ServerException {
        return business.verifyUser(user);
    }

    @ExceptionHandler(ServerException.class)
    @ResponseBody
    public ServerResponse getExeption(ServerException ex) {
        ServerResponse sr = new ServerResponse();
        sr.setRef(ex.getRef());
        sr.setNote(ex.getMessage());
        return sr;
    }

}
