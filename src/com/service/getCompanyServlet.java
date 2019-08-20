package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.dao.DaoImpl;
import com.model.TestCase;
import com.model.TestCaseTotal;

public class getCompanyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        DaoImpl testDaoImpl=new DaoImpl();
        List<TestCase> list=testDaoImpl.getCompany();
        List<TestCase> list1 =new ArrayList<>();
        int size =list.size();
        TestCase testCase;
        for(int i=0;i<size;i++){
            testCase=new TestCase();
            testCase.setCompanyName(list.get(i).getCompanyName());
            testCase.setCompanyWeb(list.get(i).getCompanyWeb());
            testCase.setCompanyCase(list.get(i).getCompanyCase());
            testCase.setCompanyDis(list.get(i).getCompanyDis());
            list1.add(testCase);
        }
        
        String page = request.getParameter("page");
        
        List<TestCase> caselist = new ArrayList<TestCase>();
        
        if (page == null || page.equals("0")) {
            if(list1.size()>9){
                for(int i=0;i<10;i++){
                    caselist.add(list1.get(i));
                }
            }
            else{
                for(int i=0;i<list1.size();i++){
                    caselist.add(list1.get(i));
                }
            }
            
        }
        else {
            int caseSize=list1.size();
            int page1 =caseSize/10;
            
            int pageNum=Integer.parseInt(page);
            //String parm=Integer.toString(a);
            if(pageNum<page1){
                for(int i=10*pageNum;i<10*pageNum+10;i++){
                    caselist.add(list1.get(i));
                }
            }
            else{
                for(int i=10*pageNum;i<list1.size();i++){
                    caselist.add(list1.get(i));
                }
            }
            
        }
        
        TestCaseTotal nt = new TestCaseTotal(caselist.size(), caselist);

        Gson gson = new Gson();
        String json = gson.toJson(nt);
        System.out.println(json);
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.print(json);
        out.flush();
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}