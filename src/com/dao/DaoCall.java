package com.dao;

import java.util.List;

import com.model.TestCase;

public interface DaoCall {
    public List<TestCase> getCaseAll();

    public boolean addCase(TestCase testCase);

    public boolean deleteCase(int id);

    public boolean updateCase(TestCase testCase);
}