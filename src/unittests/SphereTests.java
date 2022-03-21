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
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author iyele
 *
 */
class SphereTests {
	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	public void testConstructor() 
	{
		//============ Equivalence Partitions Tests ==============
		// TC01: test regular Sphere
		try {
			new Sphere(new Point(1,0,2), 3);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Sphere");
		}
				
		//=============== Boundary Values Tests ==================
		
		// TC02: test points on the same vector
		try { 
			new Sphere(new Point(1,4,0), -3);
				out.println("ERROR: can't build this shpere, the radius is negitive");
		} catch (Exception e) {
		}
	}
	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Sphere s =  new Sphere(new Point(0,0,0), 1);
		Point p1 = new Point(0,1,0);//is part of the sphere
		Point p2 = new Point(0,2,0);//is not part of the sphere
		//============ Equivalence Partitions Tests ==============
		// TC01: test point that is part of the sphere
		assertEquals( s.getNormal(p1), p1, "getNormal(p1) wrong result");
		// TC02: test point that isn't part of the sphere
		try { 
			s.getNormal(p2);
			out.println("ERROR: getNormal(p) doesn't throw an exception");
		} catch (Exception e) {
		}
		//=============== Boundary Values Tests ==================
	}
	
	  /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                   "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getXyz().getD1() > result.get(1).getXyz().getD1())
            result = List.of(result.get(1), result.get(0));       
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
       List<Point> result1 = sphere.findIntersections(new Ray(new Point(0.5,0,0),new Vector(0,0,1)));
       assertEquals(1, result1.size(), "Wrong number of points");
       
        // TC04: Ray starts after the sphere (0 points)
       List<Point> result2 = sphere.findIntersections(new Ray(new Point(3,0.5,0),new Vector(1,0,0)));
       assertEquals(null, result2, "Wrong number of points");
       

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
       List<Point> result3 = sphere.findIntersections(new Ray(new Point(1,1,0),new Vector(-1,-1,0)));
       assertEquals(1, result3.size(), "Wrong number of points");
      
        // TC12: Ray starts at sphere and goes outside (0 points)
       List<Point> result4 = sphere.findIntersections(new Ray(new Point(1,1,0),new Vector(1,1,0)));
       assertEquals(null, result4, "Wrong number of points");
      
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
       List<Point> result5 = sphere.findIntersections(new Ray(new Point(-1,0,0),new Vector(1,0,0)));
       assertEquals(2, result5.size(), "Wrong number of points");
      
        // TC14: Ray starts at sphere and goes inside (1 points)
       List<Point> result6 = sphere.findIntersections(new Ray(new Point(1,1,0),new Vector(0,-1,0)));
       assertEquals(1, result6.size(), "Wrong number of points");
      
        // TC15: Ray starts inside (1 points)
       List<Point> result7 = sphere.findIntersections(new Ray(new Point(0.5,0,0),new Vector(-1,0,0)));
       assertEquals(1, result7.size(), "Wrong number of points");
      
        // TC16: Ray starts at the center (1 points)
       List<Point> result8 = sphere.findIntersections(new Ray(new Point(1,0,0),new Vector(1,0,0)));
       assertEquals(1, result8.size(), "Wrong number of points");
      
        // TC17: Ray starts at sphere and goes outside (0 points)
       List<Point> result9 = sphere.findIntersections(new Ray(new Point(2,0,0),new Vector(1,0,0)));
       assertEquals(null, result9, "Wrong number of points");
      
        // TC18: Ray starts after sphere (0 points)
       List<Point> result10 = sphere.findIntersections(new Ray(new Point(3,0,0),new Vector(1,0,0)));
       assertEquals(null, result10, "Wrong number of points");
      
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
       List<Point> result11 = sphere.findIntersections(new Ray(new Point(2,-1,0),new Vector(0,1,0)));
       assertEquals(null, result11, "Wrong number of points");
      
        // TC20: Ray starts at the tangent point
       List<Point> result12 = sphere.findIntersections(new Ray(new Point(2,0,0),new Vector(0,1,0)));
       assertEquals(null, result12, "Wrong number of points");
       
        // TC21: Ray starts after the tangent point
       List<Point> result13 = sphere.findIntersections(new Ray(new Point(2,1,0),new Vector(0,1,0)));
       assertEquals(null, result13, "Wrong number of points");
       
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
       List<Point> result14 = sphere.findIntersections(new Ray(new Point(3,0,0),new Vector(0,1,0)));
       assertEquals(null, result14, "Wrong number of points");
    }

}
