package ristinollaapp.domain;

import java.util.ArrayList;

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

    public void updateScore(int x, int y) {
        this.score[x][y] = this.turn;
        if (this.turn == "X"){
            this.xmoves++;
        } else {
            this.omoves++;
        }
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
    
    public int getRowSize() {
        return this.rowSize;
    }
    
    public int getGridSize(){
        return this.gridSize;
    }

    public String getTurn() {
        return this.turn;
    }

    public Boolean gameIsOver() {
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
        System.out.println("x:n vuorot: " + this.xmoves + ". 0:n vuorot: " + this.omoves);
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
        if (sameSymbolsInAColumn >= this.rowSize) {
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

        if (sameSymbolsInARow >= this.rowSize) {
            this.gameOver = true;
        }
        return this.gameOver;
    }

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
            }
        }

        if (apu >= this.rowSize) {
            this.gameOver = true;
        }
        return this.gameOver;
    }

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
            }
        }

        if (apu >= this.rowSize) {
            this.gameOver = true;
        }
        return this.gameOver;
    }
    
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
    
    
    public void setWinnerMoves(){
        if(this.turn.equals("X")){
            this.winnermoves = this.xmoves;
        } else {
            this.winnermoves = this.omoves;
        }
    }
    
    public int getWinnerMoves(){
        return this.winnermoves;
    }
}
