
//A BlueJay panel that draws a BlueJay moving around in a garden
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BirdPanel extends JPanel implements ActionListener {

//Fields

	// ARRAYLIST JUSTIFICATION
	// I'm keeping all bird objects in arraylist bird and all the bug objects in
	// what is currently arraylist caterpillar, because this gives me the ability 
	// to implement the same shape caterpillar without having to use an abstract class.
	

	
	
	public ArrayList<Bird> bird;
	//public ArrayList<NightHawk> nightHawk;    -- //Might fix predator issue
	//public ArrayList<SeedEatingBird> seedEatingBird; 
	
	public ArrayList<Food> food;
	private Park park;
	private Timer timer;

	public final static int PARK_X = 20;
	public final static int PARK_Y = 20;
	public final static int PARK_WIDTH = 1160;
	public final static int PARK_HEIGHT = 760;
	public final static int CATERPILLAR_COUNT = 10;
	public final static int SEED_COUNT = 10;
	public final static int BLUEJAY_COUNT = 3;
	public final static int DOVE_COUNT = 3;
	public final static int NIGHTHAWK_COUNT = 2;
	//public final static int NON_PREDATORY_BIRD_COUNT = 5;

//Methods

	// Method for essentially creating the background (could do method call to Park
	// inside here)
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call JPanel's method to clear the background
		Graphics2D g2 = (Graphics2D) g;
		park.drawPark(g2);

		// Setup Arraylist to add instances of bird creature objects
		// Draw the blue Jay and detect collision
		for (int i = 0; i < bird.size(); i++) { // (Clumping all 3 for loops together = slower run time)

			if (bird.get(i) instanceof BlueJay) {
				BlueJay birdi = (BlueJay) bird.get(i);

				if (birdi.isMoving == true) {
					birdi.draw(g2);
					// Shape rb1 = birdi.getTranslatedShape();
				}

				if (birdi.isMoving == false) {
					birdi.drawSat(g2);
				}

				for (int j = 0; j < bird.size(); j++) {

					if (bird.get(j) instanceof Dove) {
						Dove birdj = (Dove) bird.get(j);

						if (birdj.isMoving == true) {
							birdj.draw(g2);
						}

						if (birdj.isMoving == false) {
							birdj.drawSat(g2);
						}

						for (int k = 0; k < bird.size(); k++) {

							if (bird.get(k) instanceof NightHawk) {
								NightHawk birdk = (NightHawk) bird.get(k);

								if (birdk.isMoving == true) {
									birdk.draw(g2);
								}

								if (birdk.isMoving == false) {
									birdk.drawSat(g2);
								}
								
								
								
								
								
						//Check Nighthawk hit detection with BlueJay and Dove
						for (int k1 = 0; k1 < bird.size(); k1++) {
								if (bird.get(k1) instanceof BlueJay) {
									BlueJay birdk1 = (BlueJay) bird.get(k1);
									
							
										if (birdk1.detectPreyCollision(birdk)) // Detect their collision
										{	
											//birdk1.detectCollision(birdk1);
											birdk.stopAndLand();// Timer to make blue jay stop and wait
											bird.remove(k1);									
										}
										
										
										if (bird.size() < BLUEJAY_COUNT + 4 ) {   //IMPORTANT *Size declaration for some reason doesn't store value correctly
											bird.add(new BlueJay(RandomUtility.random(20, 1160), RandomUtility.random(20, 760),
													RandomUtility.random(-5, 5), RandomUtility.random(-5, 5), 1.5 - 0.5));										
										}
							
									
								}
						}
						
								
						for (int k2 = 0; k2 < bird.size(); k2++) {					
								if (bird.get(k2) instanceof Dove) {
									Dove birdk1 = (Dove) bird.get(k2);

									
										if (birdk1.detectPreyCollision(birdk)) // Detect their collision
										{
											//birdk1.detectCollision(birdk);
											birdk.stopAndLand();// Timer to make blue jay stop and wait
											bird.remove(k2);
										}
										
										
										if (bird.size() < DOVE_COUNT + 4) { 
											bird.add(new Dove(RandomUtility.random(20, 1160), RandomUtility.random(20, 760),
													RandomUtility.random(-5, 5), RandomUtility.random(-5, 5), 1.5 + 0.1));
										}				
								}
						}
								
								
						
						
						

								for (int l = 0; l < food.size(); l++) {
									if (food.get(l) instanceof Caterpillar) {
										Caterpillar foodl = (Caterpillar) food.get(l);

										
										if (foodl.detectCollision(birdi)) // Detect their collision
										{
											birdi.stopAndLand();// Timer to make blue jay stop and wait
											food.remove(l);
										}

										if (foodl.detectCollision(birdj)) // Detect their collision
										{
											birdj.stopAndLand();// Timer to make blue jay stop and wait
											food.remove(l);
										}

										if (foodl.detectCollision(birdk)) // Detect their collision
										{
											birdk.stopAndLand();// Timer to make blue jay stop and wait
											food.remove(l);
										}

										if (food.size() < CATERPILLAR_COUNT + 7) {
											food.add(new Caterpillar(RandomUtility.random(PARK_X, PARK_WIDTH),
													RandomUtility.random(PARK_Y, PARK_HEIGHT - 40), 1.5));
										}
										foodl.draw(g2); // Otherwise, the caterpillar lives

									}
								}

								for (int m = 0; m < food.size(); m++) {
									if (food.get(m) instanceof Seed) {
										Seed foodm = (Seed) food.get(m);
										

										if (foodm.detectCollision(birdi)) // Detect their collision
										{
											birdi.stopAndLand();// Timer to make blue jay stop and wait
											food.remove(m);
										}

										if (foodm.detectCollision(birdj)) // Detect their collision
										{
											birdj.stopAndLand();// Timer to make blue jay stop and wait
											food.remove(m);
										}

										if (foodm.detectCollision(birdk)) // Detect their collision
										{
											birdk.stopAndLand();// Timer to make blue jay stop and wait
											food.remove(m);
											
										}

										if (food.size() < SEED_COUNT + 7) {
											food.add(new Seed(RandomUtility.random(PARK_X, PARK_WIDTH), 
													RandomUtility.random(PARK_Y, PARK_HEIGHT - 40), 1.5));
										}
									foodm.draw(g2); // Otherwise, the caterpillar lives
										
										
										
										
									}
								}

							}
						}
					}
				}
			}
		}

		// Setup for checking collision between birds

		for (int i = 0; i < bird.size(); i++) {
			if (bird.get(i) instanceof BlueJay) {
				BlueJay birdi = (BlueJay) bird.get(i);

				for (int i1 = i + 1; i1 < bird.size(); i1++) {
					if (bird.get(i1) instanceof BlueJay) {
						BlueJay birdi1 = (BlueJay) bird.get(i1);

						birdi.detectCollision(birdi1);
					}
				}

				for (int j = 0; j < bird.size(); j++) {
					if (bird.get(j) instanceof Dove) {
						Dove birdj = (Dove) bird.get(j);

						for (int j1 = j + 1; j1 < bird.size(); j1++) {
							if (bird.get(j1) instanceof Dove) {
								Dove birdj1 = (Dove) bird.get(j1);

								birdj.detectCollision(birdj1);
							}
						}

						for (int k = 0; k < bird.size(); k++) {
							if (bird.get(k) instanceof NightHawk) {
								NightHawk birdk = (NightHawk) bird.get(k);

								for (int k1 = k + 1; k1 < bird.size(); k1++) {
									if (bird.get(k1) instanceof NightHawk) {
										NightHawk birdk1 = (NightHawk) bird.get(k1);

										birdk.detectCollision(birdk1);
									}
								}

								birdi.detectCollision(birdj);
								//birdi.detectCollision(birdk);
								//birdj.detectCollision(birdk);


							}
						}
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < bird.size(); i++) {
			if (bird.get(i) instanceof BlueJay) {
				BlueJay birdi = (BlueJay) bird.get(i);
				birdi.move();

				for (int j = 0; j < food.size(); j++) {
					Food foodToChase = birdi.findBestFood(food);
					if (foodToChase != null) {
						birdi.attractedBy(foodToChase);
					}
				}
			}
		
	
			else if (bird.get(i) instanceof Dove) {
				Dove birdi = (Dove) bird.get(i);
				birdi.move();

				for (int j = 0; j < food.size(); j++) {
						 Food foodToChase = birdi.findBestFood(food);
						 if (foodToChase != null) {
						 birdi.attractedBy(foodToChase);
						 }
				}
			}
			

			else if (bird.get(i) instanceof NightHawk) {
				NightHawk birdi = (NightHawk) bird.get(i);
				birdi.move();
				
				for (int j = 0; j < bird.size(); j++) {		
				
						Bird birdToChase = birdi.findBestBird(bird);
						if (birdToChase != null) {
						System.out.println("Chase");
							//if (birdi.scale == 1.5) //Attempting to make NightHawk of large scale chase birds
							//{
								birdi.attractedBy(birdToChase);
							//}
					}
				}
			}
		}
		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Blue Jay in Park");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BirdPanel BirdPane = new BirdPanel(); /// This is the key
		frame.add(BirdPane);
		frame.pack();
		frame.setVisible(true);
	}

	private class MyMouseAdapter extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			
			
			
			//MOUSE INTERACTION #1 - VISUAL APPEARANCE CHANGE FOR BLUEJAY
			//When mouse is pressed with shift key held down, the Blue Jay will change into a Red Jay
			//When just mouse is pressed, it will change back
			
			for (int i = 0; i < bird.size(); i++) {
				if (bird.get(i) instanceof BlueJay) {
					BlueJay birdi = (BlueJay) bird.get(i);

					if (birdi.checkMouseHit(e)) {
						if (e.isShiftDown()){
							birdi.changeBirdColor1();
							birdi.changeBirdStrokeColor1();
							System.out.println("color1");
						}
						
						else{
							birdi.select();
							birdi.changeBirdColor2();
							birdi.changeBirdStrokeColor2();
							System.out.println("color2");
						}
					} else
						birdi.deselect();
					System.out.println("deselected");

				}
				
						
				
				
				//MOUSE INTERACTION #2 - BEHAVIOR CHANGE FOR OTHER ANIMALS
				//When mouse is pressed with shift key held down over the NightHawk predator, 
				//the height the predator 
				//is flying will increase by an increment. When mouse is pressed with alt key 
				//held down, the flight height will decrease by an increment
				
				//Once the predator is high enough, the other non-predatory birds will show fear 
				//by keeping a safe distance away.
				
				//The predator NightHawk will stop chasing the birds as it exceeds its normal flying height
				//as it is not close to the ground and in pursuit of the other birds
				
				
				else if (bird.get(i) instanceof NightHawk) {
					NightHawk birdi = (NightHawk) bird.get(i);

					if (birdi.checkMouseHit(e)) {
						if (e.isShiftDown()){
							birdi.sizeUp();
							System.out.println("sizeUp");
						}
						else if (e.isAltDown()){
							birdi.sizeDown();
							System.out.println("sizeDown");
						}
						else{
							birdi.select();
							System.out.println("selected");
						}
					} else
						birdi.deselect();
					System.out.println("deselected");

				}
				
			}
		}
	}

	
	private class MyMouseMotionAdapter extends MouseMotionAdapter {

		public void mouseDragged(MouseEvent e) {
			
			
			
			
			//MOUSE INTERACTION #3 - DRAGGABLE INTERACTION
			//The food objects are now draggable and can be used as lures for the birds
			
			for (int i = 0; i < food.size(); i++) {	
				if (food.get(i) instanceof Caterpillar) {
					Caterpillar foodi = (Caterpillar) food.get(i);
			
				if (foodi.checkMouseHit(e))				{
					foodi.setPos(e.getX(), e.getY());
					System.out.println("something");
				}
				}
				
				else if (food.get(i) instanceof Seed) {
					Seed foodi = (Seed) food.get(i);
					
				if (foodi.checkMouseHit(e))				{
					foodi.setPos(e.getX(), e.getY());
					System.out.println("something");
				}
				}			
			}
		}
	}
	
	

//Constructor 
	public BirdPanel() {

		setPreferredSize(new Dimension(1200, 800));
		double scale = 1.5;
		park = new Park(20, 20, 1160, 760);
		bird = new ArrayList<Bird>();
		food = new ArrayList<Food>();
		timer = new Timer(30, this);
		timer.start();

		addMouseListener(new MyMouseAdapter());
		addMouseMotionListener(new MyMouseMotionAdapter());

		for (int i = 0; i < BLUEJAY_COUNT; i++) {
			bird.add(new BlueJay(RandomUtility.random(20, 1160), RandomUtility.random(20, 760),
					RandomUtility.random(-5, 5), RandomUtility.random(-5, 5), scale - 0.5));
		}

		for (int j = 0; j < DOVE_COUNT; j++) {
			bird.add(new Dove(RandomUtility.random(20, 1160), RandomUtility.random(20, 760),
					RandomUtility.random(-5, 5), RandomUtility.random(-5, 5), scale + 0.1));
		}

		for (int k = 0; k < NIGHTHAWK_COUNT; k++) {
			bird.add(new NightHawk(RandomUtility.random(20, 1160), RandomUtility.random(20, 760),
					RandomUtility.random(-5, 5), RandomUtility.random(-5, 5), scale - 0.55)); //0.3 - scale  (Changed because of issues with homing detection)
		}

		for (int i = 0; i < CATERPILLAR_COUNT; i++) {
			food.add(new Caterpillar(RandomUtility.random(PARK_X, PARK_WIDTH),
					RandomUtility.random(PARK_Y, PARK_HEIGHT - 40), 1.5));
		}

		for (int i = 0; i < SEED_COUNT; i++) {
			food.add(new Seed(RandomUtility.random(PARK_X, PARK_WIDTH), RandomUtility.random(PARK_Y, PARK_HEIGHT - 40),
					1.5));
		}

	}
}
