package com.ggi.zcraft.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector2;

public class Player extends Moveable{

	public float speed = 0;
	public Weapon weapon = null;
	
	
	public Player(Model model) {
		super(model);
		
	}
	
	public void moveToward(Vector2 joy,double camAngle){
		float thetaM=(float) (Math.atan2(joy.y, joy.x));
		Vector2 diff = new Vector2();
		diff.x = (float) (speed*Math.cos(thetaM));
		diff.y = -(float) (speed*Math.sin(thetaM));
		
		diff.rotate(-(float) camAngle);
		theta = -diff.angle()+90;
		
		super.move(position.x+diff.x, 0, position.z+diff.y);
		
		if(weapon!=null){
			weapon.theta=theta;
			weapon.move(this.position.x, 0, this.position.z);
			if(weapon.type.equals("2Hand")){
				this.controller.setAnimation("Run with 2 hand gun", 2, 1/**(float) (speed*Math.abs(Math.sqrt(joy.x*joy.x+joy.y*joy.y)))*/, null);
				weapon.controller.setAnimation("Run with 2 hand gun", 2,1 /**(float) (speed*Math.abs(Math.sqrt(joy.x*joy.x+joy.y*joy.y)))*/, null);
			}
		}
			
		}
		
	public void stand(){
		if(weapon!=null){
			weapon.theta=theta;
			weapon.move(this.position.x, 0, this.position.z);
			if(weapon.type.equals("2Hand")){
				this.controller.setAnimation("Stand with 2 hand gun", 2, 1, null);
				weapon.controller.setAnimation("Stand with 2 hand gun", 2, 1, null);
			}
	}
		
	}

}
