package com.mygdx.game;

import com.badlogic.gdx.utils.Null;
import java.util.Random;

import java.io.Serializable;

public class TankGame implements Serializable {
    private Tank player1;
    private Tank player2;
    private AirDrop a;
    //LocalDateTime
    private int noOfMoves=0;

    void serialize(){};
    private int calculateDamage(){
        return 0;
    };
    public Tank play(){
        while(player1.health>0 && player2.health>0){
            if(noOfMoves%2==0){

                if(player1.currX>=a.getPosX()-50 && player1.currX<=a.getPosX()+50){
                    player1.setSpecial1(a.getP());
                }
                player1.ChooseProjectile();
                player1.makeMove();
                int d=calculateDamage();
                player2.health-=d;
                noOfMoves++;
            }
            else if(noOfMoves%2==1){
                player2.move();
                if(player2.currX>=a.getPosX()-50 && player2.currX<=a.getPosX()+50){
                    player2.setSpecial1(a.getP());
                }
                player2.ChooseProjectile();
                player2.makeMove();
                int d=calculateDamage();
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
