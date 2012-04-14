package engine.assets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import engine.Vector3D;

public class OBJLoader {
	public List<Vector3D> triangles = new ArrayList<Vector3D>();
	public List<Vector3D> vertexes = new ArrayList<Vector3D>();
	
	public OBJLoader(String filepath){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
			String line;
			while((line = reader.readLine()) != null){
				String[] c = line.split(" ");
				if(line.startsWith("f ")){
					if(!c[1].contains("/")){//Vertex
						triangles.add(new Vector3D(Float.parseFloat(c[1]), Float.parseFloat(c[2]), Float.parseFloat(c[3])));
					}
					if(c[1].split("/").length == 2){//Vertex/texture-coordinate
						
					}
					if(c[1].split("/").length == 3){//Vertex/texture-coordinate/normal NOTE: texture-coordinate is optional and may be blank in this situation!
						triangles.add(new Vector3D(Float.parseFloat(c[1].split("/")[0]), Float.parseFloat(c[2].split("/")[0]), Float.parseFloat(c[3].split("/")[0])));
					}
				}
				if(line.startsWith("v ")){
					vertexes.add(new Vector3D(Float.parseFloat(c[1]), Float.parseFloat(c[2]), Float.parseFloat(c[3])));
				}
			}
			System.out.println("Loaded "+triangles.size()+" faces");	
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	public void render(){
		GL11.glPushMatrix();
		GL11.glColor3f(255,255,255);
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(int x = 0;x<triangles.size();x++){
			GL11.glVertex3f((float)(vertexes.get((int)triangles.get(x).x-1).x * 0.25), (float)(vertexes.get((int)triangles.get(x).x-1).y * 0.25), (float)(vertexes.get((int)triangles.get(x).x-1).z * 0.25));
            GL11.glVertex3f((float)(vertexes.get((int)triangles.get(x).y-1).x * 0.25), (float)(vertexes.get((int)triangles.get(x).y-1).y * 0.25), (float)(vertexes.get((int)triangles.get(x).y-1).z * 0.25));
            GL11.glVertex3f((float)(vertexes.get((int)triangles.get(x).z-1).x * 0.25), (float)(vertexes.get((int)triangles.get(x).z-1).y * 0.25), (float)(vertexes.get((int)triangles.get(x).z-1).z * 0.25));
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
}
