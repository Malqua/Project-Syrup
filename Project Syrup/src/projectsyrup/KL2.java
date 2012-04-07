package projectsyrup;
import org.lwjgl.input.Keyboard;

import engine.Rendering;

public class KL2{
	
	public static boolean Rainbow = false;
	
	public KL2(){
		
	}
	
	public static void tick(){
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.getEventKey() == Keyboard.KEY_U){
					if(Rainbow == false){
						Rainbow = true;
					}else{
						Rainbow = false;
					}
					System.out.println(Rainbow);
				}
			}
		}
	}
}