import java.util.Random;

public class Game {

    private int[][] grid;
    private Random random;
    private int noRows, noCols;

    /**
     * Creates a new game with a set number of rows and columns and a specific grid setup input
     */
    public Game(int noRows, int noCols, int[][] grid) {
        this.noRows = noRows;
        this.noCols = noCols;
        this.grid = grid;
    }

    /**
     * Creates a new game with a set number of rows and columns but the grid setup is to be randomly assigned
     */
    public Game(int noRows, int noCols) {
        random = new Random();

        this.noRows = noRows;
        this.noCols = noCols;
        grid = new int[noRows][noCols];
        generateRandomStartingState();
    }

    /**
     * Changes the grid of the game, including the grid size and what cells are alive
     *
     * @param newGrid This sets the game with a new grid
     * */
    public void setGrid(int[][] newGrid) {
        this.grid = newGrid;
    }

    /**
     * Assigns a value of 0 or 1 randomly to each cell in the grid
     * */
    private void generateRandomStartingState() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[x][y] = random.nextInt(2);
            }
        }
    }

    /**
     * Gets the grid of the game
     *
     * @return this games grid
     *  */
    public int[][] getGrid() {
        return grid;
    }


    /**
     * Calculates which move should be made by a cell depending on it's neighbours and current state,
     * setting the grid to be in it's next state
     *
     * @return grid in it's next state
     */
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

    /**
     * Calculates the number of live neighbours a cell has
     *
     * @param r represents row value
     * @param c represents column value
     * @return number of neighbours of an input grid cell
     */
    private int countNeighbours(int r, int c) {
        int neighbours = 0;

        for (int row = r - 1; row <= r + 1; row++) {
            for (int col = c - 1; col <= c + 1; col++) {
                if (row >= 0 && col >= 0 && row < noRows && col < noCols) {
                    if (grid[row][col] == 1 && !(row == r && col == c)) {
                        neighbours++;
                    }
                }
            }
        }

        return neighbours;
    }

    /**
     * Checks if the input cell has less than 2 live neighbours
     *
     * @param r represents the row value
     * @param c represents the column value
     * @return boolean value depending on how many live neighbours the cell has
     */
    private boolean underpopulationCheck(int r, int c) {
        return countNeighbours(r, c) < 2;
    }

    /**
     * Checks if the input cell has more than 3 live neighbours
     *
     * @param r represents the row value
     * @param c represents the column value
     * @return boolean value depending on how many live neighbours the cell has
     */
    private boolean overcrowdCheck(int r, int c) {
        return countNeighbours(r, c) > 3;
    }

    /**
     * Checks if the input cell has exactly 3 live neighbours
     *
     * @param r represents the row value
     * @param c represents the column value
     * @return boolean value depending on how many live neighbours the cell has
     */
    private boolean lifeCreation(int r, int c) {
        return countNeighbours(r, c) == 3;
    }
}
