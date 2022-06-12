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

/**
 * Class that extends from RayTracerBase that implements rayTracing between the camera rays and the scene
 */

public class RayTracerBasic extends RayTracerBase {

	//private static final double DELTA = 0.1; //refactoring
	private static final double INITIAL_K = 1.0;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;


	public RayTracerBasic(Scene _myScene) {
		super(_myScene);
	}
	/*
	 * /**
     * The function checks whether there are any objects shading the light source
     * from the point and returns false if it is and true otherwise
     * @param light light source
     * @param l light to point direction vector (normalized)
     * @param n normal vector (normalized)
     * @param geopoint checked geo-point
     * @return is the point unshaded
     
	 * 
	 * refactoring to transparency
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
	
	/**
     * Calculate value of transparency of the point שקיפות
     *
     * @param ls light source
     * @param l light to point direction vector (normalized)
     * @param n normal vector (normalized)
     * @param geopoint checked geo-point
     * @return value of the transparency
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

	/**
     * Get color of the intersection of the ray with the scene
     *
     * @param ray Ray to trace
     * @return Color of intersection
     */
	
	@Override
	public Color traceRay(Ray ray) { 
		
		GeoPoint temp = findClosestIntersection(ray); // instead of getting the list of all the intersections, only get the closest one
		
		//List<GeoPoint> lst = myScene.geometries.findGeoIntersections(ray);//refactoring
		if(temp==null)
			return myScene.background;
		//GeoPoint temp = ray.findClosestGeoPoint(lst); //refactoring
		return calcColor(temp, ray);
	}
	
	 /**
     * Find closest intersection point between a ray and the scene's geometries
     * @param ray the ray
     * @return the closest point
     */
	
	private GeoPoint findClosestIntersection(Ray ray)
	{
		List<GeoPoint> geoPoints = myScene.geometries.findGeoIntersections(ray);
	    return ray.findClosestGeoPoint(geoPoints);
	}
	
	/**
     * Calculate color of pixel by using recursive function and adding the effect of ambientLight of the scene 
     *
     * @param geopoint the point of closest intersection
     * @param ray - the ray that goes through the pixel
     * @return the color
     */
	
	private Color calcColor(GeoPoint closestPoint, Ray ray)
	{
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(myScene.ambientLight.getIntensity());
	}
	
	/**
    * Get the color of an intersection point using the Phong model - that use the function of the local effect that consider the effect of the specular, diffuse and ambient lighting
    * Recursive function

    * @param geoPoint point of intersection
    * @return Color of the intersection point
    */
	
	private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
		Color color = intersection.geometry.getEmission().add(calcLocalEffects(intersection, ray, k));
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
		}
	
	
	 /**
     * Calculate global effect of the light on the color
     *
     * @param gp the point
     * @param v the ray
     * @param level - the level value that stop the recusion
     * @param k - help us to check if there is no more effect
     
     * @return the color
     */
	
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
	
	/**
     * This function returns the color of the closest intersection point

	 * The purpose of writing the function - Do not repeat yourself
     * @param ray - the ray
     * @param level - the level value that stop the recusion
     
     * @return the color
     */
	
	private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) 
	{
		GeoPoint gp = findClosestIntersection (ray);
		return (gp == null ? myScene.background : calcColor(gp, ray, level-1 , kkx)).scale(kx);
	}
	/**
     * Construct the ray getting refracted on a point נשברת
     * @param n normal to the point
     * @param point the point
     * @param v the ray entering
     * @return the refracted ray
     */
	
	private Ray constructRefractedRay(Point point, Ray v, Vector n) 
	{
		return new Ray(point, v.getDir(), n);
	}
	
	
	/**
     * Construct the ray getting reflected on a point חוזרת
     * @param n normal to the point
     * @param point the point
     * @param v the ray entering
     * @return the reflected ray
     */
	
	 private Ray constructReflectedRay(Point point, Ray v, Vector n) 
	 {
	      //r = v - 2 * (v*n) * n
	      //r is the reflected ray
		 return new Ray(point ,v.getDir().subtract((n.scale(v.getDir().dotProduct(n)).scale(2))).normalize(),n);
		 
	 }
	
	 /**
	     * Calculate the color of the local effects of the light help us to calculate the local effect according to po
	     *
	     * @param intersection point calculated
	     * @param ray - ray entering to the point
	     * @return local color effect on the point
	 */

	private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k)
	{
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = primitives.Util.alignZero(n.dotProduct(v));
		if (nv == 0) //if the ray is orthogonal to the normal of the geometry
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
	
	/**
     * Calculate color of the specular effects of the light
     *
     * @param ks specular ratio
     * @param l light's direction vector
     * @param n normal vector
     * @param v ray's direction vector
     * @param nShininess shininess of the object
     * @param lightIntensity intensity of the light
     * @return color of specular effect
     */
	
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(2*n.dotProduct(l)));
		double dotP = ((v.scale(-1)).dotProduct(r));
		if(dotP<0)
			dotP = 0;
		return lightIntensity.scale(ks*(Math.pow(dotP, nShininess)));
	}

	/**
     * Calculate color of the diffusive effects of the light
     *
     * @param kd diffusive ratio
     * @param l light's direction vector
     * @param n normal vector
     * @param lightIntensity intensity of the light
     * @return color of the diffusive effect
     */
	
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double dotP = l.normalize().dotProduct(n.normalize());
		if(dotP<0)
			dotP*=-1;
		return lightIntensity.scale(kd*(dotP));
	}
}
