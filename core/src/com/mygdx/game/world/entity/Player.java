/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.world.GameObject;
import java.util.ArrayList;

/**
 *
 * @author kristian
 */
public class Player extends Human {

    public Player(ArrayList<GameObject> object, int x, int y) {
        super(object, x, y);
    }

    
    public void moveDirection() {
        axis_x = (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ? 1 : 0)
                - (Gdx.input.isKeyPressed(Input.Keys.LEFT) ? 1 : 0);
        axis_y = (Gdx.input.isKeyPressed(Input.Keys.UP) ? 1 : 0)
                - (Gdx.input.isKeyPressed(Input.Keys.DOWN) ? 1 : 0);
        
    }

    @Override
    public void update() {
        moveDirection();
        move();
    }
}
