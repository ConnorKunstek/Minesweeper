import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private int gridSize;
    private int bombs;

    private Tile[][] grid;

    public Grid(int tiles, int numOfBombs){
        gridSize = tiles;
        bombs = numOfBombs;
        makeTiles();
        disableAllTiles();
        determineAdjacent();
        setTilesImages();
    }

    public void setTilesImages(){
        for (Tile[] row : grid) {
            for(Tile t: row) {
                t.setImages();
            }
        }
    }

    public void makeTiles(){

        boolean[] bombArray = new boolean[gridSize*gridSize];
        for(int i = 0; i < bombArray.length; i++){
            bombArray[i] = false;
        }

        for(int i = 0; i < bombs; i++){
            boolean flag = true;
            while(flag){
                int random = ThreadLocalRandom.current().nextInt(0, gridSize*gridSize);
                if(!bombArray[random]){
                    bombArray[random] = true;
                    flag = false;
                }
            }
        }

        int incrementer = 0;

        grid = new Tile[gridSize][gridSize];

        for(int row = 0; row < gridSize; row++){
            for(int col = 0; col < gridSize; col++) {

                Tile tempTile = new Tile(row, col, bombArray[incrementer]);
                tempTile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        click(tempTile);
                    }
                });

                grid[row][col] = tempTile;
                incrementer++;
            }
        }
    }

    public void click(Tile t){
        t.clicked();
        if(t.isBomb()) {
            gameOver();
            t.turnRed();
        }
        if(t.getClear()){
            clearTiles(t.getRow(), t.getCol());
        }
    }

    public void clearTiles(int row, int col) {
        try {
            if ((row > -1) && (row < gridSize) && (col > -1) && (col < gridSize) && (grid[row][col].getClear())) {
                grid[row][col].clicked();
                //clearTiles(row - 1, col - 1);
                clearTiles(row - 1, col - 0);
                //clearTiles(row - 1, col + 1);
                clearTiles(row - 0, col - 1);
                clearTiles(row - 0, col + 1);
                //clearTiles(row + 1, col - 1);
                clearTiles(row + 1, col - 0);
                //clearTiles(row + 1, col + 1);
            } else {
                return;
            }
        }catch(Exception e){System.out.print(e);}
    }

//    public void clearTiles(Tile t){
//        if(t.getClear()){
//            t.clicked();
//            try {clearTiles(grid[t.getRow() - 1][t.getCol() - 1]);}catch(Exception ignore){}
//            try {clearTiles(grid[t.getRow() - 1][t.getCol() - 0]);}catch(Exception ignore){}
//            try {clearTiles(grid[t.getRow() - 1][t.getCol() + 1]);}catch(Exception ignore){}
//            try {clearTiles(grid[t.getRow() - 0][t.getCol() - 1]);}catch(Exception ignore){}
//            try {clearTiles(grid[t.getRow() + 0][t.getCol() + 1]);}catch(Exception ignore){}
//            try {clearTiles(grid[t.getRow() + 1][t.getCol() - 1]);}catch(Exception ignore){}
//            try {clearTiles(grid[t.getRow() + 1][t.getCol() + 0]);}catch(Exception ignore){}
//            try {clearTiles(grid[t.getRow() + 1][t.getCol() + 1]);}catch(Exception ignore){}
//        }
//    }

    public void gameOver(){
        for (Tile[] row : grid) {
            for(Tile t: row) {
                if(!t.isClicked()){
                    t.gray();
                }
                t.showFront();
            }
        }
        disableAllTiles();
    }

    public void determineAdjacent(){

        int counter;

        for(int row = 0; row < gridSize; row++){
            for(int col = 0; col < gridSize; col++){
                counter = 0;
                try{if(grid[row - 1][col - 1].isBomb()){counter++;}}catch(Exception ignore){}
                try{if(grid[row - 1][col - 0].isBomb()){counter++;}}catch(Exception ignore){}
                try{if(grid[row - 1][col + 1].isBomb()){counter++;}}catch(Exception ignore){}
                try{if(grid[row - 0][col - 1].isBomb()){counter++;}}catch(Exception ignore){}
                try{if(grid[row + 0][col + 1].isBomb()){counter++;}}catch(Exception ignore){}
                try{if(grid[row + 1][col - 1].isBomb()){counter++;}}catch(Exception ignore){}
                try{if(grid[row + 1][col + 0].isBomb()){counter++;}}catch(Exception ignore){}
                try{if(grid[row + 1][col + 1].isBomb()){counter++;}}catch(Exception ignore){}
                grid[row][col].setAdjacent(counter);
            }
        }


//        for(int i = 0; i < gridSize; i++){
//            for(int j = 0; j < gridSize; j++){
//                counter = 0;
//                if(i == 0){
//                    if(j == 0){
//                        //top left corner
//                        if(grid[i][j+1].isBomb()){counter++;}
//                        if(grid[i+1][j].isBomb()){counter++;}
//                        if(grid[i+1][j+1].isBomb()){counter++;}
//                    }else{
//                        if(j == gridSize-1) {
//                            //top right corner
//                            if(grid[i][j-1].isBomb()){counter++;}
//                            if(grid[i+1][j-1].isBomb()){counter++;}
//                            if(grid[i+1][j].isBomb()){counter++;}
//                        }else{
//                            //top row
//                            if(grid[i][j-1].isBomb()){counter++;}
//                            if(grid[i][j+1].isBomb()){counter++;}
//                            if(grid[i+1][j-1].isBomb()){counter++;}
//                            if(grid[i+1][j].isBomb()){counter++;}
//                            if(grid[i+1][j+1].isBomb()){counter++;}
//                        }
//                    }
//                }else{
//                    if(i == gridSize-1){
//                        if(j == 0){
//                            //bottom left corner
//                            if(grid[i-1][j].isBomb()){counter++;}
//                            if(grid[i-1][j+1].isBomb()){counter++;}
//                            if(grid[i][j+1].isBomb()){counter++;}
//                        }else {
//                            if (j == gridSize - 1) {
//                                //bottom right corner
//                                if(grid[i-1][j-1].isBomb()){counter++;}
//                                if(grid[i-1][j].isBomb()){counter++;}
//                                if(grid[i][j-1].isBomb()){counter++;}
//                            }else{
//                                //bottom row
//                                if(grid[i-1][j-1].isBomb()){counter++;}
//                                if(grid[i-1][j].isBomb()){counter++;}
//                                if(grid[i-1][j+1].isBomb()){counter++;}
//                                if(grid[i][j-1].isBomb()){counter++;}
//                                if(grid[i][j+1].isBomb()){counter++;}
//                            }
//                        }
//                    }else{
//                        if(j == 0){
//                            //left side
//                            if(grid[i-1][j].isBomb()){counter++;}
//                            if(grid[i-1][j+1].isBomb()){counter++;}
//                            if(grid[i][j+1].isBomb()){counter++;}
//                            if(grid[i+1][j].isBomb()){counter++;}
//                            if(grid[i+1][j+1].isBomb()){counter++;}
//                        }else{
//                            if(j == gridSize-1){
//                                //right side
//                                if(grid[i-1][j-1].isBomb()){counter++;}
//                                if(grid[i-1][j].isBomb()){counter++;}
//                                if(grid[i][j-1].isBomb()){counter++;}
//                                if(grid[i+1][j-1].isBomb()){counter++;}
//                                if(grid[i+1][j].isBomb()){counter++;}
//                            }else{
//                                //middle
//                                if(grid[i-1][j-1].isBomb()){counter++;}
//                                if(grid[i-1][j].isBomb()){counter++;}
//                                if(grid[i-1][j+1].isBomb()){counter++;}
//                                if(grid[i][j-1].isBomb()){counter++;}
//                                if(grid[i][j+1].isBomb()){counter++;}
//                                if(grid[i+1][j-1].isBomb()){counter++;}
//                                if(grid[i+1][j].isBomb()){counter++;}
//                                if(grid[i+1][j+1].isBomb()){counter++;}
//                            }
//                        }
//                    }
//                }
//                grid[i][j].setAdjacent(counter);
//            }
//        }
    }

    public void fillBoardView(JPanel view)
    {
        for (Tile[] row : grid) {
            for(Tile t: row) {
                view.add(t);
            }
        }
    }

    public void enableAllTiles(){
        for (Tile[] row : grid) {
            for(Tile t: row) {
                t.setEnabled(true);
            }
        }
    }

    public void disableAllTiles(){
        for (Tile[] row : grid) {
            for(Tile t: row) {
                t.setEnabled(false);
            }
        }
    }
}
