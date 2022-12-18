import java.awt.*;
import java.awt.geom.*;

import processing.core.PVector;


public class Park{

//Fields
	
	//Declaration for Park features
	private Rectangle2D.Double sidewalk; 
	private RoundRectangle2D.Double sidewalkCrease1;
	private RoundRectangle2D.Double sidewalkCrease2;
	private RoundRectangle2D.Double sidewalkCrease3;
	private RoundRectangle2D.Double sidewalkCrease4;
	private RoundRectangle2D.Double sidewalkCrease5;
	private RoundRectangle2D.Double sidewalkCrease6;
	private RoundRectangle2D.Double sidewalkCrease7;
	private RoundRectangle2D.Double sidewalkCrease8;
	private RoundRectangle2D.Double sidewalkCrease9;
	private RoundRectangle2D.Double sidewalkCrease10;
	private RoundRectangle2D.Double sidewalkCrease11;
	private RoundRectangle2D.Double sidewalkCrease12;
	
	private RoundRectangle2D.Double lawnDetail1;
	private RoundRectangle2D.Double lawnDetail2;
	private RoundRectangle2D.Double lawnDetail3;
	
	
	
//Methods
private void setParkAttributes()
{	
	sidewalk.setFrame(20,600,1160,120);
	sidewalkCrease1.setFrame(40,600,10,120);
	sidewalkCrease2.setFrame(140,600,10,120);
	sidewalkCrease3.setFrame(240,600,10,120);
	sidewalkCrease4.setFrame(340,600,10,120);
	sidewalkCrease5.setFrame(440,600,10,120);
	sidewalkCrease6.setFrame(540,600,10,120);
	sidewalkCrease7.setFrame(640,600,10,120);
	sidewalkCrease8.setFrame(740,600,10,120);
	sidewalkCrease9.setFrame(840,600,10,120);
	sidewalkCrease10.setFrame(940,600,10,120);
	sidewalkCrease11.setFrame(1040,600,10,120);
	sidewalkCrease12.setFrame(1140,600,10,120);
	
	lawnDetail1.setFrame(50,40,1100,530);
	lawnDetail2.setFrame(70,70,700,350);
	lawnDetail3.setFrame(420,180,700,350);
}	
	
	
	
public void drawPark(Graphics2D g2)
{
	//Set up initial location
	AffineTransform transform = g2.getTransform();
	
	//Draw Background Color
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2.setColor(new Color(179, 255, 153));
	g2.fillRect(20, 20, 1160, 760);	
	
	//Draw sidewalk
	g2.setColor(new Color(90,90,90));
	g2.fill(sidewalk);
	
	g2.setColor(new Color(50,50,50));
	g2.fill(sidewalkCrease1);
	g2.fill(sidewalkCrease2);
	g2.fill(sidewalkCrease3);
	g2.fill(sidewalkCrease4);
	g2.fill(sidewalkCrease5);
	g2.fill(sidewalkCrease6);
	g2.fill(sidewalkCrease7);
	g2.fill(sidewalkCrease8);
	g2.fill(sidewalkCrease9);
	g2.fill(sidewalkCrease10);
	g2.fill(sidewalkCrease11);
	g2.fill(sidewalkCrease12);

	g2.setColor(new Color(128, 255, 128));
	g2.fill(lawnDetail1);
	
	g2.setColor(new Color(153, 255, 153));
	g2.fill(lawnDetail2);
	g2.fill(lawnDetail3);
			
	g2.setTransform(transform);
}

	
	
	
	
//Constructor
	public Park(double x, double y, double wid , double height){
				
		//Instantiate Park Features
		sidewalk = new Rectangle2D.Double(); 
		sidewalkCrease1 = new RoundRectangle2D.Double(40,600,10,120,10,10); 
		sidewalkCrease2 = new RoundRectangle2D.Double(140,600,10,120,10,10); 
		sidewalkCrease3 = new RoundRectangle2D.Double(240,600,10,120,10,10); 
		sidewalkCrease4 = new RoundRectangle2D.Double(340,600,10,120,10,10); 
		sidewalkCrease5 = new RoundRectangle2D.Double(440,600,10,120,10,10); 
		sidewalkCrease6 = new RoundRectangle2D.Double(540,600,10,120,10,10); 
		sidewalkCrease7 = new RoundRectangle2D.Double(640,600,10,120,10,10); 
		sidewalkCrease8 = new RoundRectangle2D.Double(740,600,10,120,10,10); 
		sidewalkCrease9 = new RoundRectangle2D.Double(840,600,10,120,10,10); 
		sidewalkCrease10 = new RoundRectangle2D.Double(940,600,10,120,10,10); 
		sidewalkCrease11 = new RoundRectangle2D.Double(1040,600,10,120,10,10); 
		sidewalkCrease12 = new RoundRectangle2D.Double(1140,600,10,120,10,10); 
		
		lawnDetail1 = new RoundRectangle2D.Double(50,40,1100,530,50,50);
		lawnDetail2 = new RoundRectangle2D.Double(70,70,700,350,40,40);
		lawnDetail3 = new RoundRectangle2D.Double(200,200,700,350,40,40);
			
	
		//Method calls to properly position and draw the Park's features
		setParkAttributes();
}
	

}
