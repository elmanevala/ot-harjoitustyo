package ristinollaapp.domain;

import java.util.ArrayList;

/**
 * Has methods to change and observe the current status of the game.
 *
 */
public class GameLogic {

    private String turn;
    private String[][] score;
    private boolean gameOver;
    private int rowSize;
    private String winner;
    private int xmoves;
    private int omoves;
    private int winnermoves;
    private int gridSize;

    /**
     * Stores the information about an ongoing game and has methods to change
     * the status of the game.
     */
    public GameLogic(int size, int rowsize) {

        this.gridSize = size;
        this.rowSize = rowsize;
        this.score = new String[size][size];
        this.turn = "X";
        this.gameOver = false;
        this.xmoves = 0;
        this.omoves = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                score[i][j] = "e";
            }
        }
    }

    /**
     * Sets a new value to a given place in the scoreboard.
     *
     * @param x horizontal location
     * @param y vertical location
     *
     */
    public void updateScore(int x, int y) {
        this.score[x][y] = this.turn;
        if (this.turn == "X") {
            this.xmoves++;
        } else {
            this.omoves++;
        }
    }

    /**
     * Checks if a given location in scoreboard is empty, meaning it has the
     * value "e".
     *
     * @param x horizontal location
     * @param y vertical location
     *
     * @return true if location empty, otherwise false
     */
    public boolean spaceEmpty(int x, int y) {
        if (this.score[x][y].equals("e")) {
            return true;
        } else {
            return false;
        }
    }

    public String getWinner() {
        return this.winner;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public String getTurn() {
        return this.turn;
    }

    public Boolean gameIsOver() {
        return this.gameOver;
    }

    /**
     * Changes the turn.
     *
     * @return whose turn it is
     */
    public String changeTurn() {
        if (this.turn.equals("X")) {
            this.turn = "0";
        } else {
            this.turn = "X";
        }

        return this.turn;
    }

    public String getSymbolFromScoreBoard(int x, int y) {
        return this.score[x][y];
    }

    /**
     * Checks if the scoreboard is full, is used to see if the game has ended in
     * a draw.
     *
     * @return true if scoreboard is full
     */
    public boolean scoreboardFull() {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.rowSize; j++) {
                if (this.score[i][j].equals("e")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if there is a winning row of values in the column of the given
     * location.
     *
     * @param x horizontal location
     * @param y vertical location
     *
     * @return true, if winning row is found
     */
    public boolean isThereAWinnerInColumns(int x, int y) {
        int sameSymbolsInAColumn = 1;

        for (int i = 1; i < this.score.length; i++) {
            if (this.score[i - 1][y].equals(this.score[i][y]) && !this.score[i][y].equals("e") && !this.score[i - 1][y].equals("e")) {
                sameSymbolsInAColumn++;
            } else {
                sameSymbolsInAColumn = 1;
            }
            if (sameSymbolsInAColumn >= this.rowSize) {
                this.gameOver = true;
            }

        }

        return this.gameOver;
    }

    /**
     * Checks if there is a winning row of values in the row of the given
     * location.
     *
     * @param x horizontal location
     * @param y vertical location
     *
     * @return true, if winning row is found
     */
    public boolean isThereAWinnerInRows(int x, int y) {
        int sameSymbolsInARow = 1;

        for (int i = 1; i < this.score.length; i++) {
            if (this.score[x][i - 1].equals(this.score[x][i]) && !this.score[x][i].equals("e") && !this.score[x][i - 1].equals("e")) {
                sameSymbolsInARow++;
            } else {
                sameSymbolsInARow = 1;
            }
            if (sameSymbolsInARow >= this.rowSize) {
                this.gameOver = true;
            }
        }

        return this.gameOver;
    }

    /**
     * Checks if there is a winning row of values in the diagonal line that
     * start from the upper right hand corner and ends in the lower left hand
     * corner. Row has to cross through the given location.
     *
     * @param x horizontal location
     * @param y vertical location
     *
     * @return true, if winning row is found
     */
    public boolean winnerDiagRightDown(int x, int y) {
        int row = 0;
        int col = 0;
        if (x > y) {
            row = x - y;
            col = 0;
        } else {
            row = 0;
            col = y - x;
        }

        ArrayList<String> fromRightDown = new ArrayList<>();

        while (row < this.score.length && col < this.score.length) {
            fromRightDown.add(this.score[row][col]);
            row++;
            col++;
        }

        int apu = 1;

        for (int i = 1; i < fromRightDown.size(); i++) {
            if (fromRightDown.get(i - 1).equals(fromRightDown.get(i)) && !fromRightDown.get(i - 1).equals("e") && !fromRightDown.get(i).equals("e")) {
                apu++;
            } else {
                apu = 1;
            }

            if (apu >= this.rowSize) {
                this.gameOver = true;
                break;
            }
        }

        return this.gameOver;
    }

    /**
     * Checks if there is a winning row of values in the diagonal line that
     * start from the lower right hand corner and ends in the upper left hand
     * corner. Row has to cross through the given location.
     *
     * @param x horizontal location
     * @param y vertical location
     *
     * @return true, if winning row is found
     */
    public boolean winnerDiagLeftUp(int x, int y) {
        int row = 0;
        int col = 0;
        if (x + y < this.score.length - 1) {
            row = x + y;
            col = 0;
        } else {
            row = this.score.length - 1;
            col = x - this.score.length + 1 + y;
        }

        ArrayList<String> fromLeftUp = new ArrayList<>();

        while (row >= 0 && col < this.score.length) {
            fromLeftUp.add(this.score[row][col]);
            row--;
            col++;
        }

        int apu = 1;

        for (int i = 1; i < fromLeftUp.size(); i++) {
            if (fromLeftUp.get(i - 1).equals(fromLeftUp.get(i)) && !fromLeftUp.get(i - 1).equals("e") && !fromLeftUp.get(i).equals("e")) {
                apu++;
            } else {
                apu = 1;
            }
            if (apu >= this.rowSize) {
                this.gameOver = true;
            }
        }

        return this.gameOver;
    }

    /**
     * Checks if there is a winning row of values in the diagonal lines, in the
     * row or in the column of the given location. Sets a new winner and
     * gameover value as true if a winning row is found.
     *
     *
     * @param x horizontal location
     * @param y vertical location
     *
     * @return true, if winning row is found
     */
    public boolean isThereAWinner(int x, int y) {
        if (isThereAWinnerInColumns(x, y) || isThereAWinnerInRows(x, y) || winnerDiagRightDown(x, y) || winnerDiagLeftUp(x, y)) {
            this.gameOver = true;
            changeTurn();
            this.winner = this.turn;
            setWinnerMoves();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the winnerMoves according to the winner of the game.
     */
    public void setWinnerMoves() {
        if (this.turn.equals("X")) {
            this.winnermoves = this.xmoves;
        } else {
            this.winnermoves = this.omoves;
        }
    }

    public int getWinnerMoves() {
        return this.winnermoves;
    }
}
