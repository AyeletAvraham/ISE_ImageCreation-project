package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Class that has a list of all the objects in the scene
 */
public class Geometries extends Intersectable
{

	/**
	 * list of objects
	 */
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
	/**
	 * This function sends the ray for every geometry in the array list and find the intersection point
	 *  @param ray
	 *  @return list of all the intersections points
	 */
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
	/**
	 * This function sends the ray for every geometry in the array list and find the 
	 * intersection point and its geometry
	 *  @param ray
	 *  @return list of all the intersections points and their geometries
	 */
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
