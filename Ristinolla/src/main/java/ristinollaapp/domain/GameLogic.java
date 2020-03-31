package ristinollaapp.domain;

public class GameLogic {

    private String turn;
    private String[][] score;
    private boolean gameOver;
    private int winnerRowSize;

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

    public boolean IsThereAWinnerInRows() {
        int sameSymbolsInARow = 1;

        for (int i = 0; i < this.score.length; i++) {
            for (int j = 1; j < this.score[0].length; j++) {
                if (this.score[i][j - 1].equals(this.score[i][j])) {
                    sameSymbolsInARow++;
                }
                if (sameSymbolsInARow == this.score[0].length) {
                    this.gameOver = true;
                }
            }
            sameSymbolsInARow = 1;
        }
        return this.gameOver;
    }

    public boolean IsThereAWinnerInColumns() {
        int sameSymbolsInAColumn = 1;

        for (int i = 0; i < this.score[0].length; i++) {
            for (int j = 1; j < this.score.length; j++) {
                if (this.score[j - 1][i].equals(this.score[j][i])) {
                    sameSymbolsInAColumn++;
                }
                if (sameSymbolsInAColumn == this.score.length) {
                    this.gameOver = true;
                }
            }
            sameSymbolsInAColumn = 1;
        }
        return this.gameOver;
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
    }

    public String getSymbolFromScoreBoard(int x, int y) {
        return this.score[x][y];
    }
}
