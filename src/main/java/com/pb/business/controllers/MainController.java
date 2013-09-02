/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.controllers;

import com.pb.business.message.ServerException;
import com.pb.business.message.AuthorizationResponse;
import com.pb.business.json.entity.TransferDetails;
import com.pb.business.message.ServerResponse;
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
    
    public void f(){
        System.out.println("!!!!");   
    }
    public MainController() {
    }
    
    @ResponseBody
    @RequestMapping(value = "/hiber", method = RequestMethod.GET)
    public String hiberTest(){
        return business.hiberTest();
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public void saveImage() throws Exception {
        //business.
//         BufferedReader reader  = new BufferedReader(new FileReader(new File("/home/user/img.txt")));
//        
//         StringBuilder builder = new StringBuilder();
//         String line;
//         while((line = reader.readLine()) != null){
//             builder.append(line);
//         }
//        String base64String = builder.toString();
//        byte[] imageByteArray = Base64.decodeBase64(base64String);
//        try {
//            FileOutputStream imageOutFile = new FileOutputStream(
//                    "/home/user/TransferImg/" + "pic" + ".jpg");
//
//            imageOutFile.write(imageByteArray);
//            imageOutFile.close();
//        } catch (IOException ex) {
//           // Logger.getLogger(null, null, ex);
//        }
    }
    
    

//    @RequestMapping(value = "/goods/{userToken}", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public List<Transfertable1> getAllMakers(@PathVariable String userToken) throws ServerException {
//        //logger.debug("Log smth");
//        return business.getAllMakers(userToken);
//    }

    @RequestMapping(value = "/addTransfer", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ServerResponse addTransfer(@RequestBody TransferDetails transferDetails, HttpServletRequest request) throws Exception {
        String ip = request.getRemoteAddr();
        return business.addTransfer(transferDetails, ip);
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
