/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;

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

}
