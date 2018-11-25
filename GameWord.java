package GameObject;

import java.awt.Graphics2D;

public class GameWord {
	public bombMan bombMan ;
	public physicalMap physicalMap ;
	public void Update() {
		bombMan.update();
	}
	public void Render(Graphics2D g2) {
		bombMan.draw(g2);
		physicalMap.draw(g2);
	}
	public GameWord() {
		bombMan = new bombMan(300, 300, 75, 75, 0.1f,this);
		physicalMap = new physicalMap(100, 100,this);
	}

}
