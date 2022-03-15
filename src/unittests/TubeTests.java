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
	void testGetNormal() 
	{
		Tube t = new Tube(new Ray(new Point(0,0,0), new Vector(1,0,0)), 2);
		Point p0 = new Point(0,2,0);
		//============ Equivalence Partitions Tests ==============
		assertEquals(t.getNormal(p0), new Vector(0,2,0), "getNormal(p) of tube return wrong result");
	
		//=============== Boundary Values Tests ==================

	}

}
