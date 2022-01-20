import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SweeperBoard extends JFrame implements MineSweeperConstants{
  
  private JFrame frame;
  private boolean[][] hasChecked = new boolean[BOARD_LENGTH + 1][BOARD_WIDTH + 1];
  public JLabel mineTitle, mineCount, gameTime, timeTitle, displayArea;
  private JPanel field;
  private JPanel menu;
  private boolean complete = false;
  private int count, minesLeft, flagsLeft, extraFlags;
  public String name;
  public JLabel[][] grid;
  public Cell[][] cells;
  public boolean gameEnd = false;
  
  
  
  public SweeperBoard(){
    
    IntializeGUI();
    
    
  }//SweeperBoard Constructor
  
  //Intializes Graphics for the game
  public void IntializeGUI(){
    
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter your name");
    name = sc.nextLine();
    
    //Intialize the Frame
    
    frame = new JFrame("MineSweeper");
    frame.setBounds(0, 0, 630, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setLayout(null);
    
    
    
    //Intialize game Panel
    field = new JPanel();
    field.setBounds(10, 10, 600, 600);
    field.setLayout(new GridLayout(BOARD_LENGTH, BOARD_WIDTH, 0, 0));
    frame.getContentPane().add(field);
    
    cells = new Cell[BOARD_LENGTH][BOARD_WIDTH];
    grid = new JLabel[BOARD_LENGTH][BOARD_WIDTH];
    
    //Intialize Grid of Jlabel and add to panel
    for(int row = 0; row < BOARD_LENGTH;row++){
      for(int col = 0; col < BOARD_WIDTH;col++){
        grid[row][col] = new JLabel();
        grid[row][col].setBorder(BorderFactory.createLineBorder(Color.black, 2));
        grid[row][col].setHorizontalAlignment(SwingConstants.CENTER);
        grid[row][col].setText(" ");
        field.add(grid[row][col]);
      }
    }
    
    //Intialize title for Mine label
    mineTitle = new JLabel("Mines");
    mineTitle.setBounds(10, 620, 40, 20);
    mineTitle.setHorizontalAlignment(SwingConstants.CENTER);
    mineTitle.setVerticalAlignment(SwingConstants.BOTTOM);
    mineTitle.setFont(new Font("Calibri", Font.BOLD, 10));
    frame.getContentPane().add(mineTitle);
    
    //Intialize Jlabel to display Mines
    mineCount = new JLabel(Integer.toString(MINE_NUM));
    mineCount.setBounds(10, 640, 40, 20);
    mineCount.setHorizontalAlignment(SwingConstants.CENTER);
    mineCount.setFont(new Font("Calibri", Font.BOLD, 20));
    frame.getContentPane().add(mineCount);
    
    //Intialize text area
    displayArea = new JLabel("Welcome " + name + ", to MineSweeper");
    displayArea.setBounds(60, 620, 390, 40);
    displayArea.setHorizontalAlignment(SwingConstants.CENTER);
    displayArea.setFont(new Font("Calibri", Font.PLAIN, 20));
    frame.getContentPane().add(displayArea);
    
    
    
    
    
    //Intialize Arrays
    for(int row = 0; row < BOARD_LENGTH;row++){
      for(int col = 0; col < BOARD_WIDTH;col++){
        
        cells[row][col] = new Cell();
        hasChecked[row][col] = false;
        
      }
    }
    
    setMines(MINE_NUM);
    setNums();
    
    
    
    
  }//IntializeGUI
  
  //Returns Graphics Frame
  public JFrame getFrame(){
    
    return frame;
    
  }//getFrame
  
  //Randomly Sets Mines
  public void setMines(int count){
    
    
    
    int tempRow, tempCol;
    
    for(int i = 0; i < count;i++){
      tempRow = (int)((BOARD_LENGTH - 1) * Math.random());
      tempCol = (int)((BOARD_WIDTH - 1) * Math.random());
      
      if(cells[tempRow][tempCol].isMine() != true)
        cells[tempRow][tempCol].setMine();
      else
        i--;
      
    }
    
    
    
  }//setMines
  
  //Numbers in a cell corresponds to number of mines in it's proximity
  public void setNums(){
    
    for(int x = 0; x < BOARD_LENGTH;x++){
      for(int y = 0; y < BOARD_WIDTH;y++){
        
        count = 0;
        
        if (x > 0 &&  y > 0 && cells[x - 1][y - 1].isMine())
          count++;
        
        if (y > 0 && cells[x][y - 1].isMine())
          count++;
        
        if (x < BOARD_WIDTH - 1 && y > 0 && cells[x + 1][y - 1].isMine())
          count++;
        
        if (x > 0 && cells[x - 1][y].isMine())
          count++;
        
        if (x < BOARD_WIDTH - 1 && cells[x + 1][y].isMine())
          count++;
        
        if (x > 0 && y < BOARD_LENGTH - 1 && cells[x - 1][y + 1].isMine())
          count++;
        
        if (y < BOARD_LENGTH - 1 && cells[x] [y + 1].isMine())
          count++;
        
        if (x < BOARD_WIDTH - 1 && y < BOARD_LENGTH - 1 && cells[x + 1][y + 1].isMine())
          count++;
        
        
        cells[x][y].setNum(count);
      }
    }
    
    
    
    
    
    
    
    
  }//setNums
  
  //return row clicked
  public int getRow(JLabel label){
    
    int returnRow = -1;
    
    for(int row = 0; row < BOARD_LENGTH; row++){
      for(int column = 0; column < BOARD_WIDTH; column++){
        if(grid[row][column] == label)
          returnRow = row;
      }//column
    }//row
    
    return returnRow;
    
  }//getRow()
  
  //returns column clicked
  
  public int getCol(JLabel label){
    
    int returnCol = -1;
    
    for(int row = 0; row < BOARD_LENGTH; row++){
      for(int col = 0; col < BOARD_WIDTH; col++){
        if(grid[row][col] == label)
          returnCol = col;
      }//column
    }//row
    
    return returnCol;
    
  }//getRow()
  
  //adds MouseListener to each JLabel on the grid
  public void addListener(MouseListener listener){
    
    for(int row = 0; row < BOARD_LENGTH;row++){
      for(int col = 0; col < BOARD_WIDTH;col++){
        
        grid[row][col].addMouseListener(listener);
        
      }
    }
  }
  
  //gets board position on the grid at row and col passed
  public String getPosition(int row, int col){
    
    String position = " ";
    
    if((row > 0 && row < BOARD_LENGTH - 1) && (col > 0 && col < BOARD_WIDTH - 1))
      position = "open"; //no walls surrounding
    
    else if(row == 0 && (col > 0 && col < BOARD_WIDTH - 1))
      position = "Top";
    
    else if(row == BOARD_LENGTH - 1 && (col > 0 && col < BOARD_LENGTH - 1))
      position = "Bottom";
    
    else if((row > 0 && row < BOARD_LENGTH - 1) && col == BOARD_WIDTH - 1)
      position = "Right";
    
    else if((row > 0 && row < BOARD_LENGTH - 1) && col == 0)
      position = "Left";
    
    else if(row == 0 && col == 0)
      position = "TopLeft";
    
    else if(row == 0 && col == BOARD_WIDTH - 1)
      position = "TopRight";
    
    else if(row == BOARD_LENGTH - 1 && col == 0)
      position = "BottomLeft";
    
    else if(row == BOARD_LENGTH - 1 && col == BOARD_WIDTH - 1)
      position = "BottomRight";
    
    return position;
    
  }//findPositionSituation
  
  //returns proximity of mines around cell at row and col
  public int getProx(String position, int row, int col){
    
    int prox = 0;
    
    if(position.equals("Open")){
      if(cells[row - 1][col - 1].isMine())
        prox++;
      
      if(cells[row - 1][col].isMine())
        prox++;
      
      if(cells[row - 1][col + 1].isMine())
        prox++;
      
      if(cells[row][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col].isMine())
        prox++;
      
      if(cells[row + 1][col - 1].isMine())
        prox++;
      
      if(cells[row][col - 1].isMine())
        prox++;
      
    }//free
    
    else if(position.equals("Top")){
      if(cells[row][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col].isMine())
        prox++;
      
      if(cells[row + 1][col - 1].isMine())
        prox++;
      
      if(cells[row][col - 1].isMine())
        prox++;
      
    }//sideTop
    
    else if(position.equals("Bottom")){
      if(cells[row][col - 1].isMine())
        prox++;
      
      if(cells[row - 1][col - 1].isMine())
        prox++;
      
      if(cells[row - 1][col].isMine())
        prox++;
      
      if(cells[row - 1][col + 1].isMine())
        prox++;
      
      if(cells[row][col + 1].isMine())
        prox++;
      
    }//sideBottom
    
    else if(position.equals("Left")){
      if(cells[row - 1][col].isMine())
        prox++;
      
      if(cells[row - 1][col + 1].isMine())
        prox++;
      
      if(cells[row][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col].isMine())
        prox++;
      
    }//sideLeft
    
    else if(position.equals("Right")){
      if(cells[row - 1][col].isMine())
        prox++;
      
      if(cells[row + 1][col].isMine())
        prox++;
      
      if(cells[row + 1][col - 1].isMine())
        prox++;
      
      if(cells[row][col - 1].isMine())
        prox++;
      
      if(cells[row - 1][col - 1].isMine())
        prox++;
      
    }//sideRight
    
    else if(position.equals("TopLeft")){
      if(cells[row][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col + 1].isMine())
        prox++;
      
      if(cells[row + 1][col].isMine())
        prox++;
      
    }//cornerTopLeft
    
    else if(position.equals("TopRight")){
      if(cells[row + 1][col].isMine())
        prox++;
      
      if(cells[row + 1][col - 1].isMine())
        prox++;
      
      if(cells[row][col - 1].isMine())
        prox++;
      
    }//cornerTopRight
    
    else if(position.equals("BottomLeft")){
      if(cells[row - 1][col].isMine())
        prox++;
      
      if(cells[row - 1][col + 1].isMine())
        prox++;
      
      if(cells[row][col + 1].isMine())
        prox++;
      
    }//cornerBottomLeft
    
    else if(position.equals("BottomRight")){
      if(cells[row - 1][col].isMine())
        prox++;
      
      if(cells[row][col - 1].isMine())
        prox++;
      
      if(cells[row - 1][col - 1].isMine())
        prox++;
      
    }//cornerBottomRight
    
    return prox;
    
    
  }//getProx
  
  //reveals a single cell
  public void revealSpot(int row, int col){
    
    cells[row][col].reveal();
    
  }
  
  //recursive method:
  //if 0 is clicked, reveals surrounding 0's until cell containing a number is hit
  //if an Integer > 0 is clicked, reveals that spot only
  public void revealProx(String position, int row, int col){
    
    
    if(row < 0 || col < 0 || row >= BOARD_LENGTH || col >= BOARD_WIDTH){
      
      return;
      
    }
    
    else if(hasChecked[row][col] == true){
      
      return;
      
    }
    else if(cells[row][col].getNum() >= 1){
      cells[row][col].reveal();
      if(cells[row][col].getNum() == 1){
        grid[row][col].setForeground(Color.BLUE);
      }
      else if(cells[row][col].getNum() == 2){
        grid[row][col].setForeground(Color.GREEN.darker());
      }
      else if(cells[row][col].getNum() == 3){
        grid[row][col].setForeground(Color.RED);
      }
      else if(cells[row][col].getNum() == 4){
        grid[row][col].setForeground(Color.BLUE.darker());
      }
      else if(cells[row][col].getNum() == 5){
        grid[row][col].setForeground(Color.RED.darker());
      }
      else if(cells[row][col].getNum() == 6){
        grid[row][col].setForeground(Color.CYAN);
      }
      else if(cells[row][col].getNum() == 7){
        grid[row][col].setForeground(Color.BLACK.darker());
      }
      else if(cells[row][col].getNum() == 8){
        grid[row][col].setForeground(Color.CYAN.darker());
      }
      grid[row][col].setText(Integer.toString(cells[row][col].getNum()));
      hasChecked[row][col] = true;
      
      return;
    }
    else if(cells[row][col].getNum() == 0){
      grid[row][col].setForeground(Color.LIGHT_GRAY);
      grid[row][col].setText("0");
      hasChecked[row][col] = true;
      revealProx(getPosition(row - 1, col - 1), (row - 1),(col - 1));   //top left
      revealProx(getPosition(row - 1, col),(row - 1),(col));            //top
      revealProx(getPosition(row - 1, col + 1),(row - 1),(col + 1));    //top right
      revealProx(getPosition(row + 1, col + 1),(row + 1),(col + 1));    //bottom right
      revealProx(getPosition(row + 1, col),(row + 1),(col));            //bottom
      revealProx(getPosition(row + 1, col - 1),(row + 1),(col - 1));    //bottom left
      revealProx(getPosition(row, col - 1),(row),(col - 1));            //left
      revealProx(getPosition(row, col + 1),(row),(col + 1));            //right
      
      
      
    }
    
    
  }//revealProx
  
  
  //Checks for wins (if all mines were flagged)
  public boolean checkWin(int flags){
    
    int flagged = 0;
    int extraFlags = 0;
    boolean gameWon = false;
    if(flags > 0){
      return gameWon;
    }
    else{
      for(int row = 0;row < BOARD_LENGTH;row++){
        for(int col = 0;col < BOARD_WIDTH;col++){
          if(cells[row][col].isMine() && cells[row][col].isFlagged()){
            flagged++;
          }
          else{
            extraFlags++;
          }
          
        }
      }
      
      if(flagged == MINE_NUM || extraFlags == 0){
        gameWon = true;
      }else{
        gameWon = false;
      }
      
      
      
    }
    return gameWon;
    
  }//checkWin
  

  
  
  //reveals entire board, called when game is over
  public void revealBoard(){
    
    for(int row = 0; row < BOARD_LENGTH; row++){
      for(int col = 0; col < BOARD_WIDTH; col++){
        
        if(cells[row][col].isMine() == true){
          grid[row][col].setForeground(Color.RED);
          grid[row][col].setText("M");
        }
        
        
      }
    }
  }//revealBoard
  
  //sets Mine display to display mines left (assuming all flags were correct)
  public void setMineDisplay(){
    
    int flagNum = 0;
    int mineDisplayNum;
    
    for(int row = 0; row < BOARD_LENGTH; row++){
      for(int col = 0; col < BOARD_WIDTH; col++){
        if(cells[row][col].isFlagged())
          flagNum++;
      }//column
    }//row
    
    mineDisplayNum = MINE_NUM - flagNum;
    mineCount.setText(Integer.toString(mineDisplayNum));
    
  }//setMineDisplay 
  

  
}//SweeperBoard Class





