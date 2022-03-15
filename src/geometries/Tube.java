package geometries;
import primitives.*;

public class Tube implements Geometry
{
	private Ray axisRay;
	private double radius;
	
	public Tube(Ray axisRay, double radius) 
	{
		if(radius<=0)
			throw new IllegalArgumentException("ERROR, the radius must be positive");
		this.axisRay = axisRay;
		this.radius = radius;
		
	}
	public Ray getAxisRay() {
		return axisRay;
	}
	public double getRadius() {
		return radius;
	}
	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
	}
	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		double t = (axisRay.getDir()).dotProduct(p.subtract(axisRay.getP0()));
		Point O = axisRay.getP0().add((axisRay.getDir()).scale(t));
		return (O.subtract(p)).normalize();
	}

}
