/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world;

import com.mygdx.game.world.Chunk;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author kristian
 */
public class Tile {
    //Load standard tileset
    static final Texture[] TILE_LIST = {new Texture("sprites/sprites/grass.png"),
                                        new Texture("sprites/sprites/path.png")};
    public static final Texture SPRITE_DIRT = new Texture("sprites/sprites/dirt.png");
    public static final int TILE_GRASS = 0;
    public static final int TILE_PATH = 1;
    Texture tile;
    int tile_type;
    
    int x, y;
    public static final int SIZE = 32;
    
    
    //If another tileset is wanted.
    public Tile(Chunk chunk, int tile_type_new, int x_new, int y_new) {
        tile = TILE_LIST[tile_type_new];
        x = x_new;
        y = y_new;
        
        //Add to world list
    }
    
}
