/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.world.GameObject;
import java.util.ArrayList;

/**
 *
 * @author kristian
 */
public class Entity extends GameObject {

    int hp;
    int max_hp;
    int speed;
    int alignment; //evil, neutral or peaceful.
    int axis_x, axis_y;
    float anim_speed;
    Texture sprite_whole;
    TextureRegion[][] frames;
    TextureRegion currentFrame;

    Animation animation_run[];
    Animation current_animation[];
    int dir = 0;

    float state_time;

    public Entity(ArrayList<GameObject> object, int x, int y) {
        super(object, x, y);
        setSprite(new Texture("sprites/entities/human/male.png"));
    }

    public final void setSprite(Texture sprite) {
        anim_speed = 0.2f;
        bounds = new Rectangle(x, y, 32, 32);
        animation_run = new Animation[4];
        current_animation = animation_run;
        sprite_whole = sprite;
        frames = new TextureRegion[4][3];
        for (int yy = 0; yy < 4; yy++) {
            for (int xx = 0; xx < 3; xx++) {
                frames[yy][xx] = new TextureRegion(sprite_whole, xx * 32, yy * 32, 32, 32);
            }
            animation_run[yy] = new Animation(0.1f, frames[yy]);
        }
        

        state_time = 0f;
    }

    public void move() {
        x += axis_x * 200 * MyGdxGame.deltaTime;
        y += axis_y * 200 * MyGdxGame.deltaTime;

        bounds.x = x;
        bounds.y = y;
        
        
        //get direction.
        if (axis_y < 0) {
            dir = 0;
        } else if (axis_y > 0) {
            dir = 3;
        } else if (axis_x > 0) {
            dir = 2;
        } else if (axis_x < 0) {
            dir = 1;
        }
        
        //stops animation if not moving:
        if (axis_x == 0 && axis_y == 0) {
            current_animation[dir].setFrameDuration(0);
        } else {
            current_animation[dir].setFrameDuration(anim_speed);
        }
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void draw(Batch batch, OrthographicCamera camera, GameObject obj) {
        state_time += Gdx.graphics.getDeltaTime();
        currentFrame = current_animation[dir].getKeyFrame(state_time, true);

        batch.draw(currentFrame, x, y);
    }

    @Override
    public float getPositionX() {
        return x;
    }

    @Override
    public float getPositionY() {
        return y;
    }

}
