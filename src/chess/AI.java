
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
    public AI(int playerNum){
        super.playerNum=playerNum;
    }
    public ArrayList AI(int n,Game g,int pNum){
        boolean gotSomething=false;
        if(n==0){
            ArrayList<Double>tree=new ArrayList<>();
            tree.add(g.b.findBoardValue());
            return tree;
        }else{
            ArrayList<ArrayList>tree=new ArrayList<>();
            for(int i1=0;i1<8;i1++){
                for(int i2=0;i2<8;i2++){
                    if(g.b.board[i1][i2][pNum]!=null){
                        g.validMove=g.b.analyzeBoard(pNum,i1,i2);
                        for(int j=0;j<8;j++){
                            for(int k=0;k<8;k++){
                                if(g.validMove[j][k]){
                                    Game tempGame=g.clone();
                                    tempGame.Move(i1, i2, j, k,pNum);
                                    tree.add(AI(n-1,tempGame,(pNum-1)*(pNum-1)));
                                    gotSomething=true;
                                }
                            }
                        }
                    }
                }
            }
            if(!gotSomething){
                if(n%2==1){
                    ArrayList<Double>check=new ArrayList<>();
                    //check.add(g.b.findBoardValue(pNum));
                    return check;
                }else{
                    ArrayList<Double>check=new ArrayList<>();
                    //check.add(g.b.findBoardValue((pNum-1)*(pNum-1)));
                    return check;
                }
            }
            if(tree.isEmpty()){
                return null;
            }else{
                return tree;
            }
        }
    }
    public ArrayList goDeep(ArrayList a,ArrayList pathStored){
        ArrayList<Double>r=new ArrayList<>();
        while(!a.isEmpty()){
            try{
                Double n=(Double)a.get(0);
                pathStored.add(n);
                a.remove((int)0);
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
    public void calculateMove(int n,Game g){
        ArrayList<ArrayList>a=AI(n,g,playerNum);
        double[] min=new double[a.size()];
        double max=0,num=0;
        int index=0;
        for(int i=0;i<a.size();i++){
            ArrayList<Double>temp=goDeep(a.get(i),new ArrayList<Integer>());
            for(int j=0;j<temp.size();j++){
                if(min[i]<temp.get(j)){
                    min[i]=temp.get(j);
                }
            }
        }
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
                                move(i1,i2,j,k,g);
                                break;
                            }
                        }
                    }
                }
            }
        }       
    }
    @Override
    public void move(int a,int b,int x,int y,Game g){
        g.Move(a,b, x, y,playerNum);
    }
}