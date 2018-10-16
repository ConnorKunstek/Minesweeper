import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    private int index;

    private boolean isBomb;

    private ClassLoader loader = getClass().getClassLoader();

    private Icon back;
    private Icon front;
    private boolean clicked;

    private int adjacent;

    public Tile(boolean bomb, int tileIndex){
        super();
        setBomb(bomb);
        setIndex(tileIndex);
        showBack();
        clicked = false;
    }

    public void setAdjacent(int i){adjacent = i;}

    public void showFront(){
        this.setIcon(front);
    }

    public void showBack(){
        this.setIcon(back);
    }

    public void setImages(){
        back = new ImageIcon(loader.getResource("res/blank.png"));
        if(isBomb()) {
            front = new ImageIcon(loader.getResource("res/mine.png"));
        }else{
            front = new ImageIcon(loader.getResource("res/" + adjacent + ".png"));
        }
    }

    public boolean isBomb(){
        return this.isBomb;
    }

    public void setBomb(boolean bool){
        this.isBomb = bool;
    }

    public void setIndex(int integer){
        this.index = integer;
    }
    public int getIndex(){
        return this.index;
    }

    public void clicked(){
        if(isBomb()){
            //Game Over
            turnRed();
        }else {
            showFront();
            clicked = true;
        }
    }

    public void turnRed(){
        this.setBackground(Color.RED);
    }

    public boolean isClicked(){
        return clicked;
    }
}
