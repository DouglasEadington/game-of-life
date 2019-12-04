import javax.swing.*;
import java.awt.*;

public class GUI {
    private JFrame frame;
    private JPanel gridPanel;

    /**
     * Creates a new GUI to display the game
     */
    public GUI(int[][] grid, int noRows, int noCols) {
        buildGUI(grid, noRows, noCols);
    }

    /**
     * Builds a GUI to represent the game grid and cell values
     *
     * @param grid the grid in which the display should be based on
     */
    private void buildGUI(int[][] grid, int noRows, int noCols) {
        frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        setCells(grid);
        gridPanel.setLayout(new GridLayout(noRows,noCols));
        frame.add(scrollPane);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Updates the cells to indicate whether or not they are live
     *
     * @param grid the grid to check for live cells in
     */
    private void setCells(int[][] grid) {
        gridPanel.removeAll();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                if (grid[x][y] == 1) {
                    cellPanel.setBackground(Color.green);
                } else {
                    cellPanel.setBackground(Color.white);
                }
                cellPanel.setOpaque(true);
                gridPanel.add(cellPanel);
                gridPanel.revalidate();
            }
        }

    }

    /**
     * Calls for an update on the GUI cells
     *
     * @param grid an up to date version of the grid
     */
    public void update(int[][] grid) {
        setCells(grid);
        frame.pack();
        frame.setVisible(true);
    }
}