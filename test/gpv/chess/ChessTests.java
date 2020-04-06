/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2020 Gary F. Pollice
 *******************************************************************************/

package gpv.chess;

import static gpv.chess.ChessPieceDescriptor.*;
import static org.junit.Assert.*;
import static gpv.util.Coordinate.makeCoordinate;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import gpv.util.Board;

/**
 * Tests to ensure that pieces are created correctly and that all pieces
 * get created.
 * @version Feb 23, 2020
 */
class ChessPieceTests
{
	private static ChessPieceFactory factory = null;
	private Board board;
	
	@BeforeAll
	public static void setupBeforeTests()
	{
		factory = new ChessPieceFactory();
	}
	
	@BeforeEach
	public void setupTest()
	{
		board = new Board(8, 8);
	}
	
	@Test
	void makePiece()
	{
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		assertNotNull(pawn);
	}
	
	/**
	 * This type of test loops through each value in the Enum and
	 * one by one feeds it as an argument to the test method.
	 * It's worth looking at the different types of parameterized
	 * tests in JUnit: 
	 * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
	 * @param d the Enum value
	 */
	@ParameterizedTest
	@EnumSource(ChessPieceDescriptor.class)
	void makeOneOfEach(ChessPieceDescriptor d)
	{
		ChessPiece p = factory.makePiece(d);
		assertNotNull(p);
		assertEquals(d.getColor(), p.getColor());
		assertEquals(d.getName(), p.getName());
	}

	@Test
	void placeOnePiece()
	{
		ChessPiece p = factory.makePiece(BLACKPAWN);
		board.putPieceAt(p, makeCoordinate(2, 2));
		assertEquals(p, board.getPieceAt(makeCoordinate(2, 2)));
	}

	@Test
	void placeTwoPieces()
	{
		ChessPiece bn = factory.makePiece(BLACKKNIGHT);
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		board.putPieceAt(bn, makeCoordinate(3, 5));
		board.putPieceAt(wb, makeCoordinate(2, 6));
		assertEquals(bn, board.getPieceAt(makeCoordinate(3, 5)));
		assertEquals(wb, board.getPieceAt(makeCoordinate(2, 6)));
	}
	
	@Test
	void checkForPieceHasMoved()
	{
		ChessPiece bq = factory.makePiece(BLACKQUEEN);
		assertFalse(bq.hasMoved());
		bq.setHasMoved();
		assertTrue(bq.hasMoved());
	}
	
	
	//WHITE PAWN TEST
	@Test
	void whitePawnMoveForward()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertTrue(wp.canMove(makeCoordinate(2,2),makeCoordinate(3,2),board));
	}
	
	@Test
	void whitePawnMoveForwardTwo()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertTrue(wp.canMove(makeCoordinate(2,2),makeCoordinate(4,2),board));
	}
	
	@Test
	void whitePawnMoveForwardTwoNotFromOriginalPosition()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(3,2));
		assertFalse(wp.canMove(makeCoordinate(3,2),makeCoordinate(5,2),board));
	}
	
	@Test
	void whitePawnMoveForwardThree()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(5,2),board));
	}
	
	@Test
	void whitePawnMovebackOne()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(1,2),board));
	}
	
	//BLACK PAWN TEST
	@Test
	void blackPawnMoveForward()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(7,3));
		assertTrue(bp.canMove(makeCoordinate(7,3),makeCoordinate(6,3),board));
	}
	
	@Test
	void blackPawnMoveForwardTwo()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(7,3));
		assertTrue(bp.canMove(makeCoordinate(7,3),makeCoordinate(5,3),board));
	}
	
	@Test
	void blackPawnMoveForwardTwoNotFromOriginalPosition()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(6,3));
		assertFalse(bp.canMove(makeCoordinate(6,3),makeCoordinate(4,3),board));
	}
	
	@Test
	void blackPawnMoveForwardThree()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(7,3));
		assertFalse(bp.canMove(makeCoordinate(7,3),makeCoordinate(4,3),board));
	}
	
	@Test
	void blackPawnMovebackOne()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(7,3));
		assertFalse(bp.canMove(makeCoordinate(7,3),makeCoordinate(8,3),board));
	}
	
	
	//WHITE KNIGHT
	
	@Test
	void whiteKnightMoveLForwardAndRight()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(1,7));
		assertTrue(wk.canMove(makeCoordinate(1,7),makeCoordinate(3,8),board));
	}
	
	@Test
	void whiteKnightMoveLForwardAndLeft()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(1,7));
		assertTrue(wk.canMove(makeCoordinate(1,7),makeCoordinate(3,6),board));
	}
	
	@Test
	void whiteKnightMoveLBackAndLeft()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(3,6));
		assertTrue(wk.canMove(makeCoordinate(3,6),makeCoordinate(1,5),board));
	}
	@Test
	void whiteKnightMoveLBackAndRight()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(3,6));
		assertTrue(wk.canMove(makeCoordinate(3,6),makeCoordinate(1,7),board));
	}
	
	@Test
	void whiteKnightMoveLRightAndUp()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(2,4));
		assertTrue(wk.canMove(makeCoordinate(2,4),makeCoordinate(3,6),board));
	}
	
	@Test
	void whiteKnightMoveLRightAndDown()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(2,4));
		assertTrue(wk.canMove(makeCoordinate(2,4),makeCoordinate(1,6),board));
	}
	@Test
	void whiteKnightMoveLLeftAndUp()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(2,4));
		assertTrue(wk.canMove(makeCoordinate(2,4),makeCoordinate(3,2),board));
	}
	@Test
	void whiteKnightMoveLLeftAndDown()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(2,4));
		assertTrue(wk.canMove(makeCoordinate(2,4),makeCoordinate(1,2),board));
	}
	
	
	//BLACKKNIGHT
	
	

	@Test
	void blackKnightMoveLForwardAndRight()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(8,6));
		assertTrue(wk.canMove(makeCoordinate(8,6),makeCoordinate(6,7),board));
	}
	
	@Test
	void blackKnightMoveLForwardAndLeft()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(8,6));
		assertTrue(wk.canMove(makeCoordinate(8,6),makeCoordinate(6,5),board));
	}
	
	@Test
	void blackKnightMoveLBackAndLeft()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(6,6));
		assertTrue(wk.canMove(makeCoordinate(6,6),makeCoordinate(8,5),board));
	}
	@Test
	void blackKnightMoveLBackAndRight()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(6,6));
		assertTrue(wk.canMove(makeCoordinate(6,6),makeCoordinate(8,7),board));
	}
	
	@Test
	void blackKnightMoveLRightAndUp()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(6,6));
		assertTrue(wk.canMove(makeCoordinate(6,6),makeCoordinate(7,8),board));
	}
	
	@Test
	void blackKnightMoveLRightAndDown()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(6,6));
		assertTrue(wk.canMove(makeCoordinate(6,6),makeCoordinate(5,8),board));
	}
	@Test
	void blackKnightMoveLLeftAndUp()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(6,6));
		assertTrue(wk.canMove(makeCoordinate(6,6),makeCoordinate(7,4),board));
	}
	@Test
	void blackKnightMoveLLeftAndDown()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(6,6));
		assertTrue(wk.canMove(makeCoordinate(6,6),makeCoordinate(5,4),board));
	}
	
	
	
	
	
	
	
	@Test
	void thisShouldFailOnDelivery()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(1,5));
		assertTrue(wk.canMove(makeCoordinate(1,5), makeCoordinate(2, 5), board));
	}

}
