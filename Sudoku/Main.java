package Sudoku;

import Sudoku.model.*;
import java.util.Scanner;

/**
* This class contains the main method for creating a sudoku game.
* The player can type in commands to navigate through the game field. 
*
* @author Chris-Bennet Fleger {@literal <}chris.fleger@gmx.de{@literal >}
* @version 2020 May 16
*/
public class Main{
	
	
	
	
	public static void main(String[] args){
		
		byte [][] sudoku1 = {
			{ -1 , 6 , -1 , -1 , -1 , -1 , 4 , 2 , 5} ,
			{ 5 , 7 , -1 , -1 , -1 , -1 , -1 , 8 , 1} ,
			{ -1 , -1 , -1 , 4 , 3 , -1 , 9 , -1 , -1} ,
			{ -1 , 5 , -1 , 9 , 2 , -1 , -1 , 7 , 4} ,
			{ -1 , -1 , -1 , 3 , 8 , 4 , -1 , -1 , -1} ,
			{ 8 , 4 , -1 , 5 , 6 , 7 , -1 , 9 , -1} ,
			{ -1 , -1 , 2 , -1 , 1 , -1 , -1 , -1 , -1} ,
			{ -1 , 3 , 9 , -1 , -1 , 6 , 7 , -1 , 8} ,
			{ -1 , -1 , -1 , -1 , -1 , 9 , 6 , -1 , -1},
		};
		
		SudokuField sudoku = new SudokuField(sudoku1);
		System.out.println(sudoku.str());
		
		while (true){
		   
			System.out.println("Command:");
		   
			Scanner input = new Scanner(System.in);
			String s = input.nextLine();
			String[] parts = s.split(" ");
			
			int count = parts.length;
			int i = 0;
			int j = 0;
			int value = 0;
			
			String part1 = parts[0];
			String part2 = "";
			String part3 = "";
			String part4 = "";
			
			if (count == 3){
				part2 = parts[1];
				part3 = parts[2];
			}
			
			if (count == 4){
				part2 = parts[1];
				part3 = parts[2];
				part4 = parts[3];
			} 
			
			switch(part1){
			case "q": 
				System.exit(2);
			case "quit":
				System.exit(2);
			case "exit":
				System.exit(2);
			case "mark":
				
				if(count != 4){ 									
					System.out.println("\nWrong use of 'mark' -> 'mark [row] [column] [value]'\n");
					break;
				}
				i = Integer.parseInt(part2);
				j = Integer.parseInt(part3);
				value = Integer.parseInt(part4);
				
				sudoku.addSub(i,j,value);
				
				break;
				
			case "unmark":
				
				if(count != 4){ 									
					System.out.println("\nWrong use of 'unmark' -> 'unmark [row] [column] [value]'\n");
					break;
				}
				i = Integer.parseInt(part2);
				j = Integer.parseInt(part3);
				value = Integer.parseInt(part4);
				
				sudoku.removeSub(i,j,value);
				
				break;
				
			case "viewmarks": 
			
				if(count != 3){ 									
					System.out.println("\nWrong use of 'viewmarks' -> 'viewmarks [row] [column]'\n");
					break;
				}
				i = Integer.parseInt(part2);
				j = Integer.parseInt(part3);
				sudoku.printSub(i,j);
				
				break;
			
			case "enter":
			
				if(count != 4){ 									
					System.out.println("\nWrong use of 'enter' -> 'enter [row] [column] [value]'\n");
					break;
				}
				
				i = Integer.parseInt(part2);
				j = Integer.parseInt(part3);
				value = Integer.parseInt(part4);
				if(sudoku.valid_entry(i,j,value)){
					sudoku.setField(i,j,value);
					System.out.println(sudoku.str());
				}else{
					System.out.println("Invalid entry.");
				}
				
				break;
				
				
			default: 
				System.out.println("\nCommand not found\n");
            }
        }
	}
}