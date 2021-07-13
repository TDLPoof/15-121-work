import java.awt.*;
import java.util.Arrays;

public class ShutTheBox
{

	//box states
	private static final int OPEN = 323;
	private static final int WILL_SHUT = 548;
	private static final int ALREADY_SHUT = 719;

	// helpers

	public static void openAllBoxes(int[] boxStates) {
		for (int i = 0; i < boxStates.length; i++) {
			boxStates[i] = OPEN;
		}
	}

	public static int rollDice() {
		return (int)((Math.random() * 6) + 1);
	}

	public static void copy(int[] template, int[] into) {
		for (int i = 0; i < template.length; i++) {
			into[i] = template[i];
		}
	}

	public static int max(int[] array) {
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {max = array[i];}
		}
		return max;
	}

	// game methods

	public static void main(String[] args) {
		//sample code to run the game
		int[] boxValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		play(boxValues, 2);
	}

	public static void play(int[] boxValues, int numDice) {
		int[] boxStates = new int[boxValues.length];
		int[] dice = new int[numDice];
		int[] tallies;

		//initialize boxStates, dice, and tallies here

	    openAllBoxes(boxStates);
	    for (int i = 0; i < numDice; i++) {
			dice[i] = rollDice();
		}

	    tallies = new int[46];

		gameController(boxValues, boxStates, dice, tallies);
	}

	//plays the game, beginning from the given game state
	private static void gameController(int[] boxValues, int[] boxStates, 
			int[] dice, int[] tallies) {
		//create display
		Grid display = new Grid(2, boxValues.length);
		display.setTitle("Shut the Box");

		for (int col = 0; col < boxValues.length; col++) {
			display.setColor(0, col, new Color(0, 100, 0));
			display.setColor(1, col, new Color(0, 100, 0));
		}
		display.setImage(1, 0, "newgame.gif");
		updateDisplay(display, boxValues, boxStates, dice);

		//main game loop
		while (true) {
			//pause 100 milliseconds to wait for user click
			try { Thread.sleep(100); } catch (Exception e) {}

			//check if the user clicked
			int[] click = display.checkMouse();
			if (click != null) {
				//the user clicked
				int row = click[0];
				int col = click[1];

				//process click on (row, col)
				if (row == 0)
					clickedOnBox(boxStates, col);
				else if (col >= boxValues.length - dice.length)
					clickedOnDice(boxValues, boxStates, dice);
				else if (col == 0)
					clickedNewGame(boxValues, boxStates, dice, tallies);

				//update display to show correct box states and dice
				updateDisplay(display, boxValues, boxStates, dice);
			}
		}
	}

	private static void clickedOnBox(int[] boxStates, int index) {

		if (boxStates[index] == OPEN) {
			boxStates[index] = WILL_SHUT;
		}
		else if (boxStates[index] == WILL_SHUT) {
			boxStates[index] = OPEN;
		}
		
	}

	private static void clickedOnDice(int[] boxValues, int[] boxStates, int[] dice) {
		int boxSums = 0;
		for (int i = 0; i < boxValues.length; i++) {
			if (boxStates[i] == WILL_SHUT) {
				boxSums += boxValues[i];
			}
		}
		if (boxSums != Arrays.stream(dice).sum()) {
			for (int i = 0; i < boxValues.length; i++) {
				if (boxStates[i] == WILL_SHUT)
				{
					boxStates[i] = OPEN;
				}
			}
		}
		if (boxSums == Arrays.stream(dice).sum()) {
			for (int i = 0; i < boxValues.length; i++) {
				if (boxStates[i] == WILL_SHUT)
				{
					boxStates[i] = ALREADY_SHUT;
				}
			}
			for (int i = 0; i < dice.length; i++) {
				dice[i] = rollDice();
			}
		}
	}

	private static void clickedNewGame(int[] boxValues, int[] boxStates, 
			int[] dice, int[] tallies) {

		// update and print scores
		tallies[remainingSum(boxValues, boxStates)]++;
		int freqValue = max(tallies);
		System.out.print("Most frequent scores: ");

		for (int i = 0; i < tallies.length; i++) {
			if (tallies[i] == freqValue) {
				System.out.print(i + " ");
			}
		}
		System.out.println();

		// reset game
		openAllBoxes(boxStates);
		for (int i = 0; i < dice.length; i++) {
			dice[i] = rollDice();
		}
	}

	// return the sum of the pips on the remaining boxes, including the boxes tried to shut
	private static int remainingSum(int[] boxValues, int[] boxStates) {
		int counter = 0;
		for (int i = 0; i < boxValues.length; i++) {
			if (boxStates[i] != ALREADY_SHUT) {
				counter += boxValues[i];
			}
		}
		return counter;
	}

	//updates the display to show correct box states and dice based on given game state
	private static void updateDisplay(Grid display, int[] boxValues, 
			int[] boxStates, int[] dice) {

		for (int i = 0; i < boxValues.length; i++) {
			String image;
			if (boxStates[i] == OPEN) {
				image = "box" + boxValues[i] + ".png";
			} else if (boxStates[i] == WILL_SHUT || boxStates[i] == ALREADY_SHUT) {
				image = "shut.png";
			} else {
				throw new RuntimeException("Invalid state of box " + i + ":  " 
						+ boxStates[i]);
			}
			display.setImage(0, i, image);
		}

		int remainingSum = remainingSum(boxValues, boxStates);    
		if (remainingSum == 0) {
			display.setTitle("Shut The Box:  YOU WIN!!!!!!!!!!");
		} else {
			display.setTitle("Shut The Box:  " + remainingSum + " points remaining");
		}

		for (int i = 0; i < dice.length; i++) {
			if (dice[i] < 1 || dice[i] > 6) {
				throw new RuntimeException("Invalid value of die " + i + ":  " + dice[i]);
			}
			String image;
			if (remainingSum == 0) {
				image = null;
			} else {
				image = "die" + dice[i] + ".gif";
			}
			display.setImage(1, boxValues.length - dice.length + i, image);
		}
	}
}
