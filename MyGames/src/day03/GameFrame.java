package day03;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

public class GameFrame  extends JFrame{
	Image bg =GameUtil.getImage("images/bg.jpg");
	Image planImage = GameUtil.getImage("images/plane.png");
	Plane plane=new Plane(planImage,250,250);
	Shell[] shells=new Shell[2];
	Explode bao;
	Date startTime = new Date() ;
	Date endTime;
	int period;
	public static void main(String[] args) {
		GameFrame g=new GameFrame();
		g.launchFrame();
	}
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	} 
	
	public void paint(Graphics g) {
		Color c=g.getColor();
		g.drawImage(bg,0,0,null);
		plane.drawSelf(g);
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g);
			boolean peng = shells[i].getRect().intersects(plane.getRect());
			if(peng) {
				plane.live=false;
				if(bao==null) {
					bao=new Explode(plane.x,plane.y);
					endTime=new Date();
period=(int)((endTime.getTime()-startTime.getTime()/1000));

					}
				bao.draw(g);
			}
			if(!plane.live) {
				g.setColor(Color.RED);
			    Font f=new Font("黑体",Font.BOLD,50);
			    g.setFont(f);
			    g.drawString("呕吼！共用了"+period+"秒",(int) plane. x,(int)plane.y);
				
			}
		}
		g.setColor(c);
	}
	public void launchFrame() {
		this.setTitle("战机");
		this.setVisible(true);
		this.setSize(Constant.GAME_HEIGHT,Constant.GAME_WIDTH);
		this.setLocation(250,250);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//表示正常退出
			}
		});
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
		for(int i=0;i<shells.length;i++) {
			shells[i]=new Shell();
		}
	
	}
	class KeyMonitor  extends KeyAdapter{
		
		public void keypressed (KeyEvent e) {
			plane.addDirection(e);
		}
		public void keyReleased (KeyEvent e) {
			plane.miunsDirection(e);
		}
	}
	class PaintThread extends Thread {
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);//1s=1000ms 25次
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
				
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
