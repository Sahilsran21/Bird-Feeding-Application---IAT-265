//A class for the 2nd non-predatory bird (Dove)

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
	

public class Dove extends Bird implements ActionListener{
//Fields
	
//	private Timer timer;
		
	//Declaration for blue jay shape features
	//Head Features
	private Ellipse2D.Double doveHead;
	private Ellipse2D.Double doveEyes1;
	private Ellipse2D.Double doveEyes2;
	private Ellipse2D.Double doveBeak;
	
	
	//Body Features
	private Ellipse2D.Double doveBody;
	
	private Ellipse2D.Double doveLeftWings;
	private Ellipse2D.Double doveLeftWings2;
	private Ellipse2D.Double doveLeftWings3;

	private Ellipse2D.Double doveRightWings;
	private Ellipse2D.Double doveRightWings2;
	private Ellipse2D.Double doveRightWings3;

	private Ellipse2D.Double doveTail;
	private Ellipse2D.Double doveTail2;
	private Ellipse2D.Double doveTail3;
		
	
	
	//Estimates of Dove's width and height for hit detection
	public final int HEAD_WIDTH = 15;
	public final int HEAD_HEIGHT = 15;
	public final int BODY_WIDTH = 20;
	public final int BODY_HEIGHT = 45;
	


	


	// Set Up Wing Rotation and Angle Speed (USED IN BONUS FOR ANIMAL MOVEMENT
	// ANIMATION)
	private double wingRotation1;
	private double angleSpeed1 = Math.PI / 18;

	private double wingRotation2;
	private double angleSpeed2 = Math.PI / 18;

	// Set Up Angle Limit for Wing Rotation (USED IN BONUS FOR ANIMAL MOVEMENT
	// ANIMATION)
	private double angleLimit = 2.1;

	// Set Up Dove Body Width and Height
	private final int bodyWidth = 30;
	private final int bodyHeight = 20;

	// Set Up Dove Wings
	private final int wingsWidth = 5;
	private final int wingsHeight = 20;

	// Set Up Dove Tail
	private final int tailWidth = 10;
	private final int tailHeight = 5;

	// Set Up Dove Eyes
	private final int eyesWidth = 5;

	
	

	
	//Set up dove's attributes
		public void setDoveBodyAttributes() {
			doveBody.setFrame(-bodyWidth / 2, -bodyHeight / 2, bodyWidth, bodyHeight);

			doveLeftWings.setFrame(-wingsWidth / 2, -wingsHeight / 2 + 15, wingsWidth, wingsHeight);
			doveRightWings.setFrame(-wingsWidth / 2, -wingsHeight / 2 - 15, wingsWidth, wingsHeight);

			doveLeftWings2.setFrame(-wingsWidth / 2 - 5, -wingsHeight / 2 + 15, wingsWidth, wingsHeight - 6);
			doveRightWings2.setFrame(-wingsWidth / 2 - 5, -wingsHeight / 2 - 9, wingsWidth, wingsHeight - 6);
			
			doveLeftWings3.setFrame(-wingsWidth / 2 + 5, -wingsHeight / 2 + 15, wingsWidth + 1.5, wingsHeight);
			doveRightWings3.setFrame(-wingsWidth / 2 + 5, -wingsHeight / 2 - 15, wingsWidth + 1.5, wingsHeight);

			doveTail.setFrame(-tailWidth / 2 - 21, -tailHeight / 2, tailWidth + 3, tailHeight);
			doveTail2.setFrame(-tailWidth / 2 - 16, -tailHeight / 2 - 4, tailWidth, tailHeight);
			doveTail3.setFrame(-tailWidth / 2 - 16, -tailHeight / 2 + 4, tailWidth, tailHeight);
		}
			
			

		
		public void setDoveHeadAttributes() {
			doveHead.setFrame(bodyWidth / 2 - 3, bodyHeight / 2 - 15, bodyWidth / 2 - 5, bodyHeight / 2);

			doveEyes1.setFrame(-eyesWidth / 2 + 18, -eyesWidth / 2 - 1, eyesWidth / 2, eyesWidth / 2);
			doveEyes2.setFrame(-eyesWidth / 2 + 18, -eyesWidth / 2 + 3, eyesWidth / 2, eyesWidth / 2);

			doveBeak.setFrame(-tailWidth / 2 + 24, -tailHeight / 2 + 0.5, tailWidth / 2 + 2, tailHeight / 2 + 1);
		}


		
	
	

		public void draw(Graphics2D g2) {
				// Push Matrix
				AffineTransform pushPopMatrix = g2.getTransform();

				// Sets Up an Angle for Wing Flapping (USED IN BONUS FOR ANIMAL MOVEMENT
				// ANIMATION)
				double s1 = Math.cos(wingRotation1);

				// Sets Up Wing Flap Speed (USED IN BONUS FOR ANIMAL MOVEMENT ANIMATION)
				wingRotation1 += angleSpeed1;

				// Basic Translations
				g2.translate(pos.x, (pos.y));
				g2.rotate(angle);
				g2.scale(scale, scale);

				g2.rotate(s1);

				// Sets Up an Angle Limit for How Far the Wings Flap (USED IN BONUS FOR ANIMAL
				// MOVEMENT ANIMATION)
				if (Math.abs(wingRotation1) > angleLimit) {
					angleSpeed1 *= -1;
				}

				// Wings Fill
				g2.setColor(Color.WHITE);
				g2.fill(doveLeftWings);
				g2.fill(doveLeftWings2);
				g2.fill(doveLeftWings3);

				// Dove Wings Outline
				g2.setStroke(new BasicStroke(1.2f));
				g2.setColor(Color.BLACK);
				g2.draw(doveLeftWings);
				g2.draw(doveLeftWings2);
				g2.draw(doveLeftWings3);

				// Pop Matrix
				g2.setTransform(pushPopMatrix);
				
				
				// Push Matrix
				AffineTransform pushPopMatrix1 = g2.getTransform();

				// Sets Up an Angle for Wing Flapping (USED IN BONUS FOR ANIMAL MOVEMENT
				// ANIMATION)
				double s2 = -Math.cos(wingRotation2);

				// Sets Up Wing Flap Speed (USED IN BONUS FOR ANIMAL MOVEMENT ANIMATION)
				wingRotation2 += angleSpeed2;

				// Basic Translations
				g2.translate(pos.x, (pos.y));
				g2.rotate(angle);
				g2.scale(scale, scale);
				g2.rotate(s2);

				// Sets Up an Angle Limit for How Far the Wings Flap (USED IN BONUS FOR ANIMAL
				// MOVEMENT ANIMATION)
				if (Math.abs(wingRotation2) > angleLimit) {
					angleSpeed2 *= -1;
				}

				// Wings Fill
				g2.setColor(Color.WHITE);
				g2.fill(doveRightWings);
				g2.fill(doveRightWings2);
				g2.fill(doveRightWings3);

				// Wings Outline
				g2.setStroke(new BasicStroke(1.2f));
				g2.setColor(Color.BLACK);
				g2.draw(doveRightWings);
				g2.draw(doveRightWings2);
				g2.draw(doveRightWings3);

				// Pop Matrix
				g2.setTransform(pushPopMatrix1);
				
				
				// Angle
				angle = vel.heading();

				// pushMatrix();
				AffineTransform pushPopMatrix2 = g2.getTransform();

				// Translate and Set Up Shape
				g2.translate(pos.x, (pos.y));
				g2.rotate(angle);
				g2.scale(scale, scale);

				// Dove Tail Colors
				g2.setColor(Color.WHITE);
				g2.fill(doveTail2);
				g2.fill(doveTail3);

				// Dove Tail Outline
				g2.setStroke(new BasicStroke(1.2f));
				g2.setColor(Color.BLACK);
				g2.draw(doveTail2);
				g2.draw(doveTail3);

				g2.setColor(Color.WHITE);
				g2.fill(doveTail);

				g2.setStroke(new BasicStroke(1.2f));
				g2.setColor(Color.BLACK);
				g2.draw(doveTail);

				// Dove Body Colors
				g2.setColor(Color.WHITE);
				g2.fill(doveBody);

				// Dove Body Outline
				g2.setStroke(new BasicStroke(1.2f));
				g2.setColor(Color.BLACK);
				g2.draw(doveBody);

				// Dove Head Colors
				g2.setColor(Color.WHITE);
				g2.fill(doveHead);

				// Dove Head Outline
				g2.setStroke(new BasicStroke(1.2f));
				g2.setColor(Color.BLACK);
				g2.draw(doveHead);

				// Dove Eyes Colors
				g2.setColor(Color.BLACK);
				g2.fill(doveEyes1);
				g2.fill(doveEyes2);

				// Dove Beak Colors
				g2.setColor(Color.PINK);
				g2.fill(doveBeak);

				// Dove Beak Outline
				g2.setStroke(new BasicStroke(1f));
				g2.setColor(Color.BLACK);
				g2.draw(doveBeak);

				// popMatrix();
				g2.setTransform(pushPopMatrix2);
	}
	
		
		
	public void drawSat(Graphics2D g2) {

			// Angle
			angle = vel.heading();

			// pushMatrix();
			AffineTransform pushPopMatrix2 = g2.getTransform();

			// Translate and Set Up Shape
			g2.translate(pos.x, (pos.y));
			g2.rotate(angle);
			g2.scale(scale, scale);

			// Dove Tail Colors
			g2.setColor(Color.WHITE);
			g2.fill(doveTail2);
			g2.fill(doveTail3);

			// Dove Tail Outline
			g2.setStroke(new BasicStroke(1.2f));
			g2.setColor(Color.BLACK);
			g2.draw(doveTail2);
			g2.draw(doveTail3);

			g2.setColor(Color.WHITE);
			g2.fill(doveTail);

			g2.setStroke(new BasicStroke(1.2f));
			g2.setColor(Color.BLACK);
			g2.draw(doveTail);

			// Dove Body Colors
			g2.setColor(Color.WHITE);
			g2.fill(doveBody);

			// Dove Body Outline
			g2.setStroke(new BasicStroke(1.2f));
			g2.setColor(Color.BLACK);
			g2.draw(doveBody);

			// Dove Head Colors
			g2.setColor(Color.WHITE);
			g2.fill(doveHead);

			// Dove Head Outline
			g2.setStroke(new BasicStroke(1.2f));
			g2.setColor(Color.BLACK);
			g2.draw(doveHead);

			// Dove Eyes Colors
			g2.setColor(Color.BLACK);
			g2.fill(doveEyes1);
			g2.fill(doveEyes2);

			// Dove Beak Colors
			g2.setColor(Color.PINK);
			g2.fill(doveBeak);

			// Dove Beak Outline
			g2.setStroke(new BasicStroke(1f));
			g2.setColor(Color.BLACK);
			g2.draw(doveBeak);

			// popMatrix();
			g2.setTransform(pushPopMatrix2);	
			
			
			// Push Matrix
			AffineTransform pushPopMatrix = g2.getTransform();


			// Basic Translations
			g2.translate(pos.x, (pos.y));
			g2.rotate(angle);
			g2.scale(scale, scale);


			// Wings Fill
			g2.setColor(Color.WHITE);
			g2.fill(doveLeftWings);
			g2.fill(doveLeftWings2);
			g2.fill(doveLeftWings3);

			// Dove Wings Outline
			g2.setStroke(new BasicStroke(1.2f));
			g2.setColor(Color.BLACK);
			g2.draw(doveLeftWings);
			g2.draw(doveLeftWings2);
			g2.draw(doveLeftWings3);

			// Pop Matrix
			g2.setTransform(pushPopMatrix);
			
			
			// Push Matrix
			AffineTransform pushPopMatrix1 = g2.getTransform();



			// Basic Translations
			g2.translate(pos.x, (pos.y));
			g2.rotate(angle);
			g2.scale(scale, scale);



			// Wings Fill
			g2.setColor(Color.WHITE);
			g2.fill(doveRightWings);
			g2.fill(doveRightWings2);
			g2.fill(doveRightWings3);

			// Wings Outline
			g2.setStroke(new BasicStroke(1.2f));
			g2.setColor(Color.BLACK);
			g2.draw(doveRightWings);
			g2.draw(doveRightWings2);
			g2.draw(doveRightWings3);

			// Pop Matrix
			g2.setTransform(pushPopMatrix1);
	}

	
	
	// Method to detect collision between the current bird and the other bird
		
			

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
							if (Math.abs((pos.x) - nightHawk.pos.x) < ((BODY_WIDTH / 2 + (BODY_WIDTH/2) - 2) * scale + 
												(nightHawk.HEAD_WIDTH / 2 + (BODY_WIDTH/2) / 2) * nightHawk.scale)
										&& Math.abs((pos.y) - nightHawk.pos.y) < (BODY_HEIGHT / 2 * scale + 
												nightHawk.HEAD_WIDTH / 2 * nightHawk.scale)) {
								bump = true;
							}
							
							
							double angle = Math.atan2(pos.y - nightHawk.pos.y, pos.x - nightHawk.pos.x);
							
							if (scale * 1.2 < nightHawk.scale)
						    {	
							// Make the current bird moves away along angle
							vel.x = (float) (maxSpeed * Math.cos(angle));
							vel.y = (float) (maxSpeed * Math.sin(angle));
						    }
							

						return bump;
					}
	
			
			
			
			
//			void detectCollision(NightHawk nightHawk) {
//				
//				//old test using bounding boxes that do not rotate		
//				if (Math.abs((pos.x) - nightHawk.pos.x) < ((BODY_WIDTH / 2 + HEAD_WIDTH - 2) * scale + 
//						(nightHawk.HEAD_WIDTH / 2 + HEAD_WIDTH / 2) * nightHawk.scale)
//				&& Math.abs((pos.y) - nightHawk.pos.y) < (BODY_HEIGHT / 2 * scale + 
//						nightHawk.HEAD_WIDTH / 2 * nightHawk.scale)) {
//					
//					
//					
//					double angle = Math.atan2(pos.y - nightHawk.pos.y, pos.x - nightHawk.pos.x);
//					
//					
//				if (nightHawk.scale > scale)
//				    {	
//					// Make the current bird moves away along angle
//					vel.x = (float) (maxSpeed * Math.cos(angle));
//					vel.y = (float) (maxSpeed * Math.sin(angle));
//				    }
//			
//				
//				  	if (scale > nightHawk.scale)
//				    {
//					// and send the bird away in the opposite direction: angle-PI
//					nightHawk.vel.x = (float) (maxSpeed * Math.cos(angle - Math.PI));
//					nightHawk.vel.y = (float) (maxSpeed * Math.sin(angle - Math.PI));
//				    }
//		}
//
//	}	
		
			
			
			
			
			
			

	
	
	
	


//Dove Constructor
	public Dove(double x, double y, double velX, double velY, double s) {

		//Instantiate dove's attributes
		super(x,y,velX,velY,s);
		
		//Instantiate Head Features
		doveHead = new Ellipse2D.Double();
		doveEyes1 = new Ellipse2D.Double();
		doveEyes2 = new Ellipse2D.Double();
		doveBeak = new Ellipse2D.Double();

		
		//Instantiate Body Features
		doveBody = new Ellipse2D.Double();

		doveLeftWings = new Ellipse2D.Double();
		doveLeftWings2 = new Ellipse2D.Double();
		doveLeftWings3 = new Ellipse2D.Double();
		
		doveRightWings = new Ellipse2D.Double();
		doveRightWings2 = new Ellipse2D.Double();
		doveRightWings3 = new Ellipse2D.Double();

		doveTail = new Ellipse2D.Double();
		doveTail2 = new Ellipse2D.Double();
		doveTail3 = new Ellipse2D.Double();

		
		//Method calls to properly position and draw the dove's features
		setDoveBodyAttributes();
		setDoveHeadAttributes();
	}
		
		
		
		
		
	}