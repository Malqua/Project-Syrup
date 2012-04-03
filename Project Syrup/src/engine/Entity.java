package engine;
/* medsouz */
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class Entity {
	public static List<Entity> EntityList = new ArrayList<Entity>();
	
	public Vector3D position;
	//public Vector3D size;
	public Vector3D rotation;	

	public Entity(float posx, float posy, float posz){
		this(posx, posy, posz, 0, 0, 0);
	}
	
	public Entity(float posx, float posy, float posz, float rotx, float roty, float rotz){
		Entity.EntityList.add(this);
		position = new Vector3D(posx, posy, posz);
		rotation = new Vector3D(rotx, roty, rotz);
	}

	public void Destroy(){
		Entity.EntityList.remove(this);
	}
	
	public static void RenderTick(){
		for(int i = 0;i<Entity.EntityList.size();i++){
			Entity ent = Entity.EntityList.get(i);
			GL11.glPushMatrix();
			//GL11.glTranslatef(ent.position.x, ent.position.y, ent.position.z);
			GL11.glRotatef(ent.rotation.x, 1f,0f,0f);
			GL11.glRotatef(ent.rotation.y, 0f,1f,0f);
			GL11.glRotatef(ent.rotation.z, 0f,0f,1f);
			//GL11.glTranslatef(-ent.position.x, -ent.position.y, -ent.position.z);
			ent.Render();
			GL11.glPopMatrix();
		}
	}
	
	public void Render(){
		//override me to render this model! :D
	}
	
	public void setRotation(float rotX, float rotY, float rotZ){
		rotation.x = rotX;
		rotation.y = rotY;
		rotation.z = rotZ;
	}
	
	public void setPosition(float posx, float posy, float posz){
		position.x = posx;
		position.y = posy;
		position.z = posz;
	}

}
