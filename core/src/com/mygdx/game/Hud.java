package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.Serializable;


public class Hud implements Serializable{
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    public Integer angle1;
    public Integer angle2;
    Integer health1;
    Integer health2;
    Integer moves1;
    Integer moves2;

    Label countdownLabel;
    Label scoreLabel1;

    Label scoreLabel11;
    Label scoreLabel2;
    Label moveLabel11;
    Label moveLabel1;
    Label moveLabel2;
    transient Label anglelabel1;
    transient Label anglelabel2;

    public Hud(SpriteBatch s){
        worldTimer=300;
        timeCount=0;
        health1=100;
        health2=100;
        moves1=10;
        moves2=10;
        angle1=30;
        angle2=30;


        viewport=new FitViewport(1400,705,new OrthographicCamera());
        stage=new Stage(viewport,s);
        Table table=new Table();
        table.top();
        table.setFillParent(true);
        countdownLabel=new Label(String.format("Health    %04d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        scoreLabel1=new Label(String.format("Health  %04d",health1),new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        scoreLabel2=new Label(String.format("Health  %04d",health2),new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        moveLabel1=new Label(String.format("Moves left   %02d",moves1),new Label.LabelStyle(new BitmapFont(),Color.GOLDENROD));
        moveLabel2=new Label(String.format("Moves left   %02d",moves2),new Label.LabelStyle(new BitmapFont(),Color.GOLDENROD));

        anglelabel1=new Label(String.format("Angle %02d",angle2),new Label.LabelStyle(new BitmapFont(),Color.GREEN));
        anglelabel2=new Label(String.format("Angle %02d",angle1),new Label.LabelStyle(new BitmapFont(),Color.GREEN));//scoreLabel1=new Label("Health",new Label.LabelStyle(new BitmapFont(),Color.FIREBRICK));
        moveLabel11=new Label("Moves left",new Label.LabelStyle(new BitmapFont(),Color.CHARTREUSE));

        table.add(scoreLabel1).expandX().padTop(10);
        table.add(scoreLabel2).expandX().padTop(10);
        table.row();
        table.add(moveLabel1).expandX().padTop(10);
        table.add(moveLabel2).expandX().padTop(10);
        table.row();
        table.add(anglelabel1).expandX().padTop(10);
        table.add(anglelabel2).expandX().padTop(10);
        stage.addActor(table);

    }




}