package geometries;
import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;
import primitives.*;

public class Plane extends Geometry
{
	private Point p0;
	private Vector normal;
	
	public Point getP0() 
	{
		return p0;
	}
	public Vector getNormal() 
	{
		return normal.normalize();
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
	public boolean onPlane(Point p)
	{
		double c1 = p.getXyz().getD1()*normal.getXyz().getD1() +p.getXyz().getD2()*normal.getXyz().getD2() +p.getXyz().getD3()*normal.getXyz().getD3();
		double c2 = p0.getXyz().getD1()*normal.getXyz().getD1() +p0.getXyz().getD2()*normal.getXyz().getD2() +p0.getXyz().getD3()*normal.getXyz().getD3();
		if(c1==c2)
			return true;
		return false;
	}
	@Override
	public Vector getNormal(Point p)
	{
		return normal.normalize();
	}
	/*refactoring
	@Override
	public List<Point> findIntersections(Ray ray) 
	{
		if(isZero(ray.getDir().dotProduct(normal))) //the ray includes or parallels to the plane
			return null;
		Point p1 = ray.getP0(); // the begining of the ray
		Vector v = ray.getDir();
		Point Q = p0; // point on the plane
		double t = (normal.dotProduct(Q.subtract(p1)))/(normal.dotProduct(v));
		if(t<=0)
			return null;
		//Point p = p1.add(v.scale(t));
		Point p = ray.getPoint(t); //refactoring
		List<Point> lst = new ArrayList<Point>();
		lst.add(p);
		return lst;
	}
*/
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		
		if(isZero(ray.getDir().dotProduct(normal))) //the ray includes or parallels to the plane
			return null;
		Point p1 = ray.getP0(); // the begining of the ray
		Vector v = ray.getDir();
		Point Q = p0; // point on the plane
		double t = (normal.dotProduct(Q.subtract(p1)))/(normal.dotProduct(v));
		if(t<=0)
			return null;
		//Point p = p1.add(v.scale(t));
		Point p = ray.getPoint(t); //refactoring 
		List<GeoPoint> lst = new ArrayList<GeoPoint>();
		lst.add(new GeoPoint(this, p));
		return lst;
	
	}
	
}
