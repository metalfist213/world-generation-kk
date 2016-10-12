/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 *
 * @author kristian
 */
public class WorldObject extends GameObject {

    //loads all the SPRITES for the objects:
    private static final Texture[] SPRITES = {new Texture("sprites/sprites/tree_01.png"),
        new Texture("sprites/sprites/bush.png"),
        new Texture("sprites/sprites/bush_berries.png"),
        new Texture("sprites/sprites/path.png")};

    static final int OBJ_TREE = 0;
    static final int OBJ_BUSH_1 = 1;
    static final int OBJ_BUSH_BERRIES_1 = 2;
    static final int OBJ_PATH = 3;
    int obj_id = 0;

    public WorldObject(ArrayList<GameObject> object_list, int x_new, int y_new) {
        super(object_list, x_new, y_new);
    }


    enum Type {
        CONTAINER, STATIC, ANIMATED
    };
    boolean solid = true;
    Type type = Type.STATIC;

    public void setAttributes(int obj_id_new) {
        obj_id = obj_id_new;
        sprite = SPRITES[obj_id];

        //Create standard bounds
        bounds = new Rectangle(x, y, 32, 32);

        int w = sprite.getWidth();
        int h = sprite.getHeight();
        //finish off for each individually
        additionalAttributes();
    }

    final void additionalAttributes() {
        switch (obj_id) {
            case OBJ_TREE:
                bounds = new Rectangle(x, 0, 64, 32);
                x_anchor = 16;
                break;

            default:
                break;
        }
        if (!solid) {
            bounds = null;
        }
    }
}
