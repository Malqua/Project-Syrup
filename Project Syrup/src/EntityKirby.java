import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import engine.Entity;


public class EntityKirby extends Entity{

	public EntityKirby(int posx, int posy, int posz) {
		super(posx, posy, posz);
	}
	
	public void Render(){
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

}
