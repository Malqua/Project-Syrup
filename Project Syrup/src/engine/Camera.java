package engine;
/* medsouz */
import org.lwjgl.util.glu.GLU;

public class Camera {
	private static Camera currentCamera;
	int posX;
	int posY;
	int posZ;
	int targX;
	int targY;
	int targZ;
	Vector3D vecTarg = null;
	
	public Camera(int x, int y, int z){
		this(x,y,z,0,0,0);
	}
	
	public Camera(int x, int y, int z, int targetx, int targety, int targetz){
		posX = x;
		posY = y;
		posZ = z;
		targX = targetx;
		targY = targety;
		targZ = targetz;
	}
	
	public void setPosition(int x, int y, int z){
		posX = x;
		posY = y;
		posZ = z;
	}
	
	public void setTarget(int x, int y, int z){
		vecTarg = null;
		targX = x;
		targY = y;
		targZ = z;
	}
	
	public void setTarget(Vector3D vector){
		vecTarg = vector;
	}
	
	public static void setCurrentCamera(Camera cam){
		currentCamera = cam;
		Tick();
	}
	
	public static Camera getCurrentCamera(){
		return currentCamera;
	}
	
	public static void Tick(){
	if(currentCamera != null){
		if(currentCamera.vecTarg == null){
			GLU.gluLookAt(currentCamera.posX, currentCamera.posY, currentCamera.posZ, currentCamera.targX, currentCamera.targY, currentCamera.targZ, 0, 1, 1);
		}else{
			GLU.gluLookAt(currentCamera.posX, currentCamera.posY, currentCamera.posZ, currentCamera.vecTarg.x, currentCamera.vecTarg.y, currentCamera.vecTarg.z, 0, 1, 1);
		}
	}
	}
}
