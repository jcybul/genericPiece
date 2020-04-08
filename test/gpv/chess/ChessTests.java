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
	//move to the same from and to
	@Test
	void moveToTheSameFromAndTo()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(2,2),board));
	}
	
	
	//OUT OF THE BOARD TEST
	@Test
	void outOfBoardBothXandYUpper()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(9,9),board));
	}
	@Test
	void outOfBoardBothXandYLower()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(0,0),board));
	}
	@Test
	void outOfBoardBoardRandom()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(0,0));
		assertFalse(wp.canMove(makeCoordinate(0,0),makeCoordinate(1,0),board));
	}
	@Test
	void outOfBoardBothFromAndTo()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(9,8));
		assertFalse(wp.canMove(makeCoordinate(9,8),makeCoordinate(8,8),board));
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
//	
	@Test
	void whitePawnMoveForwardTwoNotFromOriginalPosition()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(3,2));
		wp.setHasMoved();
		assertFalse(wp.canMove(makeCoordinate(3,2),makeCoordinate(5,2),board));
	}
	
	@Test
	void whitePawnMoveForwardThree()
	{
		System.out.println("Move three");
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(5,2),board));
		
	}
//		
	@Test
	void whitePawnMovebackOne()
	{
		System.out.println("Move back");
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(1,2),board));
	}
	@Test
	void whitePawnMoveDiagonalAttackBlackPiece()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		board.putPieceAt(bp, makeCoordinate(3,1));
		assertTrue(wp.canMove(makeCoordinate(2,2),makeCoordinate(3,1),board));
	}
	@Test
	void whitePawnMoveDiagonalAttackWhitePiece()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		ChessPiece wk = factory.makePiece(WHITEKING);
		board.putPieceAt(wp, makeCoordinate(2,2));
		board.putPieceAt(wk, makeCoordinate(3,1));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(3,1),board));
	}
	@Test
	void whitePawnMoveDiagonalIntoEmptySpace()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(3,1),board));
	}
	@Test
	void whitePawnMovetotheFrontOntoAnyPiece()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(wp, makeCoordinate(2,2));
		board.putPieceAt(bp, makeCoordinate(3,2));
		assertFalse(wp.canMove(makeCoordinate(2,2),makeCoordinate(3,2),board));
	}
	
	
	
//	
//	//BLACK PAWN TEST
	
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
		bp.setHasMoved();
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
	
	@Test
	void blackPawnMoveDiagonalAttackWhitePiece()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(bp, makeCoordinate(7,3));
		board.putPieceAt(wp, makeCoordinate(6,4));
		assertTrue(bp.canMove(makeCoordinate(7,3),makeCoordinate(6,4),board));
	}
	@Test
	void blackPawnMoveDiagonalAttackBlackPiece()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		ChessPiece bk = factory.makePiece(BLACKKING);
		board.putPieceAt(bp, makeCoordinate(7,3));
		board.putPieceAt(bk, makeCoordinate(6,4));
		assertFalse(bp.canMove(makeCoordinate(7,3),makeCoordinate(6,4),board));
	}
	@Test
	void blackPawnMoveDiagonalIntoEmptySpace()
	{
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(7,7));
		assertFalse(bp.canMove(makeCoordinate(7,7),makeCoordinate(6,8),board));
	}
	@Test
	void blackPawnMovetotheFrontOntoAnyPiece()
	{
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(7,7));
		board.putPieceAt(wp, makeCoordinate(6,7));
		assertFalse(bp.canMove(makeCoordinate(7,7),makeCoordinate(6,7),board));
	}
	
	
//	
//	//WHITE KNIGHT
//	
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
	@Test
	void whiteKnightMoveLToAttackBlackPiece()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(wk, makeCoordinate(1,6));
		board.putPieceAt(bp, makeCoordinate(3,5));
		assertTrue(wk.canMove(makeCoordinate(1,6),makeCoordinate(3,5),board));
	}
	
	void whiteKnightMoveLToAttackWhitePiece()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wk, makeCoordinate(1,6));
		board.putPieceAt(wp, makeCoordinate(3,5));
		assertFalse(wk.canMove(makeCoordinate(1,6),makeCoordinate(3,5),board));
	}
	@Test
	void whiteKnightMoveNotLShape()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(1,2));
		assertFalse(wk.canMove(makeCoordinate(1,2),makeCoordinate(2,2),board));
	}
	
	
	
//	
//	
//	//BLACKKNIGHT
//	
	@Test
	void blackKnightMoveLForwardAndRight()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(bk, makeCoordinate(8,6));
		assertTrue(bk.canMove(makeCoordinate(8,6),makeCoordinate(6,7),board));
	}
	
	@Test
	void blackKnightMoveLForwardAndLeft()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(bk, makeCoordinate(8,6));
		assertTrue(bk.canMove(makeCoordinate(8,6),makeCoordinate(6,5),board));
	}
	
	@Test
	void blackKnightMoveLBackAndLeft()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(bk, makeCoordinate(6,6));
		assertTrue(bk.canMove(makeCoordinate(6,6),makeCoordinate(8,5),board));
	}
	@Test
	void blackKnightMoveLBackAndRight()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(bk, makeCoordinate(6,6));
		assertTrue(bk.canMove(makeCoordinate(6,6),makeCoordinate(8,7),board));
	}
	
	@Test
	void blackKnightMoveLRightAndUp()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(bk, makeCoordinate(6,6));
		assertTrue(bk.canMove(makeCoordinate(6,6),makeCoordinate(7,8),board));
	}
	
	@Test
	void blackKnightMoveLRightAndDown()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(bk, makeCoordinate(6,6));
		assertTrue(bk.canMove(makeCoordinate(6,6),makeCoordinate(5,8),board));
	}
	@Test
	void blackKnightMoveLLeftAndUp()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(bk, makeCoordinate(6,6));
		assertTrue(bk.canMove(makeCoordinate(6,6),makeCoordinate(7,4),board));
	}
	@Test
	void blackKnightMoveLLeftAndDown()
	{
		ChessPiece wk = factory.makePiece(BLACKKNIGHT);
		board.putPieceAt(wk, makeCoordinate(6,6));
		assertTrue(wk.canMove(makeCoordinate(6,6),makeCoordinate(5,4),board));
	}
	
	@Test
	void blackKnightAttackWhitePiece()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		ChessPiece  wk = factory.makePiece(WHITEBISHOP);
		board.putPieceAt(bk, makeCoordinate(8,6));
		board.putPieceAt(wk,makeCoordinate(6,7));
		assertTrue(bk.canMove(makeCoordinate(8,6),makeCoordinate(6,7),board));
	}
	
	@Test
	void blackKnightAttackBlackPiece()
	{
		ChessPiece bk = factory.makePiece(BLACKKNIGHT);
		ChessPiece  bb = factory.makePiece(BLACKBISHOP);
		board.putPieceAt(bk, makeCoordinate(8,6));
		board.putPieceAt(bb,makeCoordinate(6,7));
		assertFalse(bk.canMove(makeCoordinate(8,6),makeCoordinate(6,7),board));
	}
	
	@Test
	void blackKnightMoveNotLShape()
	{
		ChessPiece wk = factory.makePiece(WHITEKNIGHT);
		board.putPieceAt(wk, makeCoordinate(8,7));
		assertFalse(wk.canMove(makeCoordinate(8,7),makeCoordinate(7,7),board));
	}
	
	
/// White Bishop 
	
	@Test
	void BishopMoveDirection1()
	{
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		board.putPieceAt(wb, makeCoordinate(3,4));
		assertTrue(wb.canMove(makeCoordinate(3,4),makeCoordinate(5,6),board));
	}
	@Test
	void BishopMoveDirection2()
	{
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		board.putPieceAt(wb, makeCoordinate(3,4));
		assertTrue(wb.canMove(makeCoordinate(3,4),makeCoordinate(1,2),board));
	}
	@Test
	void BishopMoveDirection3()
	{
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		board.putPieceAt(wb, makeCoordinate(3,4));
		assertTrue(wb.canMove(makeCoordinate(3,4),makeCoordinate(5,2),board));
	}
	@Test
	void BishopMoveDirection4()
	{
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		board.putPieceAt(wb, makeCoordinate(3,4));
		assertTrue(wb.canMove(makeCoordinate(3,4),makeCoordinate(1,6),board));
	}
	
	@Test
	void BishopBumpIntoAnyPiece()
	{
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wp, makeCoordinate(4,5));
		board.putPieceAt(wb, makeCoordinate(3,4));
		assertFalse(wb.canMove(makeCoordinate(3,4),makeCoordinate(6,7),board));
	}
	
	@Test
	void whiteBishopAttackBlackPiece()
	{
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(6,7));
		board.putPieceAt(wb, makeCoordinate(3,4));
		assertTrue(wb.canMove(makeCoordinate(3,4),makeCoordinate(6,7),board));
	}
	@Test
	void whiteBishopAttackWhitePiece()
	{
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		ChessPiece bp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(bp, makeCoordinate(6,7));
		board.putPieceAt(wb, makeCoordinate(3,4));
		assertFalse(wb.canMove(makeCoordinate(3,4),makeCoordinate(6,7),board));
	}
	
	@Test
	void blackBishopAttackBlackPiece()
	{
		ChessPiece wb = factory.makePiece(BLACKBISHOP);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(bp, makeCoordinate(7,6));
		board.putPieceAt(wb, makeCoordinate(8,7));
		assertFalse(wb.canMove(makeCoordinate(8,7),makeCoordinate(7,6),board));
	}
	@Test
	void blackBishopAttackWhitePiece()
	{
		ChessPiece bb = factory.makePiece(BLACKBISHOP);
		ChessPiece bp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(bp, makeCoordinate(2,1));
		board.putPieceAt(bb, makeCoordinate(8,7));
		assertTrue(bb.canMove(makeCoordinate(8,7),makeCoordinate(2,1),board));
	}
	
	@Test 
	void bishopNonDiagonal(){
		ChessPiece wb = factory.makePiece(WHITEBISHOP);
		board.putPieceAt(wb, makeCoordinate(1,2));
		assertFalse(wb.canMove(makeCoordinate(1,2),makeCoordinate(2,2),board));
		
	}
	// Rook test:
	@Test 
	void rookDirection1(){
		ChessPiece wr = factory.makePiece(WHITEROOK);
		board.putPieceAt(wr, makeCoordinate(1,8));
		assertTrue(wr.canMove(makeCoordinate(1,8),makeCoordinate(6,8),board));
		
	}
	@Test 
	void rookDirection2(){
		ChessPiece wr = factory.makePiece(WHITEROOK);
		board.putPieceAt(wr, makeCoordinate(1,8));
		assertTrue(wr.canMove(makeCoordinate(1,8),makeCoordinate(1,1),board));
		
	}
	@Test 
	void rookDirection3(){
		ChessPiece br = factory.makePiece(BLACKROOK);
		board.putPieceAt(br, makeCoordinate(8,8));
		assertTrue(br.canMove(makeCoordinate(8,8),makeCoordinate(1,8),board));
		
	}
	@Test 
	void rookDirection4(){
		ChessPiece br = factory.makePiece(BLACKROOK);
		board.putPieceAt(br, makeCoordinate(8,8));
		assertTrue(br.canMove(makeCoordinate(8,8),makeCoordinate(8,1),board));
		
	}
	
	@Test 
	void rookBumpHorizontal(){
		ChessPiece br = factory.makePiece(BLACKROOK);
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(br, makeCoordinate(1,8));
		board.putPieceAt(wp, makeCoordinate(1,5));
		assertFalse(br.canMove(makeCoordinate(1,8),makeCoordinate(1,1),board));
	}
	@Test 
	void rookBumpVertical(){
		ChessPiece br = factory.makePiece(BLACKROOK);
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(br, makeCoordinate(1,5));
		board.putPieceAt(wp, makeCoordinate(3,5));
		assertFalse(br.canMove(makeCoordinate(1,5),makeCoordinate(4,5),board));
	}
	@Test 
	void blackRookAttackWhitePiece(){
		ChessPiece br = factory.makePiece(BLACKROOK);
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(br, makeCoordinate(8,8));
		board.putPieceAt(wp, makeCoordinate(2,8));
		assertTrue(br.canMove(makeCoordinate(8,8),makeCoordinate(2,8),board));
		
	}
	
	@Test 
	void blackRookAttackBlackPiece(){
		ChessPiece br = factory.makePiece(BLACKROOK);
		ChessPiece wp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(br, makeCoordinate(8,8));
		board.putPieceAt(wp, makeCoordinate(7,8));
		assertFalse(br.canMove(makeCoordinate(8,8),makeCoordinate(7,8),board));	
	}
	@Test 
	void whiteRookAttackBlackPiece(){
		ChessPiece wr = factory.makePiece(WHITEROOK);
		ChessPiece wp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(wr, makeCoordinate(2,3));
		board.putPieceAt(wp, makeCoordinate(2,6));
		assertTrue(wr.canMove(makeCoordinate(2,3),makeCoordinate(2,6),board));	
	}
	
	@Test 
	void whiteRookAttackWhitePiece(){
		ChessPiece wr = factory.makePiece(WHITEROOK);
		ChessPiece wp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wr, makeCoordinate(1,1));
		board.putPieceAt(wp, makeCoordinate(2,1));
		assertFalse(wr.canMove(makeCoordinate(1,1),makeCoordinate(2,1),board));	
	}
	
	@Test
	void kingMoveDirection1()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(2,6), board));
	}
	
	@Test
	void kingMoveDirection2()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(3,6), board));
	}
	@Test
	void kingMoveDirection3()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(3,5), board));
	}
	@Test
	void kingMoveDirection4()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(3,4), board));
	}
	@Test
	void kingMoveDirection5()
	{
		ChessPiece wk = factory.makePiece(BLACKKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(2,4), board));
		
	}
	@Test
	void kingMoveDirection6()
	{
		ChessPiece wk = factory.makePiece(BLACKKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(1,4), board));
	}
	@Test
	void kingMoveDirection7()
	{
		ChessPiece wk = factory.makePiece(BLACKKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(1,5), board));
	}
	
	@Test
	void kingMoveDirection8()
	{
		ChessPiece wk = factory.makePiece(BLACKKING);
		board.putPieceAt(wk, makeCoordinate(2,5));
		assertTrue(wk.canMove(makeCoordinate(2,5), makeCoordinate(1,6), board));
	}
	
	
	@Test
	void whiteKingAttackBlackPiece()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(wk, makeCoordinate(1,5));
		board.putPieceAt(bp, makeCoordinate(2,4));
		assertTrue(wk.canMove(makeCoordinate(1,5), makeCoordinate(2,4), board));
	}
	
	@Test
	void blackKingAttackWhitePiece()
	{
		ChessPiece wk = factory.makePiece(BLACKKING);
		ChessPiece bp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wk, makeCoordinate(8,5));
		board.putPieceAt(bp, makeCoordinate(7,5));
		assertTrue(wk.canMove(makeCoordinate(8,5), makeCoordinate(7,5), board));
	}
	@Test
	void whiteKingAttackWhitePiece()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		ChessPiece bp = factory.makePiece(WHITEPAWN);
		board.putPieceAt(wk, makeCoordinate(1,5));
		board.putPieceAt(bp, makeCoordinate(2,4));
		assertFalse(wk.canMove(makeCoordinate(1,5), makeCoordinate(2,4), board));
	}
	void blackKingAttackBlackPiece()
	{
		ChessPiece wk = factory.makePiece(BLACKKING);
		ChessPiece bp = factory.makePiece(BLACKPAWN);
		board.putPieceAt(wk, makeCoordinate(8,5));
		board.putPieceAt(bp, makeCoordinate(7,5));
		assertFalse(wk.canMove(makeCoordinate(8,5), makeCoordinate(7,5), board));
	}
	
	@Test
	void kingMoveMoreThan1()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		board.putPieceAt(wk, makeCoordinate(1,3));
		assertFalse(wk.canMove(makeCoordinate(1,3), makeCoordinate(3,3), board));
	}
	
	
	
	@Test
	void thisShouldFailOnDelivery()
	{
		ChessPiece wk = factory.makePiece(WHITEKING);
		board.putPieceAt(wk, makeCoordinate(1,5));
		assertTrue(wk.canMove(makeCoordinate(1,5), makeCoordinate(2,5), board));
	}

}
