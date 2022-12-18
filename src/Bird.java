//A superclass to hold the info for all birds

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.Timer;

import processing.core.*;







public abstract class Bird implements ActionListener {
		
//Fields	
	//Declaration for all bird attributes
	protected PVector pos;
	protected PVector vel;
	protected double angle;
	protected double scale = 1.5;
	
	//Estimates of all bird width and height for hit detection
	public final int HEAD_WIDTH = 15;
	public final int HEAD_HEIGHT = 15;
	public final int BODY_WIDTH = 20;
	public final int BODY_HEIGHT = 45;
	
	
	public boolean isMoving = true;
	protected Timer timer;
	protected int waitTime = 2700;
	
	public float maxSpeed;
	private boolean selected;
	
	protected boolean alive;
	

	

	
	
//Methods
		
	public abstract void draw(Graphics2D g2);
	
	public abstract void drawSat(Graphics2D g2);
	
	
	
	
	
	//Private method for park edge detection
	public void edgeDetection() {
		//Check collision against left or right edge of the park
		
		if (pos.x + (BODY_WIDTH / 2 + HEAD_WIDTH) * scale > (BirdPanel.PARK_X + BirdPanel.PARK_WIDTH)) {
			pos.x = (float) (BirdPanel.PARK_X + BirdPanel.PARK_WIDTH - (BODY_WIDTH / 2 + HEAD_WIDTH) * scale);
			vel.x *= -1;
			}

		if (pos.x - (BODY_WIDTH / 2 + HEAD_WIDTH) * scale < BirdPanel.PARK_X) {
			pos.x = (float) (BirdPanel.PARK_X +(BODY_WIDTH / 2 + HEAD_WIDTH)* scale);
			vel.x *= -1;
			}
		
		// Collision against bottom or top edge of garden
		if (pos.y + BODY_HEIGHT / 2 * scale > (BirdPanel.PARK_Y + BirdPanel.PARK_HEIGHT)) {
			pos.y = (float) (BirdPanel.PARK_Y + BirdPanel.PARK_HEIGHT - BODY_HEIGHT / 2 * scale);
			vel.y *= -1;
			}
				
		if (pos.y - BODY_HEIGHT / 2 * scale < BirdPanel.PARK_Y) {
			pos.y = (float) (BirdPanel.PARK_Y + BODY_HEIGHT / 2 * scale); 
			vel.y *= -1;
			}				
	}
	
	
	
	
	//Public method for moving any bird
	public void move() {
			if(!isMoving)return;    //IMPORTANT, DO NOT ADD
			vel.limit(maxSpeed);
			pos.add(vel);
			edgeDetection();	
		}
	
	
	
	//Public method to make blueJay stop and land
	public void stopAndLand()
	{
		isMoving = false;		
		
		if (isMoving == false)
		{
			timer = new Timer(waitTime, this);
			timer.start();
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		isMoving = true;
		vel.x = (float) RandomUtility.random(-5, 5);
		vel.y = (float) RandomUtility.random(-5, 5);
		timer.stop();
	}
	
	
	
	
	
	
	public Food findBestFood(ArrayList<Food> food)
	{
	Food bestFood = null;

	if (food.size() > 0) {
		bestFood = food.get(0);
		float bestScore = bestFood.getFoodSize() / PVector.dist(pos, bestFood.getFoodPos());
		for (Food f : food) {
			float currentScore = f.getFoodSize() / PVector.dist(pos, f.pos);
			if (currentScore > bestScore) {
				bestFood = f;
				bestScore = currentScore;
			}
		}
		for (Food f : food) {
			if (f == bestFood) {
				f.setFoodColor(Color.GREEN);
			}
			else {
				f.setFoodColor(new Color(194, 158, 100));
			}
		}
	}
	return bestFood;
	}
		

	
	

public Bird findBestBird(ArrayList<Bird> bird)
{

	Bird bestBird = null;
	
if (bird.size() > 0) {
	bestBird = bird.get(0);
	float bestScore = (bestBird.getBirdSize()) / PVector.dist(pos, bestBird.getBirdPos());
	for (Bird b : bird) {
		float currentScore = (b.getBirdSize()) / PVector.dist(pos, b.pos);
		if (currentScore > bestScore) {
			bestBird = b;
			bestScore = currentScore;
		}
	}
	for (Bird b : bird) {
		if (b == bestBird) {
			b.setBirdColor(Color.GREEN);
		}
		else {
			b.setBirdColor(new Color(194, 158, 100));
		}
	}
}
return bestBird;
}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void attractedBy(Food target)
	{
		float coefficient = .2f;
		PVector direction = PVector.sub(target.pos, pos).normalize();
		PVector acc = PVector.mult(direction, maxSpeed * coefficient);
		vel.add(acc);
	}

	
	
	
	public void attractedBy(Bird target)
	{
		float coefficient = .2f;
		PVector direction = PVector.sub(target.pos, pos).normalize();
		PVector acc = PVector.mult(direction, maxSpeed * coefficient);
		vel.add(acc);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean checkMouseHit(MouseEvent e) {
		return	(Math.abs(e.getX() - pos.x) < scale + 40) && 
				(Math.abs(e.getY() - pos.y) < scale + 40);
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

	
	public void speedUp() {
		vel.x *= 1.1;
	}
	
	public void speedDown() {
		vel.x *= .9;
	}
	

	
	
	

	public int getBirdSize() {
		
		return (int) scale;
	}
	
	public void setBirdColor(Color green) {
		// TODO Auto-generated method stub
		
	}
	
	public PVector getBirdPos() {
		
		return pos;
	}
	
	
	

	
	
//Constructor 
	 public Bird(double x, double y, double velX, double velY, double s) {
			
		 	while (maxSpeed == 0)
		 		this.maxSpeed = (float) RandomUtility.random(3,5);
		 
			//Instantiate Blue Jay's attributes
			pos = new PVector((float) x, (float) y);
			vel = new PVector((float) velX, (float) velY);
			scale = s;	
			
	
		}
}
