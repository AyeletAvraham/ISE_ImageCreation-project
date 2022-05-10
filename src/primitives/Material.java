package primitives;

public class Material {

	public Double3 kD = Double3.ZERO, kS = Double3.ZERO, kT = Double3.ZERO, kR = Double3.ZERO;
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
	public Material setkT(Double3 _kT) {
		this.kT = _kT;
		return this;
	}
	public Material setkT(double _kT) {
		this.kT = new Double3(_kT);
		return this;
	}
	public Material setkR(Double3 _kR) {
		this.kR = _kR;
		return this;
	}
	public Material setkR(double _kR) {
		this.kR = new Double3(_kR);
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
