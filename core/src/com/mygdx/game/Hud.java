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



public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer health1;
    private Integer health2;
    private Integer moves1;
    private Integer moves2;

    Label countdownLabel;
    Label scoreLabel1;

    Label scoreLabel11;
    Label scoreLabel2;
    Label moveLabel11;
    Label moveLabel1;
    Label moveLabel2;

    public Hud(SpriteBatch s){
        worldTimer=300;
        timeCount=0;
        health1=100;
        health2=100;
        moves1=4;
        moves2=4;


        viewport=new FitViewport(1400,705,new OrthographicCamera());
        stage=new Stage(viewport,s);
        Table table=new Table();
        table.top();
        table.setFillParent(true);
        countdownLabel=new Label(String.format("Health    %03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        scoreLabel1=new Label(String.format("Health  %03d",health1),new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        scoreLabel2=new Label(String.format("Health  %03d",health2),new Label.LabelStyle(new BitmapFont(),Color.BLUE));
        moveLabel1=new Label(String.format("Moves left   %01d",moves1),new Label.LabelStyle(new BitmapFont(),Color.GOLDENROD));
        moveLabel2=new Label(String.format("Moves left   %01d",moves2),new Label.LabelStyle(new BitmapFont(),Color.GOLDENROD));
        //scoreLabel1=new Label("Health",new Label.LabelStyle(new BitmapFont(),Color.FIREBRICK));
        moveLabel11=new Label("Moves left",new Label.LabelStyle(new BitmapFont(),Color.CHARTREUSE));

        table.add(scoreLabel1).expandX().padTop(10);
        table.add(scoreLabel2).expandX().padTop(10);
        table.row();
        table.add(moveLabel1).expandX().padTop(10);
        table.add(moveLabel2).expandX().padTop(10);
        stage.addActor(table);

    }




}