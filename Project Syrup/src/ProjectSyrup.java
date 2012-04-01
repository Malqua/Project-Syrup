import engine.Rendering;

public class ProjectSyrup {
	public static void main(String[] argv){
		EntityKirby kirb = new EntityKirby(1,1,1);
		Rendering r = new Rendering();
		Thread t = new Thread(r);
		t.start();
	}
}
