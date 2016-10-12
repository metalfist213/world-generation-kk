/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.RandomXS128;
import com.mygdx.game.world.entity.Entity;
import com.mygdx.game.world.entity.Human;
import com.mygdx.game.world.entity.Player;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import javafx.scene.shape.Shape;

/**
 *
 * @author kristian
 */
public class World {

    final public static long SEED = 12000200;
    RandomXS128 random_seed = new RandomXS128(SEED);
    ArrayList<Chunk> chunk_list = new ArrayList<Chunk>(4);
    Chunk chunk_test;
    int chunk_x_last = 0;
    int chunk_y_last = 0;
    int chunk_x;
    int chunk_y;
    boolean has_updated = false;
    int view_distance = 2; //How far is generated to all the directions:
    Player player;
    OrthographicCamera camera;

    //All objects
    ArrayList<GameObject> object = new ArrayList<GameObject>(0);

    //initialize the world
    public World(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void auto_generate(OrthographicCamera camera_new) {
        camera = camera_new;
        chunk_x = (int) (camera.position.x / (32 * 32)); //Calculates which chunk we are in
        chunk_y = (int) (camera.position.y / (32 * 32));

        //System.out.println(Gdx.graphics.getFramesPerSecond());
        if (!has_updated) { //Update!
            has_updated = true;
            chunk_x_last = chunk_x;
            chunk_y_last = chunk_y;
            updateChunk();
            settle_objects(object);

            player = new Player(object, 0, 0);
            object.add(player);

        }
        camera.position.set(player.getPositionX(), player.getPositionY(), 0);
        //if changes has been made:
        if (chunk_x_last != chunk_x || chunk_y_last != chunk_y) {
            updateChunk();
        }
        //update and remove distanced objects!
        if (object.size() > 0) {
            update_objects();
        }

    }

    //Sort the objects by y
    class YComparatorHighToLow implements Comparator<GameObject> {

        @Override
        public int compare(GameObject loc1, GameObject loc2) {
            return (int) (loc2.getPositionY() - loc1.getPositionY());
        }
    }

    public void settle_objects(ArrayList<GameObject> object) {
        object.sort(new YComparatorHighToLow());
    }

    public void update_objects() {
        Iterator<GameObject> iter = object.iterator();

        while (iter.hasNext()) {
            GameObject o = iter.next();

            if (o.distance_to(camera.position.x, camera.position.y) > 100 * 32 && !(o instanceof Player)) {
                iter.remove();
            }

            //If entity, update move code:
            o.update();
        }
        settle_objects(object);
    }

    //Chunk coordinates
    public void removeChunk(Chunk c) {
        chunk_list.remove(c);
    }

    public void addChunk(int x, int y) {
        chunk_list.add(new Chunk(object, x, y));
    }

    public void updateChunk() {
        int h_dir = chunk_x - chunk_x_last;
        int v_dir = chunk_y - chunk_y_last;

        for (int yy = chunk_y - view_distance; yy < view_distance + chunk_y; yy++) {
            for (int xx = chunk_x - view_distance; xx < view_distance + chunk_x; xx++) {
                boolean alreadyThere = false;
                for (Chunk c : chunk_list) {
                    if (xx == c.x && yy == c.y) { // Check if already existing
                        alreadyThere = true;
                    }
                }
                if (!alreadyThere) { //place chunk if non existing.
                    addChunk(xx, yy);
                }
            }
        }

        //remove chunks out of view_distance:
        Iterator<Chunk> iter = chunk_list.iterator();
        while(iter.hasNext()) {
            Chunk c = iter.next();
            int xx = c.x - chunk_x;
            int yy = c.y - chunk_y;
            
            //abs so we always have a positive length:
            xx = Math.abs(xx);
            yy = Math.abs(yy);
            
            if(yy>view_distance || xx>view_distance) {
                iter.remove();
            }
            
            //if((Math.abs(xx)))

        }

        chunk_x_last = chunk_x;
        chunk_y_last = chunk_y;

        //Sort objects by y
        settle_objects(object);
    }

    public void draw(Batch batch, OrthographicCamera camera) {
        for (Chunk chunk_list1 : chunk_list) {
            chunk_list1.draw(batch, camera);
        }
        for (GameObject obj : object) {
            obj.draw(batch, camera, obj);
        }
    }

    public void renderBounds(ShapeRenderer renderer) { //For rendering the collision surfaces.
        renderer.setColor(Color.RED);
        for (GameObject obj : object) {
            renderer.rect(obj.x + obj.x_anchor, obj.y + obj.y_anchor, obj.bounds.width, obj.bounds.height);
        }
        renderer.rect(player.getPositionX(), player.getPositionY(), 32, 32);
        renderer.setColor(Color.WHITE);
    }
}
