/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.business.layer.dao.impl;

import Entity.Transfertable;
import com.pb.business.json.entity.Data;
import com.pb.business.json.entity.ServerResponse;
import com.pb.business.layer.dao.DAOLayer;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 *
 * @author user
 */
public class DAOLayerImpl extends JdbcDaoSupport implements DAOLayer {

    private final String GET_ALL_TRANSFER = "SELECT * FROM TRANSFERTABLE";

    @Override
    public List<Transfertable> getAllMakers() {
        List<Transfertable> list = new LinkedList<Transfertable>();
        list = getJdbcTemplate().query(GET_ALL_TRANSFER, new TransferMapper());
        // String s = list.get(0).getFio() + " " + list.get(0).getPhonenumber();
        return list;
    }

    @Override
    public ServerResponse addTransfer(Data data) {
        ServerResponse sr = new ServerResponse();
        sr.setNote("ok");
        sr.setRef("23");
        return sr;
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
}
