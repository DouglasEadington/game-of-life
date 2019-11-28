import java.util.Random;

public class Game {

    private int[][] grid;
    private Random random;
    private int width, height;

    public Game(int width, int height) {
        random = new Random();

        this.width = width;
        this.height = height;
        grid = new int[width][height];
        generateRandomStartingState(grid);
    }

    private void generateRandomStartingState(int[][] grid) {

        /*for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[x][y] = random.nextInt(2);
            }
        }*/
        /*grid[1][0] = 1;
        grid[2][1] = 1;
        grid[0][2] = 1;
        grid[1][2] = 1;
        grid[2][2] = 1;*/

        grid[20][20] = 1;
        grid[21][20] = 1;
        grid[22][20] = 1;
        grid[23][20] = 1;
        grid[24][20] = 1;
        grid[25][20] = 1;
        grid[26][20] = 1;
        grid[27][20] = 1;
        grid[28][20] = 1;
        grid[29][20] = 1;

        /*grid[1][2] = 1;
        grid[2][2] = 1;
        grid[3][2] = 1;*/

    }

    public int[][] getGrid() {
        return grid;
    }

    public int[][] nextState() {
        int[][] newGrid = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if (grid[x][y] == 1) {
                    if (underpopulationCheck(x, y)) {
                        newGrid[x][y] = 0;
                    } else if (overcrowdCheck(x, y)) {
                        newGrid[x][y] = 0;
                    } else {
                        newGrid[x][y] = 1;
                    }
                } else if (grid[x][y] == 0) {
                    if (lifeCreation(x, y)) {
                        newGrid[x][y] = 1;
                    } else {
                        newGrid[x][y] = 0;
                    }
                }
            }
        }
        grid = newGrid;
        return grid;
    }

    private int countNeighbours(int x, int y) {
        int neighbours = 0;

        for (int j = y - 1; j <= y + 1; j++) {
            for (int i = x - 1; i <= x + 1; i++) {
                if (i >= 0 && j >= 0 && i < width && j < height) {
                    if (grid[i][j] == 1 && !(i == x && j == y)) {
                        neighbours++;
                    }
                }
            }
        }

        return neighbours;
    }

    public boolean underpopulationCheck(int x, int y) {
        return countNeighbours(x, y) < 2;
    }

    public boolean overcrowdCheck(int x, int y) {
        return countNeighbours(x, y) > 3;
    }

    public boolean lifeCreation(int x, int y) {
        return countNeighbours(x, y) == 3;
    }
}
