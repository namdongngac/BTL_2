/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.effect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author admin
 */
public class FrameImage {
    private String name;
    private BufferedImage image;
    
    public FrameImage(){
        this.image = null;
        this.name = null;
    }
    public FrameImage(String name, BufferedImage image){
        this.name = name;
        this.image = image;
    }
    //contructor copy
    public FrameImage(FrameImage frameimage){
        image = new BufferedImage(frameimage.getImageWidth(), frameimage.getImageHeight(), frameimage.getImage().getType());
        Graphics g = image.getGraphics();
        g.drawImage(frameimage.getImage(), 0, 0, null);
    }
    //hiển thị ảnh
    public void draw(Graphics2D g2d, int x, int y){
        g2d.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
    }
    public int getImageWidth(){
        return image.getWidth();
    }
    public int getImageHeight(){
        return image.getHeight();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}
