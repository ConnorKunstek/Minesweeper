import javax.swing.*;

public class Grid {

    private int gridSize;
    private int bombs;

    private Tile[] tiles;

    public Grid(){
        gridSize = 8;
        bombs = 20;
        makeTiles();
        enableAllTiles();
    }

    public Grid(int tiles, int numOfBombs){
        gridSize = tiles;
        bombs = numOfBombs;
        makeTiles();
        enableAllTiles();
    }

    public void makeTiles(){

        this.tiles = new Tile[gridSize * gridSize];
        for(int i = 0; i < gridSize * gridSize; i++){
            Tile temp = new Tile(false, i);
            this.tiles[i] = temp;
        }

    }

    public void fillBoardView(JPanel view)
    {
        for (Tile t : this.tiles) {
            view.add(t);
        }
    }

    public void enableAllTiles(){
        for(Tile t: this.tiles){
            t.setEnabled(true);
        }
    }

    public void disableAllTiles(){
        for(Tile t: this.tiles){
            t.setEnabled(false);
        }
    }
}
