/*
 * CSCI 143 - Summer 2015 
 * Assignment 4 - Adventure Game
 * AdventureTest.java
 * July 28 2015
 * Alex Terikov (teraliv@gmail.com)
 */

package adventure;

/**
 * A driver class to create the adventure game.
 *
 * @author Alex Terikov
 * @version 1.0
 */
public class AdventureTest {

    /**
     * The main method to start the game.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        MakeAnAdventure adventureGame = new MakeAnAdventure("adventures/zombies.txt", 24);
        adventureGame.startAdvanture();
    }
}
