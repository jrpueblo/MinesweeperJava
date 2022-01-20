import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Buttons extends JButton implements ActionListener{
  
  ImageIcon bomb, flag;
  int value = 0;
  
  public Buttons(){
    bomb = new ImageIcon(this.getClass().getResource("mine.png"));
    flag = new ImageIcon(this.getClass().getResource("flag.png"));
    this.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e){
    value++;
    value = value % 3;
    switch(value){
      case 0:
        setIcon(null);
        break;
      case 1: 
        setIcon(bomb);
        break;
      case 2: 
        setIcon(flag);
        break;
        
    }
  }
}

