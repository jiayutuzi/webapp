package com.entity;

public class Goods{
    private String id;
    private String name;
    private String price;
    private String provider;
    private int number;
    private String classification;
    private String img;
    private int sell;
    public int Getsell(){return this.sell;}
    public String Getimg(){return this.img;}
    public String Getclass(){return this.classification;}
    public String Getid(){return this.id;}
    public String Getname(){return this.name;}
    public String Getprice(){return this.price;}
    public String Getprovider(){return this.provider;}
    public int Getnumber(){return this.number;}
    public void Setclass(String classification){this.classification=classification;}
    public void Setid(String id){this.id=id;}
    public void Setname(String name){this.name=name;}
    public void Setprice(String price){this.price=price;}
    public void Setprovider(String provider){this.provider=provider;}
    public void Setnumber(int number){this.number=number;}
    public void Setimg(String img){this.img=img;}
    public void Setsell(int sell){this.sell=sell;}
}
