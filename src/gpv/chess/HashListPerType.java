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

import java.awt.List;
import java.util.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.EnumSelector;
import gpv.chess.*;
import gpv.chess.ListOfRules.Rules;
import gpv.chess.ChessPieceDescriptor;

/**
 * Description
 * @version 6 abr. 2020
 */
public class HashListPerType
{
	HashMap<ChessPieceDescriptor, ArrayList<Rules>> hashMapOfRules;
	//White pieces 
	ArrayList<Rules> listForWhitePawn = new ArrayList<Rules>();
	ArrayList<Rules> listForWhiteKnight = new ArrayList<Rules>();
	ArrayList<Rules> listForWhiteBishop = new ArrayList<Rules>();
	ArrayList<Rules> listForWhiteRook = new ArrayList<Rules>();
	ArrayList<Rules> listForWhiteKing = new ArrayList<Rules>();
	ArrayList<Rules> listForWhiteQueen = new ArrayList<Rules>();

	


	
	//Black pieces 
	ArrayList<Rules> listForBlackKnight = new ArrayList<Rules>();
	ArrayList<Rules> listForBlackPawn = new ArrayList<Rules>();
	ArrayList<Rules> listForBlackBishop = new ArrayList<Rules>();
	ArrayList<Rules> listForBlackRook = new ArrayList<Rules>();
	ArrayList<Rules> listForBlackKing = new ArrayList<Rules>();
	ArrayList<Rules> listForBlackQueen = new ArrayList<Rules>();

	/**
	 * Cosntructor to create the hashMap of rules with all the rules
	 */
	public HashListPerType() {
		this.hashMapOfRules = new HashMap<ChessPieceDescriptor, ArrayList<Rules>>();
		//List of rules for white pawn
		this.hashMapOfRules.put(ChessPieceDescriptor.WHITEPAWN, listForWhitePawn);
		//add each rule
				this.listForWhitePawn.add(ListOfRules.whitePwanmove1);
				this.listForWhitePawn.add(ListOfRules.whitePawnMoveTwoSpots);
				this.listForWhitePawn.add(ListOfRules.whitePawnAttackDiagonal);
		//list of rules for white knigth
		this.hashMapOfRules.put(ChessPieceDescriptor.WHITEKNIGHT, listForWhiteKnight);
		//add each rule 
				this.listForWhiteKnight.add(ListOfRules.whiteKnightMoveFrontL);
				this.listForWhiteKnight.add(ListOfRules.whiteKnightMoveBackL);
				this.listForWhiteKnight.add(ListOfRules.whiteKnightMoveLeftL);
				this.listForWhiteKnight.add(ListOfRules.whiteKnightMoveRightL);
		//list of rules for white bishop 
		this.hashMapOfRules.put(ChessPieceDescriptor.WHITEBISHOP, listForWhiteBishop);
				this.listForWhiteBishop.add(ListOfRules.bishopRule);
		//list of rules for white rook 
		this.hashMapOfRules.put(ChessPieceDescriptor.WHITEROOK, listForWhiteRook);
				this.listForWhiteRook.add(ListOfRules.rookRules);
		//list of rules for white king
		this.hashMapOfRules.put(ChessPieceDescriptor.WHITEKING,listForWhiteKing);
				this.listForWhiteKing.add(ListOfRules.kingsRules);
				
				
				
				
				
		//list rules fot black pawn
		this.hashMapOfRules.put(ChessPieceDescriptor.BLACKPAWN,listForBlackPawn);
				this.listForBlackPawn.add(ListOfRules.blackPawnMove1);
				this.listForBlackPawn.add(ListOfRules.blackPawnMoveTwoSpots);
				this.listForBlackPawn.add(ListOfRules.blackPawnAttackDiagonal);

		//list of rules for black knight
		this.hashMapOfRules.put(ChessPieceDescriptor.BLACKKNIGHT,listForBlackKnight);
				this.listForBlackKnight.add(ListOfRules.blackKnightMoveFrontL);
				this.listForBlackKnight.add(ListOfRules.blackKnightMoveBackL);
				this.listForBlackKnight.add(ListOfRules.blackKnightMoveLeftL);
				this.listForBlackKnight.add(ListOfRules.blackKnightMoveRigthL);
		//list of rules for black bishop
		this.hashMapOfRules.put(ChessPieceDescriptor.BLACKBISHOP,listForBlackBishop);
				this.listForBlackBishop.add(ListOfRules.bishopRule);
		//list for black rook 
		this.hashMapOfRules.put(ChessPieceDescriptor.BLACKROOK, listForBlackRook);
				this.listForBlackRook.add(ListOfRules.rookRules);
		//list for black king 
		this.hashMapOfRules.put(ChessPieceDescriptor.BLACKKING,listForBlackKing);
				this.listForBlackKing.add(ListOfRules.kingsRules);
	}

}
