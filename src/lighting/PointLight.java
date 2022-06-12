package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**
 * Class to implement a point light source of the scene
 */
public class PointLight  extends Light implements LightSource
{
	/**
     * Position of the light
     */
	protected Point position;
	/**
     * Parameters of the light - Reduction coefficients - constant, linear and square 
     */
	protected double kC = 1,kL = 0,kQ = 0;
	
	public PointLight(Color intensity, Point position, double kC, double kL, double kQ) {
		super(intensity);
		this.position = position;
		this.kC = kC;
		this.kL = kL;
		this.kQ = kQ;
	}
	public PointLight(Color _intensity, Point _position) {
		super(_intensity);
		position = _position;
		this.kC = 1;
		this.kL = 0;
		this.kQ = 0;			
	}
	/**
     * This function set constant discount coefficient value and return the point light - builder pattern

     * @param kC
     * @return the point light

     */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}
	/**
     * This function set linear discount coefficient value and return the point light - builder pattern

     * @param kL
     * @return the point light

     */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}
	/**
     * This function set square discount coefficient value and return the point light - builder pattern

     * @param kQ
     * @return the point light

     */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}
	@Override
	public Color getIntensity(Point p) {
		Color I0 = super.getIntensity();
		double d = p.distance(position);
		return (I0.scale(1/(kC+(kL*d)+(kQ*d*d))));
	}
	@Override
	public Vector getL(Point p) {
		return p.subtract(position).normalize();
	}
	@Override
	public double getDistance(Point point) {
		return point.distance(position);
	}

}
