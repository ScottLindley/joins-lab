package com.scottlindley.joins_lab;

/**
 * Created by Scott Lindley on 10/28/2016.
 */

public class Job {
    private String mSSN, mCompany;
    private int mSalary, mExperience;

    public Job(String SSN, String company, int salary, int experience) {
        mSSN = SSN;
        mCompany = company;
        mSalary = salary;
        mExperience = experience;
    }

    public String getSSN() {
        return mSSN;
    }

    public String getCompany() {
        return mCompany;
    }

    public int getSalary() {
        return mSalary;
    }

    public int getExperience() {
        return mExperience;
    }
}
