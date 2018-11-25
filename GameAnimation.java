package GameEffect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameAnimation {
	private String name;
	private boolean isRepeated;
	private ArrayList<FrameImage> frameImages;
	private int currenFrame;
	private ArrayList<Boolean> ignoreFrames;
	private ArrayList<Double> delayFrames;
	private long beginTime;
	private boolean drawRecFrame;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getRepeated() {
		return isRepeated;
	}
	public void setRepeated(boolean isRepeated) {
		this.isRepeated = isRepeated;
	}
	public ArrayList<FrameImage> getFrameImages() {
		return frameImages;
	}
	public void setFrameImages(ArrayList<FrameImage> frameImages) {
		this.frameImages = frameImages;
	}
	public int getCurrenFrame() {
		return currenFrame;
	}
	public void setCurrenFrame(int currenFrame) {
		if(currenFrame>=0&&currenFrame<frameImages.size()) {
			this.currenFrame = currenFrame;
		}else this.currenFrame = 0;
	}
	public ArrayList<Boolean> getIgnoreFrames() {
		return ignoreFrames;
	}
	public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {
		this.ignoreFrames = ignoreFrames;
	}
	public ArrayList<Double> getDelayFrames() {
		return delayFrames;
	}
	public void setDelayFrames(ArrayList<Double> delayFrames) {
		this.delayFrames = delayFrames;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public boolean isDrawRecFrame() {
		return drawRecFrame;
	}
	public void setDrawRecFrame(boolean drawRecFrame) {
		this.drawRecFrame = drawRecFrame;
	}
	public Boolean getIgnoreFrames(int id) {
		return ignoreFrames.get(id);
	}
	public void setIgnoreFrames(int id) {
		if(id>=0&& id<ignoreFrames.size()) {
			ignoreFrames.set(id, true);
		}
	}
	public void reset() {
		currenFrame = 0;
		beginTime = 0;
		for(int i =0;i< ignoreFrames.size();i++) {
			ignoreFrames.set(i, false);
		}
	}
	public void add(FrameImage frameImage, Double timeToNextFrame) {
		ignoreFrames.add(false);
		frameImages.add(frameImage);
		delayFrames.add(new Double(timeToNextFrame));
		
	}
	public BufferedImage getCurrentImage() {
		return frameImages.get(currenFrame).getImage();
	}
	//Contructor
	public GameAnimation() {
		delayFrames = new ArrayList<Double>();
		beginTime = 0;
		currenFrame = 0;
		ignoreFrames = new ArrayList<Boolean>();
		frameImages = new ArrayList<FrameImage>();
		drawRecFrame = false;
		isRepeated = true;
	}//Contructor
	public GameAnimation(GameAnimation gameAnimation) {
		beginTime = gameAnimation.beginTime;
		currenFrame = gameAnimation.currenFrame;
		isRepeated = gameAnimation.isRepeated;
		drawRecFrame = gameAnimation.drawRecFrame;
		delayFrames = new ArrayList<Double>();
		for(Double d : gameAnimation.delayFrames) {
			delayFrames.add(d);
		}
		ignoreFrames = new ArrayList<Boolean>();
		for(Boolean b : gameAnimation.ignoreFrames) {
			ignoreFrames.add(b);
		}
		frameImages = new ArrayList<FrameImage>();
		for(FrameImage f : gameAnimation.frameImages) {
			frameImages.add(f);
		}
	}
	public void update(long currenTime) {
		if(beginTime == 0)
			beginTime = currenTime;
		else
			if(currenTime - beginTime > delayFrames.get(currenFrame)) {
				nextFrame();
				beginTime = currenTime;
			}
	}
	private void nextFrame() {
		if(currenFrame >= frameImages.size()-1) {
			if(isRepeated) currenFrame = 0;
		}
		else currenFrame ++;
		if(ignoreFrames.get(currenFrame)) nextFrame();
	}
	public Boolean isLastFrame() {
		if(currenFrame == frameImages.size()-1) {
			return true;
		}else return false;
	}
	public void flipAllImage() {//lật từ phải sang trái
		
	}
	public void draw(Graphics2D g2,int x, int y) {
		BufferedImage image = getCurrentImage();
		g2.drawImage(image, (x-image.getWidth())/2,(y-image.getHeight())/2,null);
		if(drawRecFrame) {
			g2.drawRect((x-image.getWidth()/2), (y-image.getHeight())/2, image.getWidth(), image.getHeight());
		}
	}

}
