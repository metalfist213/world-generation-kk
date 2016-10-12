/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.world.GameObject;
import java.util.ArrayList;

/**
 *
 * @author kristian
 */
public class Human extends Entity {

    boolean hasPenis;

    public Human(ArrayList<GameObject> object, int x, int y) {
        super(object, x, y);
    }

}
