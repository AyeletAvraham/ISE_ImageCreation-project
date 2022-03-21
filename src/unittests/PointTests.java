/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * @author iyele
 *
 */
class PointTests {

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: test regular point
		try {
			new Point(1,3,0);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Point");
		}
	}
	
	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(1,2,3);
		Point p2 = new Point(4,9,2);
		Vector ans = new Vector(-3,-7,1);
		//============ Equivalence Partitions Tests ==============
		// TC01: The two points are different
		assertEquals( p1.subtract(p2), ans, "subtract(p) wrong result sub between two points that are different");
        
		//=============== Boundary Values Tests ==================
		// TC02: The two points are the same
		try {
		assertEquals( isZero((p1.subtract(p1)).length()), false, "subtract(p) wrong result sub between two points that are different");
		out.println("ERROR: zero vector does not throw an exception");
		}
		catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		Point p1 = new Point(1,2,3);
		Vector v1 = new Vector(4,9,8);
		Point ans = new Point(5,11,11);
		//============ Equivalence Partitions Tests ==============
		// TC01: add vector to point
		assertEquals( p1.add(v1), ans, "add(v) wrong result add between vector and point");
        
		//=============== Boundary Values Tests ==================

	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		Point p1 = new Point(1,2,3);
		Point p2 = new Point(4,9,8);
		//============ Equivalence Partitions Tests ==============
		// TC01: The two points are different

		//=============== Boundary Values Tests ==================
		// TC02: The two points are the same


	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		Point p1 = new Point(4,9,3);
		Point p2 = new Point(4,9,2);
		//============ Equivalence Partitions Tests ==============
		// TC01: The two points are different
		assertEquals( p1.distance(p2), 1, "distance(p) wrong result distance between two points that are different");
        
		//=============== Boundary Values Tests ==================
		// TC02: The two points are the same
		assertEquals( (p1.distance(p1)), 0, "distance(p) wrong result distance between two points that are different");
		
	}

}
