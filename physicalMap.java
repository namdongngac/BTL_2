package GameObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.reflect.GenericArrayType;

import GameEffect.CacheDataLoader;

public class physicalMap extends GameObject{
	public int[][] phys_map;
	private int tileSize;
	
	public physicalMap(float x, float y,GameWord gameWord) {
		// TODO Auto-generated constructor stub
		super(x,y,gameWord);
		
		this.tileSize = 30;
		phys_map = CacheDataLoader.getInstance().getPhysicalMap();
	}
	public int getTileSize() {
		return this.tileSize;
	}
	public Rectangle haveCollisionWithLand(Rectangle rect) {
		int posX1 = rect.x/tileSize;
		posX1 -= 2;
		int posX2 = (rect.x + rect.width)/tileSize;
		posX2 +=2;
		int posY1 = (rect.y + rect.height)/tileSize;
		if(posX1<0) posX1=0;
			
		if(posX2>=phys_map[0].length) posX2 = phys_map[0].length-1;
			
		for(int y = posY1;y<phys_map.length;y++) {
			for(int x = posX1;x <= posX2;x++) {
				if(phys_map[y][x] == 1) {
					//System.out.print(posY1+" "+posX1);
					Rectangle r = new Rectangle((int) getPosX()+x*tileSize, (int) getPosY()+y*tileSize, tileSize, tileSize);
					if(rect.intersects(r)) {
						System.out.println(r.x);
						return r;
					}
						
				}
			}
			//System.out.println("");
		}
		return null;
				
		
	}
	public void draw(Graphics2D g2) {
		g2.setColor(Color.gray);
		for(int i=0;i<phys_map.length;i++) {
			for(int j=0;j<phys_map[0].length;j++) {
				//System.out.print(" "+phys_map[i][j]);
				if(phys_map[j][i]!=0) g2.fillRect((int)(getPosX()+i*tileSize), (int) (getPosY()+j*tileSize), tileSize, tileSize);
			}
			System.out.println("");
				
			
		}
			
			
				//
		
		
	}
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

}
