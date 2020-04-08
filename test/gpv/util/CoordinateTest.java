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
import org.junit.jupiter.api.*;
import gpv.chess.*;
import static gpv.chess.ChessPieceDescriptor.WHITEPAWN;
import static gpv.chess.ChessPieceDescriptor.BLACKPAWN;
import static gpv.util.Coordinate.makeCoordinate;

/**
 * Description
 * @version 1 abr. 2020
 */
class CoordinateTest
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
	
	// test sameDiagonal
	
	@Test
	void isDiagonal()
	{	
		assertTrue(makeCoordinate(2,3).sameDiagonal(makeCoordinate(5,6)));
	}
	
	@Test
	void isNotDiagonal()
	{	
		assertFalse(makeCoordinate(2,3).sameDiagonal(makeCoordinate(2,7)));
	}
	
	@Test
	void isDiagonal2()
	{	
		assertTrue(makeCoordinate(1,6).sameDiagonal(makeCoordinate(5,2)));
	}
	
	//test sameOrthogonal
	@Test
	void isOrthogonalVertical() {
		
		assertTrue(makeCoordinate(1,2).sameOrthogonal(makeCoordinate(6,2)));
	}
	
	@Test
	void isOrthogonalHorizontal() {
		
		assertTrue(makeCoordinate(1,2).sameOrthogonal(makeCoordinate(1,8)));
	}
	
	@Test
	void isNotOrthogonal() {
		
		assertFalse(makeCoordinate(1,2).sameOrthogonal(makeCoordinate(4,5)));
	}
	//test if the orthagonal is clear 
	
	@Test
	void clearOrthagonalDirection1()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(6,4));
		assertTrue(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(6,4),board));
	}
	@Test
	void clearOrthagonalDirection2()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(2,4));
		assertTrue(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(2,4),board));
	}
	@Test
	void clearOrthagonalDirection3()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(4,6));
		assertTrue(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(4,6),board));
	}
	@Test
	void clearOrthagonalDirection4()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(4,2));
		assertTrue(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(4,2),board));
	}
	@Test
	void clearOrthagonalPathOfSize0()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(4,5));
		assertTrue(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(4,5),board));
	}
	
	@Test
	void notClearOrthagonalDirection1()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(6,4));
		board.putPieceAt(pawn3, makeCoordinate(5,4));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(6,4),board));
	}
	
	@Test
	void notClearOrthagonalDirection2()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(1,4));
		board.putPieceAt(pawn3, makeCoordinate(2,4));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(1,4),board));
	}
	@Test
	void notClearOrthagonalDirection3()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(4,7));
		board.putPieceAt(pawn3, makeCoordinate(4,5));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(4,7),board));
	}
	
	@Test
	void notClearOrthagonalDirection4()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(4,1));
		board.putPieceAt(pawn3, makeCoordinate(4,2));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(4,1),board));
	}
	
	
	
	
	
	// test if the diagonal is clear 
	
	@Test
	void clearDiagonal()
	{	
		assertTrue(makeCoordinate(2,3).diagonalIsClear(makeCoordinate(5,6),board));
	}
	@Test
	void diagonalNotClear()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,3));
		assertFalse(makeCoordinate(1,6).diagonalIsClear(makeCoordinate(5,2),board));
	}
	@Test
	void diagonalClearWithStartAndEnd()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece bpawn = factory.makePiece(BLACKPAWN);
		board.putPieceAt(pawn, makeCoordinate(1,6));
		board.putPieceAt(bpawn, makeCoordinate(5,2));
		assertTrue(makeCoordinate(1,6).diagonalIsClear(makeCoordinate(5,2),board));
	}
	
	@Test
	void diagonalClearWithsize0()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece bpawn = factory.makePiece(BLACKPAWN);
		board.putPieceAt(pawn, makeCoordinate(2,4));
		board.putPieceAt(bpawn, makeCoordinate(3,4));
		assertTrue(makeCoordinate(2,4).diagonalIsClear(makeCoordinate(3,5),board));
	}
	
	@Test
	void notClearDiagonalDirection1()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(1,1));
		board.putPieceAt(pawn3, makeCoordinate(2,2));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(1,1),board));
	}
	@Test
	void notClearDiagonalDirection2()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(7,7));
		board.putPieceAt(pawn3, makeCoordinate(6,6));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(7,7),board));
	}
	
	@Test
	void notClearDiagonalDirection3()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(1,7));
		board.putPieceAt(pawn3, makeCoordinate(3,5));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(1,7),board));
	}
	@Test
	void notClearDiagonalDirection4()
	{	
		ChessPiece pawn = factory.makePiece(WHITEPAWN);
		ChessPiece pawn2 = factory.makePiece(WHITEPAWN);
		ChessPiece pawn3 = factory.makePiece(WHITEPAWN);
		board.putPieceAt(pawn, makeCoordinate(4,4));
		board.putPieceAt(pawn2, makeCoordinate(7,1));
		board.putPieceAt(pawn3, makeCoordinate(5,3));
		assertFalse(makeCoordinate(4,4).clearOrthagonal(makeCoordinate(7,1),board));
	}
	
	
	
	
	


}
