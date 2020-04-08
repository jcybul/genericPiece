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

package gpv.chess;

import gpv.util.*;



/**
 * Description
 * @version 6 abr. 2020
 */
public class ListOfRules
{
	@FunctionalInterface
	public interface Rules
	{
			boolean testRules(Coordinate from,Coordinate to,Board b);
			 
	}
	
	//General rules:
	//check that the to spot is empty:
	static Rules toSpotIsEmpty = (from,to,b) -> (ChessPiece)b.getPieceAt(to) == null;
	//check the color of the piece 
	static Rules toSpotIsBlackPiece = (from,to,b) -> (((ChessPiece)b.getPieceAt(to)).getColor() == PlayerColor.BLACK);
	static Rules toSpotIsWhitePiece = (from,to,b) -> (((ChessPiece)b.getPieceAt(to)).getColor() == PlayerColor.WHITE);
	static Rules soSpotPieceDifferentColor = (from,to,b) -> ((ChessPiece)b.getPieceAt(to)).getColor() != ((ChessPiece)b.getPieceAt(from)).getColor();
	
	
	//White Pawn rules 
	//Move forward once to an empty space
	static Rules whitePwanmove1 = ( from, to, b) -> from.distance(to) == 1 && toSpotIsEmpty.testRules(from, to, b) && from.y == to.y && to.x > from.x;
	//move twice to an empty space and it has not moved yet	
	static Rules whitePawnMoveTwoSpots = (from,to,b) -> from.y == to.y && (from.distance(to) == 2 && toSpotIsEmpty.testRules(from, to, b) &&((ChessPiece)b.getPieceAt(from)).hasMoved() == false);
	//move once diagonaly to a black piece or prevent from moving into an empty space diagonaly
	static Rules whitePawnAttackDiagonal =(from,to,b)-> (from.y != to.y && ((ChessPiece)b.getPieceAt(to)) != null) && toSpotIsBlackPiece.testRules(from, to, b);
	
	//Black Pawn rules 
	//move forward to an empty space 
	static Rules blackPawnMove1 = ( from, to, b) -> from.distance(to) == 1 && toSpotIsEmpty.testRules(from, to, b) && from.y == to.y && to.x < from.x;
	//move twice to an empty space before ever moving before
	static Rules blackPawnMoveTwoSpots = (from,to,b) -> from.y == to.y && (from.distance(to) == 2 && toSpotIsEmpty.testRules(from, to, b) &&((ChessPiece)b.getPieceAt(from)).hasMoved() == false);
	//move once diagonaly to attack a black piece 
	static Rules blackPawnAttackDiagonal =(from,to,b)-> (from.y != to.y && ((ChessPiece)b.getPieceAt(to)) != null) && toSpotIsWhitePiece.testRules(from, to, b);
	

	//whiteKnight
	//Moveforward and to the rigth or left to empty spot or a black Piece
	static Rules whiteKnightMoveFrontL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsBlackPiece.testRules(from, to, b)) && to.x == from.x + 2 && ( to.y == from.y+1 || to.y == from.y-1);
	//Move back and to the rigth or left to empty spot or black Piece 
	static Rules whiteKnightMoveBackL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsBlackPiece.testRules(from, to, b)) && to.x == from.x - 2 && ( to.y == from.y+1 || to.y == from.y-1);
	//Move to the right and up or down to an empty spot or balck Piece
	static Rules whiteKnightMoveRightL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsBlackPiece.testRules(from, to, b)) && to.y == from.y + 2 && (to.x == from.x+1 || to.x == from.x-1);
	//Move to the left and up or down to an empty spot or balck Piece
	static Rules whiteKnightMoveLeftL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsBlackPiece.testRules(from, to, b)) && to.y == from.y - 2 && (to.x == from.x+1 || to.x == from.x-1);
	
	//blackKnight
	//Move back and to the left and right  to an empty spot or white piece 
	static Rules blackKnightMoveBackL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsWhitePiece.testRules(from, to, b)) && to.x == from.x + 2 && ( to.y == from.y+1 || to.y == from.y-1);
	//Move front and to the left or rigth to an empty spot or white piece 
	static Rules blackKnightMoveFrontL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsWhitePiece.testRules(from, to, b)) && to.x == from.x - 2 && ( to.y == from.y+1 || to.y == from.y-1);
	//Move to the left and up or down to an empty spot or balck Piece
	static Rules blackKnightMoveLeftL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsWhitePiece.testRules(from, to, b)) && to.y == from.y + 2 && (to.x == from.x+1 || to.x == from.x-1);
	//Move to the Right and up or down to an empty spot or balck Piece
	static Rules blackKnightMoveRigthL = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b) || toSpotIsWhitePiece.testRules(from, to, b)) && to.y == from.y - 2 && (to.x == from.x+1 || to.x == from.x-1);
	
	
	//Bishop
	static Rules bishopRule = (from,to,b) -> (toSpotIsEmpty.testRules(from, to, b)|| soSpotPieceDifferentColor.testRules(from, to, b)) && from.diagonalIsClear(to, b);
		
	//Rook
	static Rules rookRules = (from,to,b) -> ((toSpotIsEmpty.testRules(from, to, b)) || soSpotPieceDifferentColor.testRules(from, to, b)) && from.clearOrthagonal(to, b);
	
	//King
	static Rules kingsRules = (from,to,b) -> ((toSpotIsEmpty.testRules(from, to, b)) || soSpotPieceDifferentColor.testRules(from, to, b)) && from.distance(to) == 1;
			
			
			
			
	

}
