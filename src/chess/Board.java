
package chess;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Sylvia Byer, Kent Huang
 */
public class Board {
    
    public Piece[][][] board = new Piece[8][8][2];
    /**
     * constructor
     * pre:a 3 dimensional piece array(another board array)
     * post:create a new board object with board array same as the input
     */
    public Board(Piece[][][]p){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                for(int k=0;k<2;k++){
                    board[i][j][k]=p[i][j][k];
                }
            }
        }
    }
    /**
     * constructor
     * pre:
     * post:create a new board object with board array same as a standard chess game
     */
    public Board(){//t means if its ai, n is if ai is white or black        
        //for(int i=0;i<2;i++){
            board[0][0][0]=Piece.ROOK;
            board[0][7][0]=Piece.ROOK;
            board[0][1][0]=Piece.KNIGHT;
            board[0][6][0]=Piece.KNIGHT;
            board[0][2][0]=Piece.BISHOP;
            board[0][5][0]=Piece.BISHOP;
            for(int j=0;j<8;j++){
                board[1][j][0]=Piece.NEWPAWN;
            }
            board[7][0][1]=Piece.ROOK;
            board[7][7][1]=Piece.ROOK;
            board[7][1][1]=Piece.KNIGHT;
            board[7][6][1]=Piece.KNIGHT;
            board[7][2][1]=Piece.BISHOP;
            board[7][5][1]=Piece.BISHOP;
            for(int j=0;j<8;j++){
                board[6][j][1]=Piece.NEWPAWN;
            }
        //}
        board[0][4][0]=Piece.KING;
        board[7][3][1]=Piece.QUEEN;
        board[7][4][1]=Piece.KING;
        board[0][3][0]=Piece.QUEEN;
    }
    /**
     * find numbers of each piece on board
     * pre:
     * post:return the array containing information about number of pieces
     */
    public int[][] findNumberPieces(){
        int[][] pieceArray = new int[6][2];
        for(int p = 0; p < 2; p++){
            for(int a = 0; a < 8; a++){
                for(int b = 0; b < 8; b++){
                    if(board[a][b][p] == Piece.PAWN){
                        pieceArray[0][p]++;
                    }else if(board[a][b][p] == Piece.KNIGHT){
                        pieceArray[1][p]++;
                    }else if(board[a][b][p] == Piece.BISHOP){
                        pieceArray[2][p]++;
                    }else if(board[a][b][p] == Piece.ROOK){
                        pieceArray[3][p]++;
                    }else if(board[a][b][p] == Piece.QUEEN){
                        pieceArray[4][p]++;
                    }else if(board[a][b][p] == Piece.KING){
                        pieceArray[5][p]++;
                    }
                }
            }
        }
        return pieceArray;
    }
    /**
     * make a copy of an array, we have to do this because object oriented programming forces us to do this
     * pre: array
     * post: a cloned array
     */
    public int[][] cloneArray(int[][] array){
        int[][] returnArray = new int[array.length][array[0].length];
        
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                returnArray[i][j] = array[i][j];
            }
        }
        return returnArray;
    }
    /**
     * find the value of board
     * pre:
     * post:return a value, positive in favour of black, negative in favour of white
     */
    public double findBoardValue(){
        
        //use number of pieces on the board to determine stage
        
        double totalPieceValues = 0;
        
        int[][] pieceArray = cloneArray(findNumberPieces());
        
        if(pieceArray[5][0] == 0){
            return 1000000;
        }else if (pieceArray[5][1] == 0){
            return -1000000;
        }
        
        double value = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < pieceArray[i][j]; k++){
                    int side = j == 0? -1:+1;
                    int pieceValue = 0;
                    if(i == 0){
                        pieceValue = 1;                  
                    }else if(i == 1){
                        pieceValue = 3;
                    }else if(i == 2){
                        pieceValue = 3;
                    }else if(i == 3){
                        pieceValue = 5;
                    }else if(i == 4){
                        pieceValue = 9;
                    }
                    
                    totalPieceValues += pieceValue;
                    value += side * pieceValue;
                }
            }
        }
        double midSquares = 0;
        double edgeValue = 0;
        double pawns = 0;
        //78 total
        if(totalPieceValues > 62){//begin game
            midSquares = midSquaresValue();
            
        }else if(totalPieceValues > 24){//midgame
            
            for(int p = 0; p < 2; p++){
                for(int a = 0; a < 8; a++){
                    for(int b = 0; b < 8; b++){
                        if(onEdge(a, b)){
                            if((board[a][b][p] == Piece.BISHOP)||(board[a][b][p] == Piece.KNIGHT)){
                                if((board[a][b][p] == Piece.QUEEN)||(board[a][b][p] == Piece.ROOK)){
                                    if(p == 0){
                                        edgeValue--;
                                    }else{
                                        edgeValue++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            //method to check if piece is on the edge, negative value
            //not sure if should be used 'cause it runs through entire board-not worth it
            //B, R, Q, N better in open
        }else{//endgame
            //less pieces / edges surrounding B, R, Q, value ^ || N down
            //passed pawns //player w/ pawns closest to other sidre
            
            //i guess maybe something like if white pawns are on the black side of the board, increase worth
            for(int p = 0; p < 2; p++){
                for(int a = 4; a < 8; a++){
                    for(int b = 0; b < 8; b++){
                        
                        if(board[a][b][p] == Piece.PAWN){
                            if(p == 0){
                                pawns += 0.5;
                            }else{
                                pawns += 0.5;
                            }
                        }
                    }
                }
            }
            
            //doubled pawns = weak (throughout whole game)
            if((pieceArray[3][0] == 2)&&(pieceArray[4][1] == 1)){
                value -= 1;//some value :/ //this is if black has two rooks, white has one queen
            }else if((pieceArray[3][1] == 2)&&(pieceArray[4][0] == 1)){
                value += 1;//some value :/
            }
            
        }
        value = value + midSquares + doublePawnValue() + edgeValue + pawns;
        return value;
    }
    /**
     * determine if something is on the edge
     * pre:coordinates of the piece
     * post:return a boolean for that
     */
    public boolean onEdge(int a, int b){
        
        boolean edge = false;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if((a+i < 0)||(a+i >= 8)||(b+i < 0)||(b+i >= 8)){
                    edge = true;
                }
            }
        }
        return edge;
    }
    /**
     * calculate value for pawns
     * pre:
     * post:return the value for pawns
     */
    public int doublePawnValue(){
        int value = 0;
        for(int p = 0; p < 2; p++){
            for(int a = 0; a < 8; a++){
                int pawns = 0;
                for(int b = 0; b < 8; b++){
                    if((board[a][b][p] == Piece.PAWN)||(board[a][b][p] == Piece.NEWPAWN)||(board[a][b][p] == Piece.ENPASSANT)){
                        pawns++;
                    }
                }
                if(p == 0){
                    value -= pawns;
                }else{
                    value += pawns;
                }
            }
        }
        return value;
    }
    /**
     * calculate value for knight and pawn
     * pre:
     * post:return the value of knight and pawn
     */
    public int midSquaresValue(){
        int midSquares = 0;
        for(int i = 3; i <= 4; i++){
            for(int j = 3; j <= 4; j++){
                for(int a = -2; a <= 2; a += 4){
                    for(int b = -1; b <= 1; b += 2){
                        if(board[i+a][j+b][0] == Piece.KNIGHT){
                            midSquares--;
                        }if(board[i+b][j+a][0] == Piece.KNIGHT){
                            midSquares--;
                        }if(board[i+a][j+b][1] == Piece.KNIGHT){
                            midSquares++;
                        }if(board[i+b][j+a][1] == Piece.KNIGHT){
                            midSquares++;
                        }

                        if(board[i+1][j][0] == Piece.PAWN){//using i as y //this also changes depending if the board array gets swapped
                            midSquares--;
                        }if(board[i-1][j][1] == Piece.PAWN){
                            midSquares++;
                        }if(board[i+1][j][0] == Piece.NEWPAWN){//using i as y //this also changes depending if the board array gets swapped
                            midSquares--;
                        }if(board[i-1][j][1] == Piece.NEWPAWN){
                            midSquares++;
                        }if(board[i+1][j][0] == Piece.NEWPAWN){//using i as y //this also changes depending if the board array gets swapped
                            midSquares -= 2;
                        }if(board[i-1][j][1] == Piece.NEWPAWN){
                            midSquares += 2;
                        }
                        //is there a variable contianing if the pawn can move two squares? that is necessary here
                    }
                }
            }
        }
        return midSquares;
    }

    /**
     * find valid moves
     * pre:player number, coordinates
     * post:boolean array with valid moves being true
     */
    public boolean[][] analyzeBoard(int p,int a,int b){
        boolean[][]r=new boolean[8][8];
        boolean[][]temp=analyzeWholeBoard((p-1)*(p-1),a,b);
        if(board[a][b][p]!=Piece.KING){
            for(int m=0;m<8;m++){
                for(int n=0;n<8;n++){
                    if(temp[m][n]&&board[m][n][p]==Piece.KING){
                        return r;
                    }
                }
            }
        }
        if(board[a][b][p]!=null){
        switch(board[a][b][p]){
            case NEWPAWN:
                if(board[a+2-4*p][b][p]==null&&board[a+2-4*p][b][(p-1)*(p-1)]==null){
                    r[a+2-4*p][b]=true;
                }
            case ENPASSANT:
            case PAWN:
                if(board[a+1-2*p][b][p]==null&&board[a+1-2*p][b][(p-1)*(p-1)]==null){
                    r[a+1-2*p][b]=true;
                }
                if(b+1<8&&board[a+1-2*p][b+1][(p-1)*(p-1)]!=null){
                    r[a+1-2*p][b+1]=true;
                }
                if(b-1>=0&&board[a+1-2*p][b-1][(p-1)*(p-1)]!=null){
                    r[a+1-2*p][b-1]=true;
                }
                if(b-1>=0&&board[a][b-1][(p-1)*(p-1)]==Piece.ENPASSANT&&board[a+1-2*p][b-1][p]==null&&board[a+1-2*p][b-1][(p-1)*(p-1)]==null){
                    r[a+1-2*p][b-1]=true;
                }
                if(b+1<8&&board[a][b+1][(p-1)*(p-1)]==Piece.ENPASSANT&&board[a+1-2*p][b+1][p]==null&&board[a+1-2*p][b+1][(p-1)*(p-1)]==null){
                    r[a+1-2*p][b-1]=true;
                }
                break;
            case ROOK:
                int i,j,count=0;
                for(int k=1;count<2;k*=-1){
                    i=a;j=b;
                    while(i+k<8&&i+k>=0&&board[i+k][j][p]==null&&board[i+k][j][(p-1)*(p-1)]==null){
                        r[i+k][j]=true;
                        i+=k;
                    }
                    if(i+k<8&&i+k>=0&&board[i+k][j][(p-1)*(p-1)]!=null){
                        r[i+k][j]=true;
                    }
                    i=a;j=b;
                    while(j+k<8&&j+k>=0&&board[i][j+k][p]==null&&board[i][j+k][(p-1)*(p-1)]==null){
                        r[i][j+k]=true;
                        j+=k;
                    }
                    if(j+k<8&&j+k>=0&&board[i][j+k][(p-1)*(p-1)]!=null){
                        r[i][j+k]=true;
                    }
                    count++;
                }
                break;
            case KNIGHT:
                if(a+1<8&&b+2<8&&board[a+1][b+2][p]==null){
                    r[a+1][b+2]=true;
                }
                if(a+1<8&&b-2>=0&&board[a+1][b-2][p]==null){
                    r[a+1][b-2]=true;
                }
                if(a-1>=0&&b+2<8&&board[a-1][b+2][p]==null){
                    r[a-1][b+2]=true;
                }
                if(a-1>=0&&b-2>=0&&board[a-1][b-2][p]==null){
                    r[a-1][b-2]=true;
                }
                if(a+2<8&&b+1<8&&board[a+2][b+1][p]==null){
                    r[a+2][b+1]=true;
                }
                if(a+2<8&&b-1>=0&&board[a+2][b-1][p]==null){
                    r[a+2][b-1]=true;
                }
                if(a-2>=0&&b+1<8&&board[a-2][b+1][p]==null){
                    r[a-2][b+1]=true;
                }
                if(a-2>=0&&b-1>=0&&board[a-2][b-1][p]==null){
                    r[a-2][b-1]=true;
                }
                break;
            case BISHOP:
                count=0;
                for(int k=1;count<2;k*=-1){
                    i=a;j=b;
                    while(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][p]==null&&board[i+k][j+k][(p-1)*(p-1)]==null){
                        r[i+k][j+k]=true;
                        i+=k;
                        j+=k;
                    }
                    if(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][(p-1)*(p-1)]!=null){
                        r[i+k][j+k]=true;
                    }
                    i=a;j=b;
                    while(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][p]==null&&board[i+k][j-k][(p-1)*(p-1)]==null){
                        r[i+k][j-k]=true;
                        i+=k;
                        j-=k;
                    }
                    if(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][(p-1)*(p-1)]!=null){
                        r[i+k][j-k]=true;
                    }
                    count++;
                }
                break;
            case QUEEN:
                count=0;
                for(int k=1;count<2;k*=-1){
                    i=a;j=b;
                    while(i+k<8&&i+k>=0&&board[i+k][j][p]==null&&board[i+k][j][(p-1)*(p-1)]==null){
                        r[i+k][j]=true;
                        i+=k;
                    }
                    if(i+k<8&&i+k>=0&&board[i+k][j][(p-1)*(p-1)]!=null){
                        r[i+k][j]=true;
                    }
                    i=a;j=b;
                    while(j+k<8&&j+k>=0&&board[i][j+k][p]==null&&board[i][j+k][(p-1)*(p-1)]==null){
                        r[i][j+k]=true;
                        j+=k;
                    }
                    if(j+k<8&&j+k>=0&&board[i][j+k][(p-1)*(p-1)]!=null){
                        r[i][j+k]=true;
                    }
                    i=a;j=b;
                    while(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][p]==null&&board[i+k][j+k][(p-1)*(p-1)]==null){
                        r[i+k][j+k]=true;
                        i+=k;
                        j+=k;
                    }
                    if(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][(p-1)*(p-1)]!=null){
                        r[i+k][j+k]=true;
                    }
                    i=a;j=b;
                    while(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][p]==null&&board[i+k][j-k][(p-1)*(p-1)]==null){
                        r[i+k][j-k]=true;
                        i+=k;
                        j-=k;
                    }
                    if(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][(p-1)*(p-1)]!=null){
                        r[i+k][j-k]=true;
                    }
                    count++;
                }
                break;
            case KING:
                if(a+1<8&&b+1<8&&board[a+1][b+1][p]==null){
                    r[a+1][b+1]=true;
                }
                if(b+1<8&&board[a][b+1][p]==null){
                    r[a][b+1]=true;
                }
                if(a-1>=0&&b+1<8&&board[a-1][b+1][p]==null){
                    r[a-1][b+1]=true;
                }
                if(a-1>=0&&board[a-1][b][p]==null){
                    r[a-1][b]=true;
                }
                if(a-1>=0&&b-1>=0&&board[a-1][b-1][p]==null){
                    r[a-1][b-1]=true;
                }
                if(b-1>=0&&board[a][b-1][p]==null){
                    r[a][b-1]=true;
                }
                if(a+1<8&&b-1>=0&&board[a+1][b-1][p]==null){
                    r[a+1][b-1]=true;
                }
                if(a+1<8&&board[a+1][b][p]==null){
                    r[a+1][b]=true;
                }
                temp=analyzeWholeBoard((p-1)*(p-1),a,b);
                for(int m=0;m<8;m++){
                    for(int n=0;n<8;n++){
                        if(temp[m][n]){
                            r[m][n]=false;
                        }
                    }
                }
            break;
            default:
        }
        }
        return r;
    }
    /**
     * find valid moves for the whole board including opponent pieces
     * pre:player number, coordinates
     * post:find if the king is check if the piece at x,y is moved away
     */
    //the limitation here is we don't know where the piece at x,y is moved away, we can do a for loop in analyzeBoard for that(if we had more time)
    public boolean[][] analyzeWholeBoard(int p,int x,int y){
        boolean[][]r=new boolean[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                r[i][j]=false;
            }
        }
        Piece tempPiece=Piece.KING;
        if(x>0){
            tempPiece=board[x][y][(p-1)*(p-1)];
            board[x][y][(p-1)*(p-1)]=null;
        }
        for(int a=0;a<8;a++){
            for(int b=0;b<8;b++){
                if(board[a][b][p]!=null){
                switch(board[a][b][p]){
                    case NEWPAWN:
                        if(board[a+2-4*p][b][p]==null&&board[a+2-4*p][b][(p-1)*(p-1)]==null){
                            r[a+2-4*p][b]=true;
                        }
                    case ENPASSANT:
                    case PAWN:
                        if(board[a+1-2*p][b][p]==null&&board[a+1-2*p][b][(p-1)*(p-1)]==null){
                            r[a+1-2*p][b]=true;
                        }
                        if(b+1<8&&board[a+1-2*p][b+1][(p-1)*(p-1)]!=null){
                            r[a+1-2*p][b+1]=true;
                        }
                        if(b-1>=0&&board[a+1-2*p][b-1][(p-1)*(p-1)]!=null){
                            r[a+1-2*p][b-1]=true;
                        }
                        if(b-1>=0&&board[a][b-1][(p-1)*(p-1)]==Piece.ENPASSANT&&board[a+1-2*p][b-1][p]==null&&board[a+1-2*p][b-1][(p-1)*(p-1)]==null){
                            r[a+1-2*p][b-1]=true;
                        }
                        if(b+1<8&&board[a][b+1][(p-1)*(p-1)]==Piece.ENPASSANT&&board[a+1-2*p][b+1][p]==null&&board[a+1-2*p][b+1][(p-1)*(p-1)]==null){
                            r[a+1-2*p][b-1]=true;
                        }
                        break;
                    case ROOK:
                        int i,j,count=0;
                        for(int k=1;count<2;k*=-1){
                            i=a;j=b;
                            while(i+k<8&&i+k>=0&&board[i+k][j][p]==null&&board[i+k][j][(p-1)*(p-1)]==null){
                                r[i+k][j]=true;
                                i+=k;
                            }
                            if(i+k<8&&i+k>=0&&board[i+k][j][(p-1)*(p-1)]!=null){
                                r[i+k][j]=true;
                            }
                            i=a;j=b;
                            while(j+k<8&&j+k>=0&&board[i][j+k][p]==null&&board[i][j+k][(p-1)*(p-1)]==null){
                                r[i][j+k]=true;
                                j+=k;
                            }
                            if(j+k<8&&j+k>=0&&board[i][j+k][(p-1)*(p-1)]!=null){
                                r[i][j+k]=true;
                            }
                            count++;
                        }
                        break;
                    case KNIGHT:
                        if(a+1<8&&b+2<8&&board[a+1][b+2][p]==null){
                            r[a+1][b+2]=true;
                        }
                        if(a+1<8&&b-2>=0&&board[a+1][b-2][p]==null){
                            r[a+1][b-2]=true;
                        }
                        if(a-1>=0&&b+2<8&&board[a-1][b+2][p]==null){
                            r[a-1][b+2]=true;
                        }
                        if(a-1>=0&&b-2>=0&&board[a-1][b-2][p]==null){
                            r[a-1][b-2]=true;
                        }
                        if(a+2<8&&b+1<8&&board[a+2][b+1][p]==null){
                            r[a+2][b+1]=true;
                        }
                        if(a+2<8&&b-1>=0&&board[a+2][b-1][p]==null){
                            r[a+2][b-1]=true;
                        }
                        if(a-2>=0&&b+1<8&&board[a-2][b+1][p]==null){
                            r[a-2][b+1]=true;
                        }
                        if(a-2>=0&&b-1>=0&&board[a-2][b-1][p]==null){
                            r[a-2][b-1]=true;
                        }
                        break;
                    case BISHOP:
                        count=0;
                        for(int k=1;count<2;k*=-1){
                            i=a;j=b;
                            while(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][p]==null&&board[i+k][j+k][(p-1)*(p-1)]==null){
                                r[i+k][j+k]=true;
                                i+=k;
                                j+=k;
                            }
                            if(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][(p-1)*(p-1)]!=null){
                                r[i+k][j+k]=true;
                            }
                            i=a;j=b;
                            while(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][p]==null&&board[i+k][j-k][(p-1)*(p-1)]==null){
                                r[i+k][j-k]=true;
                                i+=k;
                                j-=k;
                            }
                            if(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][(p-1)*(p-1)]!=null){
                                r[i+k][j-k]=true;
                            }
                            count++;
                        }
                        break;
                    case QUEEN:
                        count=0;
                        for(int k=1;count<2;k*=-1){
                            i=a;j=b;
                            while(i+k<8&&i+k>=0&&board[i+k][j][p]==null&&board[i+k][j][(p-1)*(p-1)]==null){
                                r[i+k][j]=true;
                                i+=k;
                            }
                            if(i+k<8&&i+k>=0&&board[i+k][j][(p-1)*(p-1)]!=null){
                                r[i+k][j]=true;
                            }
                            i=a;j=b;
                            while(j+k<8&&j+k>=0&&board[i][j+k][p]==null&&board[i][j+k][(p-1)*(p-1)]==null){
                                r[i][j+k]=true;
                                j+=k;
                            }
                            if(j+k<8&&j+k>=0&&board[i][j+k][(p-1)*(p-1)]!=null){
                                r[i][j+k]=true;
                            }
                            i=a;j=b;
                            while(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][p]==null&&board[i+k][j+k][(p-1)*(p-1)]==null){
                                r[i+k][j+k]=true;
                                i+=k;
                                j+=k;
                            }
                            if(j+k<8&&j+k>=0&&i+k<8&&i+k>=0&&board[i+k][j+k][(p-1)*(p-1)]!=null){
                                r[i+k][j+k]=true;
                            }
                            i=a;j=b;
                            while(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][p]==null&&board[i+k][j-k][(p-1)*(p-1)]==null){
                                r[i+k][j-k]=true;
                                i+=k;
                                j-=k;
                            }
                            if(j-k<8&&j-k>=0&&i+k<8&&i+k>=0&&board[i+k][j-k][(p-1)*(p-1)]!=null){
                                r[i+k][j-k]=true;
                            }
                            count++;
                        }
                        break;
                    case KING:
                        if(a+1<8&&b+1<8&&board[a+1][b+1][p]==null){
                            r[a+1][b+1]=true;
                        }
                        if(b+1<8&&board[a][b+1][p]==null){
                            r[a][b+1]=true;
                        }
                        if(a-1>=0&&b+1<8&&board[a-1][b+1][p]==null){
                            r[a-1][b+1]=true;
                        }
                        if(a-1>=0&&board[a-1][b][p]==null){
                            r[a-1][b]=true;
                        }
                        if(a-1>=0&&b-1>=0&&board[a-1][b-1][p]==null){
                            r[a-1][b-1]=true;
                        }
                        if(b-1>=0&&board[a][b-1][p]==null){
                            r[a][b-1]=true;
                        }
                        if(a+1<8&&b-1>=0&&board[a+1][b-1][p]==null){
                            r[a+1][b-1]=true;
                        }
                        if(a+1<8&&board[a+1][b][p]==null){
                            r[a+1][b]=true;
                        }
                    break;
                    default:
                }
            }
        }
        }
        if(x>0){
            board[x][y][(p-1)*(p-1)]=tempPiece;
        }
        return r;
    }
    /**
     * make a copy of a board, luckily the only field in the board class is board array
     * pre: 
     * post: a cloned board
     */
    public Board clone(){
        Board b=new Board(board);
        return b;
    }
}