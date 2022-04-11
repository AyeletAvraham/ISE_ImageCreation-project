package lighting;

import static primitives.Util.isZero;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight
{
	 
	private Vector direction;

	public SpotLight(Color intensity, Point position, Vector direction, double kC, double kL, double kQ) {
		super(intensity, position, kC, kL, kQ);
		this.direction = direction.normalize();
	}
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}
	
	@Override
	public Color getIntensity(Point p) {
		Color I0 = super.getIntensity(p);
		double d = p.distance(position);
		return (I0.scale((Math.max(direction.dotProduct(getL(p)),0))));///(kC+(kL*d)+(kQ*d*d)))));
	}
}

