package geometries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;

import primitives.*;

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
}
