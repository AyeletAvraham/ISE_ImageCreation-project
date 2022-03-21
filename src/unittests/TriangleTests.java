/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author iyele
 *
 */
class TriangleTests {
	/**
	 * Test method for {@link geometries.Triangle#constractor(primitives.Point,primitives.Point,primitives.Point)}.
	 */
	@Test
	public void testConstructor() 
	{
		//============ Equivalence Partitions Tests ==============
		// TC01: test regular Triangle
		try {
			new Triangle(new Point(1,0,0), new Point(0,1,0), new Point(0,0,1));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Triangle");
		}
				
		//=============== Boundary Values Tests ==================
		
		// TC02: test points on the same vector
		try { 
			new Triangle(new Point(1,0,0), new Point(2,0,0), new Point(3,0,0));
				out.println("ERROR: points on the same vector does not throw an exception");
		} catch (Exception e) {
		}
 }
	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	 @Test
	    public void testFindIntersections() 
	 {
		 Triangle triangle = new Triangle(new Point(2,0,0), new Point(0,2,0), new Point(-2,0,0));
	        // ============ Equivalence Partitions Tests ==============
			 // TC01: The point is insides of the triangle (1 points)
		     List<Point> result = triangle.findIntersections(new Ray(new Point(0.5,0.5,-1),new Vector(0,0,1)));
		     assertEquals(1, result.size(), "Wrong number of points with Triangle");
			 
			// TC02: The point is in front of the vertex of the triangle (0 points)
		     List<Point> result1 = triangle.findIntersections(new Ray(new Point(3,3,-1),new Vector(0,0,1)));
			 assertEquals(null, result1, "Wrong number of points with Triangle");
			 
			// TC03: The point is insides of the triangle (0 points)
		     List<Point> result2 = triangle.findIntersections(new Ray(new Point(4,-1,-1),new Vector(0,0,1)));
			 assertEquals(null, result2, "Wrong number of points with Triangle");
	        
			 // =============== Boundary Values Tests ==================
			// TC04: The point is a vertex of the triangle (0 points)
		     List<Point> result3 = triangle.findIntersections(new Ray(new Point(2,0,-1),new Vector(0,0,1)));
			 assertEquals(null, result3, "Wrong number of points with Triangle");
			 
			// TC05: The point is on a side of the triangle (0 points)
		     List<Point> result4 = triangle.findIntersections(new Ray(new Point(1,0,-1),new Vector(0,0,1)));
			 assertEquals(null, result4, "Wrong number of points with Triangle");
			 
			// TC06: The point is below a side of the triangle (0 points)
		     List<Point> result5 = triangle.findIntersections(new Ray(new Point(3,0,-1),new Vector(0,0,1)));
			 assertEquals(null, result5, "Wrong number of points with Triangle");
	 }

}
