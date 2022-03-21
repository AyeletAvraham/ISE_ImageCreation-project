package geometries;
import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public List<Point> findIntersections(Ray ray) 
	{
		List <Point> lst = null;
		Point p1, p2;
		Point p0 = ray.getP0();
		Vector v = ray.getDir();
		if(p0.equals(center))
		{
			lst = new ArrayList<Point>();
			p1 = ray.getPoint(radius); //refactoring
			//p1 = p0.add(v.scale(radius));
			lst.add(p1);
			return lst;
		}
		Vector u = center.subtract(p0);
		double tm = v.dotProduct(u);
		double d = Math.sqrt(u.lengthSquared()-(tm*tm));
		double th = Math.sqrt(radius*radius - d*d);
		double t1 = tm-th;
		double t2 = tm+th;
		
		if (d<radius)
		{
			if(t1>0||t2>0)
				lst = new ArrayList<Point>();
			if(t1>0)
			{
				p1 = ray.getPoint(t1); //refactoring
				//p1 = p0.add(v.scale(t1));
				lst.add(p1);
			}
			if(t2>0)
			{
				p2 = ray.getPoint(t2); //refactoring
				//p2 = p0.add(v.scale(t2));
				lst.add(p2);
			}
		}
		
		return lst;
	}

}
