/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author kristian
 */
public class RemoveChunk {
    ArrayList<GameObject> object;
    int x, y;
    
    
    public RemoveChunk(ArrayList<GameObject> world_objects_new, int x_new, int y_new) {
        object = world_objects_new;
        x = x_new;
        y = y_new;
    }
    
    public void start() {
        Iterator<GameObject> it_objs = object.iterator();

        while (it_objs.hasNext()) {
            try {
                GameObject temp = it_objs.next();
                if (temp.x >= x*32 &&
                    temp.x <= x*32+32 &&
                    temp.y >= y*32 &&
                    temp.y <= y*32+32) {
                        it_objs.remove();
                }
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}
