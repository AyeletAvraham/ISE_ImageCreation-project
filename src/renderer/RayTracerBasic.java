package renderer;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import lighting.PointLight;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

	private static final double DELTA = 0.1;

	public RayTracerBasic(Scene _myScene) {
		super(_myScene);
	}
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector DELTAVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point point = geopoint.point.add(DELTAVector);
		Ray lightRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = myScene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return true;
		for (GeoPoint intersection : intersections)     
			if (light.getDistance(geopoint.point) > intersection.point.distance(geopoint.point))
					return false; //if there are points in the intersections list that are closer to the point
						          //than light source – return false
		return true;
	}

	 
	@Override
	public Color traceRay(Ray ray) {
		List<GeoPoint> lst = myScene.geometries.findGeoIntersections(ray);
		if(lst==null)
			return myScene.background;
		GeoPoint temp = ray.findClosestGeoPoint(lst);
		return calcColor(temp, ray);
	}


	private Color calcColor(GeoPoint temp, Ray ray) 
	{
		return myScene.ambientLight.getIntensity().add(temp.geometry.getEmission().add(calcLocalEffects(temp, ray)));
	}

	

	private Color calcLocalEffects(GeoPoint intersection, Ray ray) 
	{
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = primitives.Util.alignZero(n.dotProduct(v));
		if (nv == 0) 
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().getnShininess();
		double kd = intersection.geometry.getMaterial().getkD().getD1(), ks = intersection.geometry.getMaterial().getkS().getD1();
		Color color = Color.BLACK;
		for (LightSource lightSource : myScene.lights) 
		{
			Vector l = lightSource.getL(intersection.point);
			double nl = primitives.Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) 
			{ 
				if (unshaded(lightSource,l, n, intersection)) {
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
				calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
			}
		}
		return color;
	}


	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(2*n.dotProduct(l)));
		double dotP = ((v.scale(-1)).dotProduct(r));
		if(dotP<0)
			dotP = 0;
		return lightIntensity.scale(ks*(Math.pow(dotP, nShininess)));
	}


	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double dotP = l.dotProduct(n);
		if(dotP<0)
			dotP*=-1;
		return lightIntensity.scale(kd*(dotP));
	}
}
