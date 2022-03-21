package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries implements Intersectable
{
	private ArrayList<Intersectable> lst;
	public Geometries() 
	{
		lst = null;
	}
	public Geometries(Intersectable...geometries)
	{
		add(geometries);
	}
	public void add(Intersectable...geometries)
	{
		for(Intersectable shape: geometries)
		{
			lst.add(shape);
		}
	}
	@Override
	public List<Point> findIntersections(Ray ray) 
	{
		List<Point> interLst = null;
		for(Intersectable shape: lst)
		{
			shape.findIntersections(ray);
		}
		return interLst;
	}
}
