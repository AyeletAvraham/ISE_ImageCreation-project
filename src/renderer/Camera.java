package renderer;

import static primitives.Util.isZero;

import java.util.MissingResourceException;

import primitives.*;

/**
 * Class to implement Camera to render the picture
 */

public class Camera {
	 /**
     * Point of the camera position
     */
	private Point p0;
	/**
     * directions of the camera
     */
	private Vector Vto,Vright, Vup;
	/**
     * distance of the view plane from the camera
     */
	double distance = 0;
	 /**
     * Height and width of the view plane
     */
	double height = 0,  width = 0;
	 /**
     * The image writer
     */
	private ImageWriter myImage;
	 /**
     * The ray tracer base
     */
	private RayTracerBase myRayTracer;

	
	/**
     * This function set the image writer and return the camera - builder pattern

     * @param myImage image writer
     * @return the camera

     */
	public Camera setMyImage(ImageWriter myImage) {
		this.myImage = myImage;
		return this;
	}

	/**
     * This function set the ray tracer and return the camera - builder pattern

     * @param myRayTracer ray tracer base
     * @return the camera

     */
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

	/**
     * This function set the view plane size and return the camera - builder pattern

     * @return the camera

     */
	public Camera setVPSize(double width, double height)
	{
		this.width = width;
		this.height = height;
		return this;
	}
	
	/**
     * This function set the distance of the view plane from the camera and return the camera - builder pattern

     * @return the camera

     */
	public Camera setVPDistance(double distance)
	{
		this.distance = distance;
		return this;
	}
	
	/**
     * This function find ray of the center of each pixel

     * @return the ray

     */
	public Ray constructRay(int nX, int nY, double j, double i) 
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
	
	/**
     * This function send the ray to calc the color of each pixel in RayTracerBasic

     * @return the color of the specific pixel

     */
	private Color castRay(double j, double i)
	{
		Ray ray = this.constructRay(myImage.getNx(), myImage.getNy(), j, i);
		
		return myRayTracer.traceRay(ray);
	}
	/**
     * This function uses the image writer to write the color of each pixel

     */
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
            	myImage.writePixel(j, i,castRay(j,i)); //before refactoring anti-analising
            }
        }
	}
	
	/**
     * This function is a refactoring for renderImage function, It sends 81 rays from
     *  every pixel and write the average color of all of this rays to this pixel 

     */
	public void antiAliasingRenderImage()
	{
		if (p0 == null || Vto==null || Vright == null || Vup == null|| height == 0|| distance == 0|| width == 0|| myImage == null|| myRayTracer == null)
			throw new MissingResourceException("Error", "Camara", "the camara is missing a field");
		int nX = myImage.getNx();
        int nY = myImage.getNy();
		Color tempC = new Color(0,0,0);
		
        for (int i = 0; i < nY; i++) 
        {
            for (int j = 0; j < nX; j++) 
            {
               	for (double k = i-0.5; k <= i+0.5; k+=(1.0/8)) 
                {
                    for (double m = j-0.5; m <= j+0.5; m+=(1.0/8))
                   {
                    	tempC = tempC.add(castRay(m,k));
                    }
                }
            	myImage.writePixel(j, i, tempC.reduce(81));
            	//myImage.writePixel(j, i,castRay(j,i)); //refactoring anti-analising
            	tempC = new Color(0,0,0);
            
            }
        }
	}
	
	/**
     * Super sampling improvment - This function is a refactoring for antiAliasingRenderImage function, 
     * Instead of sending 81 rays from every pixel,
     * It send rays only from the relevant pixels and write the average color
     * of all of this rays to this pixel

     */
	
	public void RuningTimeRenderImage()
	{
		if (p0 == null || Vto==null || Vright == null || Vup == null|| height == 0|| distance == 0|| width == 0|| myImage == null|| myRayTracer == null)
			throw new MissingResourceException("Error", "Camara", "the camara is missing a field");
		int nX = myImage.getNx();
        int nY = myImage.getNy();
		//Color tempC = new Color(0,0,0);
		
        for (int i = 0; i < nY; i++) 
        {
            for (int j = 0; j < nX; j++) 
            {
            	myImage.writePixel(j, i,superSampling(j,i,2));
            	/* refactoring anti-analising
            	for (double k = i-0.5; k <= i+0.5; k+=(1.0/8)) 
                {
                    for (double m = j-0.5; m <= j+0.5; m+=(1.0/8))
                   {
                    	tempC = tempC.add(castRay(m,k));
                    }
                }
            	myImage.writePixel(j, i, tempC.reduce(81));
            	//myImage.writePixel(j, i,castRay(j,i)); //refactoring anti-analising
            	tempC = new Color(0,0,0);
            	*/
            }
        }
	}
	
	/**
     * This is a recursive function that divides the area into 4 areas, check the
     * color of all of the corners and if they are the same - it returns this color,
     * else - it continue the recursive process 
     */
	private Color superSampling(double j, double i, int n)
	{
		if(n==16) //stopping condition
		{	
			return castRay(j,i);
		}
		Color c1 = castRay(j-1.0/n,i-1.0/n);
		Color c2 = castRay(j-1.0/n,i+1.0/n);
		Color c3 = castRay(j+1.0/n,i+1.0/n);
		Color c4 = castRay(j+1.0/n,i-1.0/n);
		if(c1==c2 && c1==c3 && c1==c4)
			return c1;
		else
		{
			Color tempC = new Color(0,0,0);
			tempC = tempC.add(superSampling(j-1.0/(n*2),i-1.0/(n*2),n*2),superSampling(j-1.0/(n*2),i+1.0/(n*2),n*2),superSampling(j+1.0/(n*2),i-1.0/(n*2),n*2),superSampling(j+1.0/(n*2),i+1.0/(n*2),n*2));
			
			return tempC.reduce(4);
		}
	}
	/**
     * This function is print the grid of the view plan in the color that was chosen

	 * @param interval the space between two grids
	 * @param color the color of the grid
     */
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
	/**
     * This function plays the image writer of the camera

     */
	public void writeToImage()
	{
		if(myImage==null)
			throw new MissingResourceException("Error", "Camara", "the camara is missing a image");
		myImage.writeToImage();
		
	}
	
	
}
