package renderer;

import static primitives.Util.isZero;

import java.util.MissingResourceException;

import primitives.*;

public class Camera {

	private Point p0;
	private Vector Vto,Vright, Vup;
	double height = 0, distance = 0, width = 0;
	private ImageWriter myImage;
	private RayTracerBase myRayTracer;

	public Camera setMyImage(ImageWriter myImage) {
		this.myImage = myImage;
		return this;
	}

	public Camera setMyRayTracer(RayTracerBase myRayTracer) {
		this.myRayTracer = myRayTracer;
		return this;
	}

	public Camera(Point _p0, Vector _Vto, Vector _Vup) {
		if(!isZero(_Vup.dotProduct(_Vto)))
			throw new IllegalArgumentException("ERROR, this vectors are not vertical");
		p0 = _p0;
		Vup = _Vup.normalize();
		Vto = _Vto.normalize();
		Vright = _Vto.crossProduct(_Vup).normalize();
	}

	public Point getP0() {
		return p0;
	}


	public Vector getVto() {
		return Vto;
	}


	public Vector getVright() {
		return Vright;
	}


	public Vector getVup() {
		return Vup;
	}

	public double getHeight() {
		return height;
	}


	public double getDistance() {
		return distance;
	}



	public double getWidth() {
		return width;
	}

	public Camera setVPSize(double _width, double _height)
	{
		width = _width;
		height = _height;
		return this;
	}
	
	public Camera setVPDistance(double _distance)
	{
		distance = _distance;
		return this;
	}
	public Ray constructRay(int nX, int nY, int j, int i)
	{
		Point pc = p0.add(Vto.scale(distance));
		double Ry = height/nY;
		double Rx = width/nX;
		double Xj = Rx*(j-(nX - 1)/2.0);
		double Yi = Ry*(-(i-(nY - 1)/2.0));
		Point Pij;
		if(Xj!=0&&Yi!=0)
			Pij = pc.add((Vright.scale(Xj)).add((Vup).scale(Yi)));
		else if (Xj!=0)
			Pij = pc.add((Vright.scale(Xj)));
		else if(Yi!=0)
			Pij = pc.add(((Vup).scale(Yi)));
		else Pij = pc;
		return new Ray(p0, Pij.subtract(p0));
	}
	private Color castRay(int j, int i)
	{
		Ray ray = this.constructRay(myImage.getNx(), myImage.getNy(), j, i);
        return myRayTracer.traceRay(ray);
	}
	
	public void renderImage()
	{
		if (p0 == null || Vto==null || Vright == null || Vup == null|| height == 0|| distance == 0|| width == 0|| myImage == null|| myRayTracer == null)
			throw new MissingResourceException("Error", "Camara", "the camara is missing a field");
		
		int nX = myImage.getNx();
        int nY = myImage.getNy();
        for (int i = 0; i < nY; i++) 
        {
            for (int j = 0; j < nX; j++) 
            {
            	myImage.writePixel(j, i, castRay(j,i));
            }
        }
	}
	
	public void printGrid(int interval, Color color) 
	{
		if(myImage==null)
			throw new MissingResourceException("Error", "Camara", "the camara is missing a image");
		int nX = myImage.getNx();
        int nY = myImage.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                	myImage.writePixel(j, i, color);
                }
            }
        }
	}
	public void writeToImage()
	{
		if(myImage==null)
			throw new MissingResourceException("Error", "Camara", "the camara is missing a image");
		myImage.writeToImage();
		
	}
	
	
}
