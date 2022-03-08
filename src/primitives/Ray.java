package primitives;

import java.util.Objects;

public class Ray
{
	private Point p0;
	private Vector dir;
	public Ray(Point p0, Vector dir)
	{
		try 
		{
			Double3 temp = p0.getXyz();
			if (((temp.d1*temp.d1) + (temp.d2*temp.d2) + (temp.d3*temp.d3))!=1)
				throw new IllegalArgumentException("ERROR, this Vector is not normalized");
			this.p0 = p0;
			this.dir = dir;
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	public Point getP0() 
	{
		return p0;
	}
	public Vector getDir() 
	{
		return dir;
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
	
	
}
