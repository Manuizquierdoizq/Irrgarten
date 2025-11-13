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

    public void spreadPlayers(ArrayList<Player> players) {
        for (Player p : players) {
            int[] pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }

    public boolean haveAWinner() {
        return this.players[this.exitRow][this.exitCol] != null;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                str.append(this.labyrinth[i][j]);
            }
            str.append("\n");
        }
        return str.toString();
    }

    public void addMonster(int row, int col, Monster monster) {
        if (posOK(row, col) && emptyPos(row, col)) {
            labyrinth[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
            monster.setPos(row, col);
        }
    }

    public Monster putPlayer(Directions direction, Player player) {
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newpos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newpos[ROW], newpos[COL], player);
        return monster;
    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        int incRow = 0;
        int incCol = 0;

        
        if (orientation == Orientation.VERTICAL) {
            incRow = 1;
        } else {
            incCol = 1;
        }
        
        int row=startRow;
        int col=startCol;
        
        while(posOK(row, col) && emptyPos(row, col) && length > 0){
            labyrinth[row][col] = BLOCK_CHAR;
            length--;
            row+= incRow;
            col+= incCol;
        }
    }

    public ArrayList<Directions> validMoves(int row, int col) {
        ArrayList<Directions> output = new ArrayList<>();
        if (canStepOn(row + 1, col)) {
            output.add(Directions.DOWN);
        }
        if (canStepOn(row - 1, col)) {
            output.add(Directions.UP);
        }
        if (canStepOn(row, col + 1)) {
            output.add(Directions.RIGHT);
        }
        if (canStepOn(row, col - 1)) {
            output.add(Directions.LEFT);
        }

        return output;
    }

    private boolean posOK(int row, int col) {
        return row >= 0 && row < nRows && col >= 0 && col < nCols;
    }

    private boolean emptyPos(int row, int col) {
        return labyrinth[row][col] == EMPTY_CHAR;
    }

    private boolean monsterPos(int row, int col) {
        return labyrinth[row][col] == MONSTER_CHAR;
    }

    private boolean exitPos(int row, int col) {
        return row == exitRow && col == exitCol;
    }

    private boolean combatPos(int row, int col) {
        return labyrinth[row][col] == COMBAT_CHAR;
    }

    private boolean canStepOn(int row, int col) {
        return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col)
                || exitPos(row, col));
    }

    private void updateOldPos(int row, int col) {
        if (posOK(row, col)) {
            if (combatPos(row, col)) {
                labyrinth[row][col] = MONSTER_CHAR;
            } else {
                labyrinth[row][col] = EMPTY_CHAR;
            }
        }
    }

    private int[] dir2Pos(int row, int col, Directions direction) {
        int[] newPos = new int[2];
        newPos[ROW] = row;
        newPos[COL] = col;

        if (direction == Directions.UP) {
            newPos[ROW]--;
        } else if (direction == Directions.DOWN) {
            newPos[ROW]++;
        } else if (direction == Directions.LEFT) {
            newPos[COL]--;
        } else if (direction == Directions.RIGHT) {
            newPos[COL]++;
        }
        return newPos;
    }

    private int[] randomEmptyPos() {
        int[] newPos = new int[2];
        int row = 0, col = 0;
        do {
            row = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
        } while (!emptyPos(row, col));

        newPos[ROW] = row;
        newPos[COL] = col;
        return newPos;
    }

    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        Monster output = null;

        if (canStepOn(row, col)) {
            if (posOK(oldRow, oldCol)) {
                Player p = players[oldRow][oldCol];
                if (p == player) {
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }

            boolean monsterPos = monsterPos(row, col);

            if (monsterPos) {
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            } else {
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }

            players[row][col] = player;
            player.setPos(row, col);
        }

        return output;
    }

}
