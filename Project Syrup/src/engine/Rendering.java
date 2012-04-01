package engine;
/* medsouz */
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glViewport;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Rendering implements Runnable{
	public void run(){
		try{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.setVSyncEnabled(true);
			Display.create();
			Display.setFullscreen(true);
			Camera.setCurrentCamera(new Camera(0, 2, 0, 0,0,0));
			initGL();
			while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				glLoadIdentity();
				Camera.Tick();
				renderTick();
				Display.sync(60);
				Display.update();
			}
		}catch(Exception err){
			err.printStackTrace();
			System.exit(0);
		}
	}
	
	public void renderTick(){
		float specular0[]={1f, 0f, 0f, 1f};
		float light0_pos[]={1f, 2f, 3f, 1f};
		ByteBuffer temp = ByteBuffer.allocateDirect(16);
		temp.order(ByteOrder.nativeOrder());
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, (FloatBuffer)temp.asFloatBuffer().put(light0_pos).flip());
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, (FloatBuffer)temp.asFloatBuffer().put(specular0).flip());
		GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
		GL11.glPushMatrix();
		Entity.RenderTick();
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHT0);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
		GL11.glDisable(GL11.GL_NORMALIZE);
	}
	
	public void initGL(){
		glClearColor (.8f, .9f, 1f, 0f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		glViewport(0, 0, 800, 600);
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective( 45.0f, (float)800/(float)600, 1.0f, 200.0f ); 
		glMatrixMode(GL11.GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
		glShadeModel (GL_SMOOTH);
	}
}
