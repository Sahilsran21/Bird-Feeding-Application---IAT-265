import java.awt.*;
import java.awt.geom.*;
import processing.core.*;


public class Seed extends Food{
//Fields
	
		//Estimates of Seed's width and height for hit detection
		private final int BODY_WIDTH = 6;
		private final int BODY_HEIGHT = 8;

		//Declaration for seed shape features
		private Ellipse2D.Double seedBody;

	
		Color seedOutlineColor = new Color(22,22,22);
		Color seedColor = new Color(255, 224, 102);

		
			
		
		
		
		
//Methods	
		
		// Set Up Seed Attributes
		public void setSeedAttributes() {
				seedBody.setFrame(BODY_WIDTH / 2, BODY_HEIGHT / 2, BODY_WIDTH, BODY_HEIGHT);
				}

		

		public void draw(Graphics2D g) {
			
			// Push Matrix
			AffineTransform pushPopMatrix = g.getTransform();

			// Basic Location Setup
			g.translate(pos.x, (pos.y));
			g.scale(scale, scale);

			// Seed Fill
			g.setColor(seedColor);
			g.fill(seedBody);

			// Seed Outline
			g.setStroke(new BasicStroke(1f));
			g.setColor(seedOutlineColor);
			g.draw(seedBody);

			// Pop Matrix
			g.setTransform(pushPopMatrix);
		}


		
		
		
		
		
		
	
		
//Constructor
public Seed(double x, double y, double s) 
{
	
	//Instantiate Seed's attributes
	super(x,y,s);
	alive = true;

	//Instantiate Body Features	
	seedBody = new Ellipse2D.Double();
	
	
	setSeedAttributes();
}
	

}
	
	
	
