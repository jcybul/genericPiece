package gpv.util;

/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016 Gary F. Pollice
 *******************************************************************************/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static gpv.util.Coordinate.makeCoordinate;

/**
 * Description
 * @version 1 abr. 2020
 */
class CoordinateTest
{
	
	@Test
	void oneDiagonlMove()
	{	
		assertEquals(1,makeCoordinate(2,3).distance(makeCoordinate(3,2)));
	}
	
	@Test
	void oneForwardMove()
	{	
		assertEquals(1,makeCoordinate(2,3).distance(makeCoordinate(3,3)));
	}
	
	@Test
	void oneForwardMoveFromLargeToSmall()
	{	
		assertEquals(1,makeCoordinate(7,3).distance(makeCoordinate(6,3)));
	}
	
	@Test
	void twoDiagonlMove()
	{	
		assertEquals(2,makeCoordinate(3,6).distance(makeCoordinate(5,4)));
	}
	
	@Test
	void twoForwardMove()
	{	
		assertEquals(2,makeCoordinate(3,3).distance(makeCoordinate(5,3)));
	}
	

}
