import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Help extends JFrame implements ActionListener{

    JButton done;

    JTextArea message;

    public Help(){
        super("Help");
        super.setAlwaysOnTop(true);

        JPanel helpPanel = new JPanel();
        Container h = getContentPane();

        helpPanel.setLayout(new GridLayout(2, 1, 2, 2));

        String helpMessage = "Welcome to Minesweeper!\n" +
                "Mines have been randomly placed among the grid. \n" +
                "Each time you press a tile, the game will reveal if it is a bomb,\n" +
                "or, if not, how many bombs are adjacent to that tile. \n" +
                "Click on a bomb, and you lose. \n" +
                "Reveal all the tiles that aren't bombs, and you win!";
        message = new JTextArea(helpMessage);

        done = new JButton("Done");

        helpPanel.add(message, BorderLayout.CENTER);
        helpPanel.add(done, BorderLayout.SOUTH);

        h.add(helpPanel);
        setSize(400, 250);
    }

    public JButton getDoneButton(){
        return done;
    }

    public void actionPerformed(ActionEvent e){}
}
