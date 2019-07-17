package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonServlet2 extends HttpServlet {
    public void destroy() {
        super.destroy(); 
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         request.setCharacterEncoding("utf-8"); 
         response.setCharacterEncoding("utf-8");
         Connection conn = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String name=request.getParameter("name");
         String post=request.getParameter("post");
         String company=request.getParameter("company");
         String phone=request.getParameter("phone");
         String email=request.getParameter("phone");
         String address=request.getParameter("address");
         String wxPhone=request.getParameter("wxPhone");
         String qqPhone=request.getParameter("qqPhone");
         String drive = "com.mysql.cj.jdbc.Driver";
         try{
             Class.forName(drive);
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC","root","123456");
             System.out.println("数据库连接成功");
             String sql="INSERT INTO tb_user (name,postd,company,phone,email,adress,wxPhone,qqPhone) VALUES(?,?,?,?,?,?,?,?)";
             stmt = conn.prepareStatement(sql);
             stmt.setString(1,name);
             stmt.setString(2,post);
             stmt.setString(3,company);
             stmt.setString(4,phone);
             stmt.setString(5,email);
             stmt.setString(6,address);
             stmt.setString(7,wxPhone);
             stmt.setString(8,qqPhone);
             stmt.executeUpdate();
             System.out.println(sql);
//             System.out.println("success");
             
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭连接（从里到外）
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
    public void init() throws ServletException {
        
    }

}