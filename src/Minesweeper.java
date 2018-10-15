import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Minesweeper extends JFrame implements ActionListener {

    private Grid grid;

    private Settings settingObject;

    private int gridSize;
    private int bombs;

    private JPanel gridView, buttons;

    private JButton start;
    private JButton quit;
    private JButton settings;
    private JButton help;

    public Minesweeper(){
        super("Minesweeper");
        initialize();
        start();
    }

    public void initialize(){

        settingObject = new Settings();

        gridView = new JPanel();
        buttons = new JPanel();

        gridSize = settingObject.getGridSize();
        bombs = settingObject.getBombs();

        grid = new Grid(gridSize, bombs);

        start = new JButton("Start");
        quit = new JButton("Quit");
        settings = new JButton("Settings");
        help = new JButton("Help");

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playGame();
            }
        });

        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settings();
            }
        });

        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });
    }

    public void start(){
        start.setText("Start");
        grid.disableAllTiles();
        draw();
    }

    public void playGame(){
        start.setText("Restart");
        grid.enableAllTiles();
    }

    public void draw(){

        buttons.removeAll();
        gridView.removeAll();

        gridSize = settingObject.getGridSize();
        bombs = settingObject.getBombs();

        grid = new Grid(gridSize, bombs);

        Container c = getContentPane();

        // Add the game board to the board layout area
        gridView.setLayout(new GridLayout(gridSize, gridSize, 2, 2));
        grid.fillBoardView(gridView);

        // Add required interface elements to the "label" JPanel
        buttons.setLayout(new GridLayout(1, 4, 2, 2));
        buttons.add(start);
        buttons.add(quit);
        buttons.add(settings);
        buttons.add(help);

        // Both panels should now be individually layed out
        // Add both panels to the container
        c.add(buttons, BorderLayout.NORTH);
        c.add(gridView, BorderLayout.CENTER);

        setSize(500, 600);
        setVisible(true);
    }

    public void settings(){
        settingObject.setVisible(true);
        start();
    }

    public void help(){

    }

    public void actionPerformed(ActionEvent e){}

    public static void main(String[] args){
        Minesweeper M = new Minesweeper();
        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}