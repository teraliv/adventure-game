/*
 * CSCI 143 - Summer 2015 
 * Assignment 4 - Adventure Game
 * MakeAnAdvanture.java
 * July 28 2015
 * Alex Terikov (teraliv@gmail.com)
 */

package adventure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class to play adventure game.
 *
 * @author Alex Terikov
 * @version 1.0
 */
public class MakeAnAdventure {

    // constants

    /** The 1st choice from menu. */
    private static final int CHOICE_ONE = 0;

    /** The 2nd choice from menu. */
    private static final int CHOICE_TWO = 1;

    /** The 3rd choice from the menu. */
    private static final int CHOICE_THREE = 2;

    /** Survived status of the game. */
    private static final int STATUS_SURVIVED = -2;

    /** Died status of the game. */
    private static final int STATUS_DIED = -1;

    /** The right side of a split string.  */
    private static final int RIGHT_SIDE = 1;

    /** The Left side of a split string. */
    private static final int LEFT_SIDE = 0;


    // fields

    /** String array that contains each line of
     * the input file containing the adventure.  */
    private String[] adventureSteps;

    /** The current index at which the reader is at (the index in adventureSteps). */
    private int currentPosition;

    /** Array to store all choice indexes. */
    private int[] choices = {0, 0, 0};


    /**
     * Constructs a new game and initializes values;
     *
     * @param adventureName The name of the game file.
     * @param numberOfLines The number of lines in the file.
     */
    public MakeAnAdventure(String adventureName, int numberOfLines) {

        adventureSteps = new String[numberOfLines];
        currentPosition = 0;

        openGameFile(adventureName, numberOfLines);
    }

    /**
     * The starter method for the game.
     */
    public void startAdvanture() {
        parseLine(adventureSteps[currentPosition]);
    }

    /**
     * This method parses the string line and displays the information
     * from this line to a user.
     *
     * @param theLine The string line from array.
     */
    private void parseLine(String theLine) {

        // Current line to split
        String[] currentLine = theLine.split(" : ");

        // Location description
        String locationDescription = currentLine[LEFT_SIDE];

        // All available choices
        String allOptions[] = currentLine[RIGHT_SIDE].split("\\| ");

        System.out.println(locationDescription);


        // Here we split a choice description and get its index number
        // as well as track the win/lose status of the game
        for (int choice = 0; choice < allOptions.length; choice++) {

            // get the choice description, index number, and remove whitespace on the sides
            String currentChoice[] = allOptions[choice].trim().split(" = ");

            // convert a number represented as a String value to integer
            int choiceNumber = Integer.parseInt(currentChoice[RIGHT_SIDE]);

            choices[choice] = choiceNumber;


            // handle dead status
            if (choiceNumber == STATUS_DIED) {
                System.out.println(currentChoice[LEFT_SIDE]);
                return;

            // handle survived status
            } else if (choiceNumber == STATUS_SURVIVED) {
                System.out.println(currentChoice[LEFT_SIDE]);
                return;

            // display choices
            } else {
                System.out.println(choice + ": " + currentChoice[LEFT_SIDE]);
            }
        }

        usersChoice();
    }

    /**
     * This method prompts a user to make a selection choice.
     */
    private void usersChoice() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your choice: ");

        int userSelection = input.nextInt();

        switch (userSelection) {
            case CHOICE_ONE:
                System.out.println();
                parseLine(adventureSteps[choices[CHOICE_ONE]]);
                break;

            case CHOICE_TWO:
                System.out.println();
                parseLine(adventureSteps[choices[CHOICE_TWO]]);
                break;

            case CHOICE_THREE:
                System.out.println();
                parseLine(adventureSteps[choices[CHOICE_THREE]]);
                break;

            default:
                System.out.println();
                System.out.println("Wrong Choice!");
                break;
        }

        input.close();
    }

    /**
     * Helper method to open game file and make a game array.
     *
     * @param adventureName The name of the file.
     * @param numberOfLines	The number of lines in the file.
     */
    private void openGameFile(String adventureName, int numberOfLines) {
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new FileInputStream(adventureName));

            // get the current line from the file and put it to the array
            for (int i = 0; i < numberOfLines; i++) {
                adventureSteps[i] = fileScanner.nextLine();
            }

        } catch (FileNotFoundException ex) {
            System.err.println("En error occured while opening the file " + ex.getMessage());

        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }
}
