// import GL11 statically which makes the GL code
// more readable
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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
 
// some more LWJGL imports that I omitted for brevity.
 
public abstract class LwjglExampleBase 
{
	/** width of window */
	private int viewportWidth = 800;
 
	/** height of window */
	private int viewportHeight = 600;
 
	/**
	 * Rudimentary OpenGL setup with LWJGL. Use perspective
	 * projection with a field of view of 45 degrees. 
	 */
	public void setUp() 
	{
		// set up LWJGL display
		try
		{
			Display.setDisplayMode(new DisplayMode(viewportWidth, viewportHeight));
			Display.create();
			Display.setVSyncEnabled(true);
		}
		catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
 
		// init viewport and projection matrix
	
		glClearColor (.8f, .9f, 1f, 0f);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		glViewport(0, 0, viewportWidth, viewportHeight);
		glMatrixMode(GL11.GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective( 45.0f, (float)viewportWidth/(float)viewportHeight, 1.0f, 200.0f ); 
		glMatrixMode(GL11.GL_MODELVIEW);
 
		glEnable(GL_DEPTH_TEST);
		glShadeModel (GL_SMOOTH);

	}
 
	/**
	 * Game loop is basically an infinite loop that runs
	 * until the window is closed. It calls the abstract
	 * render() method which is to be implemented by the
	 * specific example class. 
	 */
	public void gameLoop()
	{
		while (!Display.isCloseRequested())
		{
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			GLU.gluLookAt(0, 2 + DisplayListExample.getZoom(), 0, 
					0, 0, 0, 
					0, 0, 1);
 
                        // call the abstract render method implemented by the
                        // extending class (eg. example, tutorial, etc.)
			
			render(); 
			grabMouse();
			keyboardListener();
			//GL11.glTranslatef(1f, 0, 0);
			//DisplayListExample.drawKirby();

                        // sync to 60 frames per second
			Display.update();
			Display.sync(60);
		}
 
		Display.destroy();
	}
 
        /**
         * Abstract render method which is supposed to be
         * implemented by the extending tutorial or example
         * class.
         */
	public abstract void render();
	public abstract void grabMouse();
	public abstract void keyboardListener();
 
	public int getViewportWidth() {
		return viewportWidth;
	}
 
	public void setViewportWidth(int viewportWidth) {
		this.viewportWidth = viewportWidth;
	}
 
	public int getViewportHeight() {
		return viewportHeight;
	}
 
	public void setViewportHeight(int viewportHeight) {
		this.viewportHeight = viewportHeight;
	}
}