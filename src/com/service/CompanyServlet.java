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

public class CompanyServlet extends HttpServlet {
    public void destroy() {
        super.destroy(); 
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
         Connection conn = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String companyName=request.getParameter("companyName");
         String companyWeb=request.getParameter("companyWeb");
         String companyCase=request.getParameter("companyCase");
         String companyDis=request.getParameter("companyDis");
         String drive = "com.mysql.cj.jdbc.Driver";
         try{
             Class.forName(drive);
             conn = DriverManager.getConnection("jdbc:mysql://120.27.61.214:3306/test1?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai","root","tangqing@123");
             System.out.println("数据库连接成功");
             String sql="INSERT INTO tb_company (companyName,companyWeb,companyCase,companyDis) VALUES(?,?,?,?)";
             stmt = conn.prepareStatement(sql);
             stmt.setString(1,companyName);
             stmt.setString(2,companyWeb);
             stmt.setString(3,companyCase);
             stmt.setString(4,companyDis);
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