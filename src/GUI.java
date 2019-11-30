import javax.swing.*;
import java.awt.*;

public class GUI {
    private JFrame frame;
    private JPanel gridPanel;

    public GUI(int[][] grid) {
        buildGUI(grid);
    }

    public void buildGUI(int[][] grid) {
        frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        addCells(grid);
        gridPanel.setLayout(new GridLayout(grid.length, grid[0].length));
        frame.add(scrollPane);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.pack();
        frame.setVisible(true);

    }

    public void addCells(int[][] grid) {
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

    public void update(int[][] grid) {
        addCells(grid);
        frame.pack();
        frame.setVisible(true);
    }
}