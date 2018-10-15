import javax.swing.*;

public class Tile extends JButton {

    private int index;

    private boolean isBomb;

    public Tile(boolean bomb, int tileIndex){
        super();
        setBomb(bomb);
        setIndex(tileIndex);
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
}
