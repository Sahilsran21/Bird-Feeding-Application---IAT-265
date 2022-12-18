//A class that draws the predatory bird (Night Hawk)
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import java.awt.Shape;

import javax.swing.Timer;

import processing.core.*;


public class NightHawk extends Bird implements ActionListener{ //extends Animal{
//Fields
	
//	private Timer timer;
	
	//Declaration for Night Hawk shape features
	//Head Features
	private Ellipse2D.Double head;
	private Polygon beak;
	private Ellipse2D.Double eye1;
	private Ellipse2D.Double eye2;
	
	
	//Body Features
	private Ellipse2D.Double body;
	
	private Polygon wing1;
	private Polygon wing2;
	private Ellipse2D.Double wingStripe1;
	private Ellipse2D.Double wingStripe2;
	
	private double wingAngle = 0;
	private double angleInc = 0.005;
	
	private Ellipse2D.Double foldedWing1;
	private Ellipse2D.Double foldedWing2;
	
	private Rectangle2D.Double tail;
		
		
	//Estimates of Night Hawk's width and height for hit detection
	public final int BODY_WIDTH = 50, BODY_HEIGHT = 25; 
	public final int HEAD_WIDTH = 20;
		
	
	private boolean striped = false;	//state for the bird to have a stripe or not
	private boolean blackTailed = false;	//state for the bird to have a black tail or not
		
	//public boolean isMoving = true;
	//private int nightHawkWaitTime = 2700;     //counter value for keeping it in its eating position
	
	
	//Perhaps intented as variables in super class?
	private int SIZE_X = 50;
	private int SIZE_Y = 8;
	
	

	
	
	

		
	
//Methods
	
	//Set up Night Hawk's attributes
	private void setBodyAttributes() {
		body.setFrame(-SIZE_X/2,-SIZE_Y/2,BODY_WIDTH,BODY_HEIGHT);
		
		tail.setFrame(-SIZE_X/2 - 15, -SIZE_Y/2, 40, 8);
		
		wing1.addPoint(15,0);
		wing1.addPoint(23,-20);
		wing1.addPoint(-25,-50);
		wing1.addPoint(-5,-20);
		wing1.addPoint(-15,0);
		
		wing2.addPoint(15,15);
		wing2.addPoint(23,30);
		wing2.addPoint(-25,60);
		wing2.addPoint(-5,30);
		wing2.addPoint(-15,15);
		
		foldedWing1.setFrame(-SIZE_X/2,-SIZE_Y/2 - 3,40,8);
		foldedWing2.setFrame(-SIZE_X/2,-SIZE_Y/2 + 20,40,8);
		
		wingStripe1.setFrame(-5,-30,15,5);
		wingStripe2.setFrame(-5,30,15,5);	
	}
	
	
	private void setHeadAttributes() {
		head.setFrame(-SIZE_X/2 + 45, -SIZE_Y/2 + 3, HEAD_WIDTH, HEAD_WIDTH);
		beak.addPoint(-SIZE_X/2 + 60,-SIZE_Y/2 + 8);
		beak.addPoint(-SIZE_X/2 + 70,-SIZE_Y/2 + 13);
		beak.addPoint(-SIZE_X/2 + 60,-SIZE_Y/2 + 18);
		eye1.setFrame(-SIZE_X/2 + 53, -SIZE_Y/2 + 5, 7, 5);
		eye2.setFrame(-SIZE_X/2 + 53, -SIZE_Y/2 + 15, 7, 5);
	}

	//Public method to draw the NightHawk
	public void draw(Graphics2D g2) {
		
		//get the direction the night hawk is moving from PVector vel
		angle = vel.heading();
		
		//Set up initial location for Night Hawk and rotate the drawing space before drawing
		AffineTransform transformOriginal = g2.getTransform();
		g2.translate(pos.x,pos.y);
		g2.rotate(angle);
		g2.scale(scale,scale);
		
		AffineTransform transformPoint = g2.getTransform();	//saves the canvas like this since it'll be used quite a bit
		
		
		
		//Draw Body
		g2.setColor(new Color(133,121,109));
		g2.fill(body);
			
		
		
		
		//Draw Wings
		g2.setColor(new Color(61,53,51));
		//BONUS ATTEMPT//
		//slightly rotate the wings to create a low fidelity flapping animation
		//done by adding a small number to the amount its being rotated by and subtract that number at a certain treshold
		wingAngle = wingAngle + angleInc;
		if(wingAngle>0.3 || wingAngle<-0.3) angleInc*=-1;
		g2.rotate(wingAngle);
		g2.fill(wing1);
		if(striped) {
			g2.setColor(Color.white);	//if the bird is striped, draw its stripe
			g2.fill(wingStripe1);
		}
		g2.setTransform(transformPoint);
			
		
		g2.rotate(-wingAngle);
		wingAngle+=angleInc;
		if(wingAngle>0.3 || wingAngle<-0.3) angleInc*=-1;
		
		g2.setColor(new Color(61,53,51));
		g2.rotate(-wingAngle);
		wingAngle+=angleInc;
		if(wingAngle>0.3 || wingAngle<-0.3) angleInc*=-1;
		g2.fill(wing2);
		if(striped) {
			g2.setColor(Color.white);
			g2.fill(wingStripe2);
		}
		g2.setTransform(transformPoint);
			
		
			
		
		
		//Draw Tail
		g2.setColor(new Color(61,49,40));
		if(blackTailed) g2.setColor(Color.black);	//if its blackTailed, change its fill to black
		g2.translate(-SIZE_X/2 +5, -SIZE_Y/2 + 13);
		g2.rotate(-0.2); 	//slightly rotate the canvas for each tail
		g2.fill(tail);
		g2.rotate(0.4);
		g2.fill(tail);
		g2.setTransform(transformPoint);
			
		
		
		
		
		//Draw Head
		g2.setColor(new Color(105,98,94));
		g2.fill(beak);
			
		g2.setColor(new Color(133,109,83));
		g2.fill(head);
			
		g2.setColor(Color.black);
		g2.fill(eye1);
		g2.fill(eye2);
			
		g2.setTransform(transformOriginal);
	} 
	
	
	
	
	
	//Public method to draw the Sitting NightHawk
	public void drawSat(Graphics2D g2) {	
		//get the direction the night hawk is moving from PVector vel
		angle = vel.heading();
		
		//Set up initial location for Night Hawk and rotate the drawing space before drawing
		AffineTransform transformOriginal = g2.getTransform();
		g2.translate(pos.x, pos.y);  
		g2.rotate(angle);
		g2.scale(scale,scale);	
		
		AffineTransform transformPoint = g2.getTransform();	//saves the canvas like this since it'll be used quite a bit
		
		
		
		
		
		//Draw Body
		g2.setColor(new Color(133,121,109));
		g2.fill(body);
		
		g2.setColor(new Color(61,53,51));
		g2.fill(foldedWing1);
		g2.fill(foldedWing2);
		
		
		
		
		
		//Draw Tail
		g2.setColor(new Color(61,49,40));
		if(blackTailed) g2.setColor(Color.black);
		g2.translate(-SIZE_X/2 +5, -SIZE_Y/2 + 13);
		g2.rotate(-0.2);
		g2.fill(tail);
		g2.rotate(0.4);
		g2.fill(tail);
		g2.setTransform(transformPoint);
		
		

		
		
		//Draw Head
		g2.setColor(new Color(105,98,94));
		g2.fill(beak);
		
		g2.setColor(new Color(133,109,83));
		g2.fill(head);
		
		g2.setColor(Color.black);
		g2.fill(eye1);
		g2.fill(eye2);
		
		
		g2.setTransform(transformOriginal);
		}


	
			void detectCollision(NightHawk nightHawk) {
							
						//old test using bounding boxes that do not rotate		
						if (Math.abs((pos.x) - nightHawk.pos.x) < ((BODY_WIDTH / 2 + HEAD_WIDTH - 2) * scale + 
								(nightHawk.HEAD_WIDTH / 2 + HEAD_WIDTH / 2) * nightHawk.scale)
						&& Math.abs((pos.y) - nightHawk.pos.y) < (BODY_HEIGHT / 2 * scale + 
								nightHawk.HEAD_WIDTH / 2 * nightHawk.scale)) {
							
							
							
							double angle = Math.atan2(pos.y - nightHawk.pos.y, pos.x - nightHawk.pos.x);
										
							
							// Make the current bug moves away along angle
							vel.x = (float) (maxSpeed * Math.cos(angle));
							vel.y = (float) (maxSpeed * Math.sin(angle));
							
							

							// and send the otherBug away in the opposite direction: angle-PI
							nightHawk.vel.x = (float) (maxSpeed * Math.cos(angle - Math.PI));
							nightHawk.vel.y = (float) (maxSpeed * Math.sin(angle - Math.PI));
				}

			}
			
			
			
			
			

	public void sizeUp() {
		scale *= 1.1;
	}
			
	public void sizeDown() {
		scale *= .9;
	}
	
	
	
	
	
	
	
	
	

		
		
		
//Constructor
public NightHawk(double x, double y, double velX, double velY, double s){
		
		//Instantiate Night Hawk's attributes
		super(x,y,velX,velY,s);		
		
		striped = true;
		blackTailed = true;
		
		 
		//Instantiate Head Features
		head = new Ellipse2D.Double();
		beak = new Polygon();
		eye1 = new Ellipse2D.Double();
		eye2 = new Ellipse2D.Double();
		
		
		//Instantiate Body Features 
		body = new Ellipse2D.Double();
		tail = new Rectangle2D.Double();
		wing1 = new Polygon();
		wing2 = new Polygon();
		wingStripe1 = new Ellipse2D.Double();
		wingStripe2 = new Ellipse2D.Double();
		foldedWing1 = new Ellipse2D.Double();
		foldedWing2 = new Ellipse2D.Double();
				
		
		//Method calls to properly position and draw the Night Hawk's features
		setBodyAttributes();
		setHeadAttributes();
	}
}	


