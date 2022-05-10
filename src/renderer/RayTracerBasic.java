package renderer;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import lighting.PointLight;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static primitives.Util.alignZero;
public class RayTracerBasic extends RayTracerBase {

	//private static final double DELTA = 0.1; //refactoring
	private static final double INITIAL_K = 1.0;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;


	public RayTracerBasic(Scene _myScene) {
		super(_myScene);
	}
	/*
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		//Vector DELTAVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);//refactoring
		//Point point = geopoint.point.add(DELTAVector);//refactoring
		Ray lightRay = new Ray(geopoint.point, lightDirection,n);
		List<GeoPoint> intersections = myScene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return true;
		for (GeoPoint intersection : intersections)     
			if (light.getDistance(geopoint.point) >= intersection.point.distance(geopoint.point) && intersection.geometry.getMaterial().kT.getD1() == 0)
					return false; //if there are points in the intersections list that are closer to the point
						          //than light source – return false
		return true;
	}
	*/
	private double transparency(GeoPoint geopoint, LightSource ls, Vector l, Vector n)
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.point, lightDirection, n);
		List<GeoPoint> intersections = myScene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return 1;
		double ktr = 1.0;
		for (GeoPoint intersection : intersections)    
			if (ls.getDistance(geopoint.point) >= intersection.point.distance(geopoint.point))// && intersection.geometry.getMaterial().kT.getD1() == 0)
			{
				if(alignZero(geopoint.point.distance(geopoint.point)-ls.getDistance(geopoint.point)) <= 0)
				ktr*=intersection.geometry.getMaterial().kT.getD1(); //if there are points in the intersections list that are closer to the point
				if (ktr < MIN_CALC_COLOR_K)	return 0.0;
			}
		return ktr;
	}

	 
	@Override
	public Color traceRay(Ray ray) {
		GeoPoint temp = findClosestIntersection(ray);
		//List<GeoPoint> lst = myScene.geometries.findGeoIntersections(ray);//refactoring
		if(temp==null)
		return myScene.background;
		//GeoPoint temp = ray.findClosestGeoPoint(lst); //refactoring
		return calcColor(temp, ray);
	}
	
	private GeoPoint findClosestIntersection(Ray ray)
	{
		List<GeoPoint> geoPoints = myScene.geometries.findGeoIntersections(ray);
	    return ray.findClosestGeoPoint(geoPoints);
	}
	private Color calcColor(GeoPoint closestPoint, Ray ray)
	{
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(myScene.ambientLight.getIntensity());
	}
/*
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) 
	{
		Color color = myScene.ambientLight.getIntensity().add(calcLocalEffects(intersection, ray));
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
	}*/
	/*private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
		Color color = (intersection.geometry.getEmission().add(calcLocalEffects(intersection, ray)));
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
		}
*/
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
		Color color = intersection.geometry.getEmission().add(calcLocalEffects(intersection, ray, k));
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
		}
/*
	private Color calcColor(GeoPoint temp, Ray ray) 
	{
		return myScene.ambientLight.getIntensity().add(temp.geometry.getEmission().add(calcLocalEffects(temp, ray)));
	}*/
	private Color calcGlobalEffects(GeoPoint gp, Ray v, int level, double k) 
	{
		Color color = Color.BLACK;
		Vector n = gp.geometry.getNormal(gp.point);
		Material material = gp.geometry.getMaterial();
		double kkr = k * material.kR.getD1();
		if (kkr > MIN_CALC_COLOR_K)
		{
			color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR.getD1(), kkr);
		}
		double kkt = k * material.kT.getD1();
		if (kkt > MIN_CALC_COLOR_K)
		{
			color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT.getD1(), kkt));
		}
		return color;
	}
	
	private Ray constructRefractedRay(Point point, Ray v, Vector n) 
	{
		return new Ray(point, v.getDir(), n);
	}
	private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) 
	{
		GeoPoint gp = findClosestIntersection (ray);
		return (gp == null ? myScene.background : calcColor(gp, ray, level-1 , kkx)).scale(kx);
	}

	 private Ray constructReflectedRay(Point point, Ray v, Vector n) 
	 {
	      //r = v - 2 * (v*n) * n
	      //r is the reflected ray
		 return new Ray(point ,v.getDir().subtract((n.scale(v.getDir().dotProduct(n)).scale(2))).normalize(),n);
		 
	 }
	
	

	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k)
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
				double ktr = transparency(intersection, lightSource, l,  n);
				if (ktr * k > MIN_CALC_COLOR_K ) 
				{
					Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),calcSpecular(ks, l, n, v, nShininess, lightIntensity));
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
		double dotP = l.normalize().dotProduct(n.normalize());
		if(dotP<0)
			dotP*=-1;
		return lightIntensity.scale(kd*(dotP));
	}
}
