package lighting;

import primitives.Color;

public abstract class Light {

	private Color intensity;
	
	public Color getIntensity() {
		return intensity;
	}

	public Light(Color intensity) {
		this.intensity = intensity;
	}
	
}
