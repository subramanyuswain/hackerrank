package com.learn;

import java.awt.Point;
import java.util.Scanner;

public class MegaMaid {

	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	public static final String LEFT = "LEFT";
	public static final String RIGHT = "RIGHT";
	public static final String DEFAULT_DIRECTION = "RIGHT";
	public static final String TRASH = "d";
	public static final String CLEAN = "-";
	public static final String BOT = "b";
	public static final int FIRST_COLUMN = 0;

	public static void main(String[] args) {
		// Read input from console
		Scanner sc = new Scanner(System.in);
		int bot_x = sc.nextInt();
		int bot_y = sc.nextInt();
		Point botPosition = new Point(bot_x, bot_y);

		int height = sc.nextInt();
		int width = sc.nextInt();
		String[][] datagrid = new String[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				datagrid[i][j] = sc.next();
			}
		}
		sc.close();
		// Scan for Trash in each column at a time
		scan(botPosition, datagrid, height, width);
		// Clean the trash
	}

	static void scan(Point bot_position, String[][] datagrid, int height, int width) {
		Point currentPositionOfBot = bot_position;
		for (int i = 0; i < width; i++) {
			int Y = currentPositionOfBot.y;
			for (int j = 0; j < height; j++) {
				String data = datagrid[j][Y];
				if (data.equals(TRASH)) {
					int verticalDiference = j - currentPositionOfBot.x;
					currentPositionOfBot = moves(datagrid, height, width, currentPositionOfBot, verticalDiference);
				}
			}
			currentPositionOfBot = move(datagrid, height, width, currentPositionOfBot, DEFAULT_DIRECTION);
		}
	}

	static Point move(String[][] datagrid, int height, int width, Point bot_position, String direction) {
		Point botNewLocation = new Point();
		int X = bot_position.x;
		int Y = bot_position.y;
		switch (direction) {
		case UP: {
			if (X == 0) {
				throw new InvalidMoveException(UP);
			} else {
				botNewLocation.setLocation(X - 1, Y);
				datagrid[X][Y] = CLEAN;
				datagrid[botNewLocation.x][botNewLocation.y] = BOT;
			}
		}
			break;
		case DOWN: {
			if (X == height - 1) {
				throw new InvalidMoveException(DOWN);
			} else {
				botNewLocation.setLocation(X + 1, Y);
				datagrid[X][Y] = CLEAN;
				datagrid[botNewLocation.x][botNewLocation.y] = BOT;
			}
		}
			break;
		case LEFT: {
			if (Y == 0) {
				throw new InvalidMoveException(LEFT);
			} else {
				botNewLocation.setLocation(X, Y - 1);
				datagrid[X][Y] = CLEAN;
				datagrid[botNewLocation.x][botNewLocation.y] = BOT;
			}
		}
			break;
		case RIGHT: {
			if (Y == width - 1) {
				botNewLocation.setLocation(X, FIRST_COLUMN);
				datagrid[X][Y] = CLEAN;
				datagrid[botNewLocation.x][botNewLocation.y] = BOT;
			} else {
				botNewLocation.setLocation(X, Y + 1);
				datagrid[X][Y] = CLEAN;
				datagrid[botNewLocation.x][botNewLocation.y] = BOT;
			}
		}
			break;
		default: {
			if (Y == width - 1) {
				botNewLocation.setLocation(X, FIRST_COLUMN);
				datagrid[X][Y] = CLEAN;
				datagrid[botNewLocation.x][botNewLocation.y] = BOT;
			} else {
				botNewLocation.setLocation(X, Y + 1);
				datagrid[X][Y] = CLEAN;
				datagrid[botNewLocation.x][botNewLocation.y] = BOT;
			}
		}
		}
		printGrid(datagrid, height, width);
		System.out.println("*********************************");
		return botNewLocation;
	}

	static Point moves(String[][] datagrid, int height, int width, Point bot_position, int verticalDiference) {
		Point currentBotPosition = new Point(bot_position);
		Point result = new Point();
		if (verticalDiference == 0)
			result.setLocation(move(datagrid, height, width, currentBotPosition, DEFAULT_DIRECTION));
		if (verticalDiference > 0) {
			for (int i = 0; i < verticalDiference; i++) {
				currentBotPosition = move(datagrid, height, width, currentBotPosition, DOWN);
				if (i == verticalDiference - 1)
					result.setLocation(currentBotPosition);
			}
		} else if (verticalDiference < 0) {
			int absoluteVerticalDiff = Math.abs(verticalDiference);
			for (int i = 0; i < absoluteVerticalDiff; i++) {
				currentBotPosition = move(datagrid, height, width, currentBotPosition, UP);
				if (i == absoluteVerticalDiff - 1)
					result.setLocation(currentBotPosition);
			}
		}
		return result;
	}

	static void printGrid(String[][] dataGrid, int height, int width) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(dataGrid[i][j]+" ");
			}
			System.out.println();
		}
	}

}

class InvalidMoveException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMoveException(String e) {
		super(e);
		System.out.println(" Invalid Move : Moving " + e + " is not Possible. ");
	}
}
