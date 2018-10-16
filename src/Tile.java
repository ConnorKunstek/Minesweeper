import javax.swing.*;

public class Tile extends JButton {

    private int index;
    private String indexStr;

    private boolean isBomb;

    private ClassLoader loader = getClass().getClassLoader();

    private Icon back = new ImageIcon(loader.getResource("res/tile.png"));
    private Icon front;

    public Tile(boolean bomb, int tileIndex){
        super();
        setBomb(bomb);
        setIndex(tileIndex);
        //super.setIcon(back);
    }

    public void showFront(){
        if(isBomb){
            front = new ImageIcon(loader.getResource("res/Bomb.ico"));
            this.setIcon(front);
        }else{
            indexStr = Integer.toString(index);
            this.setText(indexStr);
        }
    }

    public void showBack(){
        this.setIcon(back);
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
