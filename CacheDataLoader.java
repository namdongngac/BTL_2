package GameEffect;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.management.InstanceAlreadyExistsException;

public class CacheDataLoader {
	private String frameFile = "data/frameFile.txt";
	private String animationFile = "data/animationFile.txt";
	private String physicalMapFile = "data/physicalFile.txt";
	
	private static CacheDataLoader instance;
	private Hashtable<String, FrameImage> frameImage;
	private Hashtable<String, GameAnimation> animations;
	private int[][] phys_map;
	private CacheDataLoader() {
		
	}
	public static CacheDataLoader getInstance() {
		if(instance == null) {
			instance = new CacheDataLoader();
			return instance;
		}
		return instance;
	}
	public void loadFrame() throws IOException   {
		frameImage = new Hashtable<String,FrameImage>();
		FileReader fr = new FileReader(frameFile);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		if(br.readLine() == null) {
			System.out.println("No data");
			throw new IOException();
		}else {
			fr = new FileReader(frameFile);
			br = new BufferedReader(fr);
			while((line = br.readLine()).equals(""));
				int n = Integer.parseInt(line);
				
				int x = 0,y = 0,w = 0,h = 0;
				for(int i=0;i<n;i++) {
					
					FrameImage frame = new FrameImage();
					while((line=br.readLine()).equals("")); 
						frame.setName(line);
						//System.out.println(frame.getName());
					
					while((line=br.readLine()).equals("")); 
						String[] arr = line.split(" ");
						String path = arr[1];
						
					
					while((line=br.readLine()).equals("")); 
						 arr = line.split(" ");
						x = Integer.parseInt(arr[1]);
					
					while((line=br.readLine()).equals("")); 
						 arr = line.split(" ");
						y = Integer.parseInt(arr[1]);
					
					while((line=br.readLine()).equals("")); 
						arr = line.split(" ");
						w = Integer.parseInt(arr[1]);
						
					
					while((line=br.readLine()).equals("")); 
						arr = line.split(" ");
						h = Integer.parseInt(arr[1]);
					
					BufferedImage imageData = ImageIO.read(new File(path));
					BufferedImage image = imageData.getSubimage(x, y, w, h);
					
					frame.setImage(image);
					instance.frameImage.put(frame.getName(), frame);
				}
				br.close();
			}
			
		
	}
	public FrameImage getFrameImage(String name) {
		FrameImage frameImage = new FrameImage(instance.frameImage.get(name));
		return frameImage;
	}
	public GameAnimation getGameAnimation(String name) {
		GameAnimation animation = new GameAnimation(instance.animations.get(name));
		return animation;
	}
	public void loadAnimation() throws IOException {
	    animations = new Hashtable<String,GameAnimation>();
	    FileReader fr = new FileReader(animationFile);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		if(br.readLine()==null) {
			System.out.println("No Data in ani");
			throw new IOException();
		}else {
			fr = new FileReader(animationFile);
			br = new BufferedReader(fr);
			while((line=br.readLine()).equals("")); 
				
				int n = Integer.parseInt(line);
				for(int i=0;i<n;i++) {
					GameAnimation animation = new GameAnimation();
					String[] arr;
					while((line=br.readLine()).equals("")); 
						animation.setName(line);
					
					while((line=br.readLine()).equals("")); 
						arr=line.split(" ");
						
					
					for(int j=0;j<arr.length;j+=2) {
						animation.add(getFrameImage(arr[j]), Double.parseDouble(arr[j+1]));
						
					}
					instance.animations.put(animation.getName(), animation);
				}
			
		}
	}
	public void loadData() throws IOException {
		loadFrame();
		loadAnimation();
		loadPhysMap();
	}
	public int[][] getPhysicalMap() {
		return instance.phys_map;
	}
	public void loadPhysMap() throws IOException {
		FileReader fr = new FileReader(physicalMapFile);
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		line = br.readLine();
		int numberofRows = Integer.parseInt(line);
		line = br.readLine();
		int numberofColums = Integer.parseInt(line);
		instance.phys_map = new int[numberofRows][numberofColums];
		for(int i=0;i<numberofRows;i++) {
			line = br.readLine();
			String arr[] = line.split(" ");
			for(int j =0;j<numberofColums;j++) {
				phys_map[i][j] = Integer.parseInt(arr[j]);
			}
		}
		/*for(int i=0;i<numberofRows;i++) {
			for(int j=0;j<numberofColums;j++) {
				System.out.print(" " +instance.phys_map[i][j]);
				
			}
			System.out.println("");
		}*/
	}
	
}
