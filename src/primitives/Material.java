package primitives;

public class Material {

	public Double3 kD = Double3.ZERO, kS = Double3.ZERO;
	public int nShininess = 0;
	
	public Material setkD(Double3 _kD) {
		this.kD = _kD;
		return this;
	}
	public Material setkD(double _kD) {
		this.kD = new Double3(_kD);
		return this;
	}
	public Material setkS(Double3 _kS) {
		this.kS = _kS;
		return this;
	}
	public Material setkS(double _kS) {
		this.kS = new Double3(_kS);
		return this;
	}
	public Material setnShininess(int _nShininess) {
		this.nShininess = _nShininess;
		return this;
	}
	public Double3 getkD() {
		return kD;
	}
	public Double3 getkS() {
		return kS;
	}
	public int getnShininess() {
		return nShininess;
	}
	
	
}
