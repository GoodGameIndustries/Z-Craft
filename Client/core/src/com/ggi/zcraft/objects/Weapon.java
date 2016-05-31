/**
 * 
 */
package com.ggi.zcraft.objects;

import com.badlogic.gdx.graphics.g3d.Model;

/**
 * @author Emmett
 *
 */
public class Weapon extends Moveable{

	public String type = "";
	
	public Weapon(Model model,int modelNo) {
		super(model);
		getModelInfo(modelNo);
	}

	
	/**Storange for all weapon info*/
	private void getModelInfo(int modelNo) {
		switch(modelNo){
		case 0:type = "2Hand"; break;
		}
		
	}

}
