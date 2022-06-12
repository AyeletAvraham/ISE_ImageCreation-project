package lighting;

import primitives.Color;
import primitives.Double3;
/**
 * Class to implement ambient light of the scene
 */

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
