package com.justin.drudgereader.newssource;

/**
 * Created by Justin on 5/3/2015.
 */
public abstract class BaseSource {
    private String strOne;
    private String strTwo;

    public BaseSource(String strOne, String strTwo) {
        this.strOne = strOne;
        this.strTwo = strTwo;
    }

    protected BaseSource() {
    }

    protected String getStrOne() {
        return strOne;
    }

    protected String getStrTwo() {
        return strTwo;
    }

    protected void setStrOne(String strOne) {
        this.strOne = strOne;
    }

    protected void setStrTwo(String strTwo) {
        this.strTwo = strTwo;
    }
}
