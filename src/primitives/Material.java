package primitives;

/**
 * Class to implement the material of the objects
 */
public class Material {

	/**
     * kd = Diffusion ratio, ks = Specular ratio, kt = Transparency ratio, kr = Reflection ratio
     */
	public Double3 kD = Double3.ZERO, kS = Double3.ZERO, kT = Double3.ZERO, kR = Double3.ZERO;
	 /**
     * Shininess of the object ratio
     */
	public int nShininess = 0;
	/**
     * This function set diffusion ratio value and return the material - builder pattern

     * @param kD diffusion ratio
     * @return the material

     */
	public Material setkD(Double3 kD) {
		this.kD = kD;
		return this;
	}
	
	public Material setkD(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
    /**
     * This function set specular ratio value and return the material - builder pattern

     * @param kS specular ratio
     * @return the material

     */
	public Material setkS(Double3 _kS) {
		this.kS = _kS;
		return this;
	}
	public Material setkS(double _kS) {
		this.kS = new Double3(_kS);
		return this;
	}
	/**
     * This function set the shininess of the object ratio value and return the material - builder pattern

     * @param nShininess - the shininess of the object rati
     * @return the material

     */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	/**
     * This function set transparency ratio value and return the material - builder pattern

     * @param kT transparency ratio שקיפות
     * @return the material

     */
	public Material setkT(Double3 _kT) {
		this.kT = _kT;
		return this;
	}
	public Material setkT(double _kT) {
		this.kT = new Double3(_kT);
		return this;
	}
	/**
     * This function set reflection ratio value and return the material - builder pattern

     * @param kR reflection ratio
     * @return the material

     */
	public Material setkR(Double3 _kR) {
		this.kR = _kR;
		return this;
	}
	public Material setkR(double _kR) {
		this.kR = new Double3(_kR);
		return this;
	}
	/**
     * This function set diffusion ratio value and return the material - builder pattern

     * @param kD diffusion ratio
     * @return the material

     */
	public Double3 getkD() {
		return kD;
	}
	/**
     * This function set specular ratio value and return the material - builder pattern

     * @param kS specular ratio
     * @return the material

     */
	public Double3 getkS() {
		return kS;
	}
	public int getnShininess() {
		return nShininess;
	}
	
	
}
