/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Sphere;
import primitives.Point;
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
		assertEquals( p1.getNormal(), new Vector(0,0,1), "getNormal(p1) wrong result");
	}

}
