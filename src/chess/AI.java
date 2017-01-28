
package chess;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Kent Huang, Sylvia Byer
 */
public class AI extends Player{
    /**
     * constructor
     * pre:player number
     * post:create a new AI object with corresponding playerNum
     */
    public AI(int playerNum){
        super.playerNum=playerNum;
    }
    /**
     * calculate all possible moves
     * pre:n is the depth(difficulty) of the AI calculation, g is the current game, pNum is the player number
     * post:create an arrayList containing arraylists of all possibilities in the next step
     */
    public ArrayList AI(int n,Game g,int pNum){
        //base case
        if(n==0){
            //add the value of current board to the array
            ArrayList<Double>tree=new ArrayList<>();
            tree.add(g.b.findBoardValue()*(pNum-0.5));
            return tree;
        }else{
            ArrayList<ArrayList>tree=new ArrayList<>();
            //go through the whole board
            for(int i1=0;i1<8;i1++){
                for(int i2=0;i2<8;i2++){
                    //if there is a piece
                    if(g.b.board[i1][i2][pNum]!=null){
                        //analyze valid moves
                        g.validMove=g.b.analyzeBoard(pNum,i1,i2);
                        //go through the whole board to check for valid moves
                        for(int j=0;j<8;j++){
                            for(int k=0;k<8;k++){
                                //if a move is valid
                                if(g.validMove[j][k]){
                                    //move the piece there and calculate all following possibilities
                                    Game tempGame=g.clone();
                                    tempGame.Move(i1, i2, j, k,pNum);
                                    tree.add(AI(n-1,tempGame,(pNum-1)*(pNum-1)));
                                }
                            }
                        }
                    }
                }
            }
            //if got nothing, end the loop because it is checkmate
            if(tree.isEmpty()){
                ArrayList<Double>a=new ArrayList<>();
                a.add(g.b.findBoardValue()*(playerNum-0.5));
                return a;
            }else{
                return tree;
            }
        }
    }
    /**
     * take out all the numbers from arrayLists of arrayLists
     * pre:a is the initial arrayList, pathStored stores the numbers in that arrayList
     * post:create an arrayList containing all primitive values in the input arraylist
     */
    public ArrayList goDeep(ArrayList a,ArrayList pathStored){
        ArrayList<Double>r=new ArrayList<>();
        while(!a.isEmpty()){
            //if it is a double, add it and remove from original array, if not, an exception will occour
            try{
                Double n=(Double)a.get(0);
                pathStored.add(n);
                a.remove((int)0);
            //if there is an exception, the item in the array is an array, so we open the array again
            }catch(Exception e){
                pathStored=goDeep((ArrayList)a.get(0),pathStored);
                a.remove(0);
            }
        }
        for(int i=0;i<pathStored.size();i++){
            r.add((double)pathStored.get(i));
        }
        return r;
    }
    /**
     * calculate the best move
     * pre:n is the number of depth, g is current game
     * post:move the piece to where it benefits the next n steps most
     */
    public void calculateMove(int n,Game g){
        //calculate all possibilities
        ArrayList<ArrayList>a=AI(n,g,playerNum);
        double[] min=new double[a.size()];
        double max=0,num=0;
        int index=0;
        for(int i=0;i<a.size();i++){
            //find all the values
            ArrayList<Double>temp=goDeep(a.get(i),new ArrayList<Integer>());
            //find the worst possibility for each move
            for(int j=0;j<temp.size();j++){
                if(min[i]>temp.get(j)){
                    min[i]=temp.get(j);
                }
            }
        }
        //find the best of all the worst possibilities to minimize all possible loss
        for(int i=0;i<min.length;i++){
            if(i==0){
                max=min[i];
                index=i;
            }
            if(min[i]>max){
                max=min[i];
                index=i;
            }else if(min[i]==max){
                double random=Math.random();
                if(random>0.5){
                    index=i;
                }
            }
        }
        //go through the board and find the coordinates of that move
        for(int i1=0;i1<8;i1++){
            for(int i2=0;i2<8;i2++){
                if(g.b.board[i1][i2][playerNum]!=null){
                    g.validMove=g.b.analyzeBoard(playerNum,i1,i2);
                    for(int j=0;j<8;j++){
                        for(int k=0;k<8;k++){
                            if(g.validMove[j][k]){
                                num++;
                            }
                            if(num==index){
                                //move it
                                move(i1,i2,j,k,g);
                                break;
                            }
                        }
                    }
                }
            }
        }       
    }
    /**
     * move the piece
     * pre:a,b coordinates of initial position, x,y end position, g is the current game
     * post:move piece from a,b to x,y
     */
    @Override
    public void move(int a,int b,int x,int y,Game g){
        g.Move(a,b, x, y,playerNum);
    }
}