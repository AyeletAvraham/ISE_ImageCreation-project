package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract Class to implement the basic actions of a ray tracer in our model
 */

public abstract class RayTracerBase 
{
	 /**
     * The scene we want to trace
     */
	protected Scene myScene;
	
	public RayTracerBase(Scene _myScene) {
		myScene =_myScene;
	}
	
	 /**
     * This function follow the ray and returns the color according to the scene

     * @param ray tracing ray
     * @return the color

     */
	
	public abstract Color traceRay(Ray ray);
	

}
