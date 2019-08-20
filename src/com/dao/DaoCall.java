package com.dao;

import java.util.List;

import com.model.TestCase;

public interface DaoCall {
    public List<TestCase> getCaseAll();
    
    public List<TestCase> getCompany();

    public boolean addCase(TestCase testCase);

    public boolean deleteCase(int openid);

    public boolean updateCase(TestCase testCase);

	public boolean addopenId(TestCase openid);

	boolean addWxId(String openid);
}