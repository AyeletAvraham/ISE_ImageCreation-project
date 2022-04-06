package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

public class Scene {

	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight;
	public Geometries geometries;
	
	public Scene(String _name) 
	{
		name = _name;
		geometries = new Geometries();
	}
	
	public Scene setBackground(Color background) {  //builder pattern
		this.background = background;
		return this;
	}


	public Scene setAmbientLight(AmbientLight ambientLight) {  //builder pattern
		this.ambientLight = ambientLight;
		return this;
	}


	public Scene setGeometries(Geometries geometries) {  //builder pattern
		this.geometries = geometries;
		return this;
	}

}
