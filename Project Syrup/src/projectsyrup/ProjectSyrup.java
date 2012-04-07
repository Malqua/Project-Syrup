package projectsyrup;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import projectsyrup.*;

import engine.Camera;
import engine.KeyListener;
import engine.MouseListener;
import engine.Rendering;

public class ProjectSyrup {

	static float rotX = 0f;
	static float rotY = 0f;
	static float rotZ = 0f;
	public static EntityKirby kirbo = new EntityKirby(1,1,1);
	
	/*
	public static void makeKirby(int i, int j, int k){
		kirbo = new EntityKirby(i, j, k);
	}
	*/
	public static EntityKirby getKirby(){
		return ProjectSyrup.kirbo;
	}
	
	public static void main(String[] argv){
		//makeKirby(1,1,1);
		//final EntityKirby kirb = new EntityKirby(1,1,1);
		Rendering r = new Rendering();
		Thread t = new Thread(r);
		t.start();

		MouseListener.regButton(0, new MouseListener(){

			public void buttonPressed(int mouseID) {
				rotX += Mouse.getDY();
				rotY += Mouse.getDX();
				
				getKirby().setRotation(rotX, rotY, rotZ);

				if(Mouse.isInsideWindow() == true){
					Rendering.Zoom += Mouse.getDWheel() / 120;
					if(Rendering.Zoom < -10){
						Rendering.Zoom = -10;
					}
					if(Rendering.Zoom > 0){
						Rendering.Zoom = 0;
					}

					Camera.getCurrentCamera().setPosition(0, 2 - Rendering.Zoom, 0);
				}
			}
		});

		MouseListener.regButton(1, new MouseListener(){
			public void buttonPressed(int mouseID){
				rotZ += Mouse.getDX();

				getKirby().setRotation(rotX, rotY, rotZ);
			}
		});

		KeyListener.registerKey(Keyboard.KEY_W, new KeyListener(){

			public void keyPressed(int keyID) {
				rotX += 1f;

				getKirby().setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_S, new KeyListener(){

			public void keyPressed(int keyID) {
				rotX -= 1f;

				getKirby().setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_D, new KeyListener(){
			public void keyPressed(int keyID) {
				rotY += 1f;

				getKirby().setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_A, new KeyListener(){
			public void keyPressed(int keyID) {
				rotY -= 1f;

				getKirby().setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_Q, new KeyListener(){
			public void keyPressed(int keyID) {
				rotZ += 1f;

				getKirby().setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_E, new KeyListener(){
			public void keyPressed(int keyID) {
				rotZ -= 1f;

				getKirby().setRotation(rotX, rotY, rotZ);
			}
		});

		KeyListener.registerKey(Keyboard.KEY_R, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(0f, -.5f, -.5f);
				getKirby().setShoeColor(-.5f, -.1f, -.1f);

			}
		});
		KeyListener.registerKey(Keyboard.KEY_G, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(-.2f, 1f, -.2f);
				getKirby().setShoeColor(-.8f, 0.5f, -.1f);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_B, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(-.5f, -.1f, 1f);
				getKirby().setShoeColor(-1f, 0f, .7f);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_P, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(0f, 0f, 0f);
				getKirby().setShoeColor(0f, 0f, 0f);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_O, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(0f, 0f, -.6f);
				getKirby().setShoeColor(-.6f, 0.1f, -.1f);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_I, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(-.2f, -.4f, .4f);
				getKirby().setShoeColor(-.8f, -.1f, .3f);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_K, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(0f, .4f, .4f);
				getKirby().setShoeColor(-.6f, .3f, .3f);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_Y, new KeyListener(){
			public void keyPressed(int keyID){
				getKirby().setBodyColor(0f, .4f, -.6f);
				getKirby().setShoeColor(-.4f, .3f, -.1f);
			}
		});
	}
}
