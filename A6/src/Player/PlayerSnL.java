/**
 * @file PlayerSnL.java
 * @author Yangfan Jiang - A5, Casey Denner - A6
 * @date 29/03/2015
 * @see Player.java
 * @brief Creates a player for Snakes and Ladders
 */

package Player;

import java.awt.Color;
import java.util.ArrayList;

import Display.DisplaySnL;
import Menu.GameSelector;
import Square.Square;
import Square.SquareSnL;

/**
 * @class PlayerSnL
 * @brief Creates a player for Snakes and Ladders
 */
public abstract class PlayerSnL extends Player {

	/** The piece initial location of the player */
	public int m_playerLocation = 1;

	/** The piece color of the player */
	public Color m_playerColor;

	/** To check if piece is on the destination square or not */
	public boolean m_isMovementOnSquare;

	/** Last location of player */
	public int m_lastLocation;

	/** Array of positions */
	public ArrayList<Integer> m_allLocations = new ArrayList<Integer>();

	/**
	 * get the current piece location
	 * 
	 * @return current piece location
	 */
	public int getPlayerLocation() {
		if(GameSelector.m_TRACE){
			System.out.println("PlayerSnL::getPlayerLocation()  "
					+ "get player piece location: "+m_playerLocation);
		}
		return m_playerLocation;
	}

	/**
	 * get the piece color of player
	 * 
	 * @return piece color
	 */
	public Color getPlayerColor() {
		if(GameSelector.m_TRACE){
			System.out.println("PlayerSnL::getPlayerColor()  "
					+ "get player piece lcolor: "+m_playerColor);
		}
		return m_playerColor;
	}

	/**
	 * if destination square is in the bound then move the piece to destination
	 * square
	 * 
	 * @param displaySnL
	 * is current all game information
	 * @param s is destination square
	 * @param isMovementSquare to check if the piece is on the destination 
	 * square or not
	 * @return true if piece gets to destination square, or false if the 
	 * destination is out of bound
	 */
	public boolean setPlayerLocation(DisplaySnL displaySnL, Square dest,
			boolean isMovementSquare) {
		if (dest.getPosition() <= 100 && dest.getPosition() >= 0) {
			if (dest.getPosition() > m_playerLocation) {
				if (GameSelector.m_TRACE) {
					System.out.println(dest.getPosition()+" "+m_playerLocation);
				}
				if(isMovementSquare){
					m_playerLocation = dest.getPosition(); 
				}else{
					for (int i = m_playerLocation; i < dest.getPosition(); i++){
						m_playerLocation = i + 1;
						if (GameSelector.m_TRACE) {
							System.out.println("Player Location is now: "
									+ m_playerLocation);
						}
						displaySnL.updateGraphics();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}
					}
				}
			} else {
				if (GameSelector.m_TRACE) {
					System.out.println(dest.getPosition()+" "+m_playerLocation);
				}
				if(isMovementSquare){
					m_playerLocation = dest.getPosition(); 
				}else{
					for (int i = m_playerLocation;
							i > dest.getPosition(); i--) {
						m_playerLocation = i - 1;
						if (GameSelector.m_TRACE) {
							System.out.println("Player Location is now: "
									+ m_playerLocation);
						}
						displaySnL.updateGraphics();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
						}
					}
				}
			}
			if(GameSelector.m_TRACE){
				System.out.println("PlayerSnL::setPlayerLocation()  "
						+ "set player piece location: "+ dest +
						"and check the move is true");
			}
			return true;

		} else {
			if (GameSelector.m_TRACE) {
				System.out.println("Square not within bounds");
			}
			if(GameSelector.m_TRACE){
				System.out.println("PlayerSnL::setPlayerLocation()  "
						+ "set player piece location: "+ dest +
						"and check success is false");
			}
			return false;
		}
	}

	/** 
	 * sets the last location of the player 
	 * @param playerLocation
	 */
	public void setLastLocation(int playerLocation) {
		m_allLocations.add(playerLocation);
		m_lastLocation = playerLocation;
	}

	/** 
	 * returns the last location of the player
	 * @return m_lastLocation
	 */
	public int getLastLocation(){
		return m_lastLocation;
	}

	/** Returns the arraylist containing all the player locations */
	public ArrayList<Integer> getAllLocations(){
		return m_allLocations;
	}

	/**
	 * check if the piece has moved to destination square
	 * 
	 * @param s is destination square
	 * @return true if piece gets to destination square, or false if not
	 */
	public boolean isMovementSquare(SquareSnL s) {
		m_isMovementOnSquare = s.isMovementSquare();
		if(GameSelector.m_TRACE){
			System.out.println("PlayerSnL::isMovementSquare()  "
					+ "check if player piece location: "+s+
					" is  destination square that is "+m_isMovementOnSquare);
		}
		return m_isMovementOnSquare;		
	}
}
