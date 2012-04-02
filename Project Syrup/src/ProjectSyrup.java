import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.Camera;
import engine.KeyListener;
import engine.MouseListener;
import engine.Rendering;

public class ProjectSyrup {
	
	static float rotX = 0f;
	static float rotY = 0f;
	static float rotZ = 0f;
	
	static int Zoom = 0;
	
	public static void main(String[] argv){
		final EntityKirby kirb = new EntityKirby(1,1,1);
		Rendering r = new Rendering();
		Thread t = new Thread(r);
		t.start();
		
		for(int i = 0; i < Mouse.getButtonCount(); i++) {
			  if(Mouse.isButtonDown(i)) {
			    System.out.println("button pressed: " + i);
			  }
			}
		
		MouseListener.regButton(0, new MouseListener(){

			public void buttonPressed(int mouseID) {
				rotX += Mouse.getDY();
				rotY += Mouse.getDX();
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});
		
		MouseListener.regButton(1, new MouseListener(){
			public void buttonPressed(int mouseID){
				rotZ += Mouse.getDX();
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});

		KeyListener.registerKey(Keyboard.KEY_W, new KeyListener(){

			public void keyPressed(int keyID) {
				rotX += 1f;
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_S, new KeyListener(){

			public void keyPressed(int keyID) {
				rotX -= 1f;
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_D, new KeyListener(){
			public void keyPressed(int keyID) {
				rotY += 1f;
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_A, new KeyListener(){
			public void keyPressed(int keyID) {
				rotY -= 1f;
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_Q, new KeyListener(){
			public void keyPressed(int keyID) {
				rotZ += 1f;
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});
		KeyListener.registerKey(Keyboard.KEY_E, new KeyListener(){
			public void keyPressed(int keyID) {
				rotZ -= 1f;
				
				kirb.setRotation(rotX, rotY, rotZ);
			}
		});
	}
}
