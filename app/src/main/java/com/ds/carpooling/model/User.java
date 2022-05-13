package com.ds.carpooling.model;

import android.widget.TextView;

public class User
{
    String email, password, fname, lname, phoneNo, address;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }


    public User(String fname, String lname, String email, String phoneNo, String address, String password)
    {
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.password=password;
        this.phoneNo=phoneNo;
        this.address=address;
    }
}
