/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import com.mygdx.game.world.WorldObject;
import com.mygdx.game.world.Chunk;
import com.mygdx.game.world.GameObject;
import com.mygdx.game.world.Tile;
import java.util.ArrayList;

/**
 *
 * @author kristian
 */
public class Chunk_Plains extends Chunk {

    public Chunk_Plains(ArrayList<GameObject> object, int x_new, int y_new) {
        super(object, x_new, y_new);
    }

    public void generate(ArrayList <WorldObject> object, int object_new, int count) {
        
    }
    
}
