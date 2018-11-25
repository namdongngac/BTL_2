/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.userinterface;

import com.gameobject.GameWorld;
import java.awt.event.KeyEvent;

/**
 *
 * @author admin
 */

// quản lí sự kiện từ keyboard
public class InputManagement{
    
    private GamePanel gp;
    private GameWorld gameWorld;
    
    public InputManagement(GameWorld gameWorld, GamePanel gp){
        this.gameWorld = gameWorld;
        this.gp = gp;
    }
    // quản lí nhấn key
    
    public void processKeyPressed(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_W:
                gameWorld.player.setDirection(gameWorld.player.DIR_UP);              
                gameWorld.player.move();              
                break;
            case KeyEvent.VK_A:
                gameWorld.player.setDirection(gameWorld.player.DIR_LEFT);           
                gameWorld.player.move();         
                break;
            case KeyEvent.VK_S:
                gameWorld.player.setDirection(gameWorld.player.DIR_DOWN);
            
                gameWorld.player.move();
                
                break;
            case KeyEvent.VK_D:
                gameWorld.player.setDirection(gameWorld.player.DIR_RIGHT);
         
                gameWorld.player.move();
                
                
                
                break;
            case KeyEvent.VK_SPACE:
                //gameWorld.player.plantBomb();
                break;
            case KeyEvent.VK_UP:
                gameWorld.player.setDirection(gameWorld.player.DIR_UP);              
                gameWorld.player.move();
                break;    
            case KeyEvent.VK_LEFT:
                gameWorld.player.setDirection(gameWorld.player.DIR_LEFT);           
                gameWorld.player.move();
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.setDirection(gameWorld.player.DIR_DOWN);
            
                gameWorld.player.move();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.setDirection(gameWorld.player.DIR_RIGHT);
         
                gameWorld.player.move();
                break;
            case KeyEvent.VK_ENTER:
                
                break;
        }
    }
    
    // quản lí thả key
    public void processKeyReleased(int keyCode){
        switch(keyCode){
            case KeyEvent.VK_W:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_A:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_S:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_D:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_SPACE:
                //System.out.println("plantBomb");
                gameWorld.player.plantBomb();
                break;
            case KeyEvent.VK_UP:
                gameWorld.player.stop();
                break;    
            case KeyEvent.VK_LEFT:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.player.stop();
                break;
            case KeyEvent.VK_ENTER:
                //System.out.println("plantBomb2");
                gameWorld.player.plantBomb();
                break;
            case KeyEvent.VK_P:
                
                if(gp.isIsPause()==false){
                    gameWorld.player.setSpeed(0);
                    gp.setIsPause(true);
                    gameWorld.backGroundSound.stop();
                }
                    
                else {
                    gameWorld.player.setSpeed(gameWorld.player.getRunSpeed());
                    gp.setIsPause(false);
                    gameWorld.backGroundSound.play();
                }
                break;
        }
    }
   
}
