package refactoring.point;

public class Point {
	
	private int x;
	private int y;
	
	public Point(){
		x = 0;
		y = 0;
	}

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	public double distance( Point p1, Point p2 ){
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
