package geometries;
import primitives.*;

public class Cylinder extends Tube implements Geometry
{
	
	private double height;
	
	public Cylinder(Ray axisRay, double radius, double height) 
	{
		super(axisRay, radius);
		if(height<=0)
			throw new IllegalArgumentException("ERROR, the height must be positive");
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public Vector getNormal(Point p) {
		// TODO Auto-generated method stub
		return null;
	}

}
