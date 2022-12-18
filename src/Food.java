//A superclass to hold the info for all food items

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.Timer;

import processing.core.*;







public abstract class Food{

//Fields
	//Declaration for food attributes
	public PVector pos;
	private double angle;
	public double scale;
	
	protected boolean alive;
	protected boolean selected;
	
	public int BODY_WIDTH = 10;
	public int BODY_HEIGHT = 15;
	
	
	//Methods
	
		public abstract void draw(Graphics2D g2);
		
		//abstract boolean detectCollision(BlueJay blueJay);
			
		//abstract boolean detectCollision(Dove dove);
				
		//abstract boolean detectCollision(NightHawk nightHawk);
			

		
		
		//Detect collision between the caterpillar and the Blue Jay's head
		boolean detectCollision(BlueJay blueJay){
				boolean bump = false;
				if (alive) {
					if (Math.abs((pos.x) - blueJay.pos.x) < ((BODY_WIDTH / 2 + (BODY_WIDTH/2) - 2) * scale + 
										(blueJay.HEAD_WIDTH / 2 + (BODY_WIDTH/2) / 2) * blueJay.scale)
								&& Math.abs((pos.y + 10) - blueJay.pos.y) < (BODY_HEIGHT / 2 * scale + 
										blueJay.HEAD_HEIGHT / 2 * blueJay.scale)) {
						bump = true;
					}
				}
				return bump;
			}
		
		
		
		//Detect collision between the caterpillar and the Dove's head
		boolean detectCollision(Dove dove){
					boolean bump = false;
					if (alive) {
						if (Math.abs((pos.x) - dove.pos.x) < ((BODY_WIDTH / 2 + (BODY_WIDTH/2) - 2) * scale + 
											(dove.HEAD_WIDTH / 2 + (BODY_WIDTH/2) / 2) * dove.scale)
									&& Math.abs((pos.y) - dove.pos.y) < (BODY_HEIGHT / 2 * scale + 
											dove.HEAD_WIDTH / 2 * dove.scale)) {
							bump = true;
						}
					}
					return bump;
				}
			
		
		
		//Detect collision between the caterpillar and the Night Hawk's head
		boolean detectCollision(NightHawk nightHawk){
				boolean bump = false;
				if (alive) {
					if (Math.abs((pos.x) - nightHawk.pos.x) < ((BODY_WIDTH / 2 + (BODY_WIDTH/2) - 2) * scale + 
										(nightHawk.HEAD_WIDTH / 2 + (BODY_WIDTH/2) / 2) * nightHawk.scale)
								&& Math.abs((pos.y + 5) - nightHawk.pos.y) < (BODY_HEIGHT / 2 * scale + 
										nightHawk.HEAD_WIDTH / 2 * nightHawk.scale)) {
						bump = true;
					}
				}
				return bump;
			}
		
		
	
		

		
		
		
		
		
		
		
		
		
		
		
	
		
		public void setFoodColor(Color green) {
			// TODO Auto-generated method stub
			
		}



		public int getFoodSize() {
			
			return (int) scale;
		}



		public PVector getFoodPos() {
			
			return pos;
		}
		
		
		
		
		
		
		
		public boolean checkMouseHit(MouseEvent e) {
			return	(Math.abs(e.getX() - pos.x) < scale + 50) && 
					(Math.abs(e.getY() - pos.y) < scale + 50);
		}
		
		
		public void setPos(int x, int y) {
			pos = new PVector(x, y);		
		}
		
		public void select() {
			this.selected = true;
		}
		
		public void deselect() {
			this.selected = false;
		}
		
		
		
		
		
		
		
		
		
		
	//Constructor 
		 public Food(double x, double y, double s) {
			 
				//Instantiate Food attributes
				pos = new PVector((float) x, (float) y);
				scale = s;
				alive = true;	
		 }
	}

