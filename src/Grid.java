import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private int gridSize;
    private int bombs;

    private Tile[] tiles;

    public Grid(int tiles, int numOfBombs){
        gridSize = tiles;
        bombs = numOfBombs;
        makeTiles();
        disableAllTiles();
        determineAdjacent();
        setTilesImages();
    }

    public void setTilesImages(){
        for (Tile t : this.tiles) {
            t.setImages();
        }
    }

    public void makeTiles(){

        boolean[] temp = new boolean[gridSize*gridSize];
        for(int i = 0; i < temp.length; i++){
            temp[i] = false;
        }

        for(int i = 0; i < bombs; i++){
            boolean flag = true;
            while(flag){
                int random = ThreadLocalRandom.current().nextInt(0, gridSize*gridSize);
                if(!temp[random]){
                    temp[random] = true;
                    flag = false;
                }
            }
        }

        tiles = new Tile[gridSize * gridSize];
        for(int i = 0; i < gridSize * gridSize; i++){
            Tile tempTile = new Tile(temp[i], i);
            tempTile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tempTile.clicked();
                    if(tempTile.isBomb()) {
                        gameOver();
                    }
                }
            });
            tiles[i] = tempTile;
        }
    }

    public void gameOver(){
        for (Tile t : this.tiles) {
            if(t.isClicked()){
                if(t.isBomb()){
                    t.turnRed();
                }
            }
            t.showFront();
        }
    }

    public void determineAdjacent(){

        int incrementer = 0;

        Tile[][] grid = new Tile[gridSize][gridSize];
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                grid[i][j] = tiles[incrementer];
                incrementer++;
            }
        }

        int counter;


//        for(int i = 0; i < gridSize; i++){
//            for(int j = 0; j < gridSize; i++){
//                counter = 0;
//                try{if(grid[i-1][j-1].isBomb()){counter++;}}catch(Exception e){continue;}
//                try{if(grid[i-1][j].isBomb()){counter++;}}catch(Exception e){continue;}
//                try{if(grid[i-1][j+1].isBomb()){counter++;}}catch(Exception e){continue;}
//                try{if(grid[i][j-1].isBomb()){counter++;}}catch(Exception e){continue;}
//                try{if(grid[i][j+1].isBomb()){counter++;}}catch(Exception e){continue;}
//                try{if(grid[i+1][j-1].isBomb()){counter++;}}catch(Exception e){continue;}
//                try{if(grid[i+1][j].isBomb()){counter++;}}catch(Exception e){continue;}
//                try{if(grid[i+1][j+1].isBomb()){counter++;}}catch(Exception e){continue;}
//                grid[i][j].setAdjacent(counter);
//            }
//        }


        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                counter = 0;
                if(i == 0){
                    if(j == 0){
                        //top left corner
                        if(grid[i][j+1].isBomb()){counter++;}
                        if(grid[i+1][j].isBomb()){counter++;}
                        if(grid[i+1][j+1].isBomb()){counter++;}
                    }else{
                        if(j == gridSize-1) {
                            //top right corner
                            if(grid[i][j-1].isBomb()){counter++;}
                            if(grid[i+1][j-1].isBomb()){counter++;}
                            if(grid[i+1][j].isBomb()){counter++;}
                        }else{
                            //top row
                            if(grid[i][j-1].isBomb()){counter++;}
                            if(grid[i][j+1].isBomb()){counter++;}
                            if(grid[i+1][j-1].isBomb()){counter++;}
                            if(grid[i+1][j].isBomb()){counter++;}
                            if(grid[i+1][j+1].isBomb()){counter++;}
                        }
                    }
                }else{
                    if(i == gridSize-1){
                        if(j == 0){
                            //bottom left corner
                            if(grid[i-1][j].isBomb()){counter++;}
                            if(grid[i-1][j+1].isBomb()){counter++;}
                            if(grid[i][j+1].isBomb()){counter++;}
                        }else {
                            if (j == gridSize - 1) {
                                //bottom right corner
                                if(grid[i-1][j-1].isBomb()){counter++;}
                                if(grid[i-1][j].isBomb()){counter++;}
                                if(grid[i][j-1].isBomb()){counter++;}
                            }else{
                                //bottom row
                                if(grid[i-1][j-1].isBomb()){counter++;}
                                if(grid[i-1][j].isBomb()){counter++;}
                                if(grid[i-1][j+1].isBomb()){counter++;}
                                if(grid[i][j-1].isBomb()){counter++;}
                                if(grid[i][j+1].isBomb()){counter++;}
                            }
                        }
                    }else{
                        if(j == 0){
                            //left side
                            if(grid[i-1][j].isBomb()){counter++;}
                            if(grid[i-1][j+1].isBomb()){counter++;}
                            if(grid[i][j+1].isBomb()){counter++;}
                            if(grid[i+1][j].isBomb()){counter++;}
                            if(grid[i+1][j+1].isBomb()){counter++;}
                        }else{
                            if(j == gridSize-1){
                                //right side
                                if(grid[i-1][j-1].isBomb()){counter++;}
                                if(grid[i-1][j].isBomb()){counter++;}
                                if(grid[i][j-1].isBomb()){counter++;}
                                if(grid[i+1][j-1].isBomb()){counter++;}
                                if(grid[i+1][j].isBomb()){counter++;}
                            }else{
                                //middle
                                if(grid[i-1][j-1].isBomb()){counter++;}
                                if(grid[i-1][j].isBomb()){counter++;}
                                if(grid[i-1][j+1].isBomb()){counter++;}
                                if(grid[i][j-1].isBomb()){counter++;}
                                if(grid[i][j+1].isBomb()){counter++;}
                                if(grid[i+1][j-1].isBomb()){counter++;}
                                if(grid[i+1][j].isBomb()){counter++;}
                                if(grid[i+1][j+1].isBomb()){counter++;}
                            }
                        }
                    }
                }
                grid[i][j].setAdjacent(counter);
            }
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
        for(Tile t: this.tiles){
            t.setEnabled(false);
        }
    }
}
