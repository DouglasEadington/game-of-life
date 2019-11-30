import java.util.Random;

public class Game {

    private int[][] grid;
    private Random random;
    private int noRows, noCols;

    public Game(int noRows, int noCols, int[][] grid) {
        this.noRows = noRows;
        this.noCols = noCols;
        this.grid = grid;
    }

    public Game(int noRows, int noCols) {
        random = new Random();

        this.noRows = noRows;
        this.noCols = noCols;
        grid = new int[noRows][noCols];
        generateRandomStartingState(grid);
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    private void generateRandomStartingState(int[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[x][y] = random.nextInt(2);
            }
        }
    }

    /*
    *
    *
    * */
    public int[][] getGrid() {
        return grid;
    }

    public int[][] nextState() {
        int[][] newGrid = new int[noRows][noCols];
        for (int row = 0; row < noCols; row++) {
            for (int col = 0; col < noRows; col++) {

                if (grid[row][col] == 1) {
                    if (underpopulationCheck(row, col)) {
                        newGrid[row][col] = 0;
                    } else if (overcrowdCheck(row, col)) {
                        newGrid[row][col] = 0;
                    } else {
                        newGrid[row][col] = 1;
                    }
                } else if (grid[row][col] == 0) {
                    if (lifeCreation(row, col)) {
                        newGrid[row][col] = 1;
                    } else {
                        newGrid[row][col] = 0;
                    }
                }
            }
        }
        grid = newGrid;
        return grid;
    }

    private int countNeighbours(int x, int y) {
        int neighbours = 0;

        for (int row = x - 1; row <= x + 1; row++) {
            for (int col = y - 1; col <= y + 1; col++) {
                if (row >= 0 && col >= 0 && row < noRows && col < noCols) {
                    if (grid[row][col] == 1 && !(row == x && col == y)) {
                        neighbours++;
                    }
                }
            }
        }

        return neighbours;
    }

    private boolean underpopulationCheck(int x, int y) {
        return countNeighbours(x, y) < 2;
    }

    private boolean overcrowdCheck(int x, int y) {
        return countNeighbours(x, y) > 3;
    }

    private boolean lifeCreation(int x, int y) {
        return countNeighbours(x, y) == 3;
    }
}
