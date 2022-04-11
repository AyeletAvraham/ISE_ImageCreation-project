package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light
{

	
	public AmbientLight() 
	{
		super(Color.BLACK);
	}
	public AmbientLight(Color IA, Double3 KA) 
	{
		super(IA.scale(KA));
	}

}
