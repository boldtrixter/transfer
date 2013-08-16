/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.dao;

import com.ibm.db2.jcc.b.c.p;
import com.pb.business.json.entity.TransferDetails;
import com.pb.business.message.ServerResponse;
import com.pb.transfer.Avtransf;
import com.pb.transfer.Person;
import com.pb.transfer.Security;
import com.pb.transfer.Status;
import com.pb.transfer.Token;
import com.pb.transfer.Transfer;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Query;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dmitry
 */
@Repository("DAO")
public class BusinessDAOImpl extends JdbcDaoSupport implements BusinessDAO {

    private final String GET_ALL_TRANSFER = "SELECT * FROM TRANSFERTABLE";
    @Autowired
    SessionFactory sf;

    @Override
    @Transactional
    public String hiberTest() {
//        Sec p = new Sec();
//        p.setIp(10000);

        Person sender = new Person();
        sender.setLname("Bond");

        Person carrier = new Person();
        carrier.setLname("James");

        Person recipient = new Person();
        recipient.setLname("Cameron");

        Token t = new Token();
        t.setPersonid(sender);
        t.setToken("1234455");



        sf.getCurrentSession().persist(sender);
        sf.getCurrentSession().persist(recipient);
        sf.getCurrentSession().persist(carrier);
        sf.getCurrentSession().persist(t);


        Transfer tr = new Transfer();
        tr.setCarrierid(carrier);
        tr.setSenderid(sender);
        tr.setRecipientid(recipient);

        sf.getCurrentSession().persist(tr);

        Avtransf avtransf = new Avtransf();
        avtransf.setPersonid(sender);
        avtransf.setTransferid(tr);

        Avtransf avtransf1 = new Avtransf();
        avtransf1.setPersonid(recipient);
        avtransf1.setTransferid(tr);

        Avtransf avtransf2 = new Avtransf();
        avtransf2.setPersonid(carrier);
        avtransf2.setTransferid(tr);

        sf.getCurrentSession().persist(avtransf);
        sf.getCurrentSession().persist(avtransf1);
        sf.getCurrentSession().persist(avtransf2);

        return "ok";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = DuplicateKeyException.class)
    public ServerResponse addTransfer(Transfer transfer) {

            String s ="Поработай плз дома!!!";
//        if (transfer.getSenderid().getId() != null) {
//            sf.getCurrentSession().merge(transfer.getSenderid());
//        }else{
//            sf.getCurrentSession().persist(transfer.getSenderid());
//        }
//
//        if (transfer.getRecipientid().getId() != null) {
//            sf.getCurrentSession().merge(transfer.getRecipientid());
//        }else{
//            sf.getCurrentSession().persist(transfer.getRecipientid());
//        }
//        
////        if (transfer.getCarrierid().getId() != null) {
////            sf.getCurrentSession().merge(transfer.getCarrierid());
////        }else{
////            sf.getCurrentSession().persist(transfer.getCarrierid());
////        }
//        
//        
//        sf.getCurrentSession().persist(transfer.getProductid());
////        
//        sf.getCurrentSession().persist(transfer.getCoordsid());
//
//        //sf.getCurrentSession().saveOrUpdate(transfer.getStatusid());

        sf.getCurrentSession().persist(transfer);



        ServerResponse sr = new ServerResponse();
        sr.setNote("ok");
        sr.setRef("23");
        return sr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Status getStatusByTitle(String title) {
        List<Status> list = sf.getCurrentSession()
                .createQuery("from Status s where s.description=:title").
                setString("title", title).list();

        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToken(String token, String time) {
        Token t = new Token();
        t.setToken(token);
        try {
            t.setDatecreate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(time));
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(BusinessDAOImpl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        sf.getCurrentSession().persist(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Token getToken(String token) {
        List<Token> list = sf.getCurrentSession().
                createQuery("from Token t where t.token=:token").
                setString("token", token).list();

        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateToken(Token t) {
        sf.getCurrentSession().saveOrUpdate(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteToken(Token token) {

        sf.getCurrentSession().delete(token);
        Logger.getLogger(BusinessDAOImpl.class.getName()).log(Level.INFO, "Deleted token " + token);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Person getPersonByPhone(String phoneNumber) {
        List<Person> list = sf.getCurrentSession().
                createQuery("from Person p where p.phonenumber like:phone").
                setString("phone", "%" + phoneNumber).list();

        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean checkIp(String ip) {
        List<Security> list = sf.getCurrentSession().
                createQuery("from Security s where s.ip=:ip").
                setString("ip", ip).list();

        if (list.isEmpty()) {
            return false;
        }
        return true;
    }
//    private static final class TransferMapper implements RowMapper<Transfertable1> {
//
//        @Override
//        public Transfertable1 mapRow(ResultSet rs, int rowNum) throws SQLException {
//            //Person p = new Person1();
//            Transfertable1 t = new Transfertable1();
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
////            p.setId(rs.getLong("ID"));
////            p.setFio(rs.getString("FIO"));
////            p.setPhonenumber(rs.getString("PHONENUMBER"));
////            p.setDatechange(rs.getDate("DATECHANGE"));
////            p.setDatecreate(rs.getDate("DATECREATE"));
////            p.setIdekb(rs.getString("IDEKB"));
////            p.setInn(new BigInteger(rs.getString("INN")));
////            p.setPass(rs.getString("PASS"));
//
////            String s = rs.getString("TABNUM");
////            System.out.println(s);
////            if(s.equals("null")){
////                p.setTabnum(new BigInteger("123456789"));
////            } else{
////                p.setTabnum(new BigInteger(s));
////            }
//
////            p.setType(rs.getShort("TYPE"));
//            return t;
//
//        }
//    }
//    private static final class TokenMapper implements RowMapper<Token1> {
//
//        @Override
//        public Token1 mapRow(ResultSet rs, int rowNum) throws SQLException {
//            //Person p = new Person1();
//            Token1 t = new Token1();
//            t.setId(rs.getLong("ID"));
//            t.setDatechange(rs.getDate("DATECHANGE"));
//            t.setToken(rs.getString("TOKEN"));
//
//
////            Transfertable1 t = new Transfertable1();
////            t.setId(rs.getLong("ID"));
////            t.setCurrency(rs.getString("CURRENCY"));
////            t.setDatechange(rs.getDate("DATECHANGE"));
////            t.setDatecreate(rs.getDate("DATECREATE"));
////            t.setPrice(rs.getDouble("PRICE"));
////            t.setPricecurrency(rs.getDouble("PRICECURRENCY"));
////            t.setProductId(new BigInteger(rs.getString("PRODUCT_ID")));
////            t.setProductscanserialnumberId(new BigInteger(rs.getString("PRODUCTSCANSERIALNUMBER_ID")));
////            t.setQty(rs.getDouble("QTY"));
////            t.setTotal(rs.getDouble("TOTALCURRENCY"));
////            t.setTransferId(new BigInteger(rs.getString("TRANSFER_ID")));
////            t.setUrlphoto(rs.getString("URLPHOTO"));
////            p.setId(rs.getLong("ID"));
////            p.setFio(rs.getString("FIO"));
////            p.setPhonenumber(rs.getString("PHONENUMBER"));
////            p.setDatechange(rs.getDate("DATECHANGE"));
////            p.setDatecreate(rs.getDate("DATECREATE"));
////            p.setIdekb(rs.getString("IDEKB"));
////            p.setInn(new BigInteger(rs.getString("INN")));
////            p.setPass(rs.getString("PASS"));
//
////            String s = rs.getString("TABNUM");
////            System.out.println(s);
////            if(s.equals("null")){
////                p.setTabnum(new BigInteger("123456789"));
////            } else{
////                p.setTabnum(new BigInteger(s));
////            }
//
////            p.setType(rs.getShort("TYPE"));
//            return t;
//
//        }
//    }
}
