package com.ggi.zcraft;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {

	public ZCraft z;
	
	/**Models*/
	public Model man;
	public Model ground;
	public Model TwoHand;

	private boolean loading = false;
	private AssetManager assets;

	public TextureRegion joyBG;
	public TextureRegion joyKnob;
	
	public Assets(ZCraft z){
		this.z=z;
		load();
	}
	
	public void load(){
		
		assets = new AssetManager();
        assets.load("data/Man.g3db", Model.class);
        assets.load("data/Ground.g3db", Model.class);
        assets.load("data/2hand.g3db", Model.class);
        assets.load("data/JoystickBackground.png", Texture.class);
        assets.load("data/JoystickKnob.png", Texture.class);
        loading = true;
        
        assets.finishLoading();
        doneLoading();
		
	}
	
	private void doneLoading() {
		man = assets.get("data/Man.g3db", Model.class);
        ground = assets.get("data/Ground.g3db", Model.class);
        TwoHand = assets.get("data/2hand.g3db", Model.class);
		joyBG=new TextureRegion(assets.get("data/JoystickBackground.png", Texture.class));
		joyKnob=new TextureRegion(assets.get("data/JoystickKnob.png", Texture.class));
        loading = false;
        
	}
	
	
	
}
