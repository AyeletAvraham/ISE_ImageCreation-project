package renderer;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

	public RayTracerBasic(Scene _myScene) {
		super(_myScene);
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
			{ // sign(nl) == sing(nv)
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
				calcSpecular(ks, l, n, v, nShininess, lightIntensity));
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
