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

package gpv.util;

import java.awt.Point;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * This class represents a two-dimensional coordinate that would be
 * used in a rectangular board. No assumptions are made about the actual
 * mapping of coordinate to the squares on the board.
 * <br/>
 * There are equals() and hashCode() methods so the Coordinate can be
 * used as keys in collections that use hashing (e.g. HashMap) and a
 * toString() to print the coordinate in some readable form. This is
 * useful for debugging.
 * 
 * @version Feb 21, 2020
 */
public class Coordinate extends Point
{
	
	/**
	 * The only constructor. It is private to avoid any client from
	 * creating one for a purpose not intended.
	 * 
	 * @param row
	 * @param column
	 */
	private Coordinate(int row, int column)
	{
		this.x = row;
		this.y = column;
	}
	
	/**
	 * Factory method. This only creates a Coordinate if it hasn't been created
	 * already.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public static Coordinate makeCoordinate(int row, int column)
	{
		return new Coordinate(row, column);
	}
	
	/**
	 * given a destination coordinate calculate the distance to that 
	 * destination 
	 * @param to is a coordinate to find distance 
	 * @return the distance to that coordinate
	 */
	public int distance(Coordinate to){
		
		int r = Math.abs(this.x - to.x);
		int c = Math.abs(this.y - to.y);
		if(c > r) {
			return c;
		}
		else 
			return r;	
	}
	
	public boolean diagonalIsClear(Coordinate to,Board b) {
		
		if(!this.sameDiagonal(to)) {
			return false;
		}
		else {
			//when the destination is larger both in X and Y
			if(this.x < to.x && this.y < to.y) {
				int xpos = this.x+1;
				int ypos = this.y+1;
				for(int i = 0;i < this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos++;
					ypos++;
			
				}
				
			}
			//when the destination is larger in x and not in y 
			else if(this.x < to.x && this.y > to.y) {
				int xpos = this.x+1;
				int ypos = this.y-1;
				for(int i = 0;i < this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos++;
					ypos--;
				}	
			}
			//when they are both less in the destination 
			else if(this.x > to.x && this.y > to.y) {
				int xpos = this.x-1;
				int ypos = this.y-1;
				for(int i = 0;i < this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos--;
					ypos--;
				}	
			}
			//when the y is larger but the x is not
			else if(this.x > to.x && this.y < to.y) {
				int xpos = this.x-1;
				int ypos = this.y+1;
				for(int i = 0;i < this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,ypos))!= null){
						return false;
					}
					xpos--;
					ypos++;
				}	
			}	
		}
		
		return true;
	}
	
	
	/**
	 * Check if two points are in the same diagonal 
	 * @param to
	 * @return true if points are in the same diagonl else return false 
	 */
	public boolean sameDiagonal(Coordinate to) {
		
		if(Math.abs((this.x - to.x)) == Math.abs((this.y - to.y))){
			return true;
		}
		else return false;
		

	}
	
	/**
	 * Description check if coordinates are orthogonal
	 * @param to coordinate 
	 * @return true if coordinates are alligned orthognaly else false 
	 */
	public boolean sameOrthogonal(Coordinate to) {
		if(this.x == to.x && this.y != to.y ) {
			return true;
		}
		else if(this.y == to.y && this.x != to.x) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Description calculate if the orthogonal is clear
	 * @param to Coordinate to destination
	 * @param b  board 
	 * @return true if the orthogonal is clear
	 */
	public boolean clearOrthagonal(Coordinate to,Board b) {
		if(!this.sameOrthogonal(to)) {
			return false;
		}
		else{
			
			if(this.x == to.x && this.y < to.y) {
				int ypos = this.y+1;
				for(int i = 0; i< this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(this.x,ypos))!= null){
						return false;
					}
					ypos++;
				}
			}
			else if(this.x == to.x && this.y> to.y) {
				int ypos = this.y-1;
				for(int i = 0; i< this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(this.x,ypos))!= null){
						return false;
					}
					ypos--;
				}
			}
			else if(this.y == to.y && this.x < to.x) {
				int xpos = this.x+1;
				for(int i = 0; i< this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,this.y))!= null){
						return false;
					}
					xpos++;
				}
			}
			else if(this.y == to.y && this.x > to.x) {
				int xpos = this.x-1;
				for(int i = 0; i< this.distance(to)-1;i++) {
					if(b.getPieceAt(makeCoordinate(xpos,this.y))!= null){
						return false;
					}
					xpos--;
				}
			}
			
		}
		return true;
	}
	
	/**
	 * @return the row
	 */
	public int getRow()
	{
		return this.x;
	}
	
	/**
	 * @return the column
	 */
	public int getColumn()
	{
		return this.y;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		return true;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
