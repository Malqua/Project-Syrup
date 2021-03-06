package engine;
/* medsouz */
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

public abstract class KeyListener {
	public static Map<Integer, KeyListener> keyMap = new HashMap<Integer, KeyListener>();
	public KeyListener(){
		//register your keys here!
	}
	
	public static void Tick(){
	Object[] x = keyMap.keySet().toArray();
	for(int y = 0;y<x.length;y++){
		if(Keyboard.isKeyDown(Integer.parseInt(x[y].toString()))){
			keyMap.get(x[y]).keyPressed(Integer.parseInt(x[y].toString()));
		}
	}
	}
	
	public static void registerKey(int keyID, KeyListener keyListen){
		if(!keyMap.containsKey(keyID)){
			keyMap.put(keyID, keyListen);
			System.out.println(Keyboard.getKeyName(keyID)+" has been bound");
		}
	}
	
	public static void unregisterKey(int keyID){
		if(keyMap.containsKey(keyID)){
			keyMap.remove(keyID);
			System.out.println(Keyboard.getKeyName(keyID)+" has been unbound");
		}
	}
	
	public abstract void keyPressed(int keyID);
}
