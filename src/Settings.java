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

    private boolean inSettings;

    private JButton done;
    private JButton beginner;
    private JButton intermediate;
    private JButton expert;

    public Settings(){

        super("Settings");
        super.setAlwaysOnTop(true);

        JPanel settingsPanel = new JPanel();
        Container s = getContentPane();

        gridSize = 8;
        bombs = 15;

        settingsPanel.setLayout(new GridLayout(3, 3, 2, 2));
        beginner = new JButton("Beginner");
        intermediate = new JButton("Intermediate");
        expert = new JButton("Expert");
        done = new JButton("Done");
        sizeField = new JTextField();
        bombsField = new JTextField();

        JLabel bombsLabel = new JLabel("Bombs:");
        JLabel gridSizeLabel = new JLabel("Grid Size:");
        JLabel doneLabel = new JLabel("");

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


    public JButton getDoneButton(){
        return done;
    }

    public JButton getBeginnerButton(){
        return beginner;
    }

    public JButton getIntermediateButton(){
        return intermediate;
    }

    public JButton getExpertButton(){
        return expert;
    }


    public void done(){
        String tempSizeStr = sizeField.getText();
        String tempBombsStr = bombsField.getText();

        int tempSize = gridSize;
        int tempBombs = bombs;

        boolean flag = true;

        try {
            gridSize = Integer.parseInt(tempSizeStr);
            bombs = Integer.parseInt(tempBombsStr);
        }catch(NumberFormatException e){
            gridSize = tempSize;
            bombs = tempBombs;
            flag = false;
        }

        if((gridSize != tempSize) && (bombs != tempBombs) && (flag)){

        }

        boolean flag2 = true;

        if(gridSize < 3){
            gridSize = 3;
            flag2 = false;
        }
        if(gridSize > 12){
            gridSize = 12;
            flag2 = false;
        }

        if((bombs < 2)){
            bombs = 2;
            flag = false;
        }
        if(bombs > (gridSize * gridSize)/2){
            bombs = (gridSize * gridSize) / 2;
            flag = false;
        }

        gridSizeString = Integer.toString(gridSize);
        bombsString = Integer.toString(bombs);

        if(flag){
            setVisible(false);
            setValues(gridSize, bombs);
        }else{
            setValues(gridSize, bombs);
        }
    }

    public void redraw(){
        gridSizeString = Integer.toString(gridSize);
        bombsString = Integer.toString(bombs);
        bombsField.setText(bombsString);
        sizeField.setText(gridSizeString);
        repaint();
    }

    public void setSettings(boolean bool){
        inSettings = bool;
    }

    public boolean getSettings(){
        return inSettings;
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
