//A BlueJay class that draws and moves the BlueJay
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.Timer;

import processing.core.*;


public class BlueJay extends Bird implements ActionListener {
//Fields
	
	
	//Declaration for blue jay shape features
	//Head Features
	private Ellipse2D.Double head;
	private Ellipse2D.Double eye1;
	private Ellipse2D.Double eye2;
	private QuadCurve2D.Double headStripe1;
	private QuadCurve2D.Double headStripe2;
	private QuadCurve2D.Double beak;
	

	//Body Features
	private Ellipse2D.Double body;
	
	private Rectangle2D.Double leftWing1;
	private Rectangle2D.Double leftWing2;
	
	private Rectangle2D.Double rightWing1;
	private Rectangle2D.Double rightWing2;
	
	private Ellipse2D.Double leftFeather1;
	private Ellipse2D.Double leftFeather2;
	private Ellipse2D.Double leftFeather3;
	private Ellipse2D.Double leftFeather4;
	private Ellipse2D.Double leftFeather5;
	
	private Ellipse2D.Double rightFeather1;
	private Ellipse2D.Double rightFeather2;
	private Ellipse2D.Double rightFeather3;
	private Ellipse2D.Double rightFeather4;
	private Ellipse2D.Double rightFeather5;

	private Ellipse2D.Double tailFeather1;
	private Ellipse2D.Double tailFeather2;
	private Ellipse2D.Double tailFeather3;
	private Ellipse2D.Double tailFeather4;
	private Ellipse2D.Double tailFeather5;
	
	private Ellipse2D.Double leftSatFeather1;
	private Ellipse2D.Double leftSatFeather2;
	private Ellipse2D.Double leftSatFeather3;

	private Ellipse2D.Double rightSatFeather1;
	private Ellipse2D.Double rightSatFeather2;
	private Ellipse2D.Double rightSatFeather3;
	
	

	//Estimates of Blue Jay's width and height for hit detection
	public final int HEAD_WIDTH = 15;
	public final int HEAD_HEIGHT = 15;
	public final int BODY_WIDTH = 20;
	public final int BODY_HEIGHT = 45;
	
	
	
	protected int colorCode1 = 128; 
	protected int colorCode2 = 191;
	protected int colorCode3 = 255;
	

	protected int colorCodeStroke1 = 0; 
	protected int colorCodeStroke2 = 51;
	protected int colorCodeStroke3 = 102;
	
	
//Methods
	
	//Set up body component location and size, assuming (0,0) is center of the body
	//with other elements drawn around it 
	private void setBodyAttributes() {
	body.setFrame(-10, -20, 20, 45);
		
	leftWing1.setFrame(-36, -13, 30, 17);
	leftWing2.setFrame(-23, 4, 20, 8);
	rightWing1.setFrame(6, -13, 30, 17);
	rightWing2.setFrame(4, 4, 19, 8);
	
	leftFeather1.setFrame(7, -13, 40, 10);
	leftFeather2.setFrame(10, -8, 43, 8);
	leftFeather3.setFrame(9, -3, 40, 8);
	leftFeather4.setFrame(9, -2, 30, 8);
	leftFeather5.setFrame(9, -2, 20, 6);
	
	rightFeather1.setFrame(-47, -13, 40, 10);
	rightFeather2.setFrame(-53, -8, 43, 8);
	rightFeather3.setFrame(-49, -3, 40, 8);
	rightFeather4.setFrame(-38, -2, 30, 8);
	rightFeather5.setFrame(-29, -2, 20, 6);
	
	tailFeather1.setFrame(-3, 17, 6, 25);
	tailFeather2.setFrame(-3, 19, 6, 25);
	tailFeather3.setFrame(-3, 20, 6, 25);
	tailFeather4.setFrame(-3, 19, 6, 25);
	tailFeather5.setFrame(-3, 17, 6, 25);
	
	leftSatFeather3.setFrame(-5, -8, 35, 9);
	leftSatFeather2.setFrame(-10, -13, 35, 9);
	leftSatFeather1.setFrame(-15, -18, 35, 9);
	
	rightSatFeather3.setFrame(-30, -10, 35, 9);
	rightSatFeather2.setFrame(-25, -15, 35, 9);
	rightSatFeather1.setFrame(-20, -20, 35, 9);
	
	
	}
	
	
	//Set up head components with reference to the center of the body (0,0)
	private void setHeadAttributes() {
		
	//beak.setCurve(BODY_WIDTH / 2, 0, BODY_WIDTH / 2 + 10, 0, BODY_WIDTH / 2 + 8, -10);
		
	head.setFrame(-7.5, -30, 15, 15);
	
	headStripe1.setCurve(-1.5, -29, -1.5, -20, -5, -17);
	headStripe2.setCurve(1.5, -29, 1.5, -20, 5, -17);
	
	eye1.setFrame(-5, -29, 3.8, 3.8);
	eye2.setFrame(1.5, -29, 3.8, 3.8);
	
	beak.setCurve(-2, -30, 0, -50, 2, -30);
	
	}
	

	
	
	
	//Public method for drawing the blue jay
	public void draw(Graphics2D g2) {
		
		//get the direction the blue jay is moving from PVector vel
		angle = vel.heading();
		
		
		
		
		
		
		
		//Set up initial location for Blue Jay and rotate the drawing space before drawing
		AffineTransform transform = g2.getTransform();
		g2.translate(pos.x, pos.y);  
		g2.rotate(angle - 300);
		g2.scale(scale,scale);		
		
		
		//Draw Wings
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(leftWing1);
		g2.fill(leftWing2);
		g2.fill(rightWing1);
		g2.fill(rightWing2);
		
		
		//Draw Body
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(body);
		
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.setStroke(new BasicStroke(2));
		g2.draw(body);
		
		
		//Draw Feathers
		g2.rotate(Math.toRadians(-10));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.setStroke(new BasicStroke(3));
		g2.draw(leftFeather1);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(leftFeather1);
		g2.rotate(Math.toRadians(10));
		
		g2.rotate(Math.toRadians(-5));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(leftFeather2);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(leftFeather2);
		g2.rotate(Math.toRadians(5));
		
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(leftFeather3);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(leftFeather3);
		
		g2.rotate(Math.toRadians(15));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(leftFeather4);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(leftFeather4);
		g2.rotate(Math.toRadians(-15));
		
		g2.rotate(Math.toRadians(35));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(leftFeather5);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(leftFeather5);
		g2.rotate(Math.toRadians(-35));
		
		
		
		    
		   
		   
		
		g2.rotate(Math.toRadians(10));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.setStroke(new BasicStroke(3));
		g2.draw(rightFeather1);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(rightFeather1);
		g2.rotate(Math.toRadians(-10));
		
		g2.rotate(Math.toRadians(5));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(rightFeather2);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(rightFeather2);
		g2.rotate(Math.toRadians(-5));
		
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(rightFeather3);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(rightFeather3);
		
		g2.rotate(Math.toRadians(-15));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(rightFeather4);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(rightFeather4);
		g2.rotate(Math.toRadians(15));
		
		g2.rotate(Math.toRadians(-35));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(rightFeather5);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(rightFeather5);
		g2.rotate(Math.toRadians(35));
		
	
		
		
	   
		
		
		
		//Draw Tail Feathers
		g2.rotate(Math.toRadians(20));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(tailFeather1);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(tailFeather1);
		g2.rotate(Math.toRadians(-20));
		
		g2.rotate(Math.toRadians(10));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(tailFeather2);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(tailFeather2);
		g2.rotate(Math.toRadians(-10));
		
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3)); 
		g2.draw(tailFeather3);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(tailFeather3);
		
		g2.rotate(Math.toRadians(-10));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(tailFeather4);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(tailFeather4);
		g2.rotate(Math.toRadians(10));
		
		g2.rotate(Math.toRadians(-20));
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.draw(tailFeather5);
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		g2.fill(tailFeather5);
		g2.rotate(Math.toRadians(20));
		
		
		
		
		
		//Draw Head 
		g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
		
		g2.fill(head);

		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.setStroke(new BasicStroke(1));
		g2.draw(headStripe1);
		g2.draw(headStripe2);
		
		g2.setStroke(new BasicStroke(2));
		g2.setColor(new Color(0));
		g2.draw(beak);
		
		g2.setColor(new Color(50,50,50));
		g2.fill(beak);
		
		
		g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
		g2.setStroke(new BasicStroke(2));
		g2.draw(head);

		g2.setColor(new Color(22,22,22));
		g2.fill(eye1);
		g2.fill(eye2);
		

		g2.setTransform(transform);
		
				
			
	}
	
	

	
	
	
	
	//Public method for drawing the blue jay
		public void drawSat(Graphics2D g2) {
			//get the direction the blue jay is moving from PVector vel
			angle = vel.heading();
			
			//Set up initial location for Blue Jay and rotate the drawing space before drawing
			AffineTransform transform = g2.getTransform();
			g2.translate(pos.x, pos.y);  
			g2.rotate(angle - 300);
			g2.scale(scale,scale);		
			
			
			//Draw Body
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(body);
			
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.setStroke(new BasicStroke(2));
			g2.draw(body);
			
			
			//Draw Feathers
			g2.rotate(Math.toRadians(60));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.setStroke(new BasicStroke(3));
			g2.draw(leftSatFeather1);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(leftSatFeather1);
			g2.rotate(Math.toRadians(-60));
			
			g2.rotate(Math.toRadians(62));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(leftSatFeather2);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(leftSatFeather2);
			g2.rotate(Math.toRadians(-62));
			
			g2.rotate(Math.toRadians(64));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(leftSatFeather3);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(leftSatFeather3);
			g2.rotate(Math.toRadians(-64));

			
			
			
			
			
			g2.rotate(Math.toRadians(-60));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.setStroke(new BasicStroke(3));
			g2.draw(rightSatFeather1);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(rightSatFeather1);
			g2.rotate(Math.toRadians(60));
			
			g2.rotate(Math.toRadians(-62));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(rightSatFeather2);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(rightSatFeather2);
			g2.rotate(Math.toRadians(62));
			
			g2.rotate(Math.toRadians(-64));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(rightSatFeather3);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(rightSatFeather3);
			g2.rotate(Math.toRadians(64));
	
			
		
			
			
			
			
			//Draw Tail Feathers
			g2.rotate(Math.toRadians(20));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(tailFeather1);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(tailFeather1);
			g2.rotate(Math.toRadians(-20));
			
			g2.rotate(Math.toRadians(10));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(tailFeather2);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(tailFeather2);
			g2.rotate(Math.toRadians(-10));
			
		
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(tailFeather3);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(tailFeather3);

			
			g2.rotate(Math.toRadians(-10));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(tailFeather4);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(tailFeather4);
			g2.rotate(Math.toRadians(10));
			
			g2.rotate(Math.toRadians(-20));
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.draw(tailFeather5);
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(tailFeather5);
			g2.rotate(Math.toRadians(20));
			
			
			
			
			
			//Draw Head 
			g2.setColor(new Color(colorCode1, colorCode2, colorCode3));
			g2.fill(head);
			
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.setStroke(new BasicStroke(1));
			g2.draw(headStripe1);
			g2.draw(headStripe2);
			
			g2.setStroke(new BasicStroke(2));
			g2.setColor(new Color(0));
			g2.draw(beak);
			g2.setColor(new Color(50,50,50));
			g2.fill(beak);
			
			
			g2.setColor(new Color(colorCodeStroke1, colorCodeStroke2, colorCodeStroke3));
			g2.setStroke(new BasicStroke(2));
			g2.draw(head);

			g2.setColor(new Color(22,22,22));
			g2.fill(eye1);
			g2.fill(eye2);
			

			g2.setTransform(transform);
		}
	

		
		
		
		// Method to detect collision between the current bird and the other bird
		void detectCollision(BlueJay blueJay) {
			
					//old test using bounding boxes that do not rotate		
					if (Math.abs((pos.x) - blueJay.pos.x) < ((BODY_WIDTH / 2 + HEAD_WIDTH - 2) * scale + 
							(blueJay.HEAD_WIDTH / 2 + HEAD_WIDTH / 2) * blueJay.scale)
					&& Math.abs((pos.y) - blueJay.pos.y) < (BODY_HEIGHT / 2 * scale + 
							blueJay.HEAD_WIDTH / 2 * blueJay.scale)) {
							
						
						
						//+ or - value to change behavior?
						double angle = Math.atan2(pos.y - blueJay.pos.y, pos.x - blueJay.pos.x);
						
						// Make the current bug moves away along angle
						vel.x = (float) (maxSpeed * Math.cos(angle));
						vel.y = (float) (maxSpeed * Math.sin(angle));

						// and send the otherBug away in the opposite direction: angle-PI
						blueJay.vel.x = (float) (maxSpeed * Math.cos(angle - Math.PI));
						blueJay.vel.y = (float) (maxSpeed * Math.sin(angle - Math.PI));		
			}

		}
		
		

		
		

		void detectCollision(Dove dove) {
		
			//old test using bounding boxes that do not rotate		
			if (Math.abs((pos.x) - dove.pos.x) < ((BODY_WIDTH / 2 + HEAD_WIDTH - 2) * scale + 
					(dove.HEAD_WIDTH / 2 + HEAD_WIDTH / 2) * dove.scale)
			&& Math.abs((pos.y) - dove.pos.y) < (BODY_HEIGHT / 2 * scale + 
					dove.HEAD_WIDTH / 2 * dove.scale)) {
				
				
				
				double angle = Math.atan2(pos.y - dove.pos.y, pos.x - dove.pos.x);
			
				// Make the current bug moves away along angle
				vel.x = (float) (maxSpeed * Math.cos(angle));
				vel.y = (float) (maxSpeed * Math.sin(angle));

				// and send the otherBug away in the opposite direction: angle-PI
				dove.vel.x = (float) (maxSpeed * Math.cos(angle - Math.PI));
				dove.vel.y = (float) (maxSpeed * Math.sin(angle - Math.PI));
			}

		}
		
		
		
		
		
		boolean detectPreyCollision(NightHawk nightHawk){
			boolean bump = false;
			if (Math.abs((pos.x) - nightHawk.pos.x) < ((BODY_WIDTH / 2 + HEAD_WIDTH - 2) * scale + 
					(nightHawk.HEAD_WIDTH / 2 + HEAD_WIDTH / 2) * nightHawk.scale)
			&& Math.abs((pos.y) - nightHawk.pos.y) < (BODY_HEIGHT / 2 * scale + 
					nightHawk.HEAD_WIDTH / 2 * nightHawk.scale)) {
					bump = true;
			
			}
					
			
			double angle = Math.atan2(pos.y - nightHawk.pos.y, pos.x - nightHawk.pos.x);
			
					if (scale * 2 < nightHawk.scale)
				    {	
					// Make the current bird moves away along angle
					vel.x = (float) (maxSpeed * Math.cos(angle));
					vel.y = (float) (maxSpeed * Math.sin(angle));
				    }
					
			return bump;
		}
		
			
		

	
		
		
		
	
		
		
		
		
		

		

		
		public void changeBirdColor1() {
			colorCode1 = 204;
			colorCode2 = 0;
			colorCode3 = 0;
		}
		
		public void changeBirdColor2() {
			colorCode1 = 128;
			colorCode2 = 191;
			colorCode3 = 255;
		}
		
		public void changeBirdStrokeColor1() {
			colorCodeStroke1 = 102;
			colorCodeStroke2 = 0;
			colorCodeStroke3 = 0;
		}
		
		public void changeBirdStrokeColor2() {
			colorCodeStroke1 = 0;
			colorCodeStroke2 = 51;
			colorCodeStroke3 = 102;
		}
		
		
		
	
//Constructor
 public BlueJay(double x, double y, double velX, double velY, double s) {
		
		//Instantiate Blue Jay's attributes
		super(x,y,velX,velY,s);   //Explicitly invokes the superclass constructor
		
		//Instantiate Head Features
		head = new Ellipse2D.Double();
		eye1 = new Ellipse2D.Double();
		eye2 = new Ellipse2D.Double();
		headStripe1 = new QuadCurve2D.Double();
		headStripe2 = new QuadCurve2D.Double();
		beak = new QuadCurve2D.Double();
		
		//Instantiate Body Features
		body = new Ellipse2D.Double();
		
		leftWing1 = new Rectangle2D.Double();
		leftWing2 = new Rectangle2D.Double();        
		
		rightWing1 = new Rectangle2D.Double();
		rightWing2 = new Rectangle2D.Double();
		
		leftFeather1 = new Ellipse2D.Double();
		leftFeather2 = new Ellipse2D.Double();
		leftFeather3 = new Ellipse2D.Double();
		leftFeather4 = new Ellipse2D.Double();
		leftFeather5 = new Ellipse2D.Double();
		
		rightFeather1 = new Ellipse2D.Double();
		rightFeather2 = new Ellipse2D.Double();
		rightFeather3 = new Ellipse2D.Double();
		rightFeather4 = new Ellipse2D.Double();
		rightFeather5 = new Ellipse2D.Double();
		
		tailFeather1 = new Ellipse2D.Double();
		tailFeather2 = new Ellipse2D.Double();
		tailFeather3 = new Ellipse2D.Double();
		tailFeather4 = new Ellipse2D.Double();
		tailFeather5 = new Ellipse2D.Double();

		leftSatFeather1 = new Ellipse2D.Double();
		leftSatFeather2 = new Ellipse2D.Double();
		leftSatFeather3 = new Ellipse2D.Double();
		
		rightSatFeather1 = new Ellipse2D.Double();
		rightSatFeather2 = new Ellipse2D.Double();
		rightSatFeather3 = new Ellipse2D.Double();
		
		

		
		//Method calls to properly position and draw the Blue Jay's features
		setHeadAttributes();
		setBodyAttributes();		
	}


}
