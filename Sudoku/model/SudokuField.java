package Sudoku.model;

/**
* This class represents a Sudoku game field.
* This class contains methods for creating a sudoku game field as well as entering an marking numbers.
*
* @author Chris-Bennet Fleger {@literal <}chris.fleger@gmx.de{@literal >}
* @version 2020 May 17
*/
public class SudokuField{
	
	/**
	* A field is being represented by a byte matrix.
	* Package private since it's not needed to be used outside.
	*/
	byte [][] field;
	
	/**
	* A sub_field is a hidden array int matrix and allows storage of marker numbers.
	* Package private since it's not needed to be used outside.
	*/
	int [][][] sub_field;
	
	/**
	* Constructor method for a Sudoku field.
	* A field needs to be of size 9x9. If that's true, a field will be created alongside a subfield
	* of the same dimension. Needs to be accessed in main method. (->public)
	*
	* @param field byte[][] 
	*/
	public SudokuField(byte[][] field){
		if(field.length == 9){
			for(int i=0;i<9;i++){
				if(field[i].length != 9){
					System.err.println("Wrong field size.");
					System.exit(2);
				}
			}	
			this.field = field;
			this.sub_field = new int[9][9][9];
		} else {
			System.err.println("Wrong field size.");
			System.exit(2);
		}
	}
	
	/**
	* Getter for a Sudoku field tile.
	* Checks whether coordinates are valid. Needs to be accessed in main method. (->public)
	*
	* @param i row
	* @param j column
	* @return field int 
	*/
	public int getField(int i, int j){
		if(0 <= i && i <= 8 && 0 <= j && j <= 8){
			return this.field[i][j];
		}else{
			System.out.println("ungueltiges Feld");
			return -2;
		}
	}
	
	/**
	* This method checks if an entry is valid.
	* Needs to be accessed in main method. (->public)
	*
	* @param i row
	* @param j column
	* @param value Integer 
	*/
	public boolean valid_entry(int i, int j, Integer value){
		
		byte val = value.byteValue();
		if(0 <= i && i <= 8 && 0 <= j && j <= 8 && value >= 0 && value <= 9){
			
			for(int x=0;x<9;x++){
				if(this.field[i][x] == val){		//check row
					return false;
				}
				if(this.field[x][j] == val){ 		//check column 
					return false;
				}
			}
			
			if(0 <= i && i <= 2 && 0 <= j && j <= 2){ 		//check block 1
				for(int x=0;x<3;x++){
					for(int y=0;y<3;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(0 <= i && i <= 2 && 3 <= j && j <= 5){		//check block 2
				for(int x=0;x<3;x++){
					for(int y=3;y<6;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(0 <= i && i <= 2 && 6 <= j && j <= 8){		//check block 3
				for(int x=0;x<3;x++){
					for(int y=0;y<9;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(3 <= i && i <= 5 && 0 <= j && j <= 2){		//check block 4
				for(int x=3;x<6;x++){
					for(int y=0;y<3;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(3 <= i && i <= 5 && 3 <= j && j <= 3){		//check block 5
				for(int x=3;x<6;x++){
					for(int y=3;y<6;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(3 <= i && i <= 5 && 6 <= j && j <= 8){		//check block 6
				for(int x=3;x<6;x++){
					for(int y=6;y<9;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(6 <= i && i <= 8 && 0 <= j && j <= 2){		//check block 7
				for(int x=6;x<9;x++){
					for(int y=0;y<3;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(6 <= i && i <= 8 && 3 <= j && j <= 5){		//check block 8
				for(int x=6;x<9;x++){
					for(int y=3;y<6;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
			
			if(6 <= i && i <= 8 && 6 <= j && j <= 8){		//check block 9
				for(int x=6;x<9;x++){
					for(int y=6;y<9;y++){
						if(this.field[x][y] == val){
							return false;
						}	
					}
				}
			}
				
			return true;
			
		}else{
			return false;
		}
	}
	
	
	/**
	* Setter for a Sudoku field tile.
	* Needs to be accessed in main method. (->public)
	*
	* @param i row
	* @param j column
	* @param value Integer 
	*/
	public void setField(int i, int j, Integer value){
		if(value == 0){
			this.field[i][j] = -1;
		}else{
			byte b = value.byteValue();
			this.field[i][j] = b;
		}
	}
	
	/**
	* This method creates a hidden field entry (marker).
	* Checks if coordinates are valid and if field tile has alreaby been marked by the same number.
	* Needs to be accessed in main method. (->public)
	*
	* @author Chris-Bennet Fleger {@literal <}chris.fleger@gmx.de{@literal >}
	* @param i row
	* @param j column
	* @param value int
	*/
	public void addSub(int i, int j, int value){
		if(0 <= i && i <= 8 && 0 <= j && j <= 8 && value >= 1 && value <= 9){
			for(int x =0;x<9;x++){
				if(this.sub_field[i][j][x] == value){
					System.out.printf("Value %d was already marked for (%d:%d)\n",value,i,j);
					break;
				}
				if(this.sub_field[i][j][x] == 0){
					this.sub_field[i][j][x] = value;
					System.out.printf("Marked value %d for (%d:%d)\n",value,i,j);
					break;
				}
			}
		}
	}
	
	/**
	* This method prints the marked numbers of a field tile.
	* Checks if coordinates are valid. The String reflects the array representation.
	* Needs to be accessed in main method. (->public)
	*
	* @author Chris-Bennet Fleger {@literal <}chris.fleger@gmx.de{@literal >}
	* @param i row
	* @param j column
	*/
	public void printSub(int i, int j){
		
		String s = "[";
		boolean toggle = false;
		
		if(0 <= i && i <= 8 && 0 <= j && j <= 8){
			
			for(int x=0;x<9;x++){
				
				if(this.sub_field[i][j][x] != 0){
					int current = this.sub_field[i][j][x];
					s += Integer.toString(current) + ", ";
					toggle = true;
				}
				
			}
			
			if(toggle) s = s.substring(0, s.length()-2);
			s += "]";
			System.out.println(s);
			
		}else{
			System.out.println("Invalid field slot");
		}
	}
	
	/**
	* This method removes marked numbers from a field tile.
	* Checks if coordinates are valid. Needs to be accessed in main method. (->public)
	*
	* @author Chris-Bennet Fleger {@literal <}chris.fleger@gmx.de{@literal >}
	* @param i row
	* @param j column
	* @param value int 
	*/
	public void removeSub(int i, int j, int value){
		if(0 <= i && i <= 8 && 0 <= j && j <= 8){
			
			for(int x=0;x<9;x++){
				if(this.sub_field[i][j][x] == value){
					this.sub_field[i][j][x] = 0;
					System.out.printf("Unmarked value %d for (%d:%d)\n",value,i,j);
					break;
				}
			}
		
		}else{
			System.out.println("Invalid field slot");
		}
	}
	
	/**
	* This method returns a String representation of the sudoku field.
	* Needs to be accessed in main method. (->public)
	*
	* @author Chris-Bennet Fleger {@literal <}chris.fleger@gmx.de{@literal >}
	*/
	public String str(){
		
		String s = "";
		int index = 0;
		s += (" |0 1 2|3 4 5|6 7 8|\n");
		s += ("-+-----+-----+-----+\n");
		
		while(index < 9){
			
			if(index < 3){
				s += String.format("%d|",index);
				for(int i =0;i<9;i++){
					if(field[index][i] == -1 && i!=2 && i!=5 && i!=8){
						s += "  ";
					}else if(field[index][i] == -1 && (i==2 || i==5 || i==8)){
						s += " ";
					}else if(i!=2 && i!=5 && i!=8){
						s += Integer.toString(field[index][i]) + " ";
					}else{
						s += Integer.toString(field[index][i]);
					} 
					if(i==2||i==5||i==8){
						s += ("|");
					}
				}
				s += ("\n");
				if(index <2){
				s += (" |     |     |     |\n");
				}	
			}
			
			if(index==2){
			s += ("-+-----+-----+-----+\n");
			}
			
			if(index < 6 && index > 2){
				s += String.format("%d|",index);
				for(int i =0;i<9;i++){
					if(field[index][i] == -1 && i!=2 && i!=5 && i!=8){
						s += "  ";
					}else if(field[index][i] == -1 && (i==2 || i==5 || i==8)){
						s += " ";
					}else if(i!=2 && i!=5 && i!=8){
						s += Integer.toString(field[index][i]) + " ";
					}else{
						s += Integer.toString(field[index][i]);
					} 
					if(i==2||i==5||i==8){
						s += ("|");
					}
				}
				s += ("\n");
				if(index > 2 && index <5){
				s += (" |     |     |     |\n");
				}	
			}
			
			if(index==5){
			s += ("-+-----+-----+-----+\n");
			}
			
			if(index < 9 && index > 5){
				s += String.format("%d|",index);
				for(int i =0;i<9;i++){
					if(field[index][i] == -1 && i!=2 && i!=5 && i!=8){
						s += "  ";
					}else if(field[index][i] == -1 && (i==2 || i==5 || i==8)){
						s += " ";
					}else if(i!=2 && i!=5 && i!=8){
						s += Integer.toString(field[index][i]) + " ";
					}else{
						s += Integer.toString(field[index][i]);
					} 
					if(i==2||i==5||i==8){
						s += ("|");
					}
				}
				s += ("\n");
				if(index >5 && index <8){
				s += (" |     |     |     |\n");
				}	
			}
			
			if(index==8){
			s += ("-+-----+-----+-----+\n");
			}
			
			index++;
		}	
		return s;
	}
	
	
	
	
	
	
}