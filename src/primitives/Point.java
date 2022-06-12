package primitives;
import java.util.Objects;

/**
 * Class to implement a Point
 */
public class Point
{
	
	public static final Point ZERO = new Point(0,0,0);
	final private Double3 xyz;

	public Point(double p1, double p2, double p3)
	{
		this.xyz = new Double3(p1,p2,p3);
	}
	public Point(Double3 d)
	{
		this.xyz = d;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		return this.getXyz().equals(other.getXyz());
	}
	@Override
	public String toString() {
		return "[xyz=" + xyz + "]";
	}
	 /**
     * vector from substraction of two point's coordinates
     *
     * @param p2 the second point
     * @return the vector
     */
	public Vector subtract(Point p2)
	{
		return new Vector(this.getXyz().d1- p2.getXyz().d1,this.getXyz().d2- p2.getXyz().d2, this.getXyz().d3- p2.getXyz().d3);
	}
	/**
     * adds vector to a point and return the getted point
     *
     * @param v the vector to add
     * @return the new point
     */
	public Point add(Vector v)
	{
		Double3 temp = v.getXyz();
		return new Point(this.xyz.d1+ temp.d1,this.xyz.d2 + temp.d2, this.xyz.d3 + temp.d3);
	}

	public Double3 getXyz()
	{
		return xyz;
	}
	
	 /**
     * squared distance between two points
     *
     * @param p2 the second point
     * @return the distance
     */
	public double distanceSquared(Point p2)
	{
		return (((this.getXyz().d1- p2.getXyz().d1)*(this.getXyz().d1- p2.getXyz().d1))	+((this.getXyz().d2- p2.getXyz().d2)*(this.getXyz().d2- p2.getXyz().d2))+((this.getXyz().d3- p2.getXyz().d3)*(this.getXyz().d3- p2.getXyz().d3)));
	}
	/**
     * distance between two points
     *
     * @param p the second point
     * @return the distance
     */
	public double distance(Point p)
	{
		return Math.sqrt((this.distanceSquared(p)));
	}
}
