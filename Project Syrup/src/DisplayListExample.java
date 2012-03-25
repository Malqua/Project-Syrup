
//import java.nio.FloatBuffer;

//import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

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

		// Render a single cube
		//renderCube(1);
		drawVertexArray();

		// End the recording of the current display list.
		GL11.glEndList();
	}
	public static void renderCube(float size)
	{
		final float HALF = size/2;
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glColor3f(0, 0, 1);
		GL11.glVertex3f(-HALF, -HALF, -HALF); 
		GL11.glVertex3f(-HALF, size-HALF, -HALF); 
		GL11.glVertex3f(size-HALF, size-HALF, -HALF); 
		GL11.glVertex3f(size-HALF, -HALF, -HALF);  

		GL11.glColor3f(0, 1, 1);
		GL11.glVertex3f(-HALF, -HALF, size-HALF);  
		GL11.glVertex3f(-HALF, size-HALF, size-HALF);  
		GL11.glVertex3f(size-HALF, size-HALF, size-HALF); 
		GL11.glVertex3f(size-HALF, -HALF, size-HALF);  

		GL11.glColor3f(1, 0, 1);
		GL11.glVertex3f(-HALF, size-HALF, -HALF);  
		GL11.glVertex3f(size-HALF, size-HALF, -HALF); 
		GL11.glVertex3f(size-HALF, size-HALF, size-HALF); 
		GL11.glVertex3f(-HALF, size-HALF, size-HALF); 

		GL11.glColor3f(1, 0, 0);
		GL11.glVertex3f(-HALF, -HALF, -HALF);  
		GL11.glVertex3f(size-HALF, -HALF, -HALF); 
		GL11.glVertex3f(size-HALF, -HALF, size-HALF); 
		GL11.glVertex3f(-HALF, -HALF, size-HALF); 

		GL11.glColor3f(1, 1, 0);
		GL11.glVertex3f(-HALF, -HALF, -HALF);  
		GL11.glVertex3f(-HALF, size-HALF, -HALF); 
		GL11.glVertex3f(-HALF, size-HALF, size-HALF); 
		GL11.glVertex3f(-HALF, -HALF, size-HALF);  

		GL11.glColor3f(0, 1, 0);
		GL11.glVertex3f(size-HALF, -HALF, -HALF);  
		GL11.glVertex3f(size-HALF, size-HALF, -HALF); 
		GL11.glVertex3f(size-HALF, size-HALF, size-HALF); 
		GL11.glVertex3f(size-HALF, -HALF, size-HALF); 

		GL11.glEnd();
	}

	public void drawVertexArray(){
		//create geometry buffers
	      FloatBuffer cBuffer = BufferUtils.createFloatBuffer(24);
	      cBuffer.put(1).put(0).put(0);
	      cBuffer.put(0).put(1).put(0);
	      cBuffer.put(0).put(0).put(1);
	      cBuffer.put(1).put(0).put(0);
	      cBuffer.put(0).put(1).put(0);
	      cBuffer.put(0).put(0).put(1);
	      cBuffer.put(1).put(0).put(0);
	      cBuffer.put(0).put(1).put(0);
	      cBuffer.flip();
		
	      FloatBuffer vBuffer = BufferUtils.createFloatBuffer(24);
	      vBuffer.put(0f).put(0f).put(0f);
	      vBuffer.put(0f).put(.4f).put(0f);
	      vBuffer.put(.2f).put(.3f).put(0f);
	      vBuffer.put(.2f).put(-.2f).put(0f);
	      vBuffer.put(.1f).put(-.3f).put(0f);
	      vBuffer.put(-.1f).put(-.2f).put(0f);
	      vBuffer.put(-.2f).put(.2f).put(0f);
	      vBuffer.put(0f).put(.4f).put(0f);
	      vBuffer.flip();
		
		Sphere sphere = new Sphere();
		

		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);


		

		float diffuse0[]={1f, 0f, 0f, 1f};
		float ambient0[]={1f, 0f, 0f, 1f};
		float specular0[]={1f, 0f, 0f, 1f};
		float light0_pos[]={1f, 2f, 3f, 1f};
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_POSITION, light0_pos.length);
		GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_AMBIENT, ambient0.length);
		GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, diffuse0.length);
		GL11.glLightf(GL11.GL_LIGHT0, GL11.GL_SPECULAR, specular0.length);
		GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);


		
		//body 
		GL11.glColor3f(1.0f, 0.6f, 0.6f);
		sphere.draw(0.5f, 10, 10);
		
		//arms
		GL11.glTranslatef(0.5f, 0f, -0.1f);
		sphere.draw(0.2f, 10, 10);
		
		GL11.glTranslatef(-1f, 0f, 0f);
		sphere.draw(0.2f, 10, 10);
		
		//feet
		
		
		GL11.glTranslatef(0.75f, 0f, -0.5f);
		//sphere.draw(0.2f, 5, 3);
		GL11.glColorPointer(3, /* stride */3 << 2, cBuffer);
		GL11.glVertexPointer(3, /* stride */3 << 2, vBuffer);
		GL11.glDrawArrays(GL11.GL_POLYGON, 0, /* elements */15);
		
		GL11.glColor3f(0.75f, 0.3f, 0.3f);
		GL11.glTranslatef(-0.5f, 0f, 0f);
		sphere.draw(0.2f, 5, 3);
		

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LIGHT0);
		GL11.glDisable(GL11.GL_COLOR_MATERIAL);
		
		GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	}

	/**
	 * Render 5x5 cubes centered around the origin. Each
	 * cubes has a slightly different rotation to make this
	 * example look more interesting.
	 */
	public void render() 
	{	
		/*
		// In each render pass, add one degree to rotation
		// angle.
		rotationAngle += 1;
		
		for( int x = 0; x < 5; x++ )
		{
			for( int z = 0; z < 5; z++ )
			{
				// Save current MODELVIEW matrix to 
				// stack.
				GL11.glPushMatrix();

				// position next cube
				GL11.glTranslatef(x*2 - 4,0, z*2 - 4);

				// rotate next cube
				//GL11.glRotatef(x*z*10+rotationAngle, 1, 1, 1);
				
				//x
				GL11.glRotatef(0f, 1, 0, 0);
				//y
				GL11.glRotatef(0f, 0f,1f,0f);
				//z
				GL11.glRotatef(0f, 0f,0f,1f);

				// Call the display list which renders
				// the cube.
				GL11.glCallList(displayListHandle);

				// Remove current MODELVIEW Matrix from
				// stack.
				GL11.glPopMatrix();
			}
		}
		
		*/
		
		//rotationAngle += 1;
		
		GL11.glPushMatrix();
		// rotate next cube
		//GL11.glRotatef(rotationAngle, 1, 1, 1);
		
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
		}
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