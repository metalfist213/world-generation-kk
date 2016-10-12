/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 *
 * @author kristian
 */
public class GameObject {

    protected float x, y;
    protected int x_anchor, y_anchor;
    protected Vector2 position;
    protected Texture sprite;
    protected Rectangle bounds;
    protected ArrayList<GameObject> object_list; //Reference to the world objects list.

    public GameObject(ArrayList<GameObject> object_list, int x_new, int y_new) {
        this.object_list = object_list;
        x = (float) x_new;
        y = (float) y_new;

        //standard sprite:
        sprite = new Texture("sprites/sprites/no_sprite.png");

        position = new Vector2(x_new, y);

        bounds = new Rectangle(x, y, 32, 32);
    }

    public float distance_to(float x, float y) {
        return position.dst(x, y);
    }

    public void draw(Batch batch, OrthographicCamera camera, GameObject obj) {
        batch.draw(obj.sprite, (obj.x) - obj.x_anchor, (obj.y) - obj.y_anchor);
    }

    void move() {
        //
    }

    public Rectangle returnBounds() {
        return bounds;
    }

    public float getPositionX() {
        return x;
    }

    public float getPositionY() {
        return y;
    }

    public void update() {

    }
    
    public Rectangle getBounds() {
        return bounds;
    }
}
