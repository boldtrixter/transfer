/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.business.impl;

import Entity.Transfertable;
import ResponsePattern.Response;
import com.pb.business.exception.ServerException;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.json.entity.User;
import com.pb.business.layer.business.BusinessLayer;
import com.pb.business.layer.dao.DAOLayer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dmitry
 */
@Service
public class BusinessLayerImpl implements BusinessLayer {

    @Autowired
    DAOLayer dao;

    @Override
    public List<Transfertable> getAllMakers() {
        return dao.getAllMakers();
    }

    @Override
    public ServerResponse checkData(Data data) throws ServerException {

        //Проверка номера телефона отправителя
        if (!checkPhone(data.getSender().getPhoneNumber())) {
            throw new ServerException(Response.IncorrectSenderPhone.MESSAGE, Response.IncorrectSenderPhone.CODE);
        }

        //Проверка номера телефона получателя
        if (!checkPhone(data.getReceiver().getPhoneNumber())) {
            throw new ServerException(Response.IncorrectReceiverPhone.MESSAGE, Response.IncorrectReceiverPhone.CODE);
        }

        //Имя товара
        if (data.getName().equals("")) {
            throw new ServerException(Response.IncorrectProductName.MESSAGE, Response.IncorrectProductName.CODE);
        }

        return dao.addTransfer(data);


    }

    @Override
    public ServerResponse deleteTransfer(String transferId) throws ServerException {
        try{
            Integer.parseInt(transferId);
        }catch(Exception e){
            throw new ServerException(Response.IncorrectID.MESSAGE, Response.IncorrectID.CODE);
        }
        
        ServerResponse sr = new ServerResponse();
        sr.setRef(Response.Done.CODE);
        sr.setNote(Response.Done.MESSAGE);

        return sr;
    }

    @Override
    public ServerResponse getSmsPassword(String phone) throws ServerException {
        if (!checkPhone(phone)) {
            throw new ServerException(Response.IncorrectPhone.MESSAGE, Response.IncorrectPhone.CODE);
        } else {
            //HORRIBLE STUB
            ServerResponse sr = new ServerResponse();
            sr.setRef(Response.Done.CODE);
            sr.setNote(Response.Done.MESSAGE);
            return sr;
            //Дергаем сервис! наверно...
        }
    }

    @Override
    public ServerResponse verifyUser(User user) throws ServerException {
        if ((user.getLogin().equals("")) || (user.getPassword().equals(""))) {
            throw new ServerException(Response.IncorrectID.MESSAGE, Response.IncorrectID.CODE);
        }
        
        ServerResponse sr = new ServerResponse();
        sr.setRef(Response.Done.CODE);
        sr.setNote(Response.Done.MESSAGE);
        return sr;
    }

    private boolean checkPhone(String phone) {

        Pattern ua = Pattern.compile("\\+380\\d{9}");
        Pattern ru = Pattern.compile("\\+7\\d{10}");

        Matcher mUA = ua.matcher(phone);
        Matcher mRU = ru.matcher(phone);

        if ((mUA.matches()) || (mRU.matches())) {
            return true;
        }
        return false;

    }
}
