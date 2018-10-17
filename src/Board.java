import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements ActionListener {

    private Grid grid;

    public Board(int gridSize, int bombs){

        super("Minesweeper");
        initialize(gridSize, bombs);
        start();

    }

    public void initialize(int gridSize, int bombs){
        grid = new Grid(gridSize, bombs);



    }

    public void start(){

    }


    public void actionPerformed(ActionEvent e){}
}
