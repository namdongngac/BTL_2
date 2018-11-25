/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.effect;


import com.gameobject.GameWorld;
import com.gameobject.PhysicalMap;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
    private String soundfile = "data/sounds.txt";
    private String framefileE = "data/frameEnemy.txt";
    private String animationfileE = "data/animationEnemy.txt";
    
    public static int level = 5;
    
    private Hashtable <String, FrameImage> frameImages;
    private Hashtable <String, Animation> animations;
    private Hashtable<String, AudioClip> sounds;
    
    private Hashtable <String, FrameImage> frameImagesE;
    private Hashtable <String, Animation> animationsE;
    
    private GameWorld gameWorld;
    private char[][] map;
    private int countEnemy = 0;
    
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
    
    public void LoadFrameE() throws IOException{
        frameImagesE = new Hashtable <String, FrameImage>();
        
        FileReader fr = new FileReader(framefileE);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        if(br.readLine()==null){
            System.out.println("No data");
            throw new IOException();
        }
        else{
            fr = new FileReader(framefileE);
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
                
                instance.frameImagesE.put(frame.getName(), frame);
            }
        }
        br.close();
    }
    public FrameImage getFrameImageE(String name){
        FrameImage frame = new FrameImage(instance.frameImagesE.get(name));
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
    
    public void LoadSounds() throws IOException{
        sounds = new Hashtable<String, AudioClip>();
        
        FileReader fr = new FileReader(soundfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        if(br.readLine()==null) { // no line = "" or something like that
            System.out.println("No data");
            throw new IOException();
        }
        else {
            
            fr = new FileReader(soundfile);
            br = new BufferedReader(fr);
            
            while((line = br.readLine()).equals(""));
            
            int n = Integer.parseInt(line);
            
            for(int i = 0;i < n; i ++){
                
                AudioClip audioClip = null;
                while((line = br.readLine()).equals(""));

                String[] str = line.split(" ");
                String name = str[0];
                
                String path = str[1];

                try {
                   audioClip =  Applet.newAudioClip(new URL("file","",str[1]));

                } catch (MalformedURLException ex) {}
                
                instance.sounds.put(name, audioClip);
            }
            
        }
        
        br.close();
        
    }
    
    public void LoadAnimationE() throws IOException{
        animationsE = new Hashtable <String, Animation>();
        
        FileReader fr = new FileReader(animationfileE);  
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        if(br.readLine()==null){
            System.out.println("No data");
            throw new IOException();
        }
        else{
            fr = new FileReader(animationfileE);
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
                    anim.add(getFrameImageE(str[j]), Long.parseLong(str[j+1]));
                }
                
                instance.animationsE.put(anim.getName(), anim);
            }
        }
        br.close();
    }
    
    
    public void LoadMap()throws IOException{
        countEnemy = 0;
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
                    
                    if(instance.map[i][j] == '1'||instance.map[i][j] == '2'||instance.map[i][j] == '3'||instance.map[i][j] == '4'||instance.map[i][j] == '5'){
                        countEnemy++;
                    }
            
        
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
    public Animation getAnimationE(String name){
        Animation anim = new Animation(instance.animationsE.get(name));
        return anim;
    }
    public AudioClip getSound(String name){
        return instance.sounds.get(name);
    }
    
    public void LoadData() throws IOException{
        LoadFrame();
        LoadAnimation();
        LoadMap();
        LoadSounds();
        LoadFrameE();
        LoadAnimationE();
    }
    
    public char[][] getMap(){      
        return instance.map;
    
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public int getCountEnemy() {
        return countEnemy;
    }

    public void setCountEnemy(int countEnemy) {
        this.countEnemy = countEnemy;
    }

    
    
    
}
