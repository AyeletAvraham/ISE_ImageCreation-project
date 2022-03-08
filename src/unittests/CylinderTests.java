/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author iyele
 *
 */
class CylinderTests {
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	public void testConstructor() 
	{
		//============ Equivalence Partitions Tests ==============
		// TC01: test proper Cylinder
		try {
			new Cylinder(new Ray(new Point(1,3,4), new Vector(2,6,3)), 2, 9);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Cylinder");
		}
				
		//=============== Boundary Values Tests ==================
		
		// TC02: test negitive height
		try { 
			new Cylinder(new Ray(new Point(1,3,4), new Vector(2,6,3)), 8, -9);
			out.println("ERROR: negitive height does not throw an exception");
		} catch (Exception e) {
		}
	}
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		//============ Equivalence Partitions Tests ==============
		//=============== Boundary Values Tests ==================

	}

}
