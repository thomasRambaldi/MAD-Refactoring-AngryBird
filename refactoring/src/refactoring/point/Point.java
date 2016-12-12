package refactoring.point;

public class Point {
	
	private double x;
	private double y;
	
	public Point(){
		x = 0;
		y = 0;
	}

	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}

	public static double distance( Point p1, Point p2 ){
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "( "+x+", "+y+" )";
	}
	
	
}
