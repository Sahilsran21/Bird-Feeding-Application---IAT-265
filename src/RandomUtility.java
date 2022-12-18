import processing.core.PVector;


public class RandomUtility {
	
	public static double random(double low, double high) {
		
		return low + Math.random() * (high - low);
		
	}
	
	
	public static double random(double high) {
		
		return (float) (Math.random() * high);
		
	}
	
	
	public static PVector randomPVector(int highX, int highY) {
		
		return new PVector((float) random(highX), (float) random(highY));
		
	}
	
	
	public static PVector randomPVector(float magnitude) {
		
		return PVector.random2D().mult(magnitude);
		
	}
	
	
	
}
