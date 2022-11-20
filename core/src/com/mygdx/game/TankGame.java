package com.mygdx.game;

import com.badlogic.gdx.utils.Null;

import java.io.Serializable;

public class TankGame implements Serializable {
    private Tank player1;
    private Tank player2;
    private AirDrop a;
    //LocalDateTime
    private int noOfMoves;

    void serialize(){};
    private int calculateDamage(){
        return 0;
    };
    public Tank play(){
        //loop that runs while both tanks have positive health or game is not quit
        //returns the tank that won;if it is left in between it returns NULL
        return null;
    }

    void quit(){};








}
