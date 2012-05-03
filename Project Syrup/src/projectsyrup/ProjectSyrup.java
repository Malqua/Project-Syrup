package projectsyrup;
import maps.test1;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.Camera;
import engine.KeyListener;
import engine.MouseListener;
import engine.Rendering;

public class ProjectSyrup {

	static int rotX = 0;
	static int rotY = 0;
	static int rotZ = 0;
	public static EntityKirby kirbo = new EntityKirby(10,10,10);
	
	public static void makeKirby(int i, int j, int k){
		kirbo = new EntityKirby(i, j, k);
	}

	public static EntityKirby getKirby(){
		return ProjectSyrup.kirbo;
	}
	
	public static void main(String[] argv){
		final test1 test1 = new test1(1,1,1);
		Rendering r = new Rendering();
		Thread t = new Thread(r);
		t.start();

		MouseListener.regButton(0, new MouseListener(){

			public void buttonPressed(int mouseID) {
				rotX += Mouse.getDY();
				rotY += Mouse.getDX();
				
				Camera.getCurrentCamera().setPosition(0, 0, 0);

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

				Camera.getCurrentCamera().setPosition(0, 0, 0);
			}
		});
	}
}
