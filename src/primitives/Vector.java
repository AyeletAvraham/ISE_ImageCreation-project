package primitives;

public class Vector extends Point
{
	
	public Vector(double p1, double p2, double p3) 
	{
		super(p1, p2, p3);
		if(this.getXyz().equals(Double3.ZERO))
			throw new IllegalArgumentException("ERROR, this is the zero vector");
	}
	public Vector(Double3 d) 
	{
		super(d);
		if(this.getXyz().equals(Double3.ZERO))
			throw new IllegalArgumentException("ERROR, this is the zero vector");
	}
	public Vector subtract(Vector v)
	{
		return new Vector(this.getXyz().d1- v.getXyz().d1,this.getXyz().d2- v.getXyz().d2, this.getXyz().d3- v.getXyz().d3);
	}
	public Vector add(Vector v)
	{
		return new Vector(this.getXyz().d1 + v.getXyz().d1,this.getXyz().d2 + v.getXyz().d2, this.getXyz().d3 + v.getXyz().d3);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	public Vector scale(int num)
	{
		return new Vector(this.getXyz().d1*num,this.getXyz().d2*num, this.getXyz().d3*num);
	}
	public double dotProduct(Vector v)
	{
		return ((this.getXyz().d1 * v.getXyz().d1)+(this.getXyz().d2 * v.getXyz().d2)+ (this.getXyz().d3 * v.getXyz().d3));
	}
	public Vector crossProduct(Vector v)
	{
		Double3 t1 = this.getXyz(), t2 = v.getXyz();
		return new Vector((t1.d2*t2.d3)-(t1.d3*t2.d2),(t1.d3*t2.d1)-(t1.d1*t2.d3), (t1.d1*t2.d2)-(t1.d2*t2.d1));

	}
	public double lengthSquared()
	{
		return this.dotProduct(this);
	}
	public double length()
	{
		return Math.sqrt(lengthSquared());
	}
	public Vector normalize()
	{
		double len = length();
		return new Vector((getXyz().d1)/len,(getXyz().d2)/len, (getXyz().d3)/len); 
	}
}
