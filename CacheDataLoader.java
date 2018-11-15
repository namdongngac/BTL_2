/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.effect;

import com.gameobject.Camera;
import com.gameobject.GameWorld;
import com.gameobject.PhysicalMap;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;

/**
 *
 * @author admin
 */
public class CacheDataLoader {
    private static CacheDataLoader instance;
    
    private String framefile = "data/frames.txt";
    private String animationfile = "data/animations.txt";
    private String mapfile = "levels/Level";
    public static int level = 1;
    
    private Hashtable <String, FrameImage> frameImages;
    private Hashtable <String, Animation> animations;
    
    
    private GameWorld gameWorld;
    private char[][] map;
    
    private CacheDataLoader(){}
    public static CacheDataLoader getInstance(){
        if(instance==null){
            instance = new CacheDataLoader();         
        }
        return instance;
    }
    public void LoadFrame() throws IOException{
        frameImages = new Hashtable <String, FrameImage>();
        
        FileReader fr = new FileReader(framefile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        if(br.readLine()==null){
            System.out.println("No data");
            throw new IOException();
        }
        else{
            fr = new FileReader(framefile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);
            
            for(int i = 0; i < n; i++){
                
                FrameImage frame = new FrameImage();
                while((line = br.readLine()).equals(""));
                frame.setName(line);
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                String path = str[1];
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);
                
                while((line = br.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);
                
                BufferedImage image = ImageIO.read(new File(path));
                BufferedImage subImage = image.getSubimage(x, y, w, h);
                frame.setImage(subImage);
                
                instance.frameImages.put(frame.getName(), frame);
            }
        }
        br.close();
    }
    public FrameImage getFrameImage(String name){
        FrameImage frame = new FrameImage(instance.frameImages.get(name));
        return frame;
    }
    
    public void LoadAnimation() throws IOException{
        animations = new Hashtable <String, Animation>();
        
        FileReader fr = new FileReader(animationfile);  
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        if(br.readLine()==null){
            System.out.println("No data");
            throw new IOException();
        }
        else{
            fr = new FileReader(animationfile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            int n = Integer.parseInt(line);
            
            for(int i = 0; i < n; i++){
                Animation anim = new Animation();
                
                while((line = br.readLine()).equals(""));
                anim.setName(line);
                
                while((line = br.readLine()).equals(""));
                String[] str = line.split(" ");
                for(int j = 0; j < str.length; j+=2){
                    anim.add(getFrameImage(str[j]), Long.parseLong(str[j+1]));
                }
                
                instance.animations.put(anim.getName(), anim);
            }
        }
        br.close();
    }
    
    public void LoadMap()throws IOException{
        
        FileReader fr = new FileReader(mapfile+level+".txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        if(br.readLine()==null){
            System.out.println("No data");
            throw new IOException();
        }
        else{
            fr = new FileReader(mapfile+level+".txt");
            br = new BufferedReader(fr);
    
            PhysicalMap pmap = new PhysicalMap(0,0,gameWorld);

            while((line = br.readLine()).equals(""));               
            String[] str = line.split(" ");
            String level = str[0];
            int row = Integer.parseInt(str[1]);           
            int col = Integer.parseInt(str[2]);
       

            instance.map = new char[row][col];
            for(int i = 0; i < row; i++){
                while((line = br.readLine()).equals("")); 
                for(int j = 0; j < col; j++){
                    instance.map[i][j] = line.charAt(j);
                }
            }
            for(int i = 0;i < row;i++){
            
                for(int j = 0;j<col;j++)
                    System.out.print(instance.map[i][j]);
            
                System.out.println();
        }
                        
                                       
        }
        br.close();
    }
    
    public Animation getAnimation(String name){
        Animation anim = new Animation(instance.animations.get(name));
        return anim;
    }
    
    public void LoadData() throws IOException{
        LoadFrame();
        LoadAnimation();
        LoadMap();
    }
    
    public char[][] getMap(){      
        return instance.map;
    
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    
    
    
}
