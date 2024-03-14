
package conwaysgameoflife;

public class ConwaysGameOfLife {
    
    public static int[][] editBoard () {
        int[][] board = {{0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,1,0,0,0,0,0,0,0},
                         {0,1,0,1,0,0,0,0,0,0,0},
                         {0,0,1,1,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0},
                         {0,0,0,0,0,0,0,0,0,0,0},};
        return board;
    }
    
    
    
    public static int[][] createPixelBoard(int sideLength) {
        int[][] pixelBoard = new int[sideLength][sideLength];
        return pixelBoard;
    }
    
    public static int[][] addPixel(int x, int y, int[][] pixelBoard) {
        pixelBoard[x][y] = 1;
        return pixelBoard;
    }
    
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
    
    public static boolean life(int neighbors, int[][] pixelBoard, int x, int y) {
        if (pixelBoard[x][y] == 1 && (neighbors == 2 || neighbors == 3)) {
            return true;
        }
        if (neighbors == 3) {
            return true;
        }
        return false;
    }
    
    public static int[][] updatePixels(int[][] pixelBoard) {
        int[][] newBoard = new int[pixelBoard.length][pixelBoard.length];
        for (int i = 1; i < pixelBoard.length - 1; i++) {
            for (int j = 1; j < pixelBoard.length - 1; j++) {
                if (life(neighbors(i, j, pixelBoard), pixelBoard, i, j) == true) {
                    newBoard[i][j] = 1;
                } else if (death(neighbors(i, j, pixelBoard))) {
                    newBoard[i][j] = 0;
                }
            }
        }
        return newBoard;
    }

    public static void printPixels (int[][] pixelBoard) {
        for (int i = 1; i < pixelBoard.length - 1; i++) {
            for (int j = 1; j < pixelBoard.length - 1; j++) {
                if (pixelBoard[i][j] == 1) {
                    System.out.print(pixelBoard[i][j]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void gameLoop (int[][] pixelBoard) {
        while (true) {
            delay();
            printPixels(pixelBoard);
            pixelBoard = updatePixels(pixelBoard);
        }
    }
    
    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void main(String[] args) {
        
        int[][] gameBoard = editBoard();
        
        gameLoop(gameBoard);
        
    }
    
}
