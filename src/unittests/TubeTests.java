/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * @author iyele
 *
 */
class TubeTests {
	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	public void testConstructor() 
	{
		//============ Equivalence Partitions Tests ==============
		// TC01: test regular Tube
		try {
			new Tube(new Ray(new Point(1,3,4), new Vector(2,6,3)), 2);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Tube");
		}
				
		//=============== Boundary Values Tests ==================
		
		// TC02: test negitive radius
		try { 
			new Tube(new Ray(new Point(1,3,4), new Vector(2,6,3)), -8);
			out.println("ERROR: negitive radius does not throw an exception");
		} catch (Exception e) {
		}
	}
	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		//============ Equivalence Partitions Tests ==============
		//=============== Boundary Values Tests ==================

	}

}
