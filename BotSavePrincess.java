package com.learn;

import java.awt.Point;
import java.util.Scanner;

public class BotSavePrincess {

	   public static final String LEFT = "LEFT";
		public static final String RIGHT = "RIGHT";
		public static final String UP = "UP";
		public static final String DOWN = "DOWN";

		public static void main(String[] args) {
			int i,j;
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			String[][] dataset = new String[n][n];
			for(i = 0; i < n; i++) {
				for(j = 0; j < n; j++) {
					dataset[i][j] = sc.next();
				}
			}
			sc.close();
			print(n, dataset);
			findPath(n, dataset);
		}
		
		static void print(int n, String s[][]) {
			int i,j;
			for(i=0;i<n;i++) {
				for(j=0;j<n;j++) {
					System.out.print(s[i][j]);
				}
				System.out.println();
			}
		}
		
		static Position findPosition(int n, String[][] dataset){
			Position output ;
			Point bot = new Point();
			Point princess = new Point();
			int i,j;
			for(i=0;i<n;i++) {
				for(j=0;j<n;j++) {
					if(dataset[i][j].equals("p"))
						princess.setLocation(i, j);
					else if(dataset[i][j].equals("m"))
						bot.setLocation(i, j);
				}
			}
			output = new Position(bot, princess, n, n);
			System.out.println("bot = "+bot.toString()+" , princess = "+princess.toString());
			return output;
			
		}
		
		static void findPath(int n, String[][] dataset) {
			Position p = findPosition(n, dataset);
			Point bot = p.getBot();
			Point princess = p.getPrincess();
			
			System.out.println("bot = "+bot.toString()+" , princess = "+princess.toString());
			int verticalDiff = princess.x - bot.x;
			int horizontalDiff = princess.y - bot.y;
			
			System.out.println("verticalDiff = "+verticalDiff);
			System.out.println("horizontalDiff = "+horizontalDiff);
			
			if(verticalDiff > 0 && horizontalDiff != 0) {
				loop(verticalDiff , DOWN);
				if(horizontalDiff < 0) {
					loop(horizontalDiff, LEFT);
				}
				else if(horizontalDiff > 0) {
					loop(horizontalDiff, RIGHT);
				}
			}
			
			else if(verticalDiff < 0 && horizontalDiff != 0) {
				loop(verticalDiff , UP);
				if(horizontalDiff < 0) {
					loop(horizontalDiff, LEFT);
				}
				else if(horizontalDiff > 0) {
					loop(horizontalDiff, RIGHT);
				}
			}
			//if both are in the same row
			else if(verticalDiff == 0) {
				if(horizontalDiff > 0) {
					loop(verticalDiff, RIGHT);
				}
				else if(horizontalDiff < 0) {
					loop(verticalDiff, LEFT);
				}
			}
			//if both are in the same column
			else if(horizontalDiff == 0) {
				if(verticalDiff < 0) {
					loop(verticalDiff, UP);
				}
				else if(verticalDiff > 0) {
					loop(verticalDiff, DOWN);
				}
			}
		}
		
		
		static void loop(int diff, String direction) {
			int dif = Math.abs(diff);
			for(int i=0; i<dif; i++)
				System.out.println(direction);
		}
	}

	class Position{
		int rows;
		int cols;
		Point bot ;
		Point princess ;
		Position(){
		}
		Position(Point bot, Point princess, int rows, int cols){
			this.bot = bot;
			this.princess = princess;
			this.rows = rows;
			this.cols = cols;
		}
		public Point getBot() {
			return bot;
		}
		public void setBot(Point bot) {
			this.bot = bot;
		}
		public Point getPrincess() {
			return princess;
		}
		public void setPrincess(Point princess) {
			this.princess = princess;
		}
		public int getRows() {
			return rows;
		}
		public void setRows(int rows) {
			this.rows = rows;
		}
		public int getCols() {
			return cols;
		}
		public void setCols(int cols) {
			this.cols = cols;
		}
	}
