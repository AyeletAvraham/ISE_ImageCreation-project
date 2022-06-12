package lighting;

import primitives.Color;
/**
 * Abstract class to implement a light source
 */
public abstract class Light {
	 /**
     * Intensity of the light
     */
	private Color intensity;
	/**
     * Get intensity color of the light
     * @return the intensity color
     */
	public Color getIntensity() {
		return intensity;
	}

	public Light(Color intensity) {
		this.intensity = intensity;
	}
	
}
