package com.mygdx.game;

import com.badlogic.gdx.utils.Null;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

import java.io.Serializable;

public class TankGame implements Serializable {
    private static HashMap<String,Integer> SavedGames=new HashMap<String,Integer>();

    private Tank player1;
    private Tank player2;
    private AirDrop a;
    //LocalDateTime
    private int noOfMoves=0;

//    void serialize() throws IOException {
//        ObjectOutputStream o=null;
//        try{
//            String p=
//            o=new ObjectOutputStream(new FileOutputStream("src/"))
//        }
//    };
    private int calculateDamage(Tank t1,Tank t2,Projectile p){

        return 0;
    };
    public Tank play(){
        while(player1.health>0 && player2.health>0){
            if(noOfMoves%2==0){

                if(player1.currX>=a.getPosX()-50 && player1.currX<=a.getPosX()+50){
                    player1.setSpecial1(a.getP());
                }
                Projectile p=player1.ChooseProjectile();
                player1.makeMove(p);
                int d=calculateDamage(player1,player2,p);
                player2.health-=d;
                noOfMoves++;
            }
            else if(noOfMoves%2==1){
                player2.move();
                if(player2.currX>=a.getPosX()-50 && player2.currX<=a.getPosX()+50){
                    player2.setSpecial1(a.getP());
                }
                Projectile p=player2.ChooseProjectile();
                player2.makeMove(p);
                int d=calculateDamage(player2,player1,p);
                player1.health-=d;
                noOfMoves++;

            }
            if(noOfMoves==5){
                Random r=new Random();
                int x=r.nextInt(3);
                if(x==0){
                    AirDrop a=new AirDrop(Projectile.getInstance(400,"Kamikaze"),(player1.currX+player2.currX)/2,player1.currY);
                }


            }
        }
        return null;
    }

    void quit(){};








}
