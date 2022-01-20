public class Cell{
 
  private int num;
  private boolean isMine;
  private boolean isCovered;
  private boolean isFlagged;
 
  public Cell(){
   
    isMine = false;
    isCovered = true;
    isFlagged = false;
    num = 10;
  }
 
  //Accessor Methods
 
  public boolean isFlagged(){
   
    return isFlagged;
   
  }//isFlagged
 
  public boolean isCovered(){
   
    return isCovered;
   
  }//isCovered
     
     
  public boolean isMine(){
   
    return isMine;
   
  }//isMine
 
  public int getNum(){
   
    return num;
   
  }//getNum
 
  //Modifier Methods
  
  public void reveal(){
    isCovered = true;
  }
 
   public void flag(){
   
    isFlagged = true;
   
  }//Flag
   
   public void unFlag(){
     
     isFlagged = false;
     
   }//unFlag
   
   public void setMine(){
     
     isMine = true;
     
   }//setMine
   

   
   public void setNum(int x){
     
     num = x;
     
   }//setNum
   
}
   