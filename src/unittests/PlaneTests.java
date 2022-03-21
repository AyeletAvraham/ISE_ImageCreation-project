/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author iyele
 *
 */
class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	public void testConstructor() 
	{
		//============ Equivalence Partitions Tests ==============
		// TC01: test regular plane
		try {
			new Plane(new Point(0,9,0), new Point(1,0,0), new Point(0,0,2));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Sphere");
		}
				
		//=============== Boundary Values Tests ==================
		// TC02: test wrong plane - all the points are on the same vector
		try {
			new Plane(new Point(2,0,0), new Point(1,0,0), new Point(7,0,0));
			out.print("all the point are on the same vector");
		} catch (IllegalArgumentException e) {
		}
		// TC03: test wrong plane - two of the points are the same
		try {
			new Plane(new Point(2,0,0), new Point(2,0,0), new Point(0,9,0));
			out.print("two of the points are the same");
		} catch (IllegalArgumentException e) {
		}
	}
	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 */
	@Test
	void testGetNormal() {
		//============ Equivalence Partitions Tests ==============
		
		//=============== Boundary Values Tests ==================

	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		Plane p1 = new Plane(new Point(0,9,0), new Point(1,0,0), new Point(0,6,0));
		//============ Equivalence Partitions Tests ==============
		assertEquals( p1.getNormal(), new Vector(0,0,-1), "getNormal(p1) wrong result");
	}
	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	 @Test
	    public void testFindIntersections() 
	 {
		 Plane p = new Plane(new Point(0,0,1), new Vector(0,0,1));
		 
	       // ============ Equivalence Partitions Tests ==============
			
		    // TC01: The ray intersects the plane (1 points)
		 	Ray r1 = new Ray(new Point(-1,-1,-1),new Vector(1,1,1));
		 	List<Point> result = p.findIntersections(r1);
		    assertEquals(1, result.size(), "Wrong number of points with Plane");
		    // TC02: The ray doesn't intersect the plane (0 points)
		 	List<Point> result1 = p.findIntersections(new Ray(new Point(2,2,2),new Vector(1,1,1)));
		 	assertEquals(null, result1, "Wrong number of points with Plane");
		   
		    //=============== Boundary Values Tests ==================
		    
		    // TC03: The ray is parallel the plane (0 points)
		 	List<Point> result2 = p.findIntersections(new Ray(new Point(0,0,2),new Vector(1,0,0)));
		    assertEquals(null, result2, "Wrong number of points with Plane");
		   
		    // TC04: The ray includes in the plane (0 points)
		 	List<Point> result3 = p.findIntersections(new Ray(new Point(0,0,1),new Vector(1,0,0)));
		    assertEquals(null, result3, "Wrong number of points with Plane");
		   
		    // TC05: The ray is orthogonal to the plane and start before the plane(1 points)
		 	List<Point> result4 = p.findIntersections(new Ray(new Point(1,0,0),new Vector(0,0,1)));
		    assertEquals(1, result4.size(), "Wrong number of points with Plane");
		   
		    // TC06: The ray is orthogonal to the plane and start after the plane(0 points)
		 	List<Point> result5 = p.findIntersections(new Ray(new Point(1,0,2),new Vector(0,0,1)));
		    assertEquals(null, result5, "Wrong number of points with Plane");
		   
		    // TC07: The ray is orthogonal to the plane and start on the plane(0 points)
		 	List<Point> result6 = p.findIntersections(new Ray(new Point(1,0,1),new Vector(0,0,1)));
		    assertEquals(null, result6, "Wrong number of points with Plane");
		   
		    // TC08: The ray isn't orthogonal and isn't parallel and start in the plane(0 points)
		 	List<Point> result7 = p.findIntersections(new Ray(new Point(1,0,2),new Vector(1,1,1)));
		    assertEquals(null, result7, "Wrong number of points with Plane");
		   
		    // TC09: The ray is orthogonal to the plane and start before the plane(01 points)
		 	List<Point> result8 = p.findIntersections(new Ray(new Point(1,0,0),new Vector(0,0,1)));
		    assertEquals(1, result8.size(), "Wrong number of points with Plane");
		   
	 }
}
