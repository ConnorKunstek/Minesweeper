import javax.swing.*;

public class Grid {

    private int gridSize;
    private int bombs;

    private Tile[] tiles;

    public Grid(){
        gridSize = 8;
        bombs = 20;
        makeTiles();
    }

    public Grid(int tiles, int numOfBombs){
        gridSize = tiles;
        bombs = numOfBombs;
        makeTiles();
    }

    public void makeTiles(){

        tiles = new Tile[gridSize * gridSize];
        for(int i = 0; i < gridSize * gridSize; i++){
            Tile temp = new Tile(false, i);
            tiles[i] = temp;
        }

    }

    public void fillBoardView(JPanel view)
    {
        for (Tile t : this.tiles) {
            view.add(t);
        }
    }

    public void enableAllTiles(){
        for(Tile t: tiles){
            t.setEnabled(true);
        }
    }

    public void disableAllTiles(){
        for(Tile t: tiles){
            t.setEnabled(false);
        }
    }
}
