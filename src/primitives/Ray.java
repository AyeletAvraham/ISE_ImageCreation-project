package primitives;

import java.util.List;
import geometries.Intersectable.GeoPoint;

import java.util.Objects;

import static java.lang.System.out;
import static primitives.Util.isZero;

public class Ray
{
	private static final double DELTA = 0.1;
	private Point p0;
	private Vector dir;
	public Ray(Point p0, Vector dir)
	{
		try 
		{
			dir = dir.normalize();
			Double3 temp = dir.getXyz();
			if (!isZero(((temp.d1*temp.d1) + (temp.d2*temp.d2) + (temp.d3*temp.d3))-1))
				throw new IllegalArgumentException("ERROR, this Vector is not normalized");
			this.p0 = p0;
			this.dir = dir;
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

    public Ray(Point head, Vector direction, Vector normal) {
        double delta = direction.dotProduct(normal) >= 0 ? DELTA : -DELTA;
        p0 = head.add(normal.scale(delta));
        dir = direction;
    }
	public Point getP0() 
	{
		return p0;
	}
	public Vector getDir() 
	{
		return dir.normalize();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return dir.equals(other.dir) && p0.equals(other.p0);
	}
	@Override
	public String toString() {
		return "p0 =" + p0 + ", dir =" + dir;
	}
	public Point getPoint(double t)
	{
		return p0.add(dir.scale(t));
	}
	public Point findClosestPoint(List<Point> points) {
	    return points == null || points.isEmpty() ? null
	           : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}
/*refactoring
	public Point findClosestPoints(List<Point> lst)
	{
		if(lst== null) // if there are not intersection points
			return null;
		double minDis = lst.get(0).distance(p0);
		Point tempP = lst.get(0);
		for(int i = 1; i<lst.size(); i++)  //find the min distance and save its point
		{
			if(minDis > lst.get(i).distance(p0))
			{
				minDis = lst.get(i).distance(p0);
				tempP =lst.get(i);
			}
		}
		return tempP;
	}
	*/

	public GeoPoint findClosestGeoPoint(List<GeoPoint> lst)
	{
		if(lst== null) // if there are not intersection points
			return null;
		double minDis = lst.get(0).point.distance(p0);
		GeoPoint tempP = lst.get(0);
		for(int i = 1; i<lst.size(); i++)  //find the min distance and save its point
		{
			if(minDis > lst.get(i).point.distance(p0))
			{
				minDis = lst.get(i).point.distance(p0);
				tempP =lst.get(i);
			}
		}
		return tempP;
	}
}
