package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;



public class TiledObjectUtil {
    private final static float PPM = 32;
    public static void parseTiledObjectLayer(World world, MapObjects mapObjects){
        for(MapObject mapObject1:mapObjects){
            Shape shape;
            if(mapObject1 instanceof PolylineMapObject){
                shape = createPolyline((PolylineMapObject) mapObject1);

            }
            else{
                shape=null;
            }
            Body body;
            BodyDef bdef=new BodyDef();
            bdef.type=BodyDef.BodyType.StaticBody;
            body=world.createBody(bdef);
            body.createFixture(shape,1.1f);
            shape.dispose();


        }

    }
    private static ChainShape createPolyline(PolylineMapObject obj){
        float[] vertices=obj.getPolyline().getTransformedVertices();
        Vector2[] worldVertices=new Vector2[vertices.length/2];
        for(int i=0;i<worldVertices.length;i++){
            worldVertices[i]=new Vector2(vertices[i*2]/PPM,vertices[i*2+1]/PPM);



        }
        ChainShape cs=new ChainShape();
        cs.createChain(worldVertices);

        return cs;
    }
}
