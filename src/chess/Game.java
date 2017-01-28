
package chess;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Kent Huang
 */
public class Game {
    
    Board b,undoBoard;
    public boolean[][]validMove=new boolean[8][8];
    /**
     * constructor
     * pre:
     * post:create a new game with a new board
     */
    public Game(){
        b=new Board();
    }
    /**
     * constructor
     * pre:
     * post:create a new game with a specified board
     */
    public Game(Board b){
        this.b=b.clone();
    }
    /**
     * move the piece
     * pre:a,b coordinates of initial position, x,y end position, p is player number
     * post:move piece from a,b to x,y
     */
    public void Move(int a,int c,int x,int y,int p){
        validMove=b.analyzeBoard(p,a,c);
        //rules for pawns and en passant
        if(validMove[x][y]){
            if(b.board[a][c][p]==Piece.ENPASSANT){
                b.board[a][c][p]=Piece.PAWN;
            }
            if(b.board[a][c][p]==Piece.NEWPAWN){
                if((a==3&&p==0)||(a==4&&p==1)){
                    b.board[a][c][p]=Piece.ENPASSANT;
                }else{
                    b.board[a][c][p]=Piece.PAWN;
                }
            }
            if(b.board[a][c][p]==Piece.PAWN){
                if((p==0&&a==6)||(p==1&&a==1)){
                    b.board[a][c][p]=Piece.QUEEN;
                }
            }
            b.board[x][y][p]=b.board[a][c][p];
            b.board[x][y][(p-1)*(p-1)]=null;
            b.board[a][c][p]=null;
        }
    }
    /**
     * make a copy of the game
     * pre: 
     * post: a cloned game
     */
    public Game clone(){
        Game g=new Game(b);
        return g;
    }
    /**
     * save this move
     * pre: 
     * post: save board into undoBoard
     */
    public void saveUndo(){
        undoBoard=b.clone();
    }
}