/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
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

}
