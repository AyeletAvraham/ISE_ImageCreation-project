/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

/**
 * @author Ayelet and Tamar
 *
 */
class VectorTests {
	/**
	 * Test method for  {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: test regular vector
		try {
			new Vector(1,3,0);
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct Vector");
		}
		
		//=============== Boundary Values Tests ==================
		
		// TC02: test zero vector
		try { 
			new Vector(0, 0, 0);
			out.println("ERROR: zero vector does not throw an exception");
		} catch (Exception e) {
		}
	}
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtractVector()
	{
		Vector v1 = new Vector(0,2,0);
		Vector v2 = new Vector(2,-2,0); //V2 creates an obtuse angle with V1
		Vector ans1 = new Vector(-2,4,0); //v1-v2
		Vector v3 = new Vector(1,1,0); //V3 creates a sharp angle with V1
		Vector ans2 = new Vector(-1,1,0); //v1-v3
		
		//============ Equivalence Partitions Tests ==============
        
		// TC01: A test that examines subtraction between vectors with an obtuse angle
		assertEquals( v1.subtract(v2), ans1, "subtract(v) wrong result substract between vectors with an obtuse angle");
        
		// TC02: A test that examines subtraction between vectors with a sharp angle
		assertEquals( v1.subtract(v3), ans2, "subtract(v) wrong result substract between vectors with a sharp angle");
        
		//=============== Boundary Values Tests ==================
		
		// TC04: Vectors are really equal
		assertEquals( isZero(v1.subtract(v1).length()), false, "subtract(v) wrong result substract between vectors on the same straight");
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector()
	{
		Vector v1 = new Vector(0,2,0);
		Vector v2 = new Vector(2,-2,0); //V2 creates an obtuse angle with V1
		Vector ans1 = new Vector(2,0,0); //v1+v2
		Vector v3 = new Vector(1,1,0); //V3 creates a sharp angle with V1
		Vector ans2 = new Vector(1,3,0); //v1+v3
		Vector v4 = new Vector(0,-2,0);
		
		//============ Equivalence Partitions Tests ==============
    
		// TC01: A test that examines adding between vectors with an obtuse angle
		assertEquals( v1.add(v2), ans1, "add(v) wrong result add between vectors with an obtuse angle");
    
		// TC02: A test that examines adding between vectors with a sharp angle
		assertEquals( v1.add(v3), ans2, "add(v) wrong result add between vectors with a sharp angle");
    
		//=============== Boundary Values Tests ==================
		
		// TC05: Vectors are exactly equal but in opposite directions
		assertEquals( isZero((v1.add(v4)).length()), false , "add(v) wrong result add between vectors are exactly equal but in opposite directions");
		 
	}

	/**
	 * Test method for {@link primitives.Vector#scale(int)}.
	 */
	@Test
	void testScale() {
		//============ Equivalence Partitions Tests ==============
		
		Vector v1 = new Vector(1,-3,4);
		Vector ans = new Vector(2,-6,8); 
		
		//TC01: check a scalar != 0
		assertEquals( v1.scale(2), ans, "scale(num) wrong result");
		
		//=============== Boundary Values Tests ==================
		
		// TC02: Scalar that == 0
		assertEquals( isZero(v1.scale(0).length()), false, "scale(num) wrong result");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		Vector v1 = new Vector(0,2,0);
		Vector v2 = new Vector(2,-2,0); //V2 creates an obtuse angle with V1
		Vector v3 = new Vector(1,1,0); //V3 creates a sharp angle with V1
		Vector v4 = new Vector(2,0,0);
		
		//============ Equivalence Partitions Tests ==============
		
		// TC01: A test that examines dot product between vectors with an obtuse angle
		assertEquals( v1.dotProduct(v2), -4, "dotProduct(v) wrong result dot product between vectors with an obtuse angle");
		    
		// TC02: A test that examines dot product between vectors with a sharp angle
		assertEquals( v1.dotProduct(v3), 2, "dotProduct(v) wrong result dot product between vectors with a sharp angle");
		
		//=============== Boundary Values Tests ==================
		
		// TC03: Vertical vectors 
		assertEquals( v1.dotProduct(v3), 0, "dotProduct(v) wrong result dot product between vectors with a sharp angle");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() 
	{
				
		Vector v1 = new Vector(0,2,0);
		Vector v2 = new Vector(2,-2,0); //V2 creates an obtuse angle with V1
		Vector ans1 = new Vector(0,0,-4);
		Vector v3 = new Vector(1,1,0); //V3 creates a sharp angle with V1
		Vector ans2 = new Vector(0,0,-2);
		Vector v4 = new Vector(0,-2,0);
		Vector v5 = new Vector(0,4,0);
		//============ Equivalence Partitions Tests ==============
		
		// TC01: A test that examines cross product between vectors with an obtuse angle
		assertEquals( v1.crossProduct(v2), ans1, "crossProduct(v) wrong result cross product between vectors with an obtuse angle");
						    
		// TC02: A test that examines cross product between vectors with a sharp angle
		assertEquals( v1.crossProduct(v3), ans2, "crossProduct(v) wrong result cross product between vectors with a sharp angle");
				
		//=============== Boundary Values Tests ==================
	      
		// TC03: Vectors on the same straight
		assertEquals( isZero(v1.crossProduct(v5).length()), false, "crossProduct(v) wrong result crossProduct between vectors on the same straight");
				
		// TC04: Vectors are really equal
		assertEquals( isZero(v1.crossProduct(v1).length()), false, "crossProduct(v) wrong result crossProduct between vectors on the same straight");
				
		// TC05: Vectors are exactly equal but in opposite directions
		assertEquals(isZero(v1.crossProduct(v4).length()), false, "crossProduct(v) wrong result crossProduct between vectors are exactly equal but in opposite directions");	
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		//============ Equivalence Partitions Tests ==============
		//=============== Boundary Values Tests ==================
		//There are no tests because this function returns a root of the distance,
		//and the tests were performed the entire distance


	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		Vector v1 = new Vector(0,2,0);
		
		//============ Equivalence Partitions Tests ==============
		assertEquals((v1.length()), 2, "length(v) wrong result length");
		
		//=============== Boundary Values Tests ==================

		
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		Vector v1 = new Vector(0,2,0);
		Vector v2 = new Vector(1,0,0);//unit vector
		
		//============ Equivalence Partitions Tests ==============
		
		// TC01: test a regular vector
		assertEquals((v1.normalize()), 1, "normalize(v) wrong result normalize of regular vector");
		
		//=============== Boundary Values Tests ==================

		// TC02: the vectors is a unit vector
		assertEquals((v2.normalize()), 1, "normalize(v) wrong result normalize of unit vector");
	}

}
