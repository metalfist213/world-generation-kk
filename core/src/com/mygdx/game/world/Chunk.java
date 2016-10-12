/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author kristian
 */

public class Chunk {
    //each chunk consists of 32 * 32 tiles
    public int x, y;
    public static final int SIZE = 32;
    static int lowest_x = 0; //for generation the terrain good
    static int lowest_y = 0;
    long x_seed;
    long y_seed;
    int[][] tile = new int[32][32]; // a 2 dimensional array for all the tiles.
    
    public RandomXS128 random_seed;
    
    //Constructor
    public Chunk(ArrayList<GameObject> object, int x_new, int y_new) {
        x = x_new;
        y = y_new;
        
        if (x<lowest_x) {
            lowest_x = x;
        }
        if (y<lowest_y) {
            lowest_y = y;
        }
        
        //make unique seed:
        String str_seed = String.valueOf(World.SEED);
        String seed_x = (str_seed.substring(0, str_seed.length()/2));
        String seed_y = (str_seed.substring(str_seed.length()/2, str_seed.length()));
        seed_x = String.valueOf(Long.parseLong(seed_x)+x);
        seed_y = String.valueOf(Long.parseLong(seed_y)+y);
        
        x_seed = Long.parseLong(seed_x); //This is for the road code fx
        y_seed = Long.parseLong(seed_y); //This is for the road code also
        
        long seed = Long.parseLong(seed_x+seed_y);
        random_seed = new RandomXS128(seed);
        
        generate(object);
    }
    
    public void generate(ArrayList <GameObject> object) {
        
        generate_fill(object, Tile.TILE_GRASS);
        
        generate_objects(object, WorldObject.OBJ_TREE, 50);
        generate_objects(object, WorldObject.OBJ_BUSH_1, 30);
        generate_objects(object, WorldObject.OBJ_BUSH_BERRIES_1, 15);
        
        //Ends with the road code all the time.
        generate_realistic_road(object);
        

    }
    
    public void generate_realistic_road(ArrayList <GameObject> object) {
        if(y_seed % 200 == 0) { 
            generate_path(object, "0,0,0,"
                                + "2,2,2,"
                                + "0,2,0");
            if(x_seed % 50 == 0) {
            generate_path(object, "0,0,2,"
                                + "0,0,0,"
                                + "0,0,0"); 
            }
        }
        if(y_seed%200>49 && y_seed%200<200 && x_seed%50==0) {
            generate_path(object, "0,2,0,"
                                + "0,2,0,"
                                + "0,2,0"); 
        }
        if(y_seed%200 == x_seed%50) {
            if (x_seed%50 == 49) {
            generate_path(object, "0,0,0,"
                                + "0,2,2,"
                                + "2,0,0"); 
            }
            else {
            generate_path(object, "0,0,2,"
                                + "0,2,0,"
                                + "2,0,0"); 
            }
        }
        if(x_seed%50 == y_seed%200-49) {
            generate_path(object, "0,2,0,"
                                + "2,2,0,"
                                + "0,0,0"); 
        }
        if(x_seed%50==y_seed%200+1) {
            generate_path(object, "1,0,0,"
                                + "0,0,0,"
                                + "0,0,0"); 
        }
        if(x_seed%50==y_seed%200-1) {
            generate_path(object, "0,0,0,"
                                + "0,0,0,"
                                + "0,0,1"); 
        }
    }
    
    public void generate_fill(ArrayList <GameObject> object, int tile_id) {
        //place value for grass in 2 dimensional array
        for(int yy = 0; yy < tile[0].length; yy++) {
            for(int xx = 0; xx < tile[0].length; xx++) {
                tile[yy][xx] = tile_id;
            }
        }
    }
    
//    public void reset_corrected_tiles() {
//        corrected_tiles = 0;
//    }
    
//    public void settle_tiles(ArrayList<Tile> tile_list) {
//        if(corrected_tiles < tile_list.size()) {
//            int x = tile_list.get(corrected_tiles).x;
//            int y = tile_list.get(corrected_tiles).y;
//            int tileset = tile_list.get(corrected_tiles).tile_type;
//            //standard ones
//            boolean left = position_at(tile_list, tileset, x-1, y);
//            boolean right = position_at(tile_list, tileset, x+1, y);
//            boolean up = position_at(tile_list, tileset, x, y+1);
//            boolean down = position_at(tile_list, tileset, x, y-1);
//            
//            //odd ones:
//            boolean up_right = position_at(tile_list, tileset, x+1, y+1);
//            boolean down_right = position_at(tile_list, tileset, x+1, y-1);
//            boolean up_left = position_at(tile_list, tileset, x-1, y+1);
//            boolean down_left = position_at(tile_list, tileset, x-1, y-1);
//            
//            int position_new;
//            
//            if(right == true && down == true) {
//                position_new = Tile.UPPER_LEFT;
//                if(left==true) {
//                    position_new = Tile.UP;
//                    if(up==true) {
//                        position_new = Tile.MIDDLE;
//                    }
//                }
//                else if (up == true) {
//                    position_new = Tile.LEFT;
//                }
//            }
//            else if(left == true && down == true) {
//                position_new = Tile.UPPER_RIGHT;
//                if(up == true) {
//                    position_new = Tile.RIGHT;
//                }
//            }
//            else if(left == true && up == true) {
//                position_new = Tile.BOTTOM_RIGHT;
//                if(right == true) {
//                    position_new = Tile.BOTTOM;
//                }
//            }
//            else if(up == true && right == true) {
//                position_new = Tile.BOTTOM_LEFT;
//                if(down == true) {
//                    position_new = Tile.LEFT;
//                }
//            }
//            else {
//                position_new = 0;
//            }
//            //update tile:
//            int xx = position_new%6;
//            int yy = (int) position_new/6;
//            
//            tile_list.get(corrected_tiles).tile = new TextureRegion(Tile.TILE_LIST[tile_list.get(corrected_tiles).tile_type], xx*32, yy*32, 32, 32);
//            corrected_tiles++;
//        }
//    }
    
    public boolean position_at(ArrayList<Tile> tile_list, int own_texture, int x, int y) {
        try {
            for(int i = 0; i < tile_list.size(); i++) {
                if (tile_list.get(i).x==x &&tile_list.get(i).y==y) {
                    return true;
                }
            }
        }
        catch(Exception e) {
            return false;
        }
        return false;
    }
    
    //generate said object in chunk:
    public void generate_objects(ArrayList <GameObject> object, int object_new, int count) {
        for(int i = 0; i < count; i++) {
            int rand_x = random_seed.nextInt(32)+x*32;
            int rand_y = random_seed.nextInt(32)+y*32;
            
            GameObject o = object_at_position(object, rand_x*32, rand_y*32);
            if (o != null) {
                object.remove(o);
            }
            
            //place in list:
            if(object_new != -1) {
                WorldObject temp = new WorldObject(object, rand_x*32, rand_y*32);
                temp.setAttributes(object_new);
                object.add(temp);
            }
        }
    }
    
    public void generate_path(ArrayList <GameObject> object, String pos_map) {
        /*
        pos_map example:
        "n,n,n
         n,n,n
         n,n,n"
        if n=0 nothing
        if n=1 place on tile at pos
        if n=2 connect to with 2 tiles
        */
        String[] map = pos_map.split(",", 9);

        int x_start = 0, y_start = 0;
        int x_place;
        int y_place;
        for (int i = 0; i < map.length; i++) {
            if("2".equals(map[i])) {
                int temp = i % 3;
                int direction_x = 1-(temp);
                int direction_y = -1 + (int) i/3;

                int pos_x = (temp*(16)-(temp-1));
                if (pos_x>0) pos_x--;
                int pos_y = (31 - (int) ((i / 3)*16)-1);
                if(pos_y < 0) pos_y=0;
                if(pos_y == 14) pos_y++;

                
                
                do {
//                    place_object(object, WorldObject.OBJ_PATH, pos_x+x*32, pos_y+y*32);
//                    place_object(object, WorldObject.OBJ_PATH, pos_x+x*32+1, pos_y+y*32);
//                    place_object(object, WorldObject.OBJ_PATH, pos_x+x*32+1, pos_y+y*32-1);
//                    place_object(object, WorldObject.OBJ_PATH, pos_x+x*32, pos_y+y*32-1);
                    tile[pos_y][pos_x] = Tile.TILE_PATH;
                    tile[pos_y+1][pos_x+1] = Tile.TILE_PATH;
                    tile[pos_y][pos_x+1] = Tile.TILE_PATH;
                    tile[pos_y+1][pos_x] = Tile.TILE_PATH;
                    
                    
                    
                    pos_x+=direction_x;
                    pos_y+=direction_y;
                    
                    
                    //remove objects there:
                    for(int yy = -2; yy < 2; yy++) {
                        for(int xx = -2; xx < 2; xx++) {
                            place_object(object, -1, (xx+pos_x)*32+x*32, (yy+pos_y)*32+x*32);
                        }
                    }
                } while((pos_x != 15 || pos_y != 15));
            }
            else if("1".equals(map[i])) {
                int temp = i % 3;
                int direction_x = 1-(temp);
                int direction_y = -1 + (int) i/3;

                int pos_x = (temp*(16)-(temp-1));
                if (pos_x>0) pos_x--;
                int pos_y = (31 - (int) ((i / 3)*16)-1);
                if(pos_y < 0) pos_y=0;
                if(pos_y == 14) pos_y++;
                if(pos_x == 30) {
                    pos_x = 31;
                }
                if(pos_y == 30) {
                    pos_y++;
                }
                
                tile[pos_y][pos_x] = Tile.TILE_PATH;
            }
            else {
                
            }
        }
    }
    
    public void place_object(ArrayList <GameObject> object, int object_new, int x, int y) {
        GameObject o = object_at_position(object, x, y);
        if (o != null) {
            object.remove(o);
        }

        //place in list:
        if(object_new != -1) {
            WorldObject temp = new WorldObject(object, x, y);
            temp.setAttributes(object_new);
            object.add(temp);
        }
    }
    
    public GameObject object_at_position(ArrayList <GameObject> object, int x, int y) {
        for(GameObject o : object) {
            try {
                if(x == o.x && y == o.y) {
                    return o;
                }
            }
            catch(NullPointerException n) {
                return null;
            }
        }
        return null;
    }

    
    public void remove(ArrayList<GameObject> object) {
        RemoveChunk c = new RemoveChunk(object, x, y);
        c.start();
    }
    
    public void draw(Batch batch, OrthographicCamera camera) {
        for(int yy = 0; yy < tile[0].length; yy++) {
            for(int xx = 0; xx < tile[0].length; xx++) {
                batch.draw(Tile.TILE_LIST[tile[yy][xx]], (xx)*Tile.SIZE+x*32*32, (yy)*Tile.SIZE+y*32*32);
            }
        }
    }
    
    public int getPositionX() {
        return (x);
    }
    public int getPositionY() {
        return (y);
    }
}

