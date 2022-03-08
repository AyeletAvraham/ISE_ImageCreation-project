package geometries;
import primitives.*;

public class Plane implements Geometry
{
	private Point p0;
	private Vector normal;
	
	public Point getP0() 
	{
		return p0;
	}
	public Vector getNormal() 
	{
		return normal;
	}
	public Plane(Point p0, Vector normal) 
	{
		this.p0 = p0;
		this.normal = normal;
	}
	public Plane(Point p1, Point p2, Point p3) 
	{
		if(p1.equals(p2)||p1.equals(p3)||p2.equals(p3))
			throw new IllegalArgumentException("ERROR, there are two points that are the same");
		if(p1.subtract(p2).equals(p1.subtract(p3)))
			throw new IllegalArgumentException("ERROR, all of the points are on the same vector");
		p0 = p1;
		this.normal = (p1.subtract(p2).crossProduct(p1.subtract(p3))).normalize();
	}
	@Override
	public Vector getNormal(Point p)
	{
		return normal;
	}
}
