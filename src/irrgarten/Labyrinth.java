/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author manu
 */
public class Labyrinth {
    private Monster[][] monsters;
    private Player[][] players;
    private char[][] labyrinth;
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;

    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.labyrinth = new char[nRows][nCols];
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                this.labyrinth[i][j] = EMPTY_CHAR;
            }
        }
        
        this.labyrinth[exitRow][exitCol] = EXIT_CHAR;
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        throw new UnsupportedOperationException();
    }
    
    public boolean haveAWinner(){
        return this.players[this.exitRow][this.exitCol]!=null;
    }
    
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < nRows; i++) {            
            for (int j = 0; j < nCols; j++) {
                str.append(this.labyrinth[i][j]);
            }
            str.append("\n"); 
        }
        return str.toString();
    }
    
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            labyrinth[row][col]=MONSTER_CHAR;
            monsters[row][col]=monster;
            monster.setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction,Player player){
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        throw new UnsupportedOperationException();
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    private boolean posOK(int row, int col){
        return row>=0 && row<nRows && col>=0 && col<nCols;
    }
    
    private boolean emptyPos(int row, int col){
        return labyrinth[row][col]==EMPTY_CHAR;
    }
    
    private boolean monsterPos(int row, int col){
        return labyrinth[row][col]==MONSTER_CHAR;
    }
    
    private boolean exitPos(int row, int col){
        return row==exitRow && col==exitCol;
    }
    
    private boolean combatPos(int row, int col){
        return labyrinth[row][col]==COMBAT_CHAR;
    }
    
    private boolean canStepOn(int row, int col){
        return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col)  ||
                exitPos(row, col));
    }
    
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(combatPos(row, col)){
                labyrinth[row][col]=MONSTER_CHAR;
            } else {
                labyrinth[row][col]=EMPTY_CHAR;
            }
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int [] newPos = new int[2];
        newPos[ROW]=row;
        newPos[COL]=col;
        
        if(direction==Directions.UP) newPos[ROW]--;
        else if(direction==Directions.DOWN) newPos[ROW]++;
        else if(direction==Directions.LEFT) newPos[COL]--;
        else if (direction==Directions.RIGHT) newPos[COL]++;
        return newPos;
    }
    
    private int[] randomEmptyPos(){
        int [] newPos = new int[2];
        int row=0,col=0;
        do {
            row = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
        } while(!emptyPos(row, col));
        
        newPos[ROW]=row;
        newPos[COL]=col;
        return newPos;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        throw new UnsupportedOperationException();
    }

}
