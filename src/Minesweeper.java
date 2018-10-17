import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Minesweeper extends JFrame implements ActionListener {

    private Grid grid;

    private Settings settings;

    private Help help;

    private int gridSize;
    private int bombs;

    private JPanel gridView, buttons;

    private JButton start;
    private JButton quit;
    private JButton settingsButton;
    private JButton helpButton;

    public Minesweeper(){
        super("Minesweeper");
        initialize();
        start();
    }

    public void initialize(){

        settings = new Settings();
        help = new Help();

        gridView = new JPanel();
        buttons = new JPanel();

        gridSize = settings.getGridSize();
        bombs = settings.getBombs();

        grid = new Grid(gridSize, bombs);
        grid.disableAllTiles();

        start = new JButton("Start");
        quit = new JButton("Quit");
        settingsButton = new JButton("Settings");
        helpButton = new JButton("Help");

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

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settings();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });

        settings.getDoneButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        settings.getBeginnerButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        settings.getIntermediateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gridSize = settings.getGridSize();
                bombs = settings.getBombs();
                start();
            }
        });

        settings.getExpertButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }

    public void start(){
        start.setText("Start");
        grid.disableAllTiles();
        draw();
    }

    public void playGame(){
        draw();
        start.setText("Restart");
        grid.enableAllTiles();
    }

    public void draw(){

        buttons.removeAll();
        gridView.removeAll();

        gridSize = settings.getGridSize();
        bombs = settings.getBombs();

        grid = new Grid(gridSize, bombs);

        Container c = getContentPane();

        // Add the game board to the board layout area
        gridView.setLayout(new GridLayout(gridSize, gridSize, 2, 2));
        grid.fillBoardView(gridView);

        // Add required interface elements to the "label" JPanel
        buttons.setLayout(new GridLayout(1, 4, 2, 2));
        buttons.add(start);
        buttons.add(quit);
        buttons.add(settingsButton);
        buttons.add(helpButton);

        // Both panels should now be individually layed out
        // Add both panels to the container
        c.add(buttons, BorderLayout.NORTH);
        c.add(gridView, BorderLayout.CENTER);

        int number = gridSize * 65;
        if(number < 400) {number = 400;}

        setSize(number,  number + 100);
        grid.disableAllTiles();
        setVisible(true);
    }

    public void settings(){
        grid.disableAllTiles();
        settings.setVisible(true);
    }

    public void help(){
        grid.disableAllTiles();
        help.setVisible(true);
        start();
    }

    public void actionPerformed(ActionEvent e){}

    public static void main(String[] args){
        Minesweeper M = new Minesweeper();
        M.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });
    }
}