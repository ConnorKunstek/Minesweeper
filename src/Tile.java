import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private int row;
    private int col;
    private boolean isBomb;
    private boolean clicked;
    private int adjacent;
    private boolean clear;

    private ClassLoader loader = getClass().getClassLoader();

    private Icon back;
    private Icon front;


    public Tile(int rowNum, int colNum, boolean bomb){
        super();
        setBomb(bomb);
        setRow(rowNum);
        setCol(colNum);
        showBack();
        clicked = false;
    }

    public void setAdjacent(int i){
        adjacent = i;
        if(i == 0){
            setClear(true);
        }else{
            setClear(false);
        }
    }

    public void showFront(){
        this.setIcon(front);
        this.setEnabled(false);
    }

    public void showBack(){
        this.setIcon(back);
    }

    public void setImages(){
        back = new ImageIcon(loader.getResource("res/blank.png"));
        if(isBomb()) {
            front = new ImageIcon(loader.getResource("res/mine.png"));
            setDisabledIcon(front);
        }else{
            front = new ImageIcon(loader.getResource("res/" + adjacent + ".png"));
            setDisabledIcon(front);
        }
    }

    public boolean isBomb(){
        return this.isBomb;
    }

    public void setBomb(boolean bool){
        this.isBomb = bool;
    }

    public void setRow(int integer){
        this.row = integer;
    }
    public void setCol(int integer){
        this.col = integer;
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }

    public void clicked(){
        showFront();
        clicked = true;
    }

    public void setClear(boolean bool){
        clear = bool;
    }

    public boolean getClear(){
        return clear;
    }

    public void turnRed(){
        this.setBackground(Color.RED);
    }

    public boolean isClicked(){
        return clicked;
    }

    public void gray(){
        setDisabledIcon(null);
    }
}
