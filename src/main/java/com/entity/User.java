package com.entity;

public class User{
    private String name;
    private String password;
    private String email;
    private String identity;
    public String Getidentity(){return identity;}
    public String Getname(){return this.name;}
    public String Getpassword(){return this.password;}
    public String Getemail(){return this.email;}
    public void Setname(String name){ this.name=name;}
    public void Setpassword(String password){this.password=password;}
    public void Setemail(String email){this.email=email;}
    public void Setidentity(String identity){this.identity=identity;}
}