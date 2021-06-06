package com.entity;

public class Oplog {
    private String username;
    private String identity;
    private String time;
    private String op;
    private String ip;
    public Oplog(String username,String identity,String time,String op,String ip)
    {
        this.username=username;
        this.identity=identity;
        this.time=time;
        this.op=op;
        this.ip=ip;
    }
    public String Getusername(){return this.username;}
    public String Getidentity(){return this.identity;}
    public String Gettime(){return this.time;}
    public String Getop(){return this.op;}
    public String Getip(){return this.ip;} 
}

