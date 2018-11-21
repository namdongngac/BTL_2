/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gameobject;

import com.effect.Animation;
import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import com.userinterface.GameFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class PhysicalMap extends GameObject{

    private char[][] map;
    private int tileSize;
    private FrameImage f;
    
    private ArrayList <Item> items;
    private ArrayList <BreakBrick> bBricks;
    
    private int numberOfEnemy = 0;
    
    
    public PhysicalMap(float x, float y, GameWorld gw) {
        super(x,y,gw);      
        this.tileSize = 60;
        this.map = CacheDataLoader.getInstance().getMap();
        
        numberOfEnemy = CacheDataLoader.getInstance().getCountEnemy();
        System.out.print("Number of Enemy: " + numberOfEnemy);
        items = new ArrayList <Item> ();
        bBricks = new ArrayList <BreakBrick> ();
    }

    public void draw(Graphics2D g2d){      
        Camera camera = getGameWorld().camera;
        
        
        
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                int x = (int)getPosX() + j*tileSize + tileSize/2 - (int) camera.getPosX();
                int y = (int)getPosY() + i*tileSize + tileSize/2;
                //if(x > (int)getPosX() + 21*tileSize - (int) camera.getPosX()) getGameWorld().camera.lock();
                //else getGameWorld().camera.unlock();
                if(map[i][j] == '#'){
                    //g2d.setColor(Color.black);
                    //g2d.fillRect((int)getPosX() + j*tileSize- (int) camera.getPosX(), (int)getPosY() + i*tileSize, tileSize, tileSize);
                    f = CacheDataLoader.getInstance().getFrameImage("wall");
                    f.draw(g2d, x, y);
                }
                if(map[i][j] != '#'){
                    f = CacheDataLoader.getInstance().getFrameImage("ground");
                    f.draw(g2d, x, y);
                }
                if(map[i][j] == '*' || map[i][j] == 'f' || map[i][j] == 'b' || map[i][j] == 's' || map[i][j] == 'x'){
                    //g2d.setColor(Color.gray);
                    //g2d.fillRect((int)getPosX() + j*tileSize- (int) camera.getPosX(), (int)getPosY() + i*tileSize, tileSize, tileSize);
                    f = CacheDataLoader.getInstance().getFrameImage("brick");
                    f.draw(g2d, x, y);
                }
                
            }
        }
        for(int i = 0; i < items.size(); i++){
            if(!items.get(i).isRemoved()){
                items.get(i).draw(g2d);
            }
        }
        for(int i = 0; i < bBricks.size(); i++){
           
            bBricks.get(i).draw(g2d);
        }
    
    }
    public Rectangle haveCol(Rectangle rect, char c){
        
        
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == c){
      
                    Rectangle r = new Rectangle((int)getPosX() + j*tileSize, (int)getPosY() + i*tileSize, tileSize, tileSize);
                    if(rect.intersects(r)) return r;
                }
            }
        }
        return null;
    }
    public Rectangle haveColWithFlame(Rectangle rect){
        
        
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == '#'|| map[i][j] == '*'|| map[i][j] == 'f' || map[i][j] == 'b' || map[i][j] == 's'|| map[i][j] == 'x'){
      
                    Rectangle r = new Rectangle((int)getPosX() + j*tileSize, (int)getPosY() + i*tileSize, tileSize, tileSize);
                    if(rect.intersects(r)) return r;
                }
            }
        }
        return null;
    }
    
    public Rectangle haveColDown(Rectangle rect){
        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;
        
        int posY1 = (rect.y + rect.height)/tileSize;
        if(posX1 < 0) posX1 = 0;
        if(posX2 >= map[0].length) posX2 = map[0].length -1;
        for(int y = posY1; y < map.length; y++){
            for(int x = posX1; x <= posX2; x++){
                if(map[y][x] == '#'|| map[y][x] == '*'|| map[y][x] == 'f' || map[y][x] == 'b' || map[y][x] == 's'|| map[y][x] == 'x'){
                    Rectangle r = new Rectangle((int)getPosX() + x*tileSize, (int)getPosY() + y*tileSize, tileSize, tileSize);
                    if(rect.intersects(r)) return r;
                }
            }
        }
        return null;
    }
    public Rectangle haveColUp(Rectangle rect){
        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;
        int posY1 = rect.y/tileSize;
        
        if(posX1 < 0) posX1 = 0;
        if(posX2 >= map[0].length) posX2 = map[0].length -1;
        for(int y = posY1; y >= 0; y--){
            for(int x = posX1; x <= posX2; x++){
                if(map[y][x] == '#'|| map[y][x] == '*'|| map[y][x] == 'f' || map[y][x] == 'b' || map[y][x] == 's'|| map[y][x] == 'x'){
                    Rectangle r = new Rectangle((int)getPosX() + x*tileSize, (int)getPosY() + y*tileSize, tileSize, tileSize);
                    if(rect.intersects(r)) return r;
                }
            }
        }
        return null;
    }
    public Rectangle haveColRight(Rectangle rect){
        
        int posY1 = rect.y/tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2 += 2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if(posX2 >= map[0].length) posX2 = map[0].length -1;
      
        
        if(posY1 < 0) posY1 = 0;
        if(posY2 >= map.length) posY2 = map.length -1;
        
        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2; y++){
                if(map[y][x] == '#'|| map[y][x] == '*'|| map[y][x] == 'f' || map[y][x] == 'b' || map[y][x] == 's'|| map[y][x] == 'x'){
                    Rectangle r = new Rectangle((int)getPosX() + x*tileSize, (int)getPosY() + y*tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r)) return r;
                }
            }
        }
        return null;
    }
    public Rectangle haveColLeft(Rectangle rect){
        
        int posY1 = rect.y/tileSize;
        posY1 -= 2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2 += 2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;
        if(posX2 < 0) posX2 = 0;
        
        if(posY1 < 0) posY1 = 0;
        if(posY2 >= map.length) posY2 = map.length -1;
        
        for(int x = posX1; x >= posX2; x--){
            for(int y = posY1; y <= posY2; y++){
                if(map[y][x] == '#'|| map[y][x] == '*'|| map[y][x] == 'f' || map[y][x] == 'b' || map[y][x] == 's'|| map[y][x] == 'x'){
                    Rectangle r = new Rectangle((int)getPosX() + x*tileSize, (int)getPosY() + y*tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r)) return r;
                }
            }
        }
        return null;
    }

    public int getPositionX(char c){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == c){
                    return (j*tileSize)+tileSize/2;               
                }
            }
        }
        return 0;
    }
    
    public int getPositionY(char c){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j] == c){
                    return (i*tileSize)+tileSize/2;               
                }
            }
        }
        return 0;
    }
    
    public void breakMap(Rectangle rect){
        int j = (rect.x)/tileSize;
        int i = (rect.y)/tileSize;
        
        System.out.println(i+" "+j);
        
        if(map[i][j] == '*'){
            map[i][j] = ' ';
            //BreakBrick bb = new BreakBrick((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            //bBricks.add(bb);
        }
        if(map[i][j] == 's'){
            map[i][j] = ' ';
            SpeedItem speeditem = new SpeedItem((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            items.add(speeditem);
            //BreakBrick bb = new BreakBrick((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            //bBricks.add(bb);
        }
        if(map[i][j] == 'b'){
            map[i][j] = ' ';
            BombItem bombitem = new BombItem((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            items.add(bombitem);
            //BreakBrick bb = new BreakBrick((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            //bBricks.add(bb);
        }
        if(map[i][j] == 'f'){
            map[i][j] = ' ';
            FlameItem flameitem = new FlameItem((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            items.add(flameitem);
            //BreakBrick bb = new BreakBrick((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            //bBricks.add(bb);
        } 
        if(map[i][j] == 'x'){
            map[i][j] = ' ';
            ExitLevel exitlevel = new ExitLevel((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            items.add(exitlevel);
            //BreakBrick bb = new BreakBrick((int)(getPosX()+j*tileSize)+tileSize/2, (int)(getPosY()+i*tileSize)+tileSize/2, tileSize, tileSize, getGameWorld());
            //bBricks.add(bb);
        }
            
        
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getNumberOfEnemy() {
        return numberOfEnemy;
    }

    public void setNumberOfEnemy(int numberOfEnemy) {
        this.numberOfEnemy = numberOfEnemy;
    }

    
    
    @Override
    public void update() {
        
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).isRemoved()){
                items.remove(i);
            }
            else{
                items.get(i).action();
            }
        }
        for(int i = 0; i < bBricks.size(); i++){
            if(!bBricks.get(i).getBreakBrick().isLastFrame()){
                bBricks.get(i).update();
            }
            else bBricks.remove(i);
        }
        
    }

    

 
}
