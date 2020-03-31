
package ristinollaapp.domain;


public class GameLogic {
    
    private String turn;
    private String[][] score;
    private boolean gameOver;
    private int winnerRowSize;
    
    public GameLogic(int size, int winnerRow){
        
        this.winnerRowSize = winnerRow;
        this.score = new String[size][size];
        this.turn = "X";
        this.gameOver = false;
        
    }
    
    public void updateScore(int x, int y){
        this.score[x][y] = this.turn;
    }
    
    public String getTurn(){
        return this.turn;
    }
    
    public Boolean GameIsOver(){
        return this.gameOver;
    }
    
    public void changeTurn(){
        if (this.turn.equals("X")){
            this.turn = "0";
        } else {
            this.turn = "X";
        }
    }
    
    public void printScoreBoard(){
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                System.out.print(score[i][j]);
                System.out.print(", ");
            }
            System.out.println("");
        }
    }
}
