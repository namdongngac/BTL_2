/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.userinterface;

import com.effect.CacheDataLoader;
import com.effect.FrameImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class GameOverPanel extends JPanel implements MouseListener{
    
    private FrameImage nextButton, exitButton;
    private JLabel next, exit;
    private ManagementPanel panel;
    
    public GameOverPanel(ManagementPanel panel){
        this.panel = panel;
        nextButton = CacheDataLoader.getInstance().getFrameImage("nextButton");
        exitButton = CacheDataLoader.getInstance().getFrameImage("exitButton");
        setLayout(null);
        addComponent();
    }
    
    public void addComponent(){
        int x =  GameFrame.SCREEN_WIDTH/2-150;
        int y = 600;
        next = setLabel(x,y,nextButton.getImage());
        exit = setLabel(x+200,y,exitButton.getImage());
        add(next);
        add(exit);
        next.addMouseListener(this);
        exit.addMouseListener(this);
    }
    
    private JLabel setLabel(int x, int y, Image i){
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(i);
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(x, y);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial",Font.BOLD,60));
        g2d.drawString("GameOver", 400, 350);
        g2d.drawString("Your Score: " + panel.gamePanel.gameWorld.score, 300, 500);
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(next)){
            panel.gamePanel.setIsPause(false);
            panel.gamePanel.newGame();
            panel.showGame();
        }
        if(e.getSource().equals(exit)){
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
