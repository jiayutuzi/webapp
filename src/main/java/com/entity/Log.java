package com.entity;

public class Log {
    private String time;
    private String user;
    private String op;
    private String id;
    private String name;
    private String Class;
    private int number;
    private String provider;
    private String price;
    public void Settime(String time){this.time=time;}
    public void Setuser(String user){this.user=user;}
    public void Setop(String op){this.op=op;}
    public void Setid(String id){this.id=id;}
    public void Setname(String name){this.name=name;}
    public void Setclass(String Class){this.Class=Class;}
    public void Setnumber(int number){this.number=number;}
    public void Setprovider(String provider){this.provider=provider;}
    public void Setprice(String price){this.price=price;}
    public String Gettime(){return this.time;}
    public String Getuser(){return this.user;}
    public String Getop(){return this.op;}
    public String Getid(){return this.id;}
    public String Getname(){return this.name;}
    public String Getclass(){return this.Class;}
    public int Getnumber(){return this.number;}
    public String Getprovider(){return this.provider;}
    public String Getprice(){return this.price;}
}
