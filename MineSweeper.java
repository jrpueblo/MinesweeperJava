/*
 * @author Jan Rylan Pueblo
 * Version 4, January 20th 2020
 */
 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MineSweeper implements Runnable{
  public static void main(String [] args){
    
    MineSweeper game = new MineSweeper();
    game.run();
    
  }//main
  
  public void run(){
    
    SweeperBoard board = new SweeperBoard();
    board.getFrame().setVisible(true);
    Clicks listener = new Clicks(board);
    
    
  }
}//MineSweeper
