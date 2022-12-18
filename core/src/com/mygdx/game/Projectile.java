package com.mygdx.game;

import java.io.Serializable;
import java.util.HashMap;

public class Projectile implements Serializable {
    private int angle;
    private int power;
    private int damage;
    private static HashMap<Integer,Projectile> pp=new HashMap<Integer,Projectile>();
    private String type;
    private Tank playerFiring;
    private Projectile(String type,int power){
        this.power=power;
        this.type=type;
    }

    public int getAngle() {
        return angle;
    }

    public int getPower() {
        return power;
    }

    public int getDamage() {
        return damage;
    }

    public static HashMap<Integer, Projectile> getPp() {
        return pp;
    }

    public String getType() {
        return type;
    }

    public Tank getPlayerFiring() {
        return playerFiring;
    }

    public static Projectile getInstance(int power, String type){
        if(!pp.containsKey(power)){
            pp.put(power,new Projectile(type,power));
        }
        return pp.get(power);
    }





}
