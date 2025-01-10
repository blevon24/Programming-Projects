/*
 * Author: Brianna Levon
 * 
 * Purpose:
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
      int size = 0;
      String username;
      String direction;
      Scanner scnr = new Scanner(System.in);
      Hero hero = new Hero("null", 100, 10, 0, 0);
      
      // Prompts the user to enter Hero's name/username and assigns the user input
      // to a String variable called username1
      System.out.print("What is your name, heroic adventurer? ");
      username = scnr.nextLine();
      hero.setName(username);
      
      // Creates a loop that ends when the boolean variable loop = false
      while(true) {
          System.out.print("How wide of a catacomb do you want to face (5-10)? ");
          size = scnr.nextInt();
          scnr.nextLine();
          
          if(size >= 5 && size <= 10){
            break;
          }
          else{
              System.out.print("That is not a valid catacomb size!");
          }
      }
      Catacomb catacomb = new Catacomb(size);
      
      catacomb.setMap(size);
      
      while(true){
        System.out.println(hero);
        
        hero.smell(catacomb.getMonsters());
        
        System.out.print("View catacomb map (yes/no)? ");
        String userChoice = scnr.nextLine(); 
        catacomb.printMap(userChoice);
        
        
        System.out.print("Which way do you want to go (north, south, east, west)? ");
        direction = scnr.nextLine();
        catacomb.moveHero(hero, direction, size);
        
        for(Monster monster: catacomb.getMonsters()){
          System.out.println("Hero: " + hero.getRow() + ", " + hero.getCol());
          System.out.println("Monster: " + monster.getRow() + ", " + monster.getCol());
        }
        
        fight(hero, catacomb.getMonsters(), catacomb);
        
        
        if(catacomb.hasEscaped(hero, size)){
          System.out.println("You have escaped the catacomb!");
          break;
        }
      }
      System.out.println("Congrats, you won the game!");
    }
    
    public static void fight(Hero hero, ArrayList<Monster> monsters, Catacomb catacomb){
      for(Monster monster: monsters){
        
        if(hero.getRow() == monster.getRow() && hero.getCol() == monster.getCol()){
          System.out.println(hero + " versus " + monster);
          
          while(true){
            hero.attack(monster);
            System.out.println(monster.getHealth());
            
            if(!monster.isAlive()){
              catacomb.removeActor(monster);
              System.out.println(monster.getName() + " has been defeated!");
              break;
            }
            
            monster.attack(hero);
            System.out.println(hero.getHealth());
            
            if(!hero.isAlive()){
              catacomb.removeActor(hero);
              System.out.println("You have died! \n Game Over.");
              break;
            }
          }
          break;
        }
      }
    }
}
