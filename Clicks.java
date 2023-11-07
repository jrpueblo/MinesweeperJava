import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Clicks implements MouseListener, MineSweeperConstants{
  
  private SweeperBoard board;
  private MineSweeper game;
  private JLabel label;
  private JLabel[][] grid;
  private int row, col;
  private int prox;
  private int flagsLeft = FLAGS;
  private String position;
  private boolean gameEnd = false;
  private boolean gameWon = false;
  
  public Clicks(SweeperBoard board){
    
    
    this.board = board;
    board.addListener(this);
    
  }//Actions Constructor
  
  
  
  public void mouseClicked(MouseEvent event){
    
    if(gameEnd != true){
      JLabel label = (JLabel) event.getComponent();
      row = board.getRow(label);
      col = board.getCol(label);   
      position = board.getPosition(row,col);
      prox = board.getProx(position, row, col);

      
      if(SwingUtilities.isLeftMouseButton(event)){
        
        if(board.cells[row][col].isMine() == true && !board.cells[row][col].isCovered()){
          
          board.displayArea.setText("GAME OVER! - You lose");
          board.revealBoard();
          board.gameEnd = true;
        }
        else if(prox == 0){
          
          board.revealProx(position,row,col);
          
        }//no mines in prox
        
        else if(prox > 0){
          
          board.grid[row][col].setForeground(Color.BLUE);
          board.grid[row][col].setText(Integer.toString(prox));
          
        }//SweepingAreas
        
      }//Reveals
      
      // Flag functionality
      else if(SwingUtilities.isRightMouseButton(event)){
        
        System.out.println(flagsLeft);

        
    
        if(flagsLeft > 0){
          
          if(board.cells[row][col].isCovered()){
            
            if(board.cells[row][col].isFlagged()){
              board.cells[row][col].unFlag();
              board.grid[row][col].setText(" ");
              flagsLeft++;
              
              
            }else{
              board.cells[row][col].flag();
              board.grid[row][col].setText("F");
              board.grid[row][col].setForeground(Color.RED.brighter());
              flagsLeft--;
              
            }
            
          }else{
            
            board.displayArea.setText("You cannot flag a discovered space");
            
          }
          
        }else{
          
          board.displayArea.setText("You are out of flags!");
          
        }
        
        if(flagsLeft == 0)
          gameEnd = true;
        
        if(board.checkWin(flagsLeft) == true){
          board.displayArea.setText("Congrats " + board.name + "! You have won!");
        }
        
        
        }//Flags
        
      }else{
        
        
      }
       board.setMineDisplay();
    }
 
    
  
  
  public void mousePressed(MouseEvent event){
    
  }
  public void mouseReleased(MouseEvent event){
    
  }
  public void mouseEntered(MouseEvent event){
    
  }
  public void mouseExited(MouseEvent event){
    
  }
  
  
}

