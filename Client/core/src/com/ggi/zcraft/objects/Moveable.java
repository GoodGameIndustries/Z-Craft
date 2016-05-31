package com.ggi.zcraft.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Moveable extends ModelInstance{
	
	public Vector3 position = new Vector3();

	public float theta = 0;
	public boolean moved = false;
	public AnimationController controller;
	
	public Moveable(Model model) {
		super(model);
		controller = new AnimationController(this);
		
	}
	
	public void move(float x, float y, float z){
		position = new Vector3(x,y,z);
		Matrix4 t = new Matrix4();
		this.transform.setToTranslationAndScaling(position, new Vector3(.005f,.005f,.005f));
		this.transform.rotate(new Vector3(0,1,0), theta);
	}

	
	public void update(){
		this.transform.setToTranslationAndScaling(position, new Vector3(.005f,.005f,.005f));
		this.transform.rotate(new Vector3(0,1,0), theta);
	}

	

}
