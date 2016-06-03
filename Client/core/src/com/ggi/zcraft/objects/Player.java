package com.ggi.zcraft.objects;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector2;

public class Player extends Human{

	
	public Weapon weapon = null;
	
	public double moveAngle = 0;
	public double aimAngle = 0;
	
	public boolean aiming=false;
	
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
		moveAngle=theta;
		super.move(position.x+diff.x, 0, position.z+diff.y);
		
		
			
		}
	
	public void aim(Vector2 joy, double camAngle){
		float thetaM=(float) (Math.atan2(joy.y, joy.x));
		Vector2 diff = new Vector2();
		diff.x = (float) (speed*Math.cos(thetaM));
		diff.y = -(float) (speed*Math.sin(thetaM));
		
		diff.rotate(-(float) camAngle);
		theta = -diff.angle()+90;
		aimAngle=theta;
		super.update();
	}
		
	public void stand(){
		if(weapon!=null){
			weapon.theta=theta;
			weapon.move(this.position.x, 0, this.position.z);
	}
		
	}

	public void animate(){
		if(weapon!=null){
			weapon.theta=theta;
			weapon.move(this.position.x, 0, this.position.z);
			if(weapon.type.equals("2Hand")){
				if(moved&&aiming){
					double AOI = (Math.abs(moveAngle-aimAngle))%360;
					//System.out.println(AOI);
					if(AOI>45&&AOI<135){
						this.controller.setAnimation("Side step right 2 hand", 2, 1, null);
						weapon.controller.setAnimation("Side step right 2 hand", 2,1, null);
					}
					else if(AOI>=135&&AOI<=225){
						this.controller.setAnimation("Back pedal 2 hand", 2, 1, null);
						weapon.controller.setAnimation("Back pedal 2 hand", 2,1, null);
					}
					else if(AOI>225&&AOI<315){
						this.controller.setAnimation("Side step left 2 hand", 2, 1, null);
						weapon.controller.setAnimation("Side step left 2 hand", 2,1, null);
					}
					else{
						this.controller.setAnimation("Run with 2 hand gun Aiming", 2, 1, null);
						weapon.controller.setAnimation("Run with 2 hand gun Aiming", 2,1, null);
					}
					
				}
				else if(moved){
					this.controller.setAnimation("Run with 2 hand gun", 2, 1, null);
					weapon.controller.setAnimation("Run with 2 hand gun", 2,1, null);
				}
				else if(aiming){
					this.controller.setAnimation("Stand shooting 2 hand", 2, 1, null);
					weapon.controller.setAnimation("Stand shooting 2 hand", 2,1, null);
				}
				
				else{
					this.controller.setAnimation("Stand with 2 hand gun", 2, 1, null);
					weapon.controller.setAnimation("Stand with 2 hand gun", 2, 1, null);
				}
			}
		}
	}
}
