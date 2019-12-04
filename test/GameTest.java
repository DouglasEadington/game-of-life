import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private int[][] grid;


    /**
     * initialises our game and grid variables for testing features
     */
    @BeforeEach
    void createDemoGrid() {
        grid = new int[5][5];
        game = new Game(5, 5,grid);
    }

    /**
     * test for an empty grid, should be no changes after nextState is called
     */
    @Test
    void emptyGrid() {
        game.nextState();
        for (int[] i : game.getGrid()) {
            for (int j : i) {
                assertEquals(0, j);
            }
        }
    }

    /**
     * test for "underpopulation situation", resulting in a cell dying if it doesn't have enough neighbours
     */
    @Test
    void underpopulation() {
        grid[1][2] = 1;
        game.setGrid(grid);
        game.nextState();

        for (int[] i : game.getGrid()) {
            for (int j : i) {
                assertEquals(0, j);
            }
        }

        grid[2][2] = 1;
        for (int[] i : game.getGrid()) {
            for (int j : i) {
                assertEquals(0, j);
            }
        }
    }

    /**
     * test for "overcrowding situation", resulting in a cell dying if it has too many neighbours
     */
    @Test
    void overcrowding() {

        grid[1][2] = 1;
        grid[2][2] = 1;
        grid[3][2] = 1;
        grid[2][1] = 1;
        grid[2][3] = 1;

        game.setGrid(grid);
        game.nextState();
        assertEquals(0, game.getGrid()[2][2]);

        grid[1][1] = 1;
        grid[1][3] = 1;
        grid[2][2] = 1;
        grid[3][1] = 1;
        grid[3][3] = 1;

        game.setGrid(grid);
        game.nextState();
        assertEquals(0, game.getGrid()[2][2]);

    }

    /**
     * tests for the "survival situation" where a cell has the correct number of neighbours to survive
     */
    @Test
    void survival(){

        grid[2][1] = 1;
        grid[2][2] = 1;
        grid[2][3] = 1;

        game.setGrid(grid);
        game.nextState();
        assertEquals(1, game.getGrid()[2][2]);

        grid[1][2] = 1;
        game.setGrid(grid);
        game.nextState();
        assertEquals(1, game.getGrid()[2][2]);
    }

    /**
     * tests for the situation in which an empty cell has enough neighbours to spawn a living cell
     */
    @Test
    void lifeCreation(){
        grid[2][1] = 1;
        grid[2][2] = 1;
        grid[2][3] = 1;

        game.setGrid(grid);

        assertEquals(0, game.getGrid()[1][2]);
        assertEquals(0, game.getGrid()[3][2]);
        game.nextState();
        assertEquals(1, game.getGrid()[1][2]);
        assertEquals(1, game.getGrid()[3][2]);
    }

    /**
     * tests the getGrid function does return the correct grid
     */
    @Test
    void getGrid() {
        assertEquals(grid, game.getGrid());
    }

    /**
     * tests that grids are randomly assigned setup values
     */
    @Test
    void runRandomGrid() {
        game = new Game(5,5);
        int[][] initialGrid = game.getGrid();
        game.nextState();
        assertNotEquals(initialGrid, game.getGrid());
    }

}