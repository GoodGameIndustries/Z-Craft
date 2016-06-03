/**
 * 
 */
package com.ggi.zcraft.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.ggi.zcraft.ZCraft;
import com.ggi.zcraft.UI.Joystick;
import com.ggi.zcraft.objects.Moveable;
import com.ggi.zcraft.objects.Player;
import com.ggi.zcraft.objects.Weapon;
import com.ggi.zcraft.objects.Zombie;

/**
 * @author Emmett
 *
 */
public class GameScreen implements Screen, InputProcessor{

	public ZCraft z;
	
	public Player player;
	public Moveable gun;
	public Zombie zomb;
	public Zombie zomb1;
	public Zombie zomb2;
	public Zombie zomb3;
	
	 public PerspectiveCamera cam;
	 public CameraInputController camController;
	 public ModelBatch modelBatch;
	 public Array<ModelInstance> instances = new Array<ModelInstance>();
	 public Environment environment;
	 public float camAngle = 0;
	 
	 private int initScreenX;
	 
	 public float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
	 
	 public Joystick leftJoy;
	 public Joystick rightJoy;
	 
	 public int leftPointer = -1;
	 public int rightPointer = -1;
	 
	 
	 public Rectangle lJBounds = new Rectangle(.1f*h,.1f*h,.2f*h,.2f*h);
	 public Rectangle rJBounds = new Rectangle(w-.3f*h,.1f*h,.2f*h,.2f*h);
	 
	 public SpriteBatch pic = new SpriteBatch();
	
	public GameScreen(ZCraft z){
		this.z=z;
		
		/**temporary player creation*/
		player = new Player(z.assets.man);
		player.weapon=new Weapon(z.assets.TwoHand,0);
		zomb = new Zombie(z.assets.man);
		zomb.target=player;
		zomb1 = new Zombie(z.assets.man);
		zomb1.target=player;
		zomb2 = new Zombie(z.assets.man);
		zomb2.target=player;
		zomb3 = new Zombie(z.assets.man);
		zomb3.target=player;
		
		
		
		/**Joystick setup*/
		
		leftJoy = new Joystick(new Vector2(.1f,.05f),z.assets.joyBG,z.assets.joyKnob);
		rightJoy = new Joystick(new Vector2(.8f,.05f),z.assets.joyBG,z.assets.joyKnob);
	}

	@Override
	public void show() {
		modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        //environment.add(new DirectionalLight().set(0.4f, 0.4f, 0.4f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 3f, 3f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(this);
        
        player.move(0, 0, 0);
        instances.add(player);
        player.weapon.move(0, 0, 0);
        instances.add(player.weapon);
        zomb.move(4,0,0);
        zomb1.move(-4,0,0);
        zomb2.move(0,0,4);
        zomb3.move(0,0,-4);
        instances.add(zomb);
        instances.add(zomb1);
        instances.add(zomb2);
        instances.add(zomb3);
        
        Moveable ground = new Moveable(z.assets.ground);
        ground.move(0, 0, 0);
        instances.add(ground);
        
		
	}

	@Override
	public void render(float delta) {
		//System.out.println(delta);
		camController.update();
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        
        if(leftPointer>-1){player.moveToward(leftJoy.diff,camAngle);player.moved=true;
        }
        if(rightPointer>-1){player.aim(rightJoy.diff,camAngle);player.aiming=true;
        }
        
        player.animate();
        
        zomb.move();
        zomb1.move();
        zomb2.move();
        zomb3.move();
        
        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
        
        
        
       if(player.moved==true){ 
       cam.position.set((float) (player.position.x+3f*Math.sin(Math.toRadians(camAngle))), player.position.y+3f, (float) (player.position.z+3f*Math.cos(Math.toRadians(camAngle))));
       player.moved=false;
       }
       
       
       player.controller.update(delta);
       player.weapon.controller.update(delta);
       zomb.controller.update(delta);
       zomb1.controller.update(delta);
       zomb2.controller.update(delta);
       zomb3.controller.update(delta);
       
		cam.update();
		
		pic.begin();
		leftJoy.draw();
		rightJoy.draw();
		pic.end();
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

	@Override
	public boolean keyDown(int keycode) {
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		initScreenX = screenX;
		screenY = (int) (h-screenY);
		Rectangle touch = new Rectangle(screenX,screenY,1,1);
		if(Intersector.overlaps(touch, lJBounds)){leftPointer = pointer;}
		else if(Intersector.overlaps(touch, rJBounds)){rightPointer = pointer;}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(leftPointer==pointer){leftPointer=-1;leftJoy.reset();}
		else if(rightPointer==pointer){rightPointer=-1;rightJoy.reset();player.aiming=false;}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		screenY = (int) (h-screenY);
		
		
		if(pointer==leftPointer){
			leftJoy.move((float)screenX, (float)screenY);//player.moved=true;
		}
		else if(pointer==rightPointer){
			rightJoy.move((float)screenX, (float)screenY);//player.aiming=true;
		}
		else{
			cam.rotateAround(player.position, new Vector3(0,1,0), -(screenX-initScreenX));
			camAngle -= screenX-initScreenX;
			initScreenX = screenX;
		}
		
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
