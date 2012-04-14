package engine;
/* medsouz */
public abstract class Game {
	public static Game theGame;
	public Game(){
		Game.theGame = this;
	}
	
	public abstract void tick(float delta);
}
