package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**
 * Interface to implement ligt source of the scene
 */
public interface LightSource 
{
	 /**
     * Get intensity at a point
     * @param p the point
     * @return the intensity
     */
	public Color getIntensity(Point p);
	/**
     * Get the direction of the light from a point
     * @param p the point
     * @return the direction
     */
	public Vector getL(Point p);
	/**
     * Get distance from the light to the point
     * @param point the point
     * @return the distance
     */
	double getDistance(Point point);

}
