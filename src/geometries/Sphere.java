package geometries;
import primitives.*;

public class Sphere implements Geometry
{
	private Point center;
	private double radius;
	
	public Sphere(Point center, double radius) 
	{
		if(radius<=0)
			throw new IllegalArgumentException("ERROR, the radius must be positive");
		this.center = center;
		this.radius = radius;
	}
	public Point getCenter() {
		return center;
	}
	public double getRadius() {
		return radius;
	}


	@Override
	public String toString() {
		return "Sphere [center=" + center + ", radius=" + radius + "]";
	}
	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		if(p.distance(center)!= radius)
			throw new IllegalArgumentException("ERROR, the point is not part of the sphere");
		return p.subtract(center);
	}

}
