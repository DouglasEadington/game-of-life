import javax.swing.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main (String[] args) {

        int[][] defaultGrid = new int[10][10];
        defaultGrid[2][1] = 1;
        defaultGrid[2][2] = 1;
        defaultGrid[2][3] = 1;

        Game game = new Game(10,10, defaultGrid);

        /*UNCOMMENT THE LINE BELOW FOR A RANDOM GRID GENERATION INSTEAD*/
//        game = new Game(50,50);

        GUI gui = new GUI(game.getGrid(), game.getNoRows(), game.getNoCols());

        Game finalGame = game;
        ActionListener taskPerformer = evt -> gui.update(finalGame.nextState());

        Timer timer = new Timer(2500 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }
}
