package lighting;

import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
	
	private Vector direction;
	public DirectionalLight(Color c, Vector v) {
		super(c);
		direction = v.normalize();
	}
	@Override
	public Color getIntensity(Point p) {
		return super.getIntensity();
	}
	@Override
	public Vector getL(Point p) {
		return direction.normalize();
	}
}
