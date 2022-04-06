package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase {

	public RayTracerBasic(Scene _myScene) {
		super(_myScene);
	}


	@Override
	public Color traceRay(Ray ray) {
		List<Point> lst = myScene.geometries.findIntersections(ray);
		if(lst==null)
			return myScene.background;
		Point temp = ray.findClosestPoints(lst);
		return calcColor(temp);
	}


	private Color calcColor(Point temp) 
	{
		return myScene.ambientLight.getIntensity();
	}

}
