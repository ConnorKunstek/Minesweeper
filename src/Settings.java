import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame implements ActionListener {

    private int gridSize;
    private int bombs;

    private String gridSizeString;
    private String bombsString;

    private JTextField sizeField;
    private JTextField bombsField;

    public Settings(){
        super("Settings");
        JPanel settingsPanel = new JPanel();
        Container s = getContentPane();

        gridSize = 8;

        bombs = 15;

        settingsPanel.setLayout(new GridLayout(3, 3, 2, 2));
        JButton beginner = new JButton("Beginner");
        JButton intermediate = new JButton("Intermediate");
        JButton expert = new JButton("Expert");
        JButton done = new JButton("Done");
        sizeField = new JTextField();
        bombsField = new JTextField();

        JLabel bombsLabel = new JLabel("Number of bombs:");
        JLabel gridSizeLabel = new JLabel("Size of Grid:");
        JLabel doneLabel = new JLabel("Press when Done:");

        gridSizeString = Integer.toString(gridSize);
        bombsString = Integer.toString(bombs);
        bombsField.setText(bombsString);
        sizeField.setText(gridSizeString);

        beginner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setValues(4, 4);
            }
        });

        intermediate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setValues(8, 15);
            }
        });

        expert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setValues(12, 40);
            }
        });

        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                done();
            }
        });

        settingsPanel.add(beginner);
        settingsPanel.add(intermediate);
        settingsPanel.add(expert);

        settingsPanel.add(gridSizeLabel);
        settingsPanel.add(bombsLabel);
        settingsPanel.add(doneLabel);

        settingsPanel.add(sizeField);
        settingsPanel.add(bombsField);
        settingsPanel.add(done);

        s.add(settingsPanel);
        setSize(400, 200);
    }

    public void done(){
        setVisible(false);
    }

    public void redraw(){
        gridSizeString = Integer.toString(gridSize);
        bombsString = Integer.toString(bombs);
        bombsField.setText(bombsString);
        sizeField.setText(gridSizeString);
        repaint();
    }


    public void setValues(int size, int bombsNum){
        gridSize = size;
        bombs = bombsNum;
        redraw();
    }

    public int getGridSize(){
        return this.gridSize;
    }

    public int getBombs(){
        return this.bombs;
    }

    public void actionPerformed(ActionEvent e){}
}
