package com.mygdx.game;

import com.mygdx.game.world.World;
import com.mygdx.game.world.Tile;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import networking.MPClient;
import networking.MPServer;
import networking.packet.Packets;

public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    World game_world;
    Tile test_tile;
    OrthographicCamera camera;
    Viewport viewport;
    ShapeRenderer shapeRenderer;

    MPServer server;
    MPClient client;
    boolean isServer;
    public static double deltaTime;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        camera = new OrthographicCamera(800, 600);
        game_world = new OverWorld(camera);
        viewport = new StretchViewport(800, 600, camera);
        isServer = true;
        shapeRenderer = new ShapeRenderer();
        deltaTime = Gdx.graphics.getDeltaTime();
        if (isServer) {
            try {
                server = new MPServer();
            } catch (IOException ex) {
                Logger.getLogger(MyGdxGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try { //Connect to own server
            client = new MPClient();
        } catch (IOException ex) {
            Logger.getLogger(MyGdxGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render() {
        //Update the delta time always:
        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game_world.auto_generate(camera);

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        camera.update();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.begin();
        game_world.draw(batch, camera);
        batch.end();
        shapeRenderer.begin(ShapeType.Line);
        game_world.renderBounds(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
