package com.entity;

public class Performance {
    private String name;
    private String life;
    private String sport;
    private String food;
    private String electronic;
    private String cloth;
    private String all;
    public Performance(){}
    public Performance(String name,String life,String sport,String food,String electronic,String cloth,String all)
    {
        this.name=name;
        this.life=life;
        this.sport=sport;
        this.food=food;
        this.electronic=electronic;
        this.cloth=cloth;
        this.all=all;
    }
    public String Getname(){return this.name;}
    public String Getlife(){return this.life;}
    public String Getsport(){return this.sport;}
    public String Getfood(){return this.food;}
    public String Getelectronic(){return this.electronic;}
    public String Getcloth(){return this.cloth;}
    public String Getall(){return all;}
}
