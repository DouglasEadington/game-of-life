import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main (String[] args) {
        Game game = new Game(50,50);
        GUI gui = new GUI(game.getGrid());

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                gui.update(game.nextState());
            }
        };
        Timer timer = new Timer(2500 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();
    }
}
