package com.ggi.zcraft.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;

public class Zombie extends Human{

	public Human target = null;
	
	public Zombie(Model model) {
		super(model);
		speed=.0175f;
		
	}
	
	public void move(){
		if(target!=null){
			
			Vector3 diff = new Vector3();
				diff.x=target.position.x-position.x;
				diff.z=target.position.z-position.z;
				theta = (float) Math.toDegrees((Math.atan2(diff.x, diff.z)));
			if(diff.len()>.3f){
				diff.setLength(speed);
				this.controller.setAnimation("Zombie Walk", 2, .5f, null);
				super.move(position.x+diff.x, position.y+diff.y, position.z+diff.z);
			}
			else{
				attack();
			}
		}
	}

	private void attack() {
		// TODO Auto-generated method stub
		
	}

}
