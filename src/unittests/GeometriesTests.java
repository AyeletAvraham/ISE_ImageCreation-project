/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Sphere;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author iyele
 *
 */
class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#Geometries()}.
	 */
	@Test
	void testGeometries() {
	}

	/**
	 * Test method for {@link geometries.Geometries#Geometries(geometries.Intersectable[])}.
	 */
	@Test
	void testGeometriesIntersectableArray() {
	}

	/**
	 * Test method for {@link geometries.Geometries#add(geometries.Intersectable[])}.
	 */
	@Test
	void testAdd() {
	}

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() 
	{
		Geometries lstGeo = new Geometries(new Sphere(new Point(2,1,0), 1), new Sphere(new Point(2,5,0), 2), new Sphere(new Point(2,-9,0), 1));
		Geometries lstGeo1 = new Geometries();
		
		// ============ Equivalence Partitions Tests ==============
		
		//TC01: some of the shapes have points of intersection
		List<Point> result = lstGeo.findIntersections(new Ray(new Point(2,-1,0),new Vector(0,1,0)));
	    assertEquals(4, result.size(), "Wrong number of points with Geometries");
	    
		
		 // =============== Boundary Values Tests ==================
		//TC02: the group is null
	    List<Point> result1 = lstGeo1.findIntersections(new Ray(new Point(2,-1,0),new Vector(0,1,0)));
	    assertEquals(0, result1.size(), "Wrong number of points with Geometries");
	    
	    //TC03: there is no shape that have an intresections with the ray
	    List<Point> result2 = lstGeo.findIntersections(new Ray(new Point(-1,0,0),new Vector(0,1,0)));
	    assertEquals(0, result2.size(), "Wrong number of points with Geometries");
	    
	    //TC04: only one shape have an intersections with the ray (2 points)
	    List<Point> result3 = lstGeo.findIntersections(new Ray(new Point(0,1,0),new Vector(1,0,0)));
	    assertEquals(2, result3.size(), "Wrong number of points with Geometries");
	    
	    //TC05: all the shapes have an intersections with the ray (6 points)
	    List<Point> result4 = lstGeo.findIntersections(new Ray(new Point(2,-11,0),new Vector(0,1,0)));
	    assertEquals(6, result4.size(), "Wrong number of points with Geometries");
	    
	}

}
