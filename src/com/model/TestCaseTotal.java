package com.model;

import java.util.List;

public class TestCaseTotal {
    private int total; 
    private List<TestCase> rows; 
    
    
    public TestCaseTotal() {
    }
    public TestCaseTotal(int total, List<TestCase> rows) {
        this.total = total;
        this.rows = rows;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<TestCase> getRows() {
        return rows;
    }
    public void setRows(List<TestCase> rows) {
        this.rows = rows;
    }
    
}