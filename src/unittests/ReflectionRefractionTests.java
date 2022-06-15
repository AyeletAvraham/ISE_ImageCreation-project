/**
 * 
 */
package unittests;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	/*
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setkL(0.0004).setkQ(0.0000006));

		camera.setMyImage(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}
*/
	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	/*
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setkL(0.00001).setkQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}
*/
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	/*
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}
	*/
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	
	/*@Test
	public void testCombinedGeometries() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(100, -100, 400), 200d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				new Sphere(new Point(400, 0, -600), 400d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
						new Sphere(new Point(-950, 450, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
						new Sphere(new Point(-950, 450, -1000), 200d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				
				new Triangle(new Point(0, 900, -1250), new Point(900, -500, -1250), new Point(-900, -500, -1250)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(1)),
				new Triangle(new Point(0, -750, -1250), new Point(900, -500, -1100),
						new Point(-900, -500, -1250)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(0, 0, 1250), new Vector(0, 0, -1)) //
				.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(1100, -600 , -1100), new Vector(-1, -1, -1)) //
				.setkL(0.00001).setkQ(0.000005));
		ImageWriter imageWriter = new ImageWriter("testCombinedGeometries", 500, 500);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}*/
	/*
	@Test
	public void testCombinedGeometries() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(0,-600,0), 400d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-600,-900,700), 100d).setEmission(new Color(255,255,255)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-600,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-450,425), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-750,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(-75,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(0,-155,450), 15d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Triangle(new Point(270,-320,0), new Point(290,-320,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(270,-320,0), new Point(520,-700,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(440,-590,0), new Point(600,-650,0), new Point(640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(450,-590,0), new Point(420,-720,0), new Point(440,-720,0)) //
						.setEmission(new Color(49,60,62)),///////////////////////////////////
				new Triangle(new Point(-270,-320,0), new Point(-290,-320,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-270,-320,0), new Point(-520,-700,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-440,-590,0), new Point(-600,-650,0), new Point(-640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-450,-590,0), new Point(-420,-720,0), new Point(-440,-720,0)) //
						.setEmission(new Color(49,60,62)),
				
				new Triangle(new Point(250,250,400), new Point(-250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(250,350,400), new Point(250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,400), new Point(-175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(175,500,400), new Point(175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,420), new Point(-175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				new Triangle(new Point(175,375,420), new Point(175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				
					//house	
				new Triangle(new Point(-600,-300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-900,700,-1000)) //
						.setEmission(new Color(red)),
						
				new Sphere(new Point(0, 0, 100), 350d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Triangle(new Point(30, 40, 450), new Point(-150, -15, 650), new Point(30, -40, 450)) //
					.setEmission(new Color(245, 134, 61))); //
		
	
				//new Sphere(new Point(-375,-925,0), 100d).setEmission(new Color(135,135,135)) //
						//.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				//new Sphere(new Point(-275,-950,100), 50d).setEmission(new Color(135,135,135)) //
					//	.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)));

				//new Triangle(new Point(0, 900, -1250), new Point(900, -500, -1250), new Point(-900, -500, -1250)) //
					//	.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(1)),
				//new Triangle(new Point(0, -750, -1250), new Point(900, -500, -1100),
					//	new Point(-900, -500, -1250)) //
						//.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(0.5)));
		scene.background = new Color(77,173,188);
		//scene.lights.add( //
			//	new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				//		.setkL(4E-4).setkQ(2E-5));

		//scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,0,700), new Vector(0,0,1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(350,0,400), new Vector(0,-1,0)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(-800,-1200,1000), new Vector(1,0.35,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(100,100,550), new Vector(-1,-1,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,-900,500), new Vector(0,0,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		//scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(1100, -600 , -1100), new Vector(-1, -1, -1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		ImageWriter imageWriter = new ImageWriter("testCombinedGeometriesFocal", 500, 500);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.renderImage(100,5); //
				camera.writeToImage();
	}*/
	
	
	//renderImageThreads
	@Test
	public void testCombinedGeometries3() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(0,-600,0), 400d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-600,-900,700), 100d).setEmission(new Color(255,255,255)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-600,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-450,425), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-750,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(-75,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(0,-155,450), 15d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Triangle(new Point(270,-320,0), new Point(290,-320,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(270,-320,0), new Point(520,-700,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(440,-590,0), new Point(600,-650,0), new Point(640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(450,-590,0), new Point(420,-720,0), new Point(440,-720,0)) //
						.setEmission(new Color(49,60,62)),///////////////////////////////////
				new Triangle(new Point(-270,-320,0), new Point(-290,-320,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-270,-320,0), new Point(-520,-700,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-440,-590,0), new Point(-600,-650,0), new Point(-640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-450,-590,0), new Point(-420,-720,0), new Point(-440,-720,0)) //
						.setEmission(new Color(49,60,62)),
				
				new Triangle(new Point(250,250,400), new Point(-250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(250,350,400), new Point(250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,400), new Point(-175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(175,500,400), new Point(175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,420), new Point(-175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				new Triangle(new Point(175,375,420), new Point(175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				
					//house	
				new Triangle(new Point(-600,-300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-900,700,-1000)) //
						.setEmission(new Color(red)),
						
				new Sphere(new Point(0, 0, 100), 350d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Triangle(new Point(30, 40, 450), new Point(-150, -15, 650), new Point(30, -40, 450)) //
					.setEmission(new Color(245, 134, 61))); //
		
	
				//new Sphere(new Point(-375,-925,0), 100d).setEmission(new Color(135,135,135)) //
						//.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				//new Sphere(new Point(-275,-950,100), 50d).setEmission(new Color(135,135,135)) //
					//	.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)));

				//new Triangle(new Point(0, 900, -1250), new Point(900, -500, -1250), new Point(-900, -500, -1250)) //
					//	.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(1)),
				//new Triangle(new Point(0, -750, -1250), new Point(900, -500, -1100),
					//	new Point(-900, -500, -1250)) //
						//.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(0.5)));
		scene.background = new Color(77,173,188);
		//scene.lights.add( //
			//	new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				//		.setkL(4E-4).setkQ(2E-5));

		//scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,0,700), new Vector(0,0,1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(350,0,400), new Vector(0,-1,0)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(-800,-1200,1000), new Vector(1,0.35,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(100,100,550), new Vector(-1,-1,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,-900,500), new Vector(0,0,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		//scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(1100, -600 , -1100), new Vector(-1, -1, -1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		ImageWriter imageWriter = new ImageWriter("testCombinedGeometries3", 500, 500);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.renderImageThreads(); //
				camera.writeToImage();
	}
	
	@Test
	public void testCombinedGeometries2() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(0,-600,0), 400d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-600,-900,700), 100d).setEmission(new Color(255,255,255)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-600,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-450,425), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-750,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(-75,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(0,-155,450), 15d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Triangle(new Point(270,-320,0), new Point(290,-320,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(270,-320,0), new Point(520,-700,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(440,-590,0), new Point(600,-650,0), new Point(640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(450,-590,0), new Point(420,-720,0), new Point(440,-720,0)) //
						.setEmission(new Color(49,60,62)),///////////////////////////////////
				new Triangle(new Point(-270,-320,0), new Point(-290,-320,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-270,-320,0), new Point(-520,-700,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-440,-590,0), new Point(-600,-650,0), new Point(-640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-450,-590,0), new Point(-420,-720,0), new Point(-440,-720,0)) //
						.setEmission(new Color(49,60,62)),
				
				new Triangle(new Point(250,250,400), new Point(-250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(250,350,400), new Point(250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,400), new Point(-175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(175,500,400), new Point(175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,420), new Point(-175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				new Triangle(new Point(175,375,420), new Point(175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				
					//house	
				new Triangle(new Point(-600,-300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-900,700,-1000)) //
						.setEmission(new Color(red)),
						
				new Sphere(new Point(0, 0, 100), 350d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Triangle(new Point(30, 40, 450), new Point(-150, -15, 650), new Point(30, -40, 450)) //
					.setEmission(new Color(245, 134, 61))); //
		
	
				//new Sphere(new Point(-375,-925,0), 100d).setEmission(new Color(135,135,135)) //
						//.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				//new Sphere(new Point(-275,-950,100), 50d).setEmission(new Color(135,135,135)) //
					//	.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)));

				//new Triangle(new Point(0, 900, -1250), new Point(900, -500, -1250), new Point(-900, -500, -1250)) //
					//	.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(1)),
				//new Triangle(new Point(0, -750, -1250), new Point(900, -500, -1100),
					//	new Point(-900, -500, -1250)) //
						//.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(0.5)));
		scene.background = new Color(77,173,188);
		//scene.lights.add( //
			//	new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				//		.setkL(4E-4).setkQ(2E-5));

		//scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,0,700), new Vector(0,0,1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(350,0,400), new Vector(0,-1,0)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(-800,-1200,1000), new Vector(1,0.35,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(100,100,550), new Vector(-1,-1,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,-900,500), new Vector(0,0,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		//scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(1100, -600 , -1100), new Vector(-1, -1, -1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		ImageWriter imageWriter = new ImageWriter("testCombinedGeometries2", 500, 500);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.RuningTimeRenderImage(); //
				camera.writeToImage();
	}
	
	
	 @Test
	public void testCombinedGeometries1() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(0,-600,0), 400d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-600,-900,700), 100d).setEmission(new Color(255,255,255)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-600,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-450,425), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-750,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(-75,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(0,-155,450), 15d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Triangle(new Point(270,-320,0), new Point(290,-320,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(270,-320,0), new Point(520,-700,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(440,-590,0), new Point(600,-650,0), new Point(640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(450,-590,0), new Point(420,-720,0), new Point(440,-720,0)) //
						.setEmission(new Color(49,60,62)),///////////////////////////////////
				new Triangle(new Point(-270,-320,0), new Point(-290,-320,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-270,-320,0), new Point(-520,-700,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-440,-590,0), new Point(-600,-650,0), new Point(-640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-450,-590,0), new Point(-420,-720,0), new Point(-440,-720,0)) //
						.setEmission(new Color(49,60,62)),
				
				new Triangle(new Point(250,250,400), new Point(-250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(250,350,400), new Point(250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,400), new Point(-175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(175,500,400), new Point(175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,420), new Point(-175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				new Triangle(new Point(175,375,420), new Point(175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				
					//house	
				new Triangle(new Point(-600,-300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-900,700,-1000)) //
						.setEmission(new Color(red)),
						
				new Sphere(new Point(0, 0, 100), 350d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Triangle(new Point(30, 40, 450), new Point(-150, -15, 650), new Point(30, -40, 450)) //
					.setEmission(new Color(245, 134, 61))); //
		
	
				//new Sphere(new Point(-375,-925,0), 100d).setEmission(new Color(135,135,135)) //
						//.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				//new Sphere(new Point(-275,-950,100), 50d).setEmission(new Color(135,135,135)) //
					//	.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)));

				//new Triangle(new Point(0, 900, -1250), new Point(900, -500, -1250), new Point(-900, -500, -1250)) //
					//	.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(1)),
				//new Triangle(new Point(0, -750, -1250), new Point(900, -500, -1100),
					//	new Point(-900, -500, -1250)) //
						//.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(0.5)));
		scene.background = new Color(77,173,188);
		//scene.lights.add( //
			//	new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				//		.setkL(4E-4).setkQ(2E-5));

		//scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,0,700), new Vector(0,0,1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(350,0,400), new Vector(0,-1,0)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(-800,-1200,1000), new Vector(1,0.35,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(100,100,550), new Vector(-1,-1,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,-900,500), new Vector(0,0,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		//scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(1100, -600 , -1100), new Vector(-1, -1, -1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		ImageWriter imageWriter = new ImageWriter("testCombinedGeometries1", 500, 500);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.antiAliasingRenderImage(); //
				camera.writeToImage();
	}
	
	 @Test
	public void testCombinedGeometries() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(0,-600,0), 400d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-600,-900,700), 100d).setEmission(new Color(255,255,255)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-600,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-450,425), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0,-750,395.81), 25d).setEmission(new Color(0,0, 0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(-90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(90,125,427), 40d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Sphere(new Point(-75,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,135,467), 15d).setEmission(new Color(150,150,150)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(0,-155,450), 15d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-29,-152,450), 13d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-54,-144,450), 12d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-74,-135,450), 10d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-92,-125,450), 9d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Sphere(new Point(-105,-112,450), 8d).setEmission(new Color(0,0,0)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(80)),//,
				new Triangle(new Point(270,-320,0), new Point(290,-320,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(270,-320,0), new Point(520,-700,0), new Point(540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(440,-590,0), new Point(600,-650,0), new Point(640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(450,-590,0), new Point(420,-720,0), new Point(440,-720,0)) //
						.setEmission(new Color(49,60,62)),///////////////////////////////////
				new Triangle(new Point(-270,-320,0), new Point(-290,-320,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-270,-320,0), new Point(-520,-700,0), new Point(-540,-700,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-440,-590,0), new Point(-600,-650,0), new Point(-640,-640,0)) //
						.setEmission(new Color(49,60,62)),
				new Triangle(new Point(-450,-590,0), new Point(-420,-720,0), new Point(-440,-720,0)) //
						.setEmission(new Color(49,60,62)),
				
				new Triangle(new Point(250,250,400), new Point(-250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(250,350,400), new Point(250,250,400), new Point(-250,350,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,400), new Point(-175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				new Triangle(new Point(175,500,400), new Point(175,350,400), new Point(-175,500,400)) //
						.setEmission(new Color(142,27,29)),
				
				new Triangle(new Point(175,350,420), new Point(-175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				new Triangle(new Point(175,375,420), new Point(175,350,420), new Point(-175,375,420)) //
						.setEmission(new Color(white)),
				
					//house	
				new Triangle(new Point(-600,-300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-1200,-300,-1000)) //
						.setEmission(new Color(230,207,142)),
				new Triangle(new Point(-1200,300,-1000), new Point(-600,300,-1000), new Point(-900,700,-1000)) //
						.setEmission(new Color(red)),
						
				new Sphere(new Point(0, 0, 100), 350d).setEmission(new Color(200,200, 200)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),//,
				new Triangle(new Point(30, 40, 450), new Point(-150, -15, 650), new Point(30, -40, 450)) //
					.setEmission(new Color(245, 134, 61))); //
		
	
				//new Sphere(new Point(-375,-925,0), 100d).setEmission(new Color(135,135,135)) //
						//.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)),
				//new Sphere(new Point(-275,-950,100), 50d).setEmission(new Color(135,135,135)) //
					//	.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(0.5)));

				//new Triangle(new Point(0, 900, -1250), new Point(900, -500, -1250), new Point(-900, -500, -1250)) //
					//	.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(1)),
				//new Triangle(new Point(0, -750, -1250), new Point(900, -500, -1100),
					//	new Point(-900, -500, -1250)) //
						//.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(0.5)));
		scene.background = new Color(77,173,188);
		//scene.lights.add( //
			//	new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
				//		.setkL(4E-4).setkQ(2E-5));

		//scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,0,700), new Vector(0,0,1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(350,0,400), new Vector(0,-1,0)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(-800,-1200,1000), new Vector(1,0.35,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(100,100,550), new Vector(-1,-1,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		scene.lights.add(new SpotLight(new Color(228,240,78), new Point(0,-900,500), new Vector(0,0,-1)) //
				.setkL(0.00001).setkQ(0.000005));
		
		//scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(1100, -600 , -1100), new Vector(-1, -1, -1)) //
			//	.setkL(0.00001).setkQ(0.000005));
		ImageWriter imageWriter = new ImageWriter("testCombinedGeometries", 500, 500);
		camera.setMyImage(imageWriter) //
				.setMyRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
				camera.writeToImage();
	}
	
}
