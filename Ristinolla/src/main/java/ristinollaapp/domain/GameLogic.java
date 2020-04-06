package ristinollaapp.domain;

import java.util.ArrayList;

public class GameLogic {

    private String turn;
    private String[][] score;
    private boolean gameOver;
    private int winnerRowSize;
    private String winner;

    public GameLogic(int size, int winnerRow) {

        this.winnerRowSize = winnerRow;
        this.score = new String[size][size];
        this.turn = "X";
        this.gameOver = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                score[i][j] = "e";
            }
        }
    }

    public void updateScore(int x, int y) {
        this.score[x][y] = this.turn;
    }

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

    public String getTurn() {
        return this.turn;
    }

    public Boolean GameIsOver() {
        return this.gameOver;
    }

    public void changeTurn() {
        if (this.turn.equals("X")) {
            this.turn = "0";
        } else {
            this.turn = "X";
        }
    }

    public void printScoreBoard() {
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                System.out.print(score[i][j]);
                System.out.print(", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public String getSymbolFromScoreBoard(int x, int y) {
        return this.score[x][y];
    }

    public boolean isThereAWinnerInColumns(int x, int y) {
        int sameSymbolsInAColumn = 1;

        for (int i = 1; i < this.score.length; i++) {
            if (this.score[i - 1][y].equals(this.score[i][y]) && !this.score[i][y].equals("e") && !this.score[i - 1][y].equals("e")) {
                sameSymbolsInAColumn++;
            }
        }
        if (sameSymbolsInAColumn == this.score.length) {
            this.gameOver = true;
        }
        return this.gameOver;
    }

    public boolean isThereAWinnerInRows(int x, int y) {
        int sameSymbolsInARow = 1;

        for (int i = 1; i < this.score.length; i++) {
            if (this.score[x][i - 1].equals(this.score[x][i]) && !this.score[x][i].equals("e") && !this.score[x][i - 1].equals("e")) {
                sameSymbolsInARow++;
            }
        }

        if (sameSymbolsInARow == this.score.length) {
            this.gameOver = true;
        }
        return this.gameOver;
    }

    public boolean isThereAWinnerDiagonalFromRighDown(int x, int y) {
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
            if (fromRightDown.get(i - 1).equals(fromRightDown.get(i))) {
                apu++;
            }
        }

        if (apu == fromRightDown.size() && apu >= 3) {
            this.gameOver = true;
        }
        return this.gameOver;
    }

    public boolean isThereAWinnerDiagonalFromLeftUp(int x, int y) {
        int row = 0;
        int col = 0;
        if (x + y < this.score.length - 1) {
            row = x + y;
            col = 0;
        } else {
            row = this.score.length - 1;
            col = x -this.score.length + 1 + y; 
        }

        ArrayList<String> fromLeftUp = new ArrayList<>();

        while (row >= 0 && col < this.score.length) {
            fromLeftUp.add(this.score[row][col]);
            row--;
            col++;
        }

        int apu = 1;

        for (int i = 1; i < fromLeftUp.size(); i++) {
            if (fromLeftUp.get(i - 1).equals(fromLeftUp.get(i))) {
                apu++;
            }
        }

        if (apu == fromLeftUp.size() && apu >= 3) {
            this.gameOver = true;
        }
        return this.gameOver;
    }

    public boolean isThereAWinnerCross() {
        return false;
    }

    public boolean isThereAWinner(int x, int y) {
        if (isThereAWinnerInColumns(x, y) || isThereAWinnerInRows(x, y) || isThereAWinnerDiagonalFromRighDown(x, y) || isThereAWinnerDiagonalFromLeftUp(x, y)) {
            this.gameOver = true;
            changeTurn();
            this.winner = this.turn;
            return true;
        } else {
            return false;
        }
    }
}
