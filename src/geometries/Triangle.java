package geometries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
/**
 * class to implement a triangle object
 *
 */
public class Triangle extends Polygon
{
	public Triangle(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
		Vector v1 = p1.subtract(p2);
		Vector v2 = p1.subtract(p3);
		Vector v3 = p3.subtract(p2);
		if((isZero(v1.crossProduct(v2).length())))
			throw new IllegalArgumentException("ERROR, can't build a triangle, this points are on the same vector");
	}
	/*refactoring
	@Override
	public List<Point> findIntersections(Ray ray)
	{
		List<Point> lst = super.findIntersections(ray); //Checks whether the ray has a point of intersection with the plane
		if (lst==null)
			return null;
		Point p0 = ray.getP0();
		Point p1 = vertices.get(0);
		Point p2 = vertices.get(1);
		Point p3 = vertices.get(2);
		Vector v1 = p1.subtract(p0);
		Vector v2 = p2.subtract(p0);
		Vector v3 = p3.subtract(p0);
		Vector n1 = (v1.crossProduct(v2)).normalize();
		Vector n2 = (v2.crossProduct(v3)).normalize();
		Vector n3 = (v3.crossProduct(v1)).normalize();
		double d1,d2,d3;
		d1 = n1.dotProduct(ray.getDir());
		d2 = n2.dotProduct(ray.getDir());
		d3 = n3.dotProduct(ray.getDir());
		if(((d1<0)&&(d2<0)&&(d3<0))||((d1>0)&&(d2>0)&&(d3>0))) //Checks whether the intersection point found is within the boundaries of the triangle
			return lst;
		return null; // A point of intersection with the plane was found but it is not in the triangle area
	}
	*/
	
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> lst = plane.findGeoIntersections(ray); //Checks whether the ray has a point of intersection with the plane
		if (lst==null)
			return null;
		Point p0 = ray.getP0();
		Point p1 = vertices.get(0);
		Point p2 = vertices.get(1);
		Point p3 = vertices.get(2);
		Vector v1 = p1.subtract(p0);
		Vector v2 = p2.subtract(p0);
		Vector v3 = p3.subtract(p0);
		Vector n1 = (v1.crossProduct(v2)).normalize();
		Vector n2 = (v2.crossProduct(v3)).normalize();
		Vector n3 = (v3.crossProduct(v1)).normalize();
		double d1,d2,d3;
		d1 = n1.dotProduct(ray.getDir());
		d2 = n2.dotProduct(ray.getDir());
		d3 = n3.dotProduct(ray.getDir());
		if(((d1<0)&&(d2<0)&&(d3<0))||((d1>0)&&(d2>0)&&(d3>0))) //Checks whether the intersection point found is within the boundaries of the triangle
			{
				Point p = lst.get(0).point;
				lst = new ArrayList<GeoPoint>();
				lst.add(new GeoPoint(this, p));
				return lst;
			}
		return null; // A point of intersection with the plane was found but it is not in the triangle area
	
	}
	
}
