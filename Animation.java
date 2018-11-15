/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.effect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Animation {
    
    private String name;
    private boolean isRepeat;
    
    private ArrayList <FrameImage> frameImages;
    private int currentFrame;
    
    private ArrayList <Boolean> ignoreFrame;
    private ArrayList <Long> delayFrame;
    
    private long beginTime;
    private boolean drawRectFrame;
    
    public Animation(){
        frameImages = new ArrayList <FrameImage>();
        ignoreFrame = new ArrayList <Boolean>();
        delayFrame = new ArrayList <Long>();
        isRepeat = true;
        currentFrame = 0;
        beginTime = 0;
        drawRectFrame = false;
    }
    // contructor copy
    public Animation(Animation anim){
        beginTime = anim.beginTime;
        currentFrame = anim.currentFrame;
        drawRectFrame = anim.drawRectFrame;
        isRepeat = anim.isRepeat;
        
        frameImages = new ArrayList <FrameImage>();
        for(FrameImage f : anim.frameImages){
            frameImages.add(f);
        }
        
        ignoreFrame = new ArrayList <Boolean>();
        for(boolean b : anim.ignoreFrame){
            ignoreFrame.add(b);
        }
        
        delayFrame = new ArrayList <Long>();
        for(Long d : anim.delayFrame){
            delayFrame.add(d);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    public ArrayList<FrameImage> getFrameImages() {
        return frameImages;
    }

    public void setFrameImages(ArrayList<FrameImage> frameImages) {
        this.frameImages = frameImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        if(currentFrame >= 0 && currentFrame < frameImages.size())
            this.currentFrame = currentFrame;
        else
            this.currentFrame = 0;
    }

    public ArrayList<Boolean> getIgnoreFrame() {
        return ignoreFrame;
    }

    public void setIgnoreFrame(ArrayList<Boolean> ignoreFrame) {
        this.ignoreFrame = ignoreFrame;
    }

    public ArrayList<Long> getDelayFrame() {
        return delayFrame;
    }

    public void setDelayFrame(ArrayList<Long> delayFrame) {
        this.delayFrame = delayFrame;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public boolean getDrawRectFrame() {
        return drawRectFrame;
    }

    public void setDrawRectFrame(boolean drawRectFrame) {
        this.drawRectFrame = drawRectFrame;
    }
    public boolean isIgnoreFrame(int id){
        return ignoreFrame.get(id);
    }
    public void setIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrame.size()){
            ignoreFrame.set(id, true);
        }
    }
    public void unIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrame.size()){
            ignoreFrame.set(id, false);
        }
    }
    //đưa animation về ảnh đầu tiên
    public void resetAnimation(){
        currentFrame = 0;
        beginTime = 0;
        for(int i = 0; i < ignoreFrame.size(); i++){
            ignoreFrame.set(i, false);
        }
    }
    //thêm ảnh vào animation
    public void add(FrameImage frameImage, long timeToNextFrame){
        ignoreFrame.add(false);
        frameImages.add(frameImage);
        delayFrame.add(timeToNextFrame);
    }
    public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();
    }
    // vẽ ảnh tiếp theo để tạo animation
    public void nextFrame(){
        if(currentFrame >= frameImages.size()-1){
            if(isRepeat) currentFrame = 0;
        }
        else currentFrame ++;
        
        if(ignoreFrame.get(currentFrame)) nextFrame();
    }
    //cập nhật animation
    public void Update(long currentTime){
        if(beginTime == 0) beginTime = currentTime;
        else{
            if(currentTime - beginTime > delayFrame.get(currentFrame)){
                nextFrame();
                beginTime = currentTime;
            }
        }
    }
    //kiểm tra hiển thị ảnh cuối cùng trong animation
    public boolean isLastFrame(){
        if(currentFrame == frameImages.size() - 1)
            return true;
        else return false;
    }
    // lật animation
    public void flipAllImage(){
        for(int i = 0; i < frameImages.size(); i++){
            BufferedImage image = frameImages.get(i).getImage();
            AffineTransform tf = AffineTransform.getScaleInstance(-1, 1);
            tf.translate(-image.getWidth(), 0);
            
            AffineTransformOp op = new AffineTransformOp(tf,AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            
            frameImages.get(i).setImage(image);
        }
    }
    // hiển thị animation
    public void draw(Graphics2D g2d, int x, int y){
        BufferedImage image = getCurrentImage();
        
        g2d.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame){
            g2d.drawRect(x - image.getWidth(), y - image.getHeight()/2, image.getWidth(), image.getHeight());
        }
    }
}
