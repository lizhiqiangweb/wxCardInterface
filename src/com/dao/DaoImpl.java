package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.DaoCall;
import com.model.TestCase;
import com.util.DBconn;

public class DaoImpl implements DaoCall {
    private PreparedStatement ptmt = null;  
    private ResultSet rs = null;
    @Override
    public List<TestCase> getCaseAll() {
        // TODO Auto-generated method stub
        List<TestCase> list = new ArrayList<TestCase>();
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from tb_user");
            while(rs.next()){
                TestCase testCase=new TestCase();
                testCase.setId(rs.getInt("id"));
                testCase.setName(rs.getString("name"));
                testCase.setPost(rs.getString("postd"));
                testCase.setCompany(rs.getString("company"));
                testCase.setPhone(rs.getString("phone"));
                testCase.setEmail(rs.getString("email"));
                testCase.setAdress(rs.getString("adress"));
                testCase.setWxPhone(rs.getString("wxPhone"));
                testCase.setQqPhone(rs.getString("qqPhone"));
                
                list.add(testCase);
            }
            DBconn.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    @Override
    public boolean addCase(TestCase testCase) {
        // TODO Auto-generated method stub
        boolean flag = false;
        DBconn.init();
        int i =DBconn.addUpdDel("insert into tb_user(name,postd,company,phone,email,adress,wxPhone,qqPhone) " +
                "values('"+testCase.getName()+"','"+testCase.getPost()+"','"+testCase.getCompany()+","+testCase.getPhone()+","+testCase.getEmail()+","+testCase.getAdress()+","+testCase.getWxPhone()+","+testCase.getQqPhone()+"')");
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    }

    @Override
    public boolean deleteCase(int id) {
        // TODO Auto-generated method stub
        boolean flag = false;
        DBconn.init();
        String sql = "delete from tb_user where id="+id;
        
        int i =DBconn.addUpdDel(sql);
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    

    }

    @Override
    public boolean updateCase(TestCase testCase) {
        // TODO Auto-generated method stub
        return false;
    }

}