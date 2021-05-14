package day03;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
public class Plane extends GameObject {
       boolean left,right,up,down;
       boolean live=true;
       public void drawSelf(Graphics g) {
    	  g.drawImage(img, (int)x, (int)y, null);
   			//根据方向判断
   			if(left) {
   				x-=speed;
   			}
   			if(right) {
   				x+=speed;
   			}
   			if(up) {
   				y-=speed;
   			}
   			if(down) {
   				y+=speed;
   			}
   			x++;
       }
 
     public Plane(Image img,double x,double y) {
    	 this.img=img;
    	 this.x=x;
    	 this.y=y;
    	 

    	 
     }  
       public void addDirection(KeyEvent e) {
    	   switch(e.getKeyCode()) {
   		case KeyEvent.VK_LEFT:
   			left=true;
   			break;
   		case KeyEvent.VK_RIGHT:
   			right=true;
   			break;
   		case KeyEvent.VK_UP:
   			up=true;
   			break;
   		case KeyEvent.VK_DOWN:
   			down=true;
   			break;
       }
     }
       public void miunsDirection(KeyEvent e) {
   		switch(e.getKeyCode()){
   		case KeyEvent.VK_LEFT:
   			left=false;
   			break;
   		case KeyEvent.VK_RIGHT:
   			right=false;
   			break;
   		case KeyEvent.VK_UP:
   			up=false;
   			break;
   		case KeyEvent.VK_DOWN:
   			down=false;
   			break;
   		}
   		}
     
}
       
