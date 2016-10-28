package com.scottlindley.joins_lab;

/**
 * Created by Scott Lindley on 10/28/2016.
 */

public class Employee {
    private String mSSN, mFirst, mLast, mCity;
    private int mBirthYear;

    public Employee(String SSN, String first, String last, int birthYear, String city) {
        mSSN = SSN;
        mFirst = first;
        mLast = last;
        mCity = city;
        mBirthYear = birthYear;
    }

    public String getSSN() {
        return mSSN;
    }

    public String getFirst() {
        return mFirst;
    }

    public String getLast() {
        return mLast;
    }

    public String getCity() {
        return mCity;
    }

    public int getBirthYear() {
        return mBirthYear;
    }
}
