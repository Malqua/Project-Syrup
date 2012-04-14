package projectsyrup;

import engine.Entity;
import engine.assets.OBJLoader;

//hooray for OBJ loading tests!
public class EntityTeapot extends Entity{

	public EntityTeapot(float posx, float posy, float posz) {
		super(posx, posy, posz);
	}
	
	OBJLoader x = new OBJLoader("src/engine/assets/teapot.obj");
	public void Render(){
		x.render();
	}
}
