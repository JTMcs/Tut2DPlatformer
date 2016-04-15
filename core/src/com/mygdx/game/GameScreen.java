//https://www.youtube.com/watch?v=ygpglRYcNS4&list=PLvnEciYMRSk-l5oKsIsbrBbP2ZJFSZLns part 5
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen extends ApplicationAdapter {

    OrthographicCamera ocCam;
    public Rectangle recBDown;
    SpriteBatch bChar;
    private Character character;

    @Override
    public void create() {
        ocCam = new OrthographicCamera();
        ocCam.setToOrtho(false);
        bChar = new SpriteBatch();
        character = new Character();
        character.setPosition(200, 100);
        System.out.println(Gdx.graphics.getWidth() + "" + Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bChar.setProjectionMatrix(ocCam.combined);
        bChar.begin();
        character.draw1(bChar);
        bChar.end();

        recBDown = new Rectangle(0, 0, Gdx.graphics.getWidth(), 10);

        //Updates
        character.update(Gdx.graphics.getDeltaTime());

        //Boundaries
        if (character.overlaps(recBDown) == 1) {
            character.action(1, 0, 10);
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                character.jump();
            }
        }

        //Controls
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.moveLeft(Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.moveRight(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        bChar.dispose();
    }
}
