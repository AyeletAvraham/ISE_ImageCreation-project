package geometries;
import primitives.*;
import static java.lang.System.out;
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
		Vector v = super.getNormal(p);
		Plane plane1 = new Plane(getAxisRay().getP0(),getAxisRay().getDir());
		Plane plane2 = new Plane(getAxisRay().getP0().add(getAxisRay().getDir().scale(height)),getAxisRay().getDir());
		if (plane1.onPlane(p)||plane2.onPlane(p))
			return getAxisRay().getDir().normalize();
		return v;
	}

}
