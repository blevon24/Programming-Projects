/* 
 * Author: Brianna Levon
 * 
 * Purpose:
 */
import java.util.*;

public class Hero extends Actor{
  int newRow;
  int newCol;
    
    public Hero(String name, int health, int maxDamage, int row, int col){
      super(name, 100, 10, 0, 0);
    }
  
    public void move(String direction, int size){
      while(true){
        if(direction.equalsIgnoreCase("north")){
          if(getRow() != 0){
            newRow = getRow() - 1;
            setRow(newRow);
            break;
          }
          else{
            System.out.println("You can't move that way!");
          }
        }
        else if(direction.equalsIgnoreCase("south")){
          if(getRow() != (size - 1)){
            newRow = getRow() + 1;
            setRow(newRow);
            break;
          }
          else{
            System.out.println("You can't move that way!");
          }
        }
        else if(direction.equalsIgnoreCase("east")){
          if(getCol() != (size - 1)){
            newCol = getCol() + 1;
            setCol(newCol);
            break;
          }
          else{
            System.out.println("You can't move that way!");
          }
        }
        else if(direction.equalsIgnoreCase("west")){
          if(getCol() != 0){
            newCol = getCol() - 1;
            setCol(newCol);
            break;
          }
          else{
            System.out.println("You can't move that way!");
          }
        }
        else{
          System.out.println("You can't move that way!");
        }
      }
    }
    
    @Override
    public void damageTaken(int damage){
      super.damageTaken(damage);
      System.out.println("You get hit for " + damage);
    }
    
    @Override
    public void attack(Actor actor){
      Random random = new Random();
      int damage = random.nextInt(getMaxDamage() + 1);
      actor.damageTaken(damage);
      System.out.println("You hit for " + damage);
    }
    
    public void smell(ArrayList<Monster> monsters){
      int count = 0;
      
      for(Monster monster: monsters){
        
        if((Math.abs(getRow() - monster.getRow()) == 1 && getCol() == monster.getCol()) || 
           (Math.abs(getCol() - monster.getCol()) == 1 && getRow() == monster.getRow())) {
            count++;
        }
      }
      
      System.out.println("You smell " + count + " monsters nearby.");
    }

}