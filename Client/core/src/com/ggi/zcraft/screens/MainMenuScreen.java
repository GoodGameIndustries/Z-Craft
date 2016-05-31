package com.ggi.zcraft.screens;

import com.badlogic.gdx.Screen;
import com.ggi.zcraft.ZCraft;

public class MainMenuScreen implements Screen{

	ZCraft z;
	int i = 0;
	
	public MainMenuScreen(ZCraft z){
		this.z=z;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if(i>30){
		z.setScreen(new GameScreen(z));
		}
		i++;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
