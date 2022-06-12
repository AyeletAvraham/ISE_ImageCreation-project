package geometries;
import java.util.List;

import primitives.*;

/**
 * Interface for any geometric body
 */
public abstract class Geometry extends Intersectable
{
	private Material material = new Material();
	protected Color emission = Color.BLACK;
	public abstract Vector getNormal(Point p); 
	public Color getEmission() {
		return emission;
	}
	public Material getMaterial() {
		return material;
	}
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
}
