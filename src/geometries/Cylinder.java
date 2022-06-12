package geometries;
import primitives.*;
import static java.lang.System.out;

/**
 * Class to implement a Cylinder object
 */
public class Cylinder extends Tube
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

	/**
	 * if the point is on one of the bases- the normal is the axis
	 * if the point is on the casing- the normal is the vector between the point
	 * and the point on the axis
	 * @param p the point
	 * @return the normal of the point
	 */
	@Override
	public Vector getNormal(Point p) {
		Vector v = super.getNormal(p);
		Plane plane1 = new Plane(getAxisRay().getP0(),getAxisRay().getDir());
		Plane plane2 = new Plane(getAxisRay().getP0().add(getAxisRay().getDir().scale(height)),getAxisRay().getDir());
		if (plane1.onPlane(p)||plane2.onPlane(p))
			return getAxisRay().getDir().normalize();
		return v.normalize();
	}

}
