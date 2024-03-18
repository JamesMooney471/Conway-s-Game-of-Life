
package conwaysgameoflife;

import java.util.Scanner;

public class ConwaysGameOfLife {
    
    /*
     *editBoard
     *Easy way to create starting pixels
     *
     *@return pixel board to be used in game
     */
    
    public static int[][] editBoard () {
        int[][] board = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},};
        return board;
    }
    
    /*
     *createPixelBoard
     *Mostly redundant, but makes creating initial array easier
     *
     *@param sideLength length of each of the 4 sides of the square board
     *
     *@return 2d array that can be used for game
     */
    
    public static int[][] createPixelBoard(int sideLength) {
        int[][] pixelBoard = new int[sideLength][sideLength];
        return pixelBoard;
    }
    
    /*
     *neighbors
     *Important check on how many live cells are directly adjacent and diagonal from the cell
     *
     *@param x x coordinate of the cell being observed
     *@param y y coordinate of the cell being observed
     *@param pixelBoard board containing each cell
     *
     *@return the number of cells that are alive next to the cell in check
     */
    
    public static int neighbors(int x, int y, int[][] pixelBoard) {
        int count = 0;
        if (pixelBoard[x + 1][y + 1] == 1) {
            count++;
        }
        if (pixelBoard[x][y + 1] == 1) {
            count++;
        }
        if (pixelBoard[x - 1][y + 1] == 1) {
            count++;
        }
        if (pixelBoard[x + 1][y] == 1) {
            count++;
        }
        if (pixelBoard[x - 1][y] == 1) {
            count++;
        }
        if (pixelBoard[x + 1][y - 1] == 1) {
            count++;
        }
        if (pixelBoard[x][y - 1] == 1) {
            count++;
        }
        if (pixelBoard[x - 1][y - 1] == 1) {
            count++;
        }
        return count;
    }
    
    /*
     *death
     *The rules of the game of life that result in the cell becoming not alive
     *
     *@param neighbors number of cells alive that are directly adjacent or diagonal
     *
     *@return whether or not the cell should die
     */
    
    public static boolean death (int neighbors) {
        if (neighbors == 0) {
            return true;
        }
        if (neighbors < 2) {
            return true;
        }
        if (neighbors > 3) {
            return true;
        }
        return false;
    }
    
    /*
     *life
     *The rules of the game of life that result in the cell becoming alive
     *
     *@param neighbors number of cells alive that are directly adjacent or diagonal
     *
     *@return whether or not the cell should live
     */
    
    public static boolean life(int neighbors, int[][] pixelBoard, int x, int y) {
        if (pixelBoard[x][y] == 1 && (neighbors == 2 || neighbors == 3)) {
            return true;
        }
        if (neighbors == 3) {
            return true;
        }
        return false;
    }
    
    /*
     *updatePixels
     *Determine what the next stage of evolution is for the given board using the game rules
     *
     *@param pixelBoard game board as is
     *
     *@return new board that takes the original one, applies the rules and determines which cells live and die
     */
    
    public static int[][] updatePixels(int[][] pixelBoard) {
        int[][] newBoard = new int[pixelBoard.length][pixelBoard.length];
        for (int i = 1; i < pixelBoard.length - 1; i++) {
            for (int j = 1; j < pixelBoard.length - 1; j++) {
                if (life(neighbors(i, j, pixelBoard), pixelBoard, i, j)) {
                    newBoard[i][j] = 1;
                } else if (death(neighbors(i, j, pixelBoard))) {
                    newBoard[i][j] = 0;
                }
            }
        }
        return newBoard;
    }

    /*
     *printPixels
     *Print out a given 2d array of cells
     *
     *@param pixelBoard board of cells to be displayed
     */
    
    public static void printPixels (int[][] pixelBoard) {
        for (int i = 1; i < pixelBoard.length - 1; i++) {
            for (int j = 1; j < pixelBoard.length - 1; j++) {
                if (i == 1 || i == pixelBoard.length - 2) {
                    System.out.print("-");
                } else if (j == 1 || j == pixelBoard.length - 2) {
                    System.out.print("|");
                } else {
                    if (pixelBoard[i][j] == 1) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /*
     *gameLoop
     *Each of the necessary methods strung together to create the game of life
     *
     *@param pixelBoard initial starting cell array
     */
    
    public static void gameLoop (int[][] pixelBoard) {
        while (true) {
            delay();
            printPixels(pixelBoard);
            pixelBoard = updatePixels(pixelBoard);
        }
    }
    
    /*
     *delay
     *Have the program pause for half a second to slow down evolution
     */
    
    public static void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    
    /*
     *randomizeBoard
     *Set each cell to alive or dead randomly
     *
     *@param pixelBoard board to be editted
     *
     *@return pixelBoard with each pixel randomly set to 1 or 0
     */
    
    public static int[][] randomizeBoard (int[][] pixelBoard) {
        for (int i = 1; i < pixelBoard.length - 1; i++) {
            for (int j = 1; j < pixelBoard.length - 1; j++) {
                if ((int) (Math.random() * 2) == 1) {
                    pixelBoard[i][j] = 1;
                }
            }
        }
        return pixelBoard;
    }
    
    public static void main(String[] args) {
        
        int[][] gameBoard = editBoard();
        boolean choosingCells = true;
        Scanner sc = new Scanner(System.in);
        int x, y;
        
        System.out.print("Would you like to hear the rules?(y/n): ");
        if (sc.nextLine().equals("y")) {
            System.out.println("The game of life is an interesting cellular automata.\n" +
                               "It opperates on a basic set of rules and an initial\n" +
                               "starting board.\n\n" +
                               "You will start by entering an x and a y coordinate if you choose to manually input cells\n" +
                               "at which point that individual cell will be set to\n" +
                               "alive. Note: x and y coordinates must be between 1 and 20\n\n" +
                               "The rules of the game of life are as follows:\n" +
                               "1. Any live cell with fewer than two live neighbors dies, as if by underpopulation.\n" +
                               "2. Any live cell with two or three live neighbors lives on to the next generation.\n" +
                               "3. Any live cell with more than three live neighbors dies, as if by overpopulation.\n" +
                               "4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.\n");
        }
        System.out.print("Would you like a randomized board? Otherwise you will set the cells yourself(y/n): ");
        if (sc.nextLine().equals("y")) {
            gameBoard = randomizeBoard(gameBoard);
        } else {
            System.out.print("Would you like to input a cell?(y/n): ");
            if (sc.nextLine().equals("n")) {
                choosingCells = false;
            }

            while (choosingCells) {
                System.out.print("Enter x coordinate: ");
                x = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter y coordinate: ");
                y = sc.nextInt();
                sc.nextLine();
                gameBoard[x][y] = 1;
                System.out.print("Would you like to input a cell?(y/n): ");
                if (sc.nextLine().equals("n")) {
                    choosingCells = false;
                }
            }
        }
        gameLoop(gameBoard);
        
    }
    
}
