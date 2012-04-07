package engine;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Mouse;

public abstract class MouseListener{
	
	public static Map<Integer, MouseListener> buttonMap = new HashMap<Integer, MouseListener>();
	
	public MouseListener(){
		
	}
	
	public static void Tick(){
		Object[] x = buttonMap.keySet().toArray();
		for(int y = 0;y<x.length;y++)
		{
			if(Mouse.isButtonDown(Integer.parseInt(x[y].toString())))
			{
				buttonMap.get(x[y]).buttonPressed(Integer.parseInt(x[y].toString()));
			}
		}
		
	}
	
	public static void regButton(int mouseID, MouseListener ml)
	{
		if(!buttonMap.containsKey(mouseID)){
			buttonMap.put(mouseID, ml);
			if(mouseID == 0){
				System.out.println("Left button has been bound");
			}else if(mouseID == 1){
				System.out.println("Right button has been bound");
			}else{
				System.out.println(mouseID +" has been bound");
			}
			
		}
	}
	
	public static void unregButton(int mouseID, MouseListener ml){
		if(buttonMap.containsKey(mouseID)){
			buttonMap.remove(mouseID);
			if(mouseID == 0){
				System.out.println("Left button has been unbound");
			}else if(mouseID == 1){
				System.out.println("Right button has been unbound");
			}else{
				System.out.println(mouseID +" has been unbound");
			}
		}
	}

	public abstract void buttonPressed(int mouseID);
}