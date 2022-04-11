package geometries;

import java.util.List;
import java.util.Objects;

import primitives.*;

public abstract class Intersectable 
{
	public List<Point> findIntersections(Ray ray) {
	    var geoList = findGeoIntersections(ray);
	    return geoList == null ? null
	                           : geoList.stream().map(gp -> gp.point).toList();
	}

//	public abstract List<Point> findIntersections(Ray ray);
	public final List<GeoPoint> findGeoIntersections(Ray ray)
    {		
    	return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
	public static class GeoPoint
	{
	    public Geometry geometry;
		public Point point;

	    public GeoPoint(Geometry geometry, Point point) 
	    {
			this.geometry = geometry;
			this.point = point;
		}

	    
		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			return Objects.equals(geometry, other.geometry) && Objects.equals(point, other.point);
		}
	    
	}
}
