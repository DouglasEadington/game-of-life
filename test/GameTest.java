import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private int[][] grid;


    @BeforeEach
    void createDemoGrid() {
        grid = new int[5][5];
        game = new Game(5, 5,grid);
    }

    @Test
    void emptyGrid() {
        game.nextState();
        for (int[] i : game.getGrid()) {
            for (int j : i) {
                assertEquals(0, j);
            }
        }
    }

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

    @Test
    void getGrid() {
        assertEquals(grid, game.getGrid());
    }

    @Test
    void runRandomGrid() {
        game = new Game(5,5);
        int[][] initialGrid = game.getGrid();
        game.nextState();
        assertNotEquals(initialGrid, game.getGrid());
    }

}