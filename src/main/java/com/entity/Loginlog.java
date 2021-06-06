package com.entity;

public class Loginlog {
    private String username;
    private String identity;
    private String logintime;
    private String logouttime;
    private String ip;
    public Loginlog(String username,String identity,String logintime,String logouttime,String ip )
    {
        this.username=username;
        this.identity=identity;
        this.logintime=logintime;
        this.logouttime=logouttime;
        this.ip=ip;
    }
    public String Getusername(){return this.username;}
    public String Getidentity(){return this.identity;}
    public String Getlogintime(){return this.logintime;}
    public String Getlogouttime(){return this.logouttime;}
    public String Getip(){return this.ip;} 
}
