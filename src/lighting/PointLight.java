package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight  extends Light implements LightSource
{
	protected Point position;
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
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}
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

}
