package maps;
import engine.Entity;
import engine.assets.OBJLoader;


public class test1 extends Entity{

	//float r,g,b,rs,gs,bs;
	
	public test1(int posx, int posy, int posz) {
		super(posx, posy, posz);
	}
	
	
	OBJLoader t1 = new OBJLoader("src/engine/assets/Test_Track_1.obj");
	
	public void Render(){
		t1.render();
	}
}