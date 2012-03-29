
//import java.nio.FloatBuffer;

//import org.lwjgl.BufferUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class DisplayListExample extends LwjglExampleBase
{
	// handle (id) of the display list to be generated
	private int displayListHandle = -1;

	// angle in degrees required for rotating the cubes
	//private float rotationAngle = 0;

	/**
	 * Sets up LWJGL and OpenGL and renders the 
	 * display list.
	 */
	
	float rotX = 0.0f, rotY = 0.0f, rotZ = 0.0f;
	
	static float Zoom = 0f;
	
	public void setUp() 
	{
		// Set up LWJGL and OpenGL using the
		// base class.
		super.setUp();

		// Generate one (!) display list.
		// The handle is used to identify the
		// list later.
		displayListHandle = GL11.glGenLists(1);

		// Start recording the new display list.
		GL11.glNewList(displayListHandle, GL11.GL_COMPILE);

		//GL11.glTranslatef(-1f, 0, 0);
		drawKirby();

		// End the recording of the current display list.
		GL11.glEndList();
	}

	private static float rot = 0f;
	
	public static void drawKirby(){
		
		Sphere sphere = new Sphere();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
		//body 
		GL11.glPushMatrix();
			GL11.glColor3f(1.0f, 0.6f, 0.6f);
			sphere.draw(0.45f, 10, 10);
		GL11.glPopMatrix();
		
		
		//arms
		GL11.glPushMatrix();
			GL11.glTranslatef(0.45f, 0f, 0f);
			GL11.glRotatef(45f, 0, 1, 0);
			GL11.glScalef(1.2f, 1.0f, 0.8f);
			sphere.draw(0.125f, 10, 10);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
			GL11.glTranslatef(-.45f, 0f, 0f);
			GL11.glRotatef(-45f, 0, 1, 0);
			GL11.glScalef(1.2f, 1.0f, 0.8f);
			sphere.draw(0.125f, 10, 10);
		GL11.glPopMatrix();
			
		//feet
		GL11.glColor3f(1f, 0.1f, 0.1f);
		GL11.glPushMatrix();
			GL11.glTranslatef(.2f, .1f, -.44f);
			GL11.glRotatef(-15f, 0, 0, 1);
			GL11.glScalef(1.0f, 1.5f, 0.5f);
			sphere.draw(0.175f, 10, 10);
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
			GL11.glTranslatef(-.2f, .1f, -.44f);
			GL11.glRotatef(15f, 0, 0, 1);
			GL11.glScalef(1.0f, 1.5f, 0.5f);
			sphere.draw(0.175f, 10, 10);
		GL11.glPopMatrix();

	}
	
	public void render() 
	{			
		//float diffuse0[]={1f, 0f, 0f, 1f};
		//float ambient0[]={1f, 0f, 0f, 1f};
		
		float specular0[]={1f, 0f, 0f, 1f};
		float light0_pos[]={1f, 2f, 3f, 1f};
		
		ByteBuffer temp = ByteBuffer.allocateDirect(16);
		temp.order(ByteOrder.nativeOrder());

		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glEnable(GL11.GL_LIGHTING);
		
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, (FloatBuffer)temp.asFloatBuffer().put(light0_pos).flip());

		/**
		 * These two make shit red, don't uncomment them until you fix it. :D
		 * Also it's not really necessary.
		 * 
		 * Well, Ambient would be for Lava areas and shit. 
		 */
		
		//GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, (FloatBuffer)temp.asFloatBuffer().put(ambient0).flip());
		
		//GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, (FloatBuffer)temp.asFloatBuffer().put(diffuse0).flip());
		
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, (FloatBuffer)temp.asFloatBuffer().put(specular0).flip());
		GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);

		
		GL11.glPushMatrix();
		
		//x
		GL11.glRotatef(rotX, 1, 0, 0);
		//y
		GL11.glRotatef(rotY, 0f,1f,0f);
		//z
		GL11.glRotatef(rotZ, 0f,0f,1f);

		// Call the display list which renders
		// the cube.
		GL11.glCallList(displayListHandle);

		// Remove current MODELVIEW Matrix from
		// stack.
		GL11.glPopMatrix();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHT0);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
		GL11.glDisable(GL11.GL_NORMALIZE);
	}

	public void grabMouse(){
		
		while(Mouse.next()){
			if(/*Mouse.isInsideWindow() == true && */Mouse.isButtonDown(0) == true){
				//Mouse.setGrabbed(true);
				rotY += Mouse.getDX();
				rotX += Mouse.getDY();
				Zoom += Mouse.getDWheel() / -120;
				
				if(Zoom < 0) Zoom = 0;
				if(Zoom > 20) Zoom = 20;
			}
			if(Mouse.isButtonDown(1) == true){
				rotZ += Mouse.getDX();
			}
		}
	}
	
	boolean backward = false;
	
	public void keyboardListener(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) == true){
			
			if(backward == false){rot += 5f;}
			
			if(rot >= 50f){backward = true;}
			
			if(backward == true){rot -= 5f;}
			
			if(rot <= -50f){backward = false;}			
		}
	
		System.out.println(backward);
		
	}
	
	public static float getZoom(){
		return Zoom;
	}
	
	public static void main(String[] argv) 
	{
		DisplayListExample example = new DisplayListExample();
		example.setUp();
		example.gameLoop();
	}
}