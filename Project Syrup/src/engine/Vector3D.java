package engine;
/* medsouz */
public class Vector3D {
	float x;
	float y;
	float z;
	
	public Vector3D(float x1, float y1, float z1){
		x = x1;
		y = y1;
		z = z1;
	}
	
	public static double getDistance(Vector3D pointA, Vector3D pointB){
		return Math.sqrt(Math.pow((pointB.x - pointA.x),2) + Math.pow((pointB.y - pointA.y),2) + Math.pow((pointB.z - pointA.z),2));
	}
}
