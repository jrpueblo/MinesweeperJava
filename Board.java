import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
  
  
  final int BOARD_SIZE = 64;
  final int BOARD_LENGTH = 8;
  final int BOARD_WIDTH = 8;
  JPanel p = new JPanel();
  Buttons[] buttons = new Buttons[64];
  
  public static void main(String [] args){
    new Board();
  }
  
  public Board(){
    
    super("MineSweeper");
    setSize(600,600);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    p.setLayout(new GridLayout(BOARD_LENGTH, BOARD_WIDTH));
    for(int i = 0; i < BOARD_SIZE;i++){
      buttons[i] = new Buttons();
      p.add(buttons[i]);
    } 
    add(p);
    setVisible(true);
  }
  
  
}