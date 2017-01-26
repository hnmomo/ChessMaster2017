
package chess;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Sylvia Byer
 */
public abstract class Player {
    public int playerNum;
    public abstract void move(int a,int b,int x,int y,Game g);
    
}