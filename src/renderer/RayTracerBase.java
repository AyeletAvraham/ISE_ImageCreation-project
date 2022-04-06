package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase //return the color of the pixel
{

	protected Scene myScene;
	
	public RayTracerBase(Scene _myScene) {
		myScene =_myScene;
	}
	public abstract Color traceRay(Ray ray);
	

}
