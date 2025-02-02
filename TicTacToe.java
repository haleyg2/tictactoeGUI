/*
Haley Gray
05/26/2024
Purpose: CLI tictactoe game. Uses enums for each tictactoe cell & the gamestate
*/
import java.util.Scanner;
public class TicTacToe {
    //values for each spot on the board
    enum CellState {
        X,
        O,
        EMPTY
    }
    //current game status
    enum GameState {
        WINO,
        WINX,
        DRAW,
        CONTINUE
    }
    private CellState[][] board;
    private CellState turn;
    private int rowChoice;
    private int colChoice;

    public TicTacToe() {
        board = new CellState[3][3];
        //set all board spots to CellState.EMPTY
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                board[i][j] = CellState.EMPTY;
            }
        }
		//Choose player for first turn
        turn = CellState.X;
        rowChoice = 0;
        colChoice = 0;
    }

    //prints the board
    public void printBoard() {
		
        for(int i = 0; i < board.length; ++i) {
            //print top //31 wide 3x3
            if(i == 0) {
                System.out.println("_______________________________\n"
						 +         "|         |         |         |");
            } else {
                System.out.println("|         |         |         |");
            }
            //print middle
			//this row has an X,O, or nothing
            for(int j = 0; j < board[i].length; ++j) {
                if(j == 0) {
                    System.out.print("|    ");
                    //print space if EMPTY
                    if(board[i][j] == CellState.EMPTY) {
                        System.out.print(" ");//spot is empty
                    } else {
                        System.out.print(board[i][j]);//print User here
                    }
                    System.out.print("    |");
				  //In the middle column or end
                } else {
                    System.out.print("    ");
                    if(board[i][j] == CellState.EMPTY) {
                        System.out.print(" ");
                    } else {
                        System.out.print(board[i][j]);
                    }
                    System.out.print("    |");
                }
            }//end for
            System.out.println();
            //print floor
            System.out.println("|_________|_________|_________|");
        }//end for
        System.out.println();
    }

    //Test cases for issues
    public void runTests() {
    System.out.println("valid move is false when both inputs out of bounds on high end: "
    + validMove(4, 4));

    System.out.println("valid move is false when both inputs out of bounds on low end: "
    + validMove(-1, -1));

    board[1][1] = CellState.X;
    System.out.println("validMove returns false when a spot is already not empty: "
    + validMove(2, 2));

    System.out.println("validMove is true when input is in bounds and spot is empty: "
    + validMove(1, 1));

    //vertWin() true
    board[0][1] = CellState.X;
    board[2][1] = CellState.X;
    printBoard();
    System.out.println("gameStatus returns Win when player has 3 in a row: "
    + gameStatus());

    //diagWin() true
    board[0][0] = CellState.X;
    board[2][2] = CellState.X;
    board[0][1] = CellState.EMPTY;
    printBoard();
    System.out.println("gameStatus returns Win when player has 3 in a row: "
    + gameStatus());

    //other diagWin() true
    board[0][0] = CellState.EMPTY;
    board[2][1] = CellState.EMPTY;
    board[2][0] = CellState.X;
    board[0][2] = CellState.X;
    printBoard();
    System.out.println("gameStatus returns Win when player has 3 in a row: "
    + gameStatus());

    //sideWin() true
    board[1][1] = CellState.EMPTY;
    board[2][1] = CellState.X;
    printBoard();
    System.out.println("gameStatus returns Win when player has 3 in a row: "
    + gameStatus());

    board[0][1] = CellState.O;
    board[0][0] = CellState.X;
    board[0][2] = CellState.X;
    board[1][0] = CellState.X;
    board[2][0] = CellState.O;
    board[1][2] = CellState.O;
    board[2][2] = CellState.O;
    board[1][1] = CellState.O;
    printBoard();
    System.out.println("gameStatus returns draw win board is full and no 3 in a row: "
    + gameStatus());

    board[1][1] = CellState.EMPTY;
    printBoard();
    System.out.println("gameStatus returns Continue when board is not full and no 3 in row: "
    + gameStatus());
    }

    //returns current gameStatus
    private GameState gameStatus() {
        //check wins with win methods
        CellState temp;
        //check if any side wins
        temp = sideWin();
        if(temp == CellState.X) {
            return GameState.WINX;
        } else if(temp == CellState.O) {
            return GameState.WINO;
        }

        //check if any vert wins
        temp = vertWin();
        if(temp == CellState.X) {
            return GameState.WINX;
        } else if(temp == CellState.O) {
            return GameState.WINO;
        }

        //check if any diagnal wins
        temp = diagWin();
        if(temp == CellState.X) {
            return GameState.WINX;
        } else if(temp == CellState.O) {
            return GameState.WINO;
        }
        //if no wins yet, check if game is draw (board is filled)
        //or game should continue (at least one empty space)
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] == CellState.EMPTY) {
                    return GameState.CONTINUE;
                }
            }
        }
        //if here and continue wasn't returned, board is full it's a DRAW
        return GameState.DRAW;
    }

    //checks if won side to side
    //return CellState of winner or EMPTY if no win
    private CellState sideWin() {
        //side wins if x or o is 0
        int x = 3;
        int o = 3;
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] == CellState.X) {
                    --x;
                } else if(board[i][j] == CellState.O) {
                    --o;
                } else {
                    break; //can't be 3 in row if one is EMPTY
                }
            }
            //check if x or o wins
            if(x == 0) {
                return CellState.X;
            }
            if(o == 0) {
                return CellState.O;
            }
            //reset counter
            x = 3;
            o = 3;
        }
        //no one won if got to end
        return CellState.EMPTY;
    }

    //checks if x or o have 3 vetically
    private CellState vertWin() {
        //side wins if x or o is 0
        int x = 3;
        int o = 3;
       
	    for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[j][i] == CellState.X) {
                    --x;
                } else if(board[j][i] == CellState.O) {
                    --o;
                } else {
                    break; //can't be 3 in row if one is EMPTY
                }
			}//end inner for
			//check if x or o wins
            if(x == 0) {
                return CellState.X;
            }
            if(o == 0) {
                return CellState.O;
            }
            //reset counter
            x = 3;
            o = 3;
			
		}//end outer for
	   
	   
	   

        //if got past no win
        return CellState.EMPTY;
    }

    //checks for 3 in row diagnally
    //returns CellState of winner or EMPTY if none
    private CellState diagWin() {
        int x = 3;
        int o = 3;
        int i = 0;
        //check for x diagnal
        while(i < 3) {
            if(board[i][i] == CellState.X) {
                --x;
                ++i;
            } else if(board[i][i] == CellState.O) {
                --o;
                ++i;
            } else {
                break; //can't 3 in row when EMPTY spot
            }
        }//end while
        //check for win condition
        if(x == 0) {
            return CellState.X;
        }
        if(o == 0) {
            return CellState.O;
        }

        //if here, no wins yet reset and check other diagnal
        x = 3;
        o = 3;
        i = 0;
        int j = 2;
        while(i < 3) {
            if(board[i][j] == CellState.X) {
                --x;
                ++i;
                --j;
            } else if(board[i][j] == CellState.O) {
                --o;
                ++i;
                --j;
            } else {
                break; //can't 3 in row when EMPTY spot
            }
        }//end  while
        //check for win condition
        if(x == 0) {
            return CellState.X;
        }
        if(o == 0) {
            return CellState.O;
        }
        //if here no wins
        return CellState.EMPTY;
    }

    //returns true if move is in bounds and is CellState.EMPTY and false if not
    private boolean validMove(int row, int col) {
        //check if in bounds and not Empty
        if(row < 4 && col < 4 && row > 0 && col > 0) {
            //in bounds
            if(board[row-1][col-1] == CellState.EMPTY) {
                return true; //can use
            } else {
                return false; //space is already used
            }
        } else {
            //not in bounds
            return false;
        }
    }

    //Loops through the game
    public void play() {
        Scanner input = new Scanner(System.in);
        printBoard();
        while(gameStatus() == GameState.CONTINUE) {
            //prompt player for a move
            if(turn == CellState.X) {
                System.out.println("Player X's turn");
                System.out.print("Enter row (1, 2, or 3): ");
                rowChoice = input.nextInt(); //read input
                System.out.print("Enter col (1, 2, or 3): ");
                colChoice = input.nextInt(); //read input
            } else {
                System.out.println("Player O's turn");
                System.out.print("Enter row (1, 2, or 3): ");
                rowChoice = input.nextInt(); //read input
                System.out.print("Enter col (1, 2, or 3): ");
                colChoice = input.nextInt(); //read input
            }

            //check if valid move
            if(validMove(rowChoice, colChoice)) {
                board[rowChoice - 1][colChoice - 1] = turn;
            } else {
                System.out.println("That is not a valid move. Please try again.");
                continue; //prompt for player turn again
            }

            //display updated board
            printBoard();

            //check game state
            GameState temp = gameStatus();
            //end game if either player wins
            if(temp == GameState.WINX){
                System.out.println("Player X wins.");
                break; //end game
            } else if(temp == GameState.WINO) {
                System.out.println("Player O wins.");
                break; //end game
            } else if(temp == GameState.DRAW) {
                System.out.println("Game is draw");
                break; //end game
            }

            //swap player's turn
            if(turn == CellState.X) {
                turn = CellState.O;
            } else if(turn == CellState.O) {
                turn = CellState.X;
            }

        }//end while
        input.close(); //game end/done with input
    }


}
