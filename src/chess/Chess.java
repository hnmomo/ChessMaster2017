/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;
/**
 *
 * Kent Huang, Evan Zhao
 */     
public class Chess extends javax.swing.JFrame {
    boolean aiChoosed=false,fogMode=false,friendlyMode=false;
    boolean[][] fog=new boolean[8][8];
    //int colorChoosed;
    File dataFile = new File("dataaa.txt");
    FileWriter out;
    BufferedWriter writeFile;
    FileReader in;
    BufferedReader readFile;
    JLabel [] [] gridboard= new JLabel [8][8];
    Game game=new Game();
    Player[] p=new Player[2];
    int moveNum=0,selectX=-1,selectY=-1,aiNum=1;
    boolean pieceSelected=false;
    public void display(){
        ImageIcon[] icon = new ImageIcon[14];
        icon[0] = new ImageIcon("src/chess/0.png");
        icon[1] = new ImageIcon("src/chess/1.png");
        icon[2] = new ImageIcon("src/chess/2.png");
        icon[3] = new ImageIcon("src/chess/3.png");
        icon[4] = new ImageIcon("src/chess/4.png");
        icon[5] = new ImageIcon("src/chess/5.png");
        icon[6] = new ImageIcon("src/chess/6.png");
        icon[7] = new ImageIcon("src/chess/7.png");
        icon[8] = new ImageIcon("src/chess/8.png");
        icon[9] = new ImageIcon("src/chess/9.png");
        icon[10] = new ImageIcon("src/chess/10.png");
        icon[11] = new ImageIcon("src/chess/11.png");
        icon[12] = new ImageIcon("src/chess/12.png");
        icon[13] = new ImageIcon("src/chess/fog.png");
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                if(game.b.board[i][j][0]!=null){
                    switch(game.b.board[i][j][0]){
                        case ENPASSANT:
                        case NEWPAWN:
                        case PAWN:
                            gridboard[i][j].setIcon(icon[1]);
                            break;
                        case ROOK:
                            gridboard[i][j].setIcon(icon[2]);
                            break;
                        case KNIGHT:
                            gridboard[i][j].setIcon(icon[3]);
                            break;
                        case BISHOP:
                            gridboard[i][j].setIcon(icon[4]);
                            break;
                        case QUEEN:
                            gridboard[i][j].setIcon(icon[5]);
                            break;
                        case KING:
                            gridboard[i][j].setIcon(icon[6]);
                            break;
                        default:
                            gridboard[i][j].setIcon(icon[0]);
                    }
                }
                if(game.b.board[i][j][1]!=null){
                    switch(game.b.board[i][j][1]){
                        case ENPASSANT:
                        case NEWPAWN:
                        case PAWN:
                            gridboard[i][j].setIcon(icon[7]);
                            break;
                        case ROOK:
                            gridboard[i][j].setIcon(icon[8]);
                            break;
                        case KNIGHT:
                            gridboard[i][j].setIcon(icon[9]);
                            break;
                        case BISHOP:
                            gridboard[i][j].setIcon(icon[10]);
                            break;
                        case QUEEN:
                            gridboard[i][j].setIcon(icon[11]);
                            break;
                        case KING:
                            gridboard[i][j].setIcon(icon[12]);
                            break;
                        default:
                            gridboard[i][j].setIcon(icon[0]);
                    }
                }
                if(game.b.board[i][j][1]==null&&game.b.board[i][j][0]==null){
                    gridboard[i][j].setIcon(icon[0]);
                }
            }
        }
        if(fogMode){
            boolean[][] valid=new boolean[8][8];
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(game.b.board[i][j][moveNum]!=null){
                                fog[i][j]=true;
                    }
                    valid=game.b.analyzeBoard(p[moveNum].playerNum,i,j);
                    for(int k=0;k<8;k++){
                        for(int l=0;l<8;l++){
                            if(valid[k][l]){
                                fog[k][l]=true;
                            }
                        }
                    }
                }
            }
            for(int k=0;k<8;k++){
                for(int l=0;l<8;l++){
                    if(!fog[k][l]){
                        gridboard[k][l].setIcon(icon[13]);
                    }
                }
            }
        }
        
    }
    
    public Chess() {
        if(Start.mode.equals("Fog of War")){
            fogMode=true;
        }else if(Start.mode.equals("Friendly to Newbs")){
            friendlyMode=true;
        }
        if(Start.ai.equals("AI")){
            aiChoosed=true;
        }else{
            aiChoosed=false;
        }
        if(Start.num.equals("black")){
            aiNum=0;
        }else{
            aiNum=1;
        }
        if(aiChoosed){
            p[(aiNum-1)*(aiNum-1)]=new Human((aiNum-1)*(aiNum-1));
            p[aiNum]=new AI(aiNum);
        }else{
            p[0]=new Human(0);
            p[1]=new Human(1);
        }
        if(aiNum==0&&aiChoosed){
            moveNum=1;
            ((AI)p[aiNum]).calculateMove(3,game);
        }
        initComponents();
        //piecesPanel1.setBounds(55,55,600,600);
        // make grid 
        gridboard = new JLabel [8][8];
        ImageIcon greenBox = new ImageIcon("src/chess/13.png");
        piecesPanel.setLayout(new GridLayout (8,8,0,0));
        for (int y = 0; y < 8; y++){
            for (int x = 0; x < 8; x++){
//                String position = Integer.toString(x) + ", " + Integer.toString(y);
                gridboard[7-y][x] = new JLabel(); //adds text for debugging
//                gridboard[x][y].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                
                gridboard[7-y][x].setPreferredSize(new Dimension(75,75));
                
                piecesPanel.add(gridboard[7-y][x]);
                
            }
        }
        // make grid 
        
        
        display();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chessPanel = new javax.swing.JPanel();
        backgroundPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        piecesPanel = new javax.swing.JPanel();
        piecesPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        xin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        yin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ain = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bin = new javax.swing.JTextField();
        moveButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chessPanel.setOpaque(false);
        chessPanel.setPreferredSize(new java.awt.Dimension(710, 710));

        backgroundPanel.setOpaque(false);
        backgroundPanel.setPreferredSize(new java.awt.Dimension(710, 710));
        backgroundPanel.setLayout(new javax.swing.OverlayLayout(backgroundPanel));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chess/0.png"))); // NOI18N
        backgroundPanel.add(jLabel6);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chess/chessboard.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        backgroundPanel.add(jLabel1);

        piecesPanel.setOpaque(false);
        piecesPanel.setPreferredSize(new java.awt.Dimension(600, 600));
        piecesPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                piecesPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout piecesPanelLayout = new javax.swing.GroupLayout(piecesPanel);
        piecesPanel.setLayout(piecesPanelLayout);
        piecesPanelLayout.setHorizontalGroup(
            piecesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        piecesPanelLayout.setVerticalGroup(
            piecesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        piecesPanel1.setOpaque(false);
        piecesPanel1.setPreferredSize(new java.awt.Dimension(600, 600));
        piecesPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                piecesPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout piecesPanel1Layout = new javax.swing.GroupLayout(piecesPanel1);
        piecesPanel1.setLayout(piecesPanel1Layout);
        piecesPanel1Layout.setHorizontalGroup(
            piecesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );
        piecesPanel1Layout.setVerticalGroup(
            piecesPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout chessPanelLayout = new javax.swing.GroupLayout(chessPanel);
        chessPanel.setLayout(chessPanelLayout);
        chessPanelLayout.setHorizontalGroup(
            chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chessPanelLayout.createSequentialGroup()
                    .addContainerGap(54, Short.MAX_VALUE)
                    .addComponent(piecesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(56, Short.MAX_VALUE)))
            .addGroup(chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chessPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chessPanelLayout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .addComponent(piecesPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(45, 45, 45)))
        );
        chessPanelLayout.setVerticalGroup(
            chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chessPanelLayout.createSequentialGroup()
                    .addContainerGap(56, Short.MAX_VALUE)
                    .addComponent(piecesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
            .addGroup(chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chessPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(chessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chessPanelLayout.createSequentialGroup()
                    .addContainerGap(44, Short.MAX_VALUE)
                    .addComponent(piecesPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(45, 45, 45)))
        );

        jLabel2.setText("Row(1-8)");

        jLabel3.setText("Column(A-H)");

        jLabel4.setText("Row(1-8)");

        jLabel5.setText("Column(A-H)");

        moveButton.setText("MOVE!");
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        undoButton.setText("UNDO!");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });

        exitButton.setText("EXIT");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        saveButton.setText("SAVE");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadButton.setText("LOAD");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));

        jLabel8.setText("Start:");

        jLabel9.setText("Destination:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(undoButton)
                                    .addComponent(moveButton)
                                    .addComponent(exitButton)
                                    .addComponent(saveButton)
                                    .addComponent(loadButton)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(20, 20, 20)
                                        .addComponent(xin, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(bin, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel9))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(ain)
                                                .addComponent(yin))))
                                    .addComponent(jLabel8))))
                        .addGap(0, 77, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(xin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(yin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(bin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(moveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
    }//GEN-LAST:event_jLabel1MouseClicked
    /**
     * acts when you click on the board
     * pre:mouse event
     * post:select piece if nothing is selected, cancel selection if clicked on a invalid position
     *      move if clicked on a valid position
     */
    private void piecesPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_piecesPanelMouseClicked
        int x=evt.getX(),y=evt.getY();
        int LabelX,LabelY;
        boolean check=true;
        ImageIcon[] icon = new ImageIcon[8];
        if(moveNum==1){
            icon[0] = new ImageIcon("src/chess/g0.png");
            icon[1] = new ImageIcon("src/chess/g1.png");
            icon[2] = new ImageIcon("src/chess/g2.png");
            icon[3] = new ImageIcon("src/chess/g3.png");
            icon[4] = new ImageIcon("src/chess/g4.png");
            icon[5] = new ImageIcon("src/chess/g5.png");
            icon[6] = new ImageIcon("src/chess/g6.png");
        }else{
            icon[0] = new ImageIcon("src/chess/g0.png");
            icon[1] = new ImageIcon("src/chess/g7.png");
            icon[2] = new ImageIcon("src/chess/g8.png");
            icon[3] = new ImageIcon("src/chess/g9.png");
            icon[4] = new ImageIcon("src/chess/g10.png");
            icon[5] = new ImageIcon("src/chess/g11.png");
            icon[6] = new ImageIcon("src/chess/g12.png");
        }
        icon[7] = new ImageIcon("src/chess/bestChoice.png");
        boolean[][] valid=new boolean[8][8];
        LabelY = x/75;
        LabelX = 7-y/75;
        display();
        if(game.b.board[LabelX][LabelY][p[moveNum].playerNum]!=null){
            valid=game.b.analyzeBoard(p[moveNum].playerNum,LabelX,LabelY);
            pieceSelected=true;
            selectX=LabelX;
            selectY=LabelY;
            for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
            if(valid[i][j]){
                gridboard[i][j].setIcon(icon[0]);
                if(game.b.board[i][j][(moveNum-1)*(moveNum-1)]!=null){
                    switch(game.b.board[i][j][(moveNum-1)*(moveNum-1)]){
                        case ENPASSANT:
                        case NEWPAWN:
                        case PAWN:
                            gridboard[i][j].setIcon(icon[1]);
                            break;
                        case ROOK:
                            gridboard[i][j].setIcon(icon[2]);
                            break;
                        case KNIGHT:
                            gridboard[i][j].setIcon(icon[3]);
                            break;
                        case BISHOP:
                            gridboard[i][j].setIcon(icon[4]);
                            break;
                        case QUEEN:
                            gridboard[i][j].setIcon(icon[5]);
                            break;
                        case KING:
                            gridboard[i][j].setIcon(icon[6]);
                            break;
                    }
                }
                if(game.b.board[i][j][(moveNum-1)*(moveNum-1)]==null&&game.b.board[i][j][moveNum]==null){
                    gridboard[i][j].setIcon(icon[0]);
                }
            }
            
            }
            }
        }else if(pieceSelected){
            game.saveUndo();
            p[moveNum].move(selectX,selectY,LabelX,LabelY,game);
            if(game.b.board[LabelX][LabelY][moveNum]!=null){
                if(aiChoosed){
                    moveNum=(moveNum-1)*(moveNum-1);
                    if(winCheck()){
                        if(aiNum==1){
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate1.png"));
                        }else{
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate2.png"));
                        }
                    }
                    ((AI)p[aiNum]).calculateMove(3,game);
                    moveNum=(moveNum-1)*(moveNum-1);
                    if(winCheck()){
                        if(aiNum==1){
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate2.png"));
                        }else{
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate1.png"));
                        }
                    }else if(friendlyMode){
                        display();
                        int[]cal=((Human)p[moveNum]).calculateMoveForYou(3,game);
                        if(cal!=null){
                            if(moveNum==0){
                                icon[0] = new ImageIcon("src/chess/g0.png");
                                icon[1] = new ImageIcon("src/chess/g1.png");
                                icon[2] = new ImageIcon("src/chess/g2.png");
                                icon[3] = new ImageIcon("src/chess/g3.png");
                                icon[4] = new ImageIcon("src/chess/g4.png");
                                icon[5] = new ImageIcon("src/chess/g5.png");
                                icon[6] = new ImageIcon("src/chess/g6.png");
                            }else{
                                icon[0] = new ImageIcon("src/chess/g0.png");
                                icon[1] = new ImageIcon("src/chess/g7.png");
                                icon[2] = new ImageIcon("src/chess/g8.png");
                                icon[3] = new ImageIcon("src/chess/g9.png");
                                icon[4] = new ImageIcon("src/chess/g10.png");
                                icon[5] = new ImageIcon("src/chess/g11.png");
                                icon[6] = new ImageIcon("src/chess/g12.png");
                            }
                            switch(game.b.board[cal[0]][cal[1]][moveNum]){
                                case ENPASSANT:
                                case NEWPAWN:
                                case PAWN:
                                    gridboard[cal[0]][cal[1]].setIcon(icon[1]);
                                    break;
                                case ROOK:
                                    gridboard[cal[0]][cal[1]].setIcon(icon[2]);
                                    break;
                                case KNIGHT:
                                    gridboard[cal[0]][cal[1]].setIcon(icon[3]);
                                    break;
                                case BISHOP:
                                    gridboard[cal[0]][cal[1]].setIcon(icon[4]);
                                    break;
                                case QUEEN:
                                    gridboard[cal[0]][cal[1]].setIcon(icon[5]);
                                    break;
                                case KING:
                                    gridboard[cal[0]][cal[1]].setIcon(icon[6]);
                                    break;
                            }
                            gridboard[cal[2]][cal[3]].setIcon(icon[7]);
                        }
                    }
                }else{
                    moveNum=(moveNum-1)*(moveNum-1);
                    if(winCheck()&&moveNum==1){
                        jLabel6.setIcon(new ImageIcon("src/chess/checkmate1.png"));
                    }
                    if(winCheck()&&moveNum==0){
                        jLabel6.setIcon(new ImageIcon("src/chess/checkmate2.png"));
                    }
                }
            }
            for( int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(game.b.board[i][j][moveNum]!=Piece.KING){
                    valid=game.b.analyzeBoard(p[moveNum].playerNum,i,j);
                        for(int k=0;k<8;k++){
                            for(int l=0;l<8;l++){
                                if(valid[k][l]){
                                    check=false;
                                }
                            }
                        }
                    }
                }
            }
            if(check){
                jLabel7.setText("CHECK");
            }else{
            jLabel7.setText("");
        }
            pieceSelected=false;
            if(!friendlyMode){
            display();
            }
        }
        
        
    }//GEN-LAST:event_piecesPanelMouseClicked
    
    private void piecesPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_piecesPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_piecesPanel1MouseClicked
    /**
     * checks if the game ends
     * pre:
     * post:if king is dead or all pieces have no place to go, the display checkmate
     */
    public boolean winCheck(){
        boolean[][] valid=new boolean[8][8];
        boolean checkMate=true;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(game.b.board[i][j][moveNum]==Piece.KING){
                    checkMate=false;
                }
            }
        }
        if(checkMate){
            return true;
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                valid=game.b.analyzeBoard(p[moveNum].playerNum,i,j);
                for(int k=0;k<8;k++){
                    for(int l=0;l<8;l++){
                        if(valid[k][l]){
                            checkMate=false;
                        }
                    }
                }
            }}
        return checkMate;
    }
    /**
     * move piece when clicked
     * pre:
     * post:if the move is valid, move the piece
     */
    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed
        String yInput=yin.getText(),bInput=bin.getText();
        int x=Integer.parseInt(xin.getText())-1,a=Integer.parseInt(ain.getText())-1,y,b;
        y=(int)yInput.charAt(0)-65;
        b=(int)bInput.charAt(0)-65;
       boolean check=true;
       game.saveUndo();
            p[moveNum].move(x,y,a,b,game);
            if(game.b.board[a][b][moveNum]!=null){
                if(aiChoosed){
                    moveNum=(moveNum-1)*(moveNum-1);
                    if(winCheck()){
                        if(aiNum==1){
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate1.png"));
                        }else{
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate2.png"));
                        }
                    }
                    ((AI)p[aiNum]).calculateMove(3,game);
                    moveNum=(moveNum-1)*(moveNum-1);
                    if(winCheck()){
                        if(aiNum==1){
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate2.png"));
                        }else{
                            jLabel6.setIcon(new ImageIcon("src/chess/checkmate1.png"));
                        }
                    }
                }else{
                    moveNum=(moveNum-1)*(moveNum-1);
                    if(winCheck()&&moveNum==1){
                        jLabel6.setIcon(new ImageIcon("src/chess/checkmate1.png"));
                    }
                    if(winCheck()&&moveNum==0){
                        jLabel6.setIcon(new ImageIcon("src/chess/checkmate2.png"));
                    }
                }
            }
            for( int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(game.b.board[i][j][moveNum]!=Piece.KING){
                    boolean[][] valid=game.b.analyzeBoard(p[moveNum].playerNum,i,j);
                        for(int k=0;k<8;k++){
                            for(int l=0;l<8;l++){
                                if(valid[k][l]){
                                    check=false;
                                }
                            }
                        }
                    }
                }
            }
            if(check){
                jLabel7.setText("CHECK");
            }else{
            jLabel7.setText("");
        }
            pieceSelected=false;
            if(!friendlyMode){
            display();
            }
        
    }//GEN-LAST:event_moveButtonActionPerformed
    /**
     * go to previous state
     * pre:
     * post:cover the current board with the undoBoard
     */
    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        // TODO add your handling code here:
        if(!aiChoosed){
            moveNum=(moveNum-1)*(moveNum-1);
        }
        game.b=game.undoBoard.clone();
        display();
    }//GEN-LAST:event_undoButtonActionPerformed
    /**
     * exit when clicked
     * pre:
     * post:exit
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed
    /**
     * save current state to a file
     * pre:
     * post:save the board to dataaa.txt
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
			out = new FileWriter(dataFile);
			writeFile = new BufferedWriter(out);
			for (int i = 0; i < 8; i++) {
				for(int j=0;j<8;j++){
                                    for(int k=0;k<2;k++){
                                        if(game.b.board[i][j][k]!=null){
                                            switch(game.b.board[i][j][k]){
                                                case PAWN:
                                                    writeFile.write("1");
                                                    break;
                                                case ROOK:
                                                    writeFile.write("2");
                                                    break;
                                                case KNIGHT:
                                                    writeFile.write("3");
                                                    break;
                                                case BISHOP:
                                                    writeFile.write("4");
                                                    break;
                                                case QUEEN:
                                                    writeFile.write("5");
                                                    break;
                                                case KING:
                                                    writeFile.write("6");
                                                    break;
                                                case NEWPAWN:
                                                    writeFile.write("7");
                                                    break;
                                                case ENPASSANT:
                                                    writeFile.write("8");
                                                    break;
                                                default:
                                                    writeFile.write("0");
                                            }
                                        }else{
                                            writeFile.write("0");
                                        }
                                        writeFile.newLine();
                                    }
                                }
			}  
                        writeFile.write(String.valueOf(moveNum));
    		writeFile.close();
    		out.close();
    	} catch (Exception e) {
    	}
    }//GEN-LAST:event_saveButtonActionPerformed
    /**
     * load the saved state
     * pre:
     * post:redefine the board with dataaa.txt
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        String[] l=new String[129];
        int index=0;
        try {
			in = new FileReader(dataFile);
			readFile = new BufferedReader(in);
    		while ((l[index] = readFile.readLine()) != null ) {
                    index++;
    		}
    		readFile.close();
    		in.close();
            } catch (Exception e) {
                
            }
        index=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                for(int k=0;k<2;k++){
                    switch(Integer.parseInt(l[index])){
                        case 0:
                            game.b.board[i][j][k]=null;
                            break;
                        case 1:
                            game.b.board[i][j][k]=Piece.PAWN;
                            break;
                        case 2:
                            game.b.board[i][j][k]=Piece.ROOK;
                            break;
                        case 3:
                            game.b.board[i][j][k]=Piece.KNIGHT;
                            break;
                        case 4:
                            game.b.board[i][j][k]=Piece.BISHOP;
                            break;
                        case 5:
                            game.b.board[i][j][k]=Piece.QUEEN;
                            break;
                        case 6:
                            game.b.board[i][j][k]=Piece.KING;
                            break;
                        case 7:
                            game.b.board[i][j][k]=Piece.NEWPAWN;
                            break;
                        case 8:
                            game.b.board[i][j][k]=Piece.ENPASSANT;
                            break;
                        default:
                            break;
                    }
                    index++;
                }
            }
        }
        moveNum=Integer.parseInt(l[index]);
        display();
    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
  
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
    }
    
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ain;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JTextField bin;
    private javax.swing.JPanel chessPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton moveButton;
    private javax.swing.JPanel piecesPanel;
    private javax.swing.JPanel piecesPanel1;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton undoButton;
    private javax.swing.JTextField xin;
    private javax.swing.JTextField yin;
    // End of variables declaration//GEN-END:variables
    
    
    
    
}
