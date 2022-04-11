package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

public class Geometries extends Intersectable
{
	private ArrayList<Intersectable> lst;
	public Geometries() 
	{
		lst = null;
	}
	public Geometries(Intersectable...geometries)
	{
		lst = new ArrayList<Intersectable>();
		add(geometries);
	}
	public void add(Intersectable...geometries)
	{
		if (lst==null) lst = new ArrayList<Intersectable>();
		for(Intersectable shape: geometries)
		{
			lst.add(shape);
		}
	}
	@Override
	public List<Point> findIntersections(Ray ray) 
	{
		List<Point> interLst = null;
		List<Point> temp = null;
		if(lst!= null)
		{
			for(Intersectable shape: lst)
			{
				temp = shape.findIntersections(ray);
				if(temp!=null)
				{
					if(interLst==null)
						interLst = new ArrayList<Point>();
					interLst.addAll(temp);
					temp = null;
				}
			}
		}
		return interLst;
	}
	
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> interLst = null;
		List<GeoPoint> temp = null;
		if(lst!= null)
		{
			for(Intersectable shape: lst)
			{
				temp = shape.findGeoIntersections(ray);
				if(temp!=null)
				{
					if(interLst==null)
						interLst = new ArrayList<GeoPoint>();
					interLst.addAll(temp);
					temp = null;
				}
			}
		}
		return interLst;
	}
	
}
