/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.dao.impl;

import Entity.Token;
import Entity.Transfertable;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.layer.dao.DAOLayer;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dmitry
 */
public class DAOLayerImpl extends JdbcDaoSupport implements DAOLayer {

    private final String GET_ALL_TRANSFER = "SELECT * FROM TRANSFERTABLE";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Transfertable> getAllMakers() {
        List<Transfertable> list = new LinkedList<Transfertable>();
        list = getJdbcTemplate().query(GET_ALL_TRANSFER, new TransferMapper());
        // String s = list.get(0).getFio() + " " + list.get(0).getPhonenumber();
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ServerResponse addTransfer(Data data) {


        ServerResponse sr = new ServerResponse();
        sr.setNote("ok");
        sr.setRef("23");
        return sr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToken(String token, String time) {
        
        //Обязательно переделать ИД сделать автоинкремент!!!
        getJdbcTemplate().update("insert into TOKEN(TOKEN.ID, TOKEN.DATECHANGE, TOKEN.TOKEN) values(?, ?, ?)", new Object[]{
            new BigInteger(new SimpleDateFormat("yyMMddHHmm").format(Calendar.getInstance().getTime())).intValue(),
            time,
            token
        });

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Token getToken(String token) {
        List<Token> list = getJdbcTemplate().query("SELECT TOKEN.ID, TOKEN.DATECHANGE, TOKEN.TOKEN FROM TOKEN WHERE TOKEN.TOKEN = ?", new Object[]{token}, new TokenMapper());
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateToken(Date datechange, String token) {
        getJdbcTemplate().update("UPDATE TOKEN SET TOKEN.DATECHANGE = ? WHERE TOKEN.TOKEN = ?", new Object[]{
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").format(datechange),
        token
        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteToken(String token) {
        getJdbcTemplate().update("DELETE FROM TOKEN WHERE TOKEN.TOKEN = ?", new Object[]{token});
        Logger.getLogger(DAOLayerImpl.class.getName()).log(Level.INFO, "Deleted token " + token);
    }

    private static final class TransferMapper implements RowMapper<Transfertable> {

        @Override
        public Transfertable mapRow(ResultSet rs, int rowNum) throws SQLException {
            //Person p = new Person();
            Transfertable t = new Transfertable();
            t.setId(rs.getLong("ID"));
            t.setCurrency(rs.getString("CURRENCY"));
            t.setDatechange(rs.getDate("DATECHANGE"));
            t.setDatecreate(rs.getDate("DATECREATE"));
            t.setPrice(rs.getDouble("PRICE"));
            t.setPricecurrency(rs.getDouble("PRICECURRENCY"));
            t.setProductId(new BigInteger(rs.getString("PRODUCT_ID")));
            t.setProductscanserialnumberId(new BigInteger(rs.getString("PRODUCTSCANSERIALNUMBER_ID")));
            t.setQty(rs.getDouble("QTY"));
            t.setTotal(rs.getDouble("TOTALCURRENCY"));
            t.setTransferId(new BigInteger(rs.getString("TRANSFER_ID")));
            t.setUrlphoto(rs.getString("URLPHOTO"));
//            p.setId(rs.getLong("ID"));
//            p.setFio(rs.getString("FIO"));
//            p.setPhonenumber(rs.getString("PHONENUMBER"));
//            p.setDatechange(rs.getDate("DATECHANGE"));
//            p.setDatecreate(rs.getDate("DATECREATE"));
//            p.setIdekb(rs.getString("IDEKB"));
//            p.setInn(new BigInteger(rs.getString("INN")));
//            p.setPass(rs.getString("PASS"));

//            String s = rs.getString("TABNUM");
//            System.out.println(s);
//            if(s.equals("null")){
//                p.setTabnum(new BigInteger("123456789"));
//            } else{
//                p.setTabnum(new BigInteger(s));
//            }

//            p.setType(rs.getShort("TYPE"));
            return t;

        }
    }
    
        private static final class TokenMapper implements RowMapper<Token> {

        @Override
        public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
            //Person p = new Person();
            Token t = new Token();
            t.setId(rs.getLong("ID"));
            t.setDatechange(rs.getDate("DATECHANGE"));
            t.setToken(rs.getString("TOKEN"));
            
            
//            Transfertable t = new Transfertable();
//            t.setId(rs.getLong("ID"));
//            t.setCurrency(rs.getString("CURRENCY"));
//            t.setDatechange(rs.getDate("DATECHANGE"));
//            t.setDatecreate(rs.getDate("DATECREATE"));
//            t.setPrice(rs.getDouble("PRICE"));
//            t.setPricecurrency(rs.getDouble("PRICECURRENCY"));
//            t.setProductId(new BigInteger(rs.getString("PRODUCT_ID")));
//            t.setProductscanserialnumberId(new BigInteger(rs.getString("PRODUCTSCANSERIALNUMBER_ID")));
//            t.setQty(rs.getDouble("QTY"));
//            t.setTotal(rs.getDouble("TOTALCURRENCY"));
//            t.setTransferId(new BigInteger(rs.getString("TRANSFER_ID")));
//            t.setUrlphoto(rs.getString("URLPHOTO"));
//            p.setId(rs.getLong("ID"));
//            p.setFio(rs.getString("FIO"));
//            p.setPhonenumber(rs.getString("PHONENUMBER"));
//            p.setDatechange(rs.getDate("DATECHANGE"));
//            p.setDatecreate(rs.getDate("DATECREATE"));
//            p.setIdekb(rs.getString("IDEKB"));
//            p.setInn(new BigInteger(rs.getString("INN")));
//            p.setPass(rs.getString("PASS"));

//            String s = rs.getString("TABNUM");
//            System.out.println(s);
//            if(s.equals("null")){
//                p.setTabnum(new BigInteger("123456789"));
//            } else{
//                p.setTabnum(new BigInteger(s));
//            }

//            p.setType(rs.getShort("TYPE"));
            return t;

        }
    }
}
