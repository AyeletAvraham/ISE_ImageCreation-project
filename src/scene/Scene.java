package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

public class Scene {

	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight;
	public Geometries geometries;
	public List<LightSource> lights = new LinkedList<>();
	public Scene(String _name) 
	{
		this.name = _name;
        this.background= Color.BLACK;
        this.ambientLight= new AmbientLight();
        this.geometries=new Geometries();
	}
	
	public Scene setBackground(Color _background) {  //builder pattern
		background = _background;
		return this;
	}


	public Scene setAmbientLight(AmbientLight _ambientLight) {  //builder pattern
		ambientLight = _ambientLight;
		return this;
	}


	public Scene setGeometries(Geometries _geometries) {  //builder pattern
		geometries = _geometries;
		return this;
	}
	public Scene setLights(List<LightSource> _lights) {  //builder pattern
		lights = _lights;
		return this;
	}
}
